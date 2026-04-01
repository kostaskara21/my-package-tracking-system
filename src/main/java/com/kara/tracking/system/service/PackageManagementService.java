package com.kara.tracking.system.service;

import com.kara.tracking.system.model.PackageCreated;
import com.kara.tracking.system.model.entities.PackageEntity;
import com.kara.tracking.system.model.enums.EventTrackingType;

import java.util.function.Consumer;

public interface PackageManagementService {

    void createOrUpdatePackag(String packageId, String orderId,EventTrackingType eventTrackingType,Consumer<PackageEntity> consumer);

}
