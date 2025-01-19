package com.flab.jangteoapi.user.domain;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID id;

    private String username;

    private String password;

    @Email
    private String email;

    private String fullName;
}
