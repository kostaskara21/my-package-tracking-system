package com.kara.tracking.system.repositories;

import com.kara.tracking.system.model.entities.PackageEntity;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, String> {
}
