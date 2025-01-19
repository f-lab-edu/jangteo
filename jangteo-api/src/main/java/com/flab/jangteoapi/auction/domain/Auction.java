package com.flab.jangteoapi.auction.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Auction {

    private UUID id;

    private String title;

    @NotBlank
    private UUID itemId;

    @NotBlank
    private String itemName;

    private String description;

    private String category;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @PositiveOrZero
    private int startingPrice;

    @Positive
    private int minBidIncrement;

    @PositiveOrZero
    private int requiredDeposit;

    private Boolean isActive;
}
