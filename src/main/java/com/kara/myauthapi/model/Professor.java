package com.kara.myauthapi.model;

import com.kara.myauthapi.event.TestEvent;
import com.kara.myauthapi.service.EventPayloadHandler;
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
