package org.johan.microservices.pubsubgcp.controller;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.johan.microservices.pubsubgcp.model.PubSubMessage;
import org.johan.microservices.pubsubgcp.repository.PubSubMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PubSubPublisherController {

    private final PubSubTemplate pubSubTemplate;
    private final PubSubMessageRepository messageRepository;

    @Autowired
    public PubSubPublisherController(PubSubTemplate pubSubTemplate, PubSubMessageRepository messageRepository) {
        this.pubSubTemplate = pubSubTemplate;
        this.messageRepository = messageRepository;
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestBody String message) {
        PubSubMessage pubSubMessage = new PubSubMessage();
        pubSubMessage.setMessage(message);
        messageRepository.save(pubSubMessage);
        pubSubTemplate.publish("Johan-topic", message);
        return "Message published and saved to DB!";
    }
}