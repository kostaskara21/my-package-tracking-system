package com.kara.tracking.system.service;

import com.kara.tracking.system.model.PackageCreated;
import com.kara.tracking.system.model.entities.PackageEntity;

import java.util.function.Consumer;

public interface PackageManagementService {

    void createOrUpdatePackag(String packageId,String orderId, Consumer<PackageEntity> consumer);

}
