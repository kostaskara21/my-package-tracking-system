package com.kara.tracking.system.model;

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
    //TODO UPDATE THE OTHER EVENTS WITH THE TIMESTAMP
    private Instant timestamp;
}
