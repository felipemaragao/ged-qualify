package br.com.qualify.ged.aws.consumer;


import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerFileUpload {
    @SqsListener("${sqsQueueName}")
    public void receiveMessage(String message) {

        log.info("Consumindo mensagem");
        log.info(message);
    }
}
