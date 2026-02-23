package com.kara.myauthapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.myauthapi.event.TestEvent;
import com.kara.myauthapi.parsing.EventHandlerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventProcessor {

    private final EventHandlerRegistry registry;

    public void process(TestEvent event) throws JsonProcessingException {
        registry.getHandler(event.getType())
                .handle(event.getPayload());
    }
}