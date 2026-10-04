package com.kara.tracking.system.service;

import com.kara.tracking.system.model.entities.PackageEntity;
import com.kara.tracking.system.model.enums.EventTrackingType;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PackageDeliveredService {

    List<PackageEntity> fetchPackagesByStatus(EventTrackingType status);
    void updateDeliveredPackageStatus(String packageId);
}
