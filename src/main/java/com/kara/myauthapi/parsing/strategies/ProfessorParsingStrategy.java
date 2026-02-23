package com.kara.myauthapi.parsing.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.myauthapi.event.TestEvent;
import com.kara.myauthapi.service.EventPayloadHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProfessorParsingStrategy implements EventPayloadHandler {

    @Override
    public String getType() {
        return "PROFESOR";
    }



    @Override
    public void handle(String payload) throws JsonProcessingException {

    }
}
