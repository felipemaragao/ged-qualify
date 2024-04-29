package br.com.qualify.ged.config;



import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.CreateSecretRequest;
import software.amazon.awssdk.services.secretsmanager.model.CreateSecretResponse;
import software.amazon.awssdk.services.secretsmanager.model.ListSecretsRequest;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Slf4j
@Configuration
@Generated
public class SecretsManagerConfig {

    private QualifyApiProperty property;

    public SecretsManagerConfig(QualifyApiProperty property) {
        this.property = property;
    }

    @Value("${app.aws.sm.name}")
    private String secretName;

    @Bean
    public SecretsManagerClient secretsManagerClient() throws URISyntaxException {

        SecretsManagerClient secretsManagerClient;

        if (Objects.nonNull(property.getAws().getEnv()) && property.getAws().getEnv().equals("localstack")) {
            log.info("Acessando secrets via locakstack");

            secretsManagerClient = SecretsManagerClient.builder()
                    .credentialsProvider(ProfileCredentialsProvider.create())
                    .endpointOverride(new URI("http://localhost:4566"))
                    .region(Region.US_EAST_1)
                    .build();

            boolean existSecret = secretsManagerClient.listSecrets().secretList()
                    .stream().anyMatch(nameSecret-> nameSecret.name().equals(secretName));

            if (!existSecret) {
                log.info("Criando secret");
                String secretARN = createNewSecret(secretsManagerClient, secretName,
                        "{\"login\": \"fmaragao\", \"passowrd\":\"1234\"}");
            } else {
                log.info("Já existe secret");
            }

        } else {
            log.info("Acessando secrets na AWS");

            secretsManagerClient = SecretsManagerClient.builder()
                    .region(Region.US_EAST_1)
                    .credentialsProvider(ProfileCredentialsProvider.create())
                    .build();
        }

        return secretsManagerClient;
    }

    public static String createNewSecret( SecretsManagerClient secretsClient, String secretName, String secretValue) {

        try {
            CreateSecretRequest secretRequest = CreateSecretRequest.builder()
                    .name(secretName)
                    .description("This secret was created by the AWS Secret Manager Java API")
                    .secretString(secretValue)
                    .build();

            CreateSecretResponse secretResponse = secretsClient.createSecret(secretRequest);
            return secretResponse.arn();

        } catch (SecretsManagerException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return "";
    }
    //snippet-end:[secretsmanager.java2.create_secret.main]
}




