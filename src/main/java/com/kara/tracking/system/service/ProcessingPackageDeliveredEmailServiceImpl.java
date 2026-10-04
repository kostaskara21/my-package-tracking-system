package com.kara.tracking.system.service;

import com.kara.tracking.system.model.entities.PackageEntity;
import com.kara.tracking.system.model.enums.EventTrackingType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessingPackageDeliveredEmailServiceImpl implements ProcessingPackageDeliveredEmailService{

    private final PackageDeliveredService packageDeliveredService;
    private final EmailService emailService;

    private static final String EMAIL_SUBJECT="PACKAGE NOTIFICATION";
    private static final String EMAIL_TEXT="Your package has arrived in the Box point";

    @Override
    public void processPackageDeliveredEmail() {
        log.info("Starting scheduled processing for Packages with status {} ", EventTrackingType.PACKAGE_DELIVERED);

        List<PackageEntity> packages =packageDeliveredService.fetchPackagesByStatus(EventTrackingType.PACKAGE_DELIVERED);

        if(packages.isEmpty()){
            log.info("No Delivered Packages Found");
            return;
        }

        log.info("Found {} Delivered Packages in total",packages.size());

        packages.forEach(this::processSingleDeliveredPackages);
    }


    private void  processSingleDeliveredPackages(PackageEntity packageEntity){
        log.info("Processing Package with PackageId : {}",packageEntity.getPackageId());
        if(packageEntity.getRecipient_email()==null) {
            log.info("Package with ID: {} does not contain email User could not be informed",packageEntity.getPackageId());
            return;
        }
        emailService.sendEmail(packageEntity.getRecipient_email(),EMAIL_SUBJECT,EMAIL_TEXT);
        packageDeliveredService.updateDeliveredPackageStatus(packageEntity.getPackageId());

    }
}
