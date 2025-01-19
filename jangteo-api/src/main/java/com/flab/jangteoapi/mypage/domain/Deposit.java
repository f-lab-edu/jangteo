package com.flab.jangteoapi.mypage.domain;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Deposit {

    private UUID id;

    private UUID userId;

    @PositiveOrZero
    private int amount;

    private LocalDateTime savedAt;
}
