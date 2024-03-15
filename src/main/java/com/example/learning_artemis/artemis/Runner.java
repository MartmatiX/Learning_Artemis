package com.example.learning_artemis.artemis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private MessageSender messageSender;

    @Override
    public void run(String... args) {
        messageSender.sendMessage("my.testing.queue", "This is a testing message");
    }

    @Autowired
    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }
}
