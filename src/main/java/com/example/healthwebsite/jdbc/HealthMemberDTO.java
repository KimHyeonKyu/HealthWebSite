package com.example.healthwebsite.jdbc;

import lombok.Data;

@Data
public class HealthMemberDTO {
    private String id;
    private String email;
    private String password;
    private String phoneNumber;
}
