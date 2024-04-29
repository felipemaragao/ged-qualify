package br.com.qualify.ged.config;


import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Slf4j
@Configuration
@Generated
public class S3Config {


    private QualifyApiProperty property;

    public S3Config(QualifyApiProperty property){
        this.property = property;
    }

    @Bean
    public S3Presigner s3Presigner() throws URISyntaxException {

        S3Presigner s3Presigner;

        if (Objects.nonNull(property.getAws().getEnv()) && property.getAws().getEnv().equals("localstack")) {
            log.info("Acessando S3Presigner via localstack");

            s3Presigner = S3Presigner.builder()
                    .endpointOverride(new URI("http://localhost:4566"))
                    .build();

        } else {
            log.info("Acessando S3Presigner na AWS");
            s3Presigner = S3Presigner.builder().region(Region.US_EAST_1)
                    .build();
        }

        return s3Presigner;

    }


    @Bean
    public S3Client s3Client() throws URISyntaxException {

        S3Client s3Client;

        if (Objects.nonNull(property.getAws().getEnv()) && property.getAws().getEnv().equals("localstack")) {
            log.info("Acessando bucket via localstack");

            s3Client = S3Client.builder()
                    .credentialsProvider(ProfileCredentialsProvider.create())
                    .endpointOverride(new URI("http://localhost:4566"))
                    .region(Region.US_EAST_1)
                    .forcePathStyle(true)
                    .build();

                boolean existBucket = s3Client.listBuckets().buckets().stream().anyMatch(nameBucket-> nameBucket
                    .name().equals(property.getS3().getBucketName()));

            if (!existBucket) {
                log.info("Criando bucket");
                s3Client.createBucket(CreateBucketRequest.builder()
                                .bucket(property.getS3().getBucketName())
                        .build());
            } else {
                log.info("Já existe bucket criado");
            }

        } else {
            log.info("Acessando bucket na AWS");
            s3Client = S3Client.builder().region(Region.US_EAST_1)
                    .build();
            }
        return s3Client;
    }

}
