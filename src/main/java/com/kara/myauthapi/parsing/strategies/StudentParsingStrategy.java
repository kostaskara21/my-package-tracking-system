package com.kara.myauthapi.parsing.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kara.myauthapi.event.TestEvent;
import com.kara.myauthapi.model.Student;
import com.kara.myauthapi.service.EventPayloadHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentParsingStrategy implements EventPayloadHandler {

    private final ObjectMapper objectMapper;

    @Override
    public String getType() {
        return "STUDENT";
    }

    @Override
    public void handle(String payload) throws JsonProcessingException {
        Student student = objectMapper.readValue(payload, Student.class);
        log.info(student.toString()+"this is the student");
    }
}
