package com.max.myskills.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class RegisterRequest {
    private String username;
    private String password;
    private String phone;
    private Integer age;
    private String region;
    private String avatar;
}
