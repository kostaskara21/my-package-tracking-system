package com.kara.tracking.system.service;

import com.kara.tracking.system.model.entities.PackageEntity;
import com.kara.tracking.system.model.enums.EventTrackingType;
import com.kara.tracking.system.repositories.PackageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PackageDeliveredServiceImpl implements PackageDeliveredService {

    private final PackageRepository packageRepository;


    @Override
    public void updateDeliveredPackageStatus(String packageId) {
        Optional<PackageEntity> packageEntity = packageRepository.findById(packageId);
        if(packageEntity.isPresent()){
            packageEntity.get().setStatus(EventTrackingType.NOTIFIED);
            log.info("STATUS Changed  to : {} FOR PACKAGE WITH ID {}",EventTrackingType.NOTIFIED,packageId);
            packageRepository.save(packageEntity.get());
        }
    }

    @Override
    public List<PackageEntity> fetchPackagesByStatus(EventTrackingType statusDelivered) {
        log.info("Fetching all Packages Packages with status: {}", statusDelivered);

        List<PackageEntity> deliveredPackages = packageRepository.findByStatus(statusDelivered);

        return deliveredPackages == null ? Collections.emptyList() : deliveredPackages;
    }


}
