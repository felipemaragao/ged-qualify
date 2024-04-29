//package br.com.qualify.ged.aws.producer;
//
//import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class ProducerFileUpload {
//
//    @Autowired
//    QueueMessagingTemplate queueMessagingTemplate;
//
//
//    public void sendMessage(String queueName, Object message) {
//        queueMessagingTemplate.convertAndSend(queueName, message);
//    }
//
//
//}
//
