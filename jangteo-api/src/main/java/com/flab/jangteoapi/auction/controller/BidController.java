package com.flab.jangteoapi.auction.controller;

import com.flab.jangteoapi.auction.dto.BiddingRequest;
import com.flab.jangteoapi.auction.dto.BiddingResponse;
import com.flab.jangteoapi.auction.service.BidService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auctions/{auctionId}/bids")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;

    @PostMapping
    public ResponseEntity<BiddingResponse> placeBid(@PathVariable UUID auctionId, @RequestBody @Valid BiddingRequest request) {

        BiddingResponse response = bidService.requestBid(auctionId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<BiddingResponse> getBidDetails(@PathVariable UUID auctionId, @RequestParam UUID userId) {

        BiddingResponse response = bidService.getBidDetailHistory(auctionId, userId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/cancel")
    public ResponseEntity cancelBid(@PathVariable UUID auctionId, @RequestParam UUID userId) {

        bidService.cancelBid(auctionId, userId);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
