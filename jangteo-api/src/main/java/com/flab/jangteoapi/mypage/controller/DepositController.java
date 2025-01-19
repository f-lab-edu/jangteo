package com.flab.jangteoapi.mypage.controller;


import com.flab.jangteoapi.mypage.dto.DepositRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/deposits")
public class DepositController {

    @PostMapping
    public ResponseEntity requestDeposit(@RequestBody @Valid DepositRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity getDeposit(@PathVariable UUID userId) {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/refund")
    public ResponseEntity requestDepositRefund(@RequestBody @Valid DepositRequest request) {

        return ResponseEntity.ok().build();
    }
}
