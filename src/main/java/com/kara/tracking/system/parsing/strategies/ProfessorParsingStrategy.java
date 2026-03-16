package com.kara.tracking.system.parsing.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.tracking.system.model.Professor;
import com.kara.tracking.system.parsing.EventPayloadHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProfessorParsingStrategy implements EventPayloadHandler<Professor> {



    @Override
    public String getTypeOfEvent() {
        return "";
    }

    @Override
    public void handle(Professor payload) throws JsonProcessingException {

    }

    @Override
    public Class<Professor> getPayloadType() {
        return null;
    }

}
