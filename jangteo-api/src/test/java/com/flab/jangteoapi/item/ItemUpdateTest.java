package com.flab.jangteoapi.item;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemUpdateTest {

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
    @DisplayName("등록된 물품을 정상적으로 수정합니다.")
    public void shouldUpdateItemSuccessfully() throws Exception {

        ItemSaveRequest updateRequest = ItemSaveRequest.builder()
                .itemId(itemId)
                .itemName("게이밍 노트북 PRO")
                .description("Updated gaming laptop")
                .category("Electronics")
                .imageUrl("http://example.com/image.jpg")
                .originalPrice(200000)
                .condition("Used")
                .status("Sold")
                .build();

        mockMvc.perform(put("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("물품 이름이 누락될 경우, 수정이 실패합니다.")
    public void shouldFailWhenItemNameIsMissing() throws Exception {

        ItemSaveRequest invalidRequest = ItemSaveRequest.builder()
                .itemId(itemId)
                .itemName(null)
                .description("Updated gaming laptop")
                .category("Electronics")
                .imageUrl("http://example.com/image.jpg")
                .originalPrice(2000)
                .condition("Used")
                .status("Sold")
                .build();

        mockMvc.perform(put("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }
}
