package com.flab.jangteoapi.auction.service;

import com.flab.jangteoapi.auction.domain.Bid;
import com.flab.jangteoapi.auction.dto.BiddingRequest;
import com.flab.jangteoapi.auction.dto.BiddingResponse;
import com.flab.jangteoapi.auction.exception.BidInvalidRequestException;
import com.flab.jangteoapi.auction.exception.BidNotFoundException;
import com.flab.jangteoapi.auction.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BidService {

    private final BidRepository bidRepository;

    private final AuctionService auctionService;

    public BiddingResponse requestBid(UUID auctionId, BiddingRequest request) {

        if (auctionService.isAuctionActive(auctionId)) {
            throw new BidInvalidRequestException("입찰 가능 시점이 아닙니다.");
        }

        if (request.getAmount() <= 0) {
            throw new BidInvalidRequestException("입찰 금액은 0원보다 커야 합니다.");
        }

        Bid bid = Bid.builder()
                .auctionId(auctionId)
                .userId(request.getUserId())
                .amount(request.getAmount())
                .build();

        bidRepository.save(bid);

        return BiddingResponse.from(bid);
    }

    public BiddingResponse getBidDetailHistory(UUID auctionId, UUID userId) {

        Bid bid = bidRepository.findByAuctionIdAndUserId(auctionId, userId)
                .orElseThrow(() -> new BidNotFoundException("해당하는 입찰이 존재하지 않습니다."));

        return BiddingResponse.from(bid);
    }

    public void cancelBid(UUID auctionId, UUID userId) {

        Bid bid = bidRepository.findByAuctionIdAndUserId(auctionId, userId)
                .orElseThrow(() -> new BidNotFoundException("해당하는 입찰이 존재하지 않습니다."));

        bid.setActive(false);
    }
}
