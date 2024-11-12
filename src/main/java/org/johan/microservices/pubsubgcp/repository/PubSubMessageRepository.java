package org.johan.microservices.pubsubgcp.repository;


import org.johan.microservices.pubsubgcp.model.PubSubMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PubSubMessageRepository extends JpaRepository<PubSubMessage, Long> {
}