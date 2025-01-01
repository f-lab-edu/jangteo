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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemDeleteTest {

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
    @DisplayName("등록된 아이템을 정상적으로 삭제합니다.")
    public void shouldDeleteItemSuccessfully() throws Exception {

        mockMvc.perform(delete("/items/" + itemId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 아이템을 삭제 요청 시, 에러를 반환합니다.")
    public void shouldFailWhenDeletingNonExistentItem() throws Exception {

        UUID nonExistItemId = UUID.randomUUID();

        mockMvc.perform(delete("/items/" + nonExistItemId))
                .andExpect(status().isNotFound());
    }
}
