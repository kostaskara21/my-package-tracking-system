package com.kara.tracking.system.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "package")
public class PackageEntity {

    @Id
    @Column(name = "package_id")
    private String packageId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "sender")
    private String sender;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "weight_kg")
    private Double weightKg;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "priority")
    private String priority;

    @Column(name = "courier_id")
    private String courierId;

    @Column(name = "location")
    private String location ;



}