package com.kara.tracking.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String phone;



}
