package com.kara.tracking.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageCreatedPayload {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String phone;


}
