package com.flab.jangteoapi.auction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuctionSaveRequest {

    private String auctionTitle;

    @NotNull
    private UUID itemId;

    @NotBlank
    private String itemName;
    private String description;
    private String category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @PositiveOrZero
    private Integer startingPrice;

    @Positive
    private Integer minBidIncrement;

    @PositiveOrZero
    private Integer requiredDeposit;
}
