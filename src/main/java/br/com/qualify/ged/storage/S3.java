package br.com.qualify.ged.storage;

import br.com.qualify.ged.config.QualifyApiProperty;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.*;
import software.amazon.awssdk.transfer.s3.progress.LoggingTransferListener;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;

import static org.yaml.snakeyaml.tokens.Token.ID.Tag;

@Component
@AllArgsConstructor
public class S3 {

    private static final Logger logger = LoggerFactory.getLogger(S3.class);

    private QualifyApiProperty property;

    private S3Client amazonS3;

    private S3Presigner presigner;


    public String uploadFile(S3TransferManager transferManager, String key, String filePath) {



        UploadFileRequest uploadFileRequest =
                UploadFileRequest.builder()
                        .putObjectRequest(b -> b.bucket(property.getS3().getBucketName()).key(key))
                        .addTransferListener(LoggingTransferListener.create())
                        .source(Paths.get(filePath))
                        .build();

        FileUpload fileUpload = transferManager.uploadFile(uploadFileRequest);

        CompletedFileUpload uploadResult = fileUpload.completionFuture().join();
        return uploadResult.response().eTag();
    }

    public String uploadFileNew(String fileName, MultipartFile multipartFile)
            throws S3Exception, AwsServiceException, SdkClientException, IOException {


        String checksum = DigestUtils.md5DigestAsHex(multipartFile.getBytes());


        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(property.getS3().getBucketName())
                .key(checksum)
                .build();


        amazonS3.putObject(putObjectRequest, RequestBody.fromBytes(multipartFile.getBytes()));
        return checksum;

    }

    public Long downloadFile(S3TransferManager transferManager, String key, String downloadedFileWithPath) {
        DownloadFileRequest downloadFileRequest =
                DownloadFileRequest.builder()
                        .getObjectRequest(b -> b.bucket(property.getS3().getBucketName()).key(key))
                        .addTransferListener(LoggingTransferListener.create())
                        .destination(Paths.get(downloadedFileWithPath))
                        .build();

        FileDownload downloadFile = transferManager.downloadFile(downloadFileRequest);

        CompletedFileDownload downloadResult = downloadFile.completionFuture().join();
        logger.info("Content length [{}]", downloadResult.response().contentLength());
        return downloadResult.response().contentLength();
    }
    public void setLifecycleConfig(S3Client s3, String accountId) {

        try {
        // Create a rule to archive objects with the "glacierobjects/" prefix to Amazon S3 Glacier.
        LifecycleRuleFilter ruleFilter = LifecycleRuleFilter.builder()
                .prefix("glacierobjects/")
                .build();

        Transition transition = Transition.builder()
                .storageClass(TransitionStorageClass.GLACIER)
                .days(0)
                .build();

        LifecycleRule rule1 = LifecycleRule.builder()
                .id("Archive immediately rule")
                .filter(ruleFilter)
                .transitions(transition)
                .status(ExpirationStatus.ENABLED)
                .build();

        // Create a second rule.
        Transition transition2 = Transition.builder()
                .storageClass(TransitionStorageClass.GLACIER)
                .days(0)
                .build();

        List<Transition> transitionList = new ArrayList<>();
        transitionList.add(transition2);

        LifecycleRuleFilter ruleFilter2 = LifecycleRuleFilter.builder()
                .prefix("glacierobjects/")
                .build();

        LifecycleRule rule2 = LifecycleRule.builder()
                .id("Archive and then delete rule")
                .filter(ruleFilter2)
                .transitions(transitionList)
                .status(ExpirationStatus.ENABLED)
                .build();

        // Add the LifecycleRule objects to an ArrayList.
        ArrayList<LifecycleRule> ruleList = new ArrayList<>();
        ruleList.add(rule1);
        ruleList.add(rule2);

        BucketLifecycleConfiguration lifecycleConfiguration = BucketLifecycleConfiguration.builder()
                .rules(ruleList)
                .build();

        PutBucketLifecycleConfigurationRequest putBucketLifecycleConfigurationRequest = PutBucketLifecycleConfigurationRequest.builder()
                .bucket(property.getS3().getBucketName())
                .lifecycleConfiguration(lifecycleConfiguration)
                .expectedBucketOwner(accountId)
                .build();

        s3.putBucketLifecycleConfiguration(putBucketLifecycleConfigurationRequest);

    } catch (S3Exception e) {
        System.err.println(e.awsErrorDetails().errorMessage());
        System.exit(1);
    }
}

