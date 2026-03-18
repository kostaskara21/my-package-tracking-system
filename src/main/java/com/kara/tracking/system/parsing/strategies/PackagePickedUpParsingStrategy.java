package com.kara.tracking.system.parsing.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.tracking.system.model.PackagePickedUp;
import com.kara.tracking.system.model.enums.EventTrackingType;
import com.kara.tracking.system.parsing.EventPayloadHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PackagePickedUpParsingStrategy implements EventPayloadHandler<PackagePickedUp> {


    @Override
    public EventTrackingType getTypeOfEvent() {
        return EventTrackingType.PACKAGE_PICKED_UP;
    }

    @Override
    public void handle(PackagePickedUp payload) throws JsonProcessingException {
        //og.info();
    }

    @Override
    public Class<PackagePickedUp> getPayloadType() {
        return PackagePickedUp.class;
    }

}
