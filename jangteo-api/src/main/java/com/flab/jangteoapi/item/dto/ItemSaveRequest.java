package com.flab.jangteoapi.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    // 삭제 플래그용 필드
    private Boolean deleteItemName;

    private Boolean deleteDescription;

    private Boolean deleteCategory;

    private Boolean deleteImageUrl;

    private Boolean deleteOriginalPrice;

    private Boolean deleteCondition;

    private Boolean deleteStatus;
}
