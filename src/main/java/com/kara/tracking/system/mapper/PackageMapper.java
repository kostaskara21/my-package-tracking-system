package com.kara.tracking.system.mapper;

import com.kara.tracking.system.model.PackageCreated;
import com.kara.tracking.system.model.PackagePickedUp;
import com.kara.tracking.system.model.entities.PackageEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PackageMapper {

    // for CREATE
    //@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(source = "sender", target = "sender"),
            @Mapping(source = "recipient", target = "recipient"),
            @Mapping(source = "origin", target = "origin"),
            @Mapping(source = "destination", target = "destination"),
            @Mapping(source = "weightKg", target = "weightKg"),
            @Mapping(source = "priority", target = "priority")
    })
    void updateFromCreated(PackageCreated source, @MappingTarget PackageEntity target);

    // for PICKED UP
    @Mappings({
            @Mapping(source = "destination", target = "destination"),
            @Mapping(source = "priority", target = "priority"),
            @Mapping(source = "location", target = "location")
    })
    void updateFromPickedUp(PackagePickedUp source, @MappingTarget PackageEntity target);
}