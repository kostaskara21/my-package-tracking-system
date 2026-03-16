package com.kara.tracking.system.parsing.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.tracking.system.model.PackagePickedUpPayload;
import com.kara.tracking.system.model.enums.EventTrackingType;
import com.kara.tracking.system.parsing.EventPayloadHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParsingPackagePickedUpParsingStrategy implements EventPayloadHandler<PackagePickedUpPayload> {


    @Override
    public EventTrackingType getTypeOfEvent() {
        return EventTrackingType.PACKAGE_PICKED_UP;
    }

    @Override
    public void handle(PackagePickedUpPayload payload) throws JsonProcessingException {
        //og.info();
    }

    @Override
    public Class<PackagePickedUpPayload> getPayloadType() {
        return PackagePickedUpPayload.class;
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
