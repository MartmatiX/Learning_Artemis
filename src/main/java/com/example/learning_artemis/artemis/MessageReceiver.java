package com.example.learning_artemis.artemis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MessageReceiver {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageReceiver(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public String receiveMessage(String queueName) {
        if (queueName.trim().isBlank()) {
            System.err.println("Queue name can not be empty!");
            return "Queue name can not be empty!";
        }
        jmsTemplate.setReceiveTimeout(10);
        try {
            String message = (String) jmsTemplate.receiveAndConvert(queueName);
            return Objects.requireNonNullElse(message, "No message to consume");
        } catch (Exception e) {
            System.err.println("Unable to consume message!\n[" + e + "]");
            return "Unable to consume message!\n[" + e + "]";
        }
    }

}
