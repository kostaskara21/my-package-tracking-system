package com.kara.tracking.system.parsing.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.tracking.system.mapper.PackageMapper;
import com.kara.tracking.system.model.PackageCreated;
import com.kara.tracking.system.model.entities.PackageEntity;
import com.kara.tracking.system.model.enums.EventTrackingType;
import com.kara.tracking.system.parsing.EventPayloadHandler;
import com.kara.tracking.system.service.PackageManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PackageCreatedParsingStrategy implements EventPayloadHandler<PackageCreated> {

    private final PackageManagementService packageManagementService;
    private  final PackageMapper packageMapper;

    @Override
    public EventTrackingType getTypeOfEvent() {
        return EventTrackingType.PACKAGE_CREATED;
    }

    @Override
    public void handle(PackageCreated payload) throws JsonProcessingException {
        log.info("Received event of type  {}",getTypeOfEvent());

        packageManagementService.createOrUpdatePackag(payload.getPackageId(), payload.getOrderId(), aPackage -> {
            packageMapper.updateFromCreated(payload,aPackage);

        });
    }

    @Override
    public Class<PackageCreated> getPayloadType() {
        return PackageCreated.class;
    }

}
