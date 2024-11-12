package org.johan.microservices.pubsubgcp;

import org.johan.microservices.pubsubgcp.model.PubSubMessage;
import org.johan.microservices.pubsubgcp.repository.PubSubMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PubSubGcpApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PubSubGcpApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {





    }
}
