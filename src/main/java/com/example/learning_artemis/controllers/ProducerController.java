package com.example.learning_artemis.controllers;

import com.example.learning_artemis.artemis.MessageSender;
import com.example.learning_artemis.models.MessageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProducerController {

    private MessageSender messageSender;

    @GetMapping(path = "/producer")
    public String renderForm() {
        return "producer";
    }

    @PostMapping(path = "/sendMessage")
    public String handleMessageSend(@ModelAttribute MessageForm messageForm) {
        if (isFormValid(messageForm)) {
            messageSender.setDestination(messageForm.getQueueName());
            messageSender.setMessage(messageForm.getMessage());
            messageSender.sendMessage();
        }
        return "redirect:/producer";
    }

    private boolean isFormValid(MessageForm messageForm) {
        if (messageForm.getQueueName().trim().isBlank()) {
            System.err.println("Please fill in the queue name!");
            return false;
        }
        if (messageForm.getMessage().trim().isBlank()) {
            System.err.println("Please fill in the message!");
            return false;
        }
        return true;
    }

    @Autowired
    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }
}
