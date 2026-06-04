package com.kara.tracking.system.model;

import com.kara.tracking.system.model.enums.EventTrackingType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.stream.Location;
import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PackageCreated {
    private String packageId;
    private String orderId;
    private String courierId;
    private String sender;
    private String recipient;
    private String origin;
    private String destination;
    private double weightKg;
    private String priority;
    private Instant timestamp;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)

    private EventTrackingType status;


}
