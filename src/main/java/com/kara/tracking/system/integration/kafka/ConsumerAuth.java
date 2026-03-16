package com.kara.tracking.system.integration.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.tracking.system.event.TestEvent;
import com.kara.tracking.system.process.EventProcessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConsumerAuth {


    private final EventProcessor eventProcessor;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(TestEvent message) throws JsonProcessingException {
       log.info("Received message: " + message);

       eventProcessor.process(message);
    }
}
