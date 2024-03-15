package com.example.learning_artemis.models;

public class MessageForm {

    private final String queueName;
    private final String message;

    public MessageForm(String queueName, String message) {
        this.queueName = queueName;
        this.message = message;
    }

    public String getQueueName() {
        return queueName;
    }

    public String getMessage() {
        return message;
    }

}
