//package br.com.qualify.ged;
//
//import br.com.qualify.ged.domain.Documento;
//import org.junit.Rule;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.testcontainers.containers.localstack.LocalStackContainer;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.utility.DockerImageName;
//
//import java.io.IOException;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.testcontainers.containers.localstack.LocalStackContainer.Service.*;
//
//@SpringBootTest
//@Testcontainers
//public class S3ServiceTest {
//    DockerImageName localstackImage = DockerImageName.parse("localstack/localstack:0.11.3");
//
//    @Rule
//    public static LocalStackContainer localstack = new LocalStackContainer(localstackImage)
//            .withServices(S3, SQS, LAMBDA, SECRETSMANAGER);
//
//
//    private static String initializeLocalStack() throws IOException, InterruptedException {
//
//        localstack.execInContainer("aws --endpoint-url=http://localhost:4566 secretsmanager create-secret --name aws/secret --secret-string '{\"my_uname\":\"username\",\"my_pwd\":\"password\"}'");
//        localstack.execInContainer("aws --endpoint-url=http://localhost:4566  s3api create-bucket --bucket bucketname --region eu-west-1 --create-bucket-configuration LocationConstraint=eu-west-3");
//        final Integer mappedPort = localstack.getMappedPort(4566);
//        return "cloud.aws.secrets-manager.end-point.uri=http://localhost:" + mappedPort;
//    }
//
//    @Test
//    void shouldGetCustomers() throws IOException, InterruptedException {
//        localstack.execInContainer("aws --endpoint-url=http://localhost:4566 secretsmanager create-secret --name aws/secret --secret-string '{\"my_uname\":\"username\",\"my_pwd\":\"password\"}'");
//        localstack.execInContainer("aws --endpoint-url=http://localhost:4566  s3api create-bucket --bucket bucketname --region eu-west-1 --create-bucket-configuration LocationConstraint=eu-west-3");
//
//        assertEquals("Documento", documentos.get(0).getNome());
//        assertEquals(1, documentoRepository.count());
//    }
//}
