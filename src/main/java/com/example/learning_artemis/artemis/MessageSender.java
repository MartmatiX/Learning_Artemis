package com.example.learning_artemis.artemis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private final JmsTemplate jmsTemplate;

    private String destination;
    private String message;

    @Autowired
    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage() {
        try {
            jmsTemplate.convertAndSend(this.destination, this.message);
            System.out.println("Message sent to queue");
        } catch (Exception e) {
            System.err.println("Unable to send message!\n[" + e + "]");
        }
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
