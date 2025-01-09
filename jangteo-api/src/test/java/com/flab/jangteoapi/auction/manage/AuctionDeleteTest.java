package com.flab.jangteoapi.auction.manage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.jangteoapi.item.dto.ItemSaveRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuctionDeleteTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID itemId;

    @BeforeEach
    public void setup() throws Exception {

        itemId = UUID.randomUUID();

        ItemSaveRequest registerRequest = ItemSaveRequest.builder()
                .itemId(itemId)
                .itemName("게이밍 노트북")
                .description("Gaming laptop")
                .category("Electronics")
                .imageUrl("http://example.com/image.jpg")
                .originalPrice(1500)
                .condition("New")
                .status("Available")
                .build();

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("경매를 성공적으로 삭제합니다.")
    void shouldReturnNoContentWhenAuctionIsDeleted() throws Exception {

        UUID auctionId = UUID.randomUUID();

        mockMvc.perform(delete("/auctions/{auctionId}", auctionId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 경매를 삭제하려고 하면, 오류를 반환합니다.")
    void shouldReturnNotFoundWhenAuctionDoesNotExist() throws Exception {

        UUID nonExistentAuctionId = UUID.randomUUID();

        mockMvc.perform(delete("/auctions/{auctionId}", nonExistentAuctionId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
