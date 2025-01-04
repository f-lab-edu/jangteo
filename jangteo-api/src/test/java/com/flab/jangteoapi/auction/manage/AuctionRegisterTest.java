package com.flab.jangteoapi.auction.manage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.jangteoapi.auction.dto.AuctionSaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuctionRegisterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("경매 생성에 성공합니다.")
    void shouldReturnCreatedWhenAuctionIsRegistered() throws Exception {

        AuctionSaveRequest request = AuctionSaveRequest.builder()
                .auctionTitle("신규 아이폰을 판매합니다.")
                .itemId(UUID.randomUUID())
                .itemName("Apple iPhone 13")
                .description("128GB")
                .category("electronics")
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusDays(1))
                .startingPrice(500000)
                .minBidIncrement(10000)
                .requiredDeposit(250000)
                .build();

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/auctions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("경매 진행 시간 범위가 잘못된 경우 오류를 반환합니다.")
    void shouldFailWhenAuctionTimeRangeIsInvalid() throws Exception {

        AuctionSaveRequest request = AuctionSaveRequest.builder()
                .auctionTitle("아이폰 판매합니다.")
                .itemId(UUID.randomUUID())
                .itemName("Apple iPhone 13")
                .description("128GB")
                .category("electronics")
                .startTime(LocalDateTime.now().minusDays(1))
                .endTime(LocalDateTime.now().plusDays(1))
                .startingPrice(500000)
                .minBidIncrement(10000)
                .requiredDeposit(250000)
                .build();

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/auctions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }
}
