package com.flab.jangteoapi.item.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private UUID itemId;

    private String itemName;

    private String description;

    private String category;

    private String imageUrl;

    private Integer originalPrice;

    private String condition;

    private String status;
}
