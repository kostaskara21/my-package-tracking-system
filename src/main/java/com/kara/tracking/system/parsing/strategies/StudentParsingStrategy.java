package com.kara.tracking.system.parsing.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.tracking.system.model.Student;
import com.kara.tracking.system.parsing.EventPayloadHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentParsingStrategy implements EventPayloadHandler<Student> {


    @Override
    public String getTypeOfEvent() {
        return "STUDENT";
    }

    @Override
    public void handle(Student payload) throws JsonProcessingException {

    }

    @Override
    public Class<Student> getPayloadType() {
        return Student.class;
    }

/*    @Override
    public void handle(String payload) throws JsonProcessingException {
        //Student student = objectMapper.readValue(payload, Student.class);
        Student student =xmlMapper.readValue(payload, Student.class);
        log.info(student.toString()+"this is the student");
    }

    @Override
    public Class<?> getPayloadOfEvent() {
        return null;
   } */
}
