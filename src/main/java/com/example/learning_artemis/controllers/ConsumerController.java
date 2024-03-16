package com.example.learning_artemis.controllers;

import com.example.learning_artemis.artemis.MessageReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ConsumerController {

    private MessageReceiver messageReceiver;

    private String message;

    @GetMapping(path = "/consumer")
    public String renderConsumer(Model model) {
        model.addAttribute("message", message);
        return "consumer";
    }

    @GetMapping(path = "/consumeMessage")
    public String printMessage(@ModelAttribute(name = "queueName") String queueName) {
        message = messageReceiver.receiveMessage(queueName);
        return "redirect:/consumer";
    }

    @Autowired
    public void setMessageReceiver(MessageReceiver messageReceiver) {
        this.messageReceiver = messageReceiver;
    }
}
