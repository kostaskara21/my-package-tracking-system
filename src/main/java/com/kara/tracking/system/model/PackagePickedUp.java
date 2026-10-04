package com.kara.tracking.system.model;
import com.kara.tracking.system.model.enums.EventTrackingType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import javax.xml.stream.Location;
import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackagePickedUp {
    private String packageId;
    private String courierId;
    private String orderId;
    private String location;
    private String destination;
    private String priority;
    private Instant timestamp;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EventTrackingType status;
    private String recipient_email;



}
