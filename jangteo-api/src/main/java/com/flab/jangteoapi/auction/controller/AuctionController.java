package com.flab.jangteoapi.auction.controller;

import com.flab.jangteoapi.auction.dto.AuctionSaveRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    @PostMapping
    public ResponseEntity createAuction(@RequestBody AuctionSaveRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{auctionId}")
    public ResponseEntity updateAuction(@PathVariable UUID auctionId, @RequestBody AuctionSaveRequest request) {

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{auctionId}")
    public ResponseEntity deleteAuction(@PathVariable UUID auctionId) {

        return ResponseEntity.ok().build();
    }
}
