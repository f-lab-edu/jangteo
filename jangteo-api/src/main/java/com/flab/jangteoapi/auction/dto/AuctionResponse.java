package com.flab.jangteoapi.auction.dto;

import com.flab.jangteoapi.auction.domain.Auction;
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
public class AuctionResponse {


    private UUID auctionId;
    private String auctionTitle;
    private UUID itemId;
    private String itemName;
    private String description;
    private String category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int startingPrice;
    private int minBidIncrement;
    private int requiredDeposit;

    public static AuctionResponse from(Auction auction) {

        return AuctionResponse.builder()
                .auctionId(auction.getId())
                .auctionTitle(auction.getTitle())
                .itemId(auction.getItemId())
                .itemName(auction.getItemName())
                .description(auction.getDescription())
                .category(auction.getCategory())
                .startTime(auction.getStartTime())
                .endTime(auction.getEndTime())
                .startingPrice(auction.getStartingPrice())
                .minBidIncrement(auction.getMinBidIncrement())
                .requiredDeposit(auction.getRequiredDeposit())
                .build();
    }
}
