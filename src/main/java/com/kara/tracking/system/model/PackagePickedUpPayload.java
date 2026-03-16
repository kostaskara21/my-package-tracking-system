package com.kara.tracking.system.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackagePickedUpPayload {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String classroom;

}
