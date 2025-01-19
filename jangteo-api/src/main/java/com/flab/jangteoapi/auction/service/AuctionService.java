package com.flab.jangteoapi.auction.service;

import com.flab.jangteoapi.auction.domain.Auction;
import com.flab.jangteoapi.auction.dto.AuctionResponse;
import com.flab.jangteoapi.auction.dto.AuctionSaveRequest;
import com.flab.jangteoapi.auction.exception.AuctionNotFoundException;
import com.flab.jangteoapi.auction.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;

    public UUID createAuction(AuctionSaveRequest request) {

        Auction auction = Auction.builder()
                .title(request.getAuctionTitle())
                .itemId(request.getItemId())
                .itemName(request.getItemName())
                .description(request.getDescription())
                .category(request.getCategory())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .startingPrice(request.getStartingPrice())
                .minBidIncrement(request.getMinBidIncrement())
                .requiredDeposit(request.getRequiredDeposit())
                .build();

        auctionRepository.save(auction);

        return auction.getId();
    }

    public void updateAuction(UUID auctionId, AuctionSaveRequest request) {

        Auction existingAuction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new AuctionNotFoundException("해당 경매를 찾을 수 없습니다."));

        Auction updatedAuction = existingAuction.toBuilder()
                .title(request.getAuctionTitle())
                .itemId(request.getItemId())
                .itemName(request.getItemName())
                .description(request.getDescription())
                .category(request.getCategory())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .startingPrice(request.getStartingPrice())
                .minBidIncrement(request.getMinBidIncrement())
                .requiredDeposit(request.getRequiredDeposit())
                .build();

        auctionRepository.save(updatedAuction);
    }

    public void deleteAuction(UUID auctionId) {

        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new AuctionNotFoundException("해당 경매를 찾을 수 없습니다."));

        auctionRepository.delete(auctionId);
    }

    public AuctionResponse getAuction(UUID auctionId) {

        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new AuctionNotFoundException("해당 경매를 찾을 수 없습니다"));

        return AuctionResponse.from(auction);
    }

    public List<AuctionResponse> getAllAuctions() {

        return auctionRepository.findAll().stream()
                .map(AuctionResponse::from)
                .collect(Collectors.toList());
    }

    public boolean isAuctionActive(UUID auctionID) {

        Auction auction = auctionRepository.findById(auctionID)
                .orElseThrow(() -> new AuctionNotFoundException("해당 경매를 찾을 수 없습니다"));

        LocalDateTime now = LocalDateTime.now();

        return now.isAfter(auction.getStartTime())
                && now.isBefore(auction.getEndTime());
    }
}
