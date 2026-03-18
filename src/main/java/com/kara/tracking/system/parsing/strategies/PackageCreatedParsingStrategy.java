package com.kara.tracking.system.parsing.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.tracking.system.model.PackageCreated;
import com.kara.tracking.system.model.enums.EventTrackingType;
import com.kara.tracking.system.parsing.EventPayloadHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PackageCreatedParsingStrategy implements EventPayloadHandler<PackageCreated> {



    @Override
    public EventTrackingType getTypeOfEvent() {
        return EventTrackingType.PACKAGE_CREATED;
    }

    @Override
    public void handle(PackageCreated payload) throws JsonProcessingException {
        log.info("we got {}",payload);
    }

    @Override
    public Class<PackageCreated> getPayloadType() {
        return PackageCreated.class;
    }

}
