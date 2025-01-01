package com.flab.jangteoapi.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.jangteoapi.item.dto.ItemSaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemRegistrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("정상적으로 물품이 등록됩니다.")
    public void shouldReturnCreatedWhenItemIsRegistered() throws Exception {

        ItemSaveRequest request = ItemSaveRequest.builder()
                .itemName("게이밍 노트북")
                .description("gaming laptop")
                .category("Electronics")
                .imageUrl("http://example.com/image.jpg")
                .originalPrice(1500)
                .condition("New")
                .status("Available")
                .build();

        String payload = objectMapper.writeValueAsString(request);


        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("물품 이름이 누락될 경우, 에러를 리턴합니다.")
    public void shouldFailWhenItemNameIsMissing() throws Exception {

        ItemSaveRequest request = ItemSaveRequest.builder()
                .description("gaming laptop")
                .category("Electronics")
                .imageUrl("http://example.com/image.jpg")
                .originalPrice(1500)
                .condition("New")
                .status("Available")
                .build();

        String payload = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("물품 가격이 음수일 경우 에러를 리턴합니다.")
    public void shouldFailWhenOriginalPriceIsNegative() throws Exception {

        ItemSaveRequest request = ItemSaveRequest.builder()
                .itemName("게이밍 노트북")
                .description("Gaming laptop")
                .category("Electronics")
                .imageUrl("http://example.com/image.jpg")
                .originalPrice(-100)
                .condition("New")
                .status("Available")
                .build();

        String payload = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest());
    }
}
