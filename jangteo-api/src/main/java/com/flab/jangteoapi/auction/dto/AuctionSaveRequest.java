package com.flab.jangteoapi.auction.dto;

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
    private UUID itemId;
    private String itemName;
    private String description;
    private String category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer startingPrice;
    private Integer minBidIncrement;
    private Integer requiredDeposit;
}
