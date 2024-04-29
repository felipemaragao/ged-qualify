//package br.com.qualify.ged.config;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.client.builder.AwsClientBuilder;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.sns.AmazonSNS;
//import com.amazonaws.services.sqs.AmazonSQSAsync;
//import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import io.awspring.cloud.messaging.config.QueueMessageHandlerFactory;
//import io.awspring.cloud.messaging.support.NotificationMessageArgumentResolver;
//import lombok.Generated;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.messaging.converter.MappingJackson2MessageConverter;
//import org.springframework.messaging.converter.MessageConverter;
//
//import java.util.List;
//
//import static br.com.qualify.ged.config.AWSConfigConstants.*;
//
//
//@Slf4j
//@Configuration
//public class SQSConfig {
//    @Bean
//    public AwsClientBuilder.EndpointConfiguration endpointConfiguration() {
//        return new AwsClientBuilder.EndpointConfiguration(ENDPOINT, US_EAST_1);
//    }
//
//    @Bean
//    @Primary
//    public AmazonSQSAsync amazonSQSAsync(final AwsClientBuilder.EndpointConfiguration endpointConfiguration) {
//        BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
//        return AmazonSQSAsyncClientBuilder
//                .standard()
//                .withEndpointConfiguration(endpointConfiguration)
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .build();
//    }
//
//    @Bean
//    public QueueMessageHandlerFactory queueMessageHandlerFactory(MessageConverter messageConverter) {
//        var factory = new QueueMessageHandlerFactory();
//        factory.setArgumentResolvers(List.of(new NotificationMessageArgumentResolver(messageConverter)));
//        return factory;
//    }
//
//    @Bean
//    protected MessageConverter messageConverter() {
//        var converter = new MappingJackson2MessageConverter();
//        converter.setSerializedPayloadClass(String.class);
//        converter.setStrictContentTypeMatch(false);
//        return converter;
//    }
//}
