package org.johan.microservices.pubsubgcp;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PubSubPublisherController {

    private PubSubTemplate pubSubTemplate;

    public PubSubPublisherController(PubSubTemplate pubSubTemplate) {
        this.pubSubTemplate = pubSubTemplate;
    }


    @PostMapping("/publish")
    public String publishMessage(@RequestBody String message) {
        pubSubTemplate.publish("Johan-topic", message);
        return "Message published! Johan";
    }
}