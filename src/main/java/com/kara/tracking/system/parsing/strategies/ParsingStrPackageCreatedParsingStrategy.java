package com.kara.tracking.system.parsing.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.tracking.system.model.PackageCreatedPayload;
import com.kara.tracking.system.model.enums.EventTrackingType;
import com.kara.tracking.system.parsing.EventPayloadHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ParsingStrPackageCreatedParsingStrategy implements EventPayloadHandler<PackageCreatedPayload> {



    @Override
    public EventTrackingType getTypeOfEvent() {
        return EventTrackingType.PACKAGE_CREATED;
    }

    @Override
    public void handle(PackageCreatedPayload payload) throws JsonProcessingException {
        log.info("we got {}",payload);
    }

    @Override
    public Class<PackageCreatedPayload> getPayloadType() {
        return PackageCreatedPayload.class;
    }

}
