package com.kara.tracking.system.model;
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
    private String location;
    private String destination;
    private String priority;

}
