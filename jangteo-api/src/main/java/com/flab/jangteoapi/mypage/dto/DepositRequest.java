package com.flab.jangteoapi.mypage.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class DepositRequest {

    @NotBlank
    private UUID userId;

    @Positive
    private int amount;
}