    public String createPresignedGetUrl(String keyName, int timeDuration) {


            GetObjectRequest objectRequest = GetObjectRequest.builder()
                    .bucket(property.getS3().getBucketName())
                    .key(keyName)
                    .build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(timeDuration))  // The URL will expire in minutes.
                    .getObjectRequest(objectRequest)
                    .build();

            PresignedGetObjectRequest presignedRequest = presigner.presignGetObject(presignRequest);
            logger.info("Presigned URL: [{}]", presignedRequest.url().toString());
            logger.info("HTTP method: [{}]", presignedRequest.httpRequest().method());

            return presignedRequest.url().toExternalForm();

    }

    public String salvarTemporariamente(String objeto) {

        // setar ACL

        try {
            Grant ownerGrant = Grant.builder()
                    .grantee(builder -> builder.id(objeto)
                            .type(Type.CANONICAL_USER))
                    .permission(Permission.FULL_CONTROL)
                    .build();

            Grant usersGrant = Grant.builder()
                    .grantee(builder -> builder.id(objeto)
                            .type(Type.CANONICAL_USER))
                    .permission(Permission.READ)
                    .build();

            List<Grant> grantList2 = new ArrayList<>();
            grantList2.add(ownerGrant);
            grantList2.add(usersGrant);

            AccessControlPolicy acl = AccessControlPolicy.builder()
                    .owner(builder -> builder.id(objeto))
                    .grants(grantList2)
                    .build();


            PutBucketAclRequest putAclReq = PutBucketAclRequest.builder()
                    .bucket(property.getS3().getBucketName())
                    .accessControlPolicy(acl)
                    .build();

            PutBucketTaggingRequest tagRequest = PutBucketTaggingRequest.builder()
                    .bucket(property.getS3().getBucketName())
                    .tagging(Tagging.builder()
                            .tagSet(software.amazon.awssdk.services.s3.model.Tag.builder()
                                    .key("Expirar").value("true")
                                    .build())
                            .build())
                    .build();

            amazonS3.putBucketAcl(putAclReq);
            amazonS3.putBucketTagging(tagRequest);
            return salvar(objeto);

        } catch (S3Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return "";
    }

    public String configurarUrl(String objeto) {
        return "\\\\" + property.getS3().getBucketName() +
                ".s3.amazonaws.com/" + objeto;
    }

    public String salvar(String objeto) {

        CopyObjectRequest copyReq = CopyObjectRequest.builder()
                .sourceBucket(property.getS3().getBucketName())
                .sourceKey(objeto)
                .destinationBucket(property.getS3().getBucketName())
                .destinationKey(gerarNomeUnico(objeto))
                .build();

        try {
            CopyObjectResponse copyRes = amazonS3.copyObject(copyReq);
            return copyRes.copyObjectResult().toString();

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return "";
    }

    public void remover(String objeto) {

        try {

        Delete del = Delete.builder()
                .objects(ObjectIdentifier.builder().key(objeto).build())
                .build();

        DeleteObjectsRequest deleteObjectRequest = DeleteObjectsRequest.builder()
                .bucket(property.getS3().getBucketName())
                .delete(del)
                .build();

        amazonS3.deleteObjects(deleteObjectRequest);

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    public void substituir(String objetoAntigo, String objetoNovo) {
        if (StringUtils.hasText(objetoAntigo)) {
            this.remover(objetoAntigo);
        }

        salvar(objetoNovo);
    }

    private String gerarNomeUnico(String originalFilename) {
        return UUID.randomUUID().toString() + "_" + originalFilename;
    }

    static long getInputLength(InputStream inputStream) {
        try {
            if (inputStream instanceof FilterInputStream) {
                FilterInputStream filtered = (FilterInputStream)inputStream;
                Field field = FilterInputStream.class.getDeclaredField("in");
                field.setAccessible(true);
                InputStream internal = (InputStream) field.get(filtered);
                return getInputLength(internal);
            } else if (inputStream instanceof ByteArrayInputStream) {
                ByteArrayInputStream wrapper = (ByteArrayInputStream)inputStream;
                Field field = ByteArrayInputStream.class.getDeclaredField("buf");
                field.setAccessible(true);
                byte[] buffer = (byte[])field.get(wrapper);
                return buffer.length;
            } else if (inputStream instanceof FileInputStream) {
                FileInputStream fileStream = (FileInputStream)inputStream;
                return fileStream.getChannel().size();
            }
        } catch (NoSuchFieldException | IllegalAccessException | IOException exception) {
            // Ignore all errors and just return -1.
        }
        return -1;
    }
}
