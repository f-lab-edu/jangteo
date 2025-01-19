package com.flab.jangteoapi.user.dto;

import lombok.Data;

@Data
public class UserSignUpRequestDto {

    private String username;

    private String password;

    private String email;

    private String fullName;
}
