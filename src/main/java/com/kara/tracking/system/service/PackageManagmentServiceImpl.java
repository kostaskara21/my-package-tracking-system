package com.kara.tracking.system.service;

import com.kara.tracking.system.model.PackageCreated;
import com.kara.tracking.system.model.entities.PackageEntity;
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
    public void createOrUpdatePackag(PackageCreated packageCreated,Consumer<PackageEntity> packageCreatedConsumer) {

        log.info("Checking if package exists or Creating new Entry in DB with packageId: {}",packageCreated.getPackageId());
        if(packageCreated.getPackageId()!=null) {
            PackageEntity newPackage = packageRepository
                    .findById(packageCreated.getPackageId())
                    .orElseGet(() -> {
                        PackageEntity newPkg = new PackageEntity();
                        newPkg.setPackageId(packageCreated.getPackageId());
                        return newPkg;
                    });

            packageCreatedConsumer.accept(newPackage);

            packageRepository.save(newPackage);
        }

    }
}
