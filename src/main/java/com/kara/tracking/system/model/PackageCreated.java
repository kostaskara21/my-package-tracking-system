package com.kara.tracking.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.stream.Location;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PackageCreated {
    private String packageId;
    private String orderId;
    private ContactInfo sender;
    private ContactInfo recipient;
    private Location origin;
    private Location destination;
    private double weightKg;
    private String priority;

}
