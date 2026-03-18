package com.kara.tracking.system.model;
import lombok.*;

import javax.xml.stream.Location;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class PackagePickedUp {
    private String packageId;
    private String courierId;
    private Instant pickupTime;
    private Location location;


}
