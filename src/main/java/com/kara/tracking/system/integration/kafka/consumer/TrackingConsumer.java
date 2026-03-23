package com.kara.tracking.system.integration.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.tracking.system.event.TrackingEvent;
import com.kara.tracking.system.process.EventProcessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TrackingConsumer {


    private final EventProcessor eventProcessor;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(TrackingEvent message) throws JsonProcessingException {
       log.info("Received message: " + message);
       eventProcessor.process(message);
    }
}
