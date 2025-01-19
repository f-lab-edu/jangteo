package com.flab.jangteoapi.auction.dto;


import com.flab.jangteoapi.auction.domain.Bid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BiddingResponse {

    private UUID id;

    private UUID auctionId;

    private UUID userId;

    private int amount;

    public static BiddingResponse from(Bid bid) {

        return BiddingResponse.builder()
                .id(bid.getId())
                .auctionId(bid.getAuctionId())
                .userId(bid.getUserId())
                .amount(bid.getAmount())
                .build();
    }
}
