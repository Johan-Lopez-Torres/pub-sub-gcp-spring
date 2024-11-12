package org.johan.microservices.pubsubgcp.config;


import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.johan.microservices.pubsubgcp.model.PubSubMessage;
import org.johan.microservices.pubsubgcp.repository.PubSubMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class PubSubSubscriberConfig {

    private static final Logger log = LoggerFactory.getLogger(PubSubSubscriberConfig.class);

    private final PubSubMessageRepository messageRepository;

    @Autowired
    public PubSubSubscriberConfig(PubSubMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
            MessageChannel inputChannel, PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, "Johan-topic-sub");
        adapter.setOutputChannel(inputChannel);
        return adapter;
    }

    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "inputChannel")
    public MessageHandler messageReceiver() {
        return message -> {
            String receivedMessage = new String((byte[]) message.getPayload());
            log.info("Message received: {}", receivedMessage);
//            PubSubMessage pubSubMessage = new PubSubMessage();
//            pubSubMessage.setMessage(receivedMessage);
//            messageRepository.save(pubSubMessage);
        };
    }
}