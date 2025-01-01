package com.flab.jangteoapi.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ItemSaveRequest {

    private UUID itemId;

    @NotBlank
    private String itemName;

    private String description;

    @NotBlank
    private String category;

    private String imageUrl;

    @PositiveOrZero
    private Integer originalPrice;

    private String condition;

    private String status;
}
