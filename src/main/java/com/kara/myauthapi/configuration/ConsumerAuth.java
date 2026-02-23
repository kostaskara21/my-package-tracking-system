package com.kara.myauthapi.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kara.myauthapi.event.TestEvent;
import com.kara.myauthapi.service.EventProcessor;

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
