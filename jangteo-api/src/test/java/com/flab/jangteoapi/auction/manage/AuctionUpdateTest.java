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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuctionUpdateTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("경매 정보를 성공적으로 수정합니다.")
    void shouldReturnOkWhenAuctionIsUpdatedSuccessfully() throws Exception {

        UUID auctionId = UUID.randomUUID();

        AuctionSaveRequest request = AuctionSaveRequest.builder()
                .auctionTitle("아이폰 판매 수정")
                .itemId(UUID.randomUUID())
                .itemName("Apple iPhone 13 Pro")
                .description("256GB, 상태 좋음")
                .category("electronics")
                .startTime(LocalDateTime.now().plusDays(1))
                .endTime(LocalDateTime.now().plusDays(2))
                .startingPrice(600000)
                .minBidIncrement(15000)
                .requiredDeposit(300000)
                .build();

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(put("/auctions/{auctionId}", auctionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("기존에 존재하지 않은 경매를 수정하려 하면 오류를 반환합니다.")
    void shouldReturnNotFoundWhenAuctionDoesNotExist() throws Exception {

        UUID nonExistentAuctionId = UUID.randomUUID();

        AuctionSaveRequest request = AuctionSaveRequest.builder()
                .auctionTitle("아이폰 판매 수정")
                .itemId(UUID.randomUUID())
                .itemName("Apple iPhone 13 Pro")
                .description("256GB, 상태 좋음")
                .category("electronics")
                .startTime(LocalDateTime.now().plusDays(1))
                .endTime(LocalDateTime.now().plusDays(2))
                .startingPrice(600000)
                .minBidIncrement(15000)
                .requiredDeposit(300000)
                .build();

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(put("/auctions/{auctionId}", nonExistentAuctionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("수정 요청의 입력 값이 잘못된 경우에는 오류를 반환합니다.")
    void shouldReturnBadRequestWhenRequestIsInvalid() throws Exception {

        UUID auctionId = UUID.randomUUID();
        AuctionSaveRequest request = AuctionSaveRequest.builder()
                .auctionTitle("")
                .itemId(null)
                .itemName("Apple iPhone 13 Pro")
                .description("256GB, 상태 좋음")
                .category("electronics")
                .startTime(LocalDateTime.now().plusDays(1))
                .endTime(LocalDateTime.now().plusDays(2))
                .startingPrice(-1)
                .minBidIncrement(0)
                .requiredDeposit(-100)
                .build();

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(put("/auctions/{auctionId}", auctionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }
}
