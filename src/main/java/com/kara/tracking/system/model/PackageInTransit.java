package com.kara.tracking.system.model;

import com.kara.tracking.system.model.enums.EventTrackingType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageInTransit {
    private String packageId;
    private String courierId;
    private String orderId;
    private String location;
    private Instant timestamp;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)

    private EventTrackingType status;
}
