package com.kara.tracking.system.service;

import com.kara.tracking.system.exceptions.PackageNotFoundException;
import com.kara.tracking.system.model.PackageCreated;
import com.kara.tracking.system.model.entities.PackageEntity;
import com.kara.tracking.system.model.enums.EventTrackingType;
import com.kara.tracking.system.repositories.PackageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class PackageManagmentServiceImpl  implements PackageManagementService{

    private  final PackageRepository packageRepository;

    @Override
    public void createOrUpdatePackag(String packageId, String orderId, EventTrackingType eventTrackingType, Consumer<PackageEntity> packageCreatedConsumer) {

        log.info("Checking if package exists or Creating new Entry in DB with packageId: {}",packageId);

            PackageEntity newPackage = packageRepository
                    .findById(packageId)
                    .orElseGet(() -> {
                        if(!eventTrackingType.equals(EventTrackingType.PACKAGE_CREATED)){
                            throw new PackageNotFoundException("Not such a package found");
                        }
                        PackageEntity newPkg = new PackageEntity();
                        newPkg.setPackageId(packageId);
                        newPkg.setOrderId(orderId);
                        return newPkg;
                    });

            packageCreatedConsumer.accept(newPackage);

            packageRepository.save(newPackage);


    }
}
