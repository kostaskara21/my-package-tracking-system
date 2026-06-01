package com.kara.tracking.system.parsing.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.tracking.system.mapper.PackageMapper;
import com.kara.tracking.system.model.PackageInTransit;
import com.kara.tracking.system.model.enums.EventTrackingType;
import com.kara.tracking.system.parsing.EventPayloadHandler;
import com.kara.tracking.system.service.PackageManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class PackageInTransitStrategy implements EventPayloadHandler<PackageInTransit> {

    private  final PackageMapper packageMapper;
    private final PackageManagementService packageManagementService;

    @Override
    public EventTrackingType getTypeOfEvent() {
        return EventTrackingType.PACKAGE_IN_TRANSIT;
    }

    @Override
    public void handle(PackageInTransit payload) throws JsonProcessingException {
        log.info("Package in transit updated");

        packageManagementService.createOrUpdatePackag(payload.getPackageId(),
                payload.getOrderId(),
                getTypeOfEvent(),
                packageEntity ->
                            packageMapper.updateFromPackageInTransit(payload,packageEntity));
    }

    @Override
    public Class<PackageInTransit> getPayloadType() {
        return PackageInTransit.class;
    }
}
