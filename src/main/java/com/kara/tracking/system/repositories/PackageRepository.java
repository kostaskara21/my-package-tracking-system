package com.kara.tracking.system.repositories;

import com.kara.tracking.system.model.entities.PackageEntity;
import com.kara.tracking.system.model.enums.EventTrackingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, String> {
    List<PackageEntity> findByStatus(EventTrackingType status);
    PackageEntity save(PackageEntity packageEntity);
}
