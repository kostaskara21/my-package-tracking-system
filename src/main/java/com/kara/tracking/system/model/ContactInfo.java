package com.kara.tracking.system.model;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ContactInfo {
    private String name;
    private String phone;
    private String email;
}
