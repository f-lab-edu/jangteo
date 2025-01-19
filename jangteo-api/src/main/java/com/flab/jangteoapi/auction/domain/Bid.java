package com.flab.jangteoapi.auction.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bid {

    private UUID id;

    @NotBlank
    private UUID auctionId;

    @NotBlank
    private UUID userId;

    @Positive
    private int amount;

    private boolean isActive = true;
}
