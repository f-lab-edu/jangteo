package com.flab.jangteoapi.item.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ItemSaveRequest {

    private UUID itemId;
    private String itemName;
    private String description;
    private String category;
    private String imageUrl;
    private Integer originalPrice;
    private String condition;
    private String status;
}
