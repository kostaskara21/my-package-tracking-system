package com.kara.tracking.system.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingEvent {
    private String payload;
    private String type;
    private String id;
    private String authId;
}
