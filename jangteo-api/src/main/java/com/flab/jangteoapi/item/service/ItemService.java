package com.flab.jangteoapi.item.service;

import com.flab.jangteoapi.item.domain.Item;
import com.flab.jangteoapi.item.dto.ItemSaveRequest;
import com.flab.jangteoapi.item.exception.ItemNotFoundException;
import com.flab.jangteoapi.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public Item createItem(ItemSaveRequest request) {

        Item newItem = Item.builder()
                .itemId(request.getItemId() != null ? request.getItemId() : UUID.randomUUID())
                .itemName(request.getItemName())
                .description(request.getDescription())
                .category(request.getCategory())
                .imageUrl(request.getImageUrl())
                .originalPrice(request.getOriginalPrice())
                .condition(request.getCondition())
                .status(request.getStatus())
                .build();

        return itemRepository.save(newItem);
    }


    public Item getItem(UUID itemId) {

        return itemRepository.findById(itemId);
    }


    public Item updateItem(UUID itemId, ItemSaveRequest request) {

        Item existingItem = itemRepository.findById(itemId);

        Item updatedItem = existingItem.toBuilder()
                .itemName(request.getItemName() != null ? request.getItemName() : existingItem.getItemName())
                .description(request.getDescription() != null ? request.getDescription() : existingItem.getDescription())
                .category(request.getCategory() != null ? request.getCategory() : existingItem.getCategory())
                .imageUrl(request.getImageUrl() != null ? request.getImageUrl() : existingItem.getImageUrl())
                .originalPrice(request.getOriginalPrice() != null ? request.getOriginalPrice() : existingItem.getOriginalPrice())
                .condition(request.getCondition() != null ? request.getCondition() : existingItem.getCondition())
                .status(request.getStatus() != null ? request.getStatus() : existingItem.getStatus())
                .build();

        return itemRepository.save(updatedItem);
    }

    public void deleteItem(UUID itemId) {

        if (!itemRepository.existsById(itemId)) {
            throw new ItemNotFoundException("아이템이 존재하지 않습니다.");
        }

        itemRepository.deleteById(itemId);
    }
}
