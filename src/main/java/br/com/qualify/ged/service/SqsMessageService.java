//package br.com.qualify.ged.service;
//
//import com.amazonaws.services.sqs.AmazonSQS;
//import com.amazonaws.services.sqs.AmazonSQSAsync;
//import com.amazonaws.services.sqs.model.SendMessageRequest;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
//
//
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Service;
//
//
//@Service
//@Slf4j
//public class SqsMessageService {
//
//    @Value("${sqsQueueName}")
//    private String queueName;
//    private final QueueMessagingTemplate queueMessagingTemplate;
//
//    @Autowired
//    public SqsMessageService(AmazonSQSAsync amazonSQSAsync) {
//        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
//    }
//
//    public void sendMessage(String message) {
//        this.queueMessagingTemplate.send(queueName, MessageBuilder.withPayload(message).build());
//    }
//}
