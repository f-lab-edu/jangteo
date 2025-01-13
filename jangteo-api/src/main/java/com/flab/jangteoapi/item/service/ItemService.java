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

        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("물품이 존재하지 않습니다."));
    }


    public Item updateItem(UUID itemId, ItemSaveRequest request) {

        Item existingItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("물품이 존재하지 않습니다."));

        Item updatedItem = Item.builder()
                .itemId(existingItem.getItemId())
                .itemName(resolveField(request.getItemName(), request.getDeleteItemName(), existingItem.getItemName()))
                .description(resolveField(request.getDescription(), request.getDeleteDescription(), existingItem.getDescription()))
                .category(resolveField(request.getCategory(), request.getDeleteCategory(), existingItem.getCategory()))
                .imageUrl(resolveField(request.getImageUrl(), request.getDeleteImageUrl(), existingItem.getImageUrl()))
                .originalPrice(resolveField(request.getOriginalPrice(), request.getDeleteOriginalPrice(), existingItem.getOriginalPrice()))
                .condition(resolveField(request.getCondition(), request.getDeleteCondition(), existingItem.getCondition()))
                .status(resolveField(request.getStatus(), request.getDeleteStatus(), existingItem.getStatus()))
                .build();

        return itemRepository.save(updatedItem);
    }

    public void deleteItem(UUID itemId) {

        try {
            itemRepository.deleteById(itemId);
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("물품이 존재하지 않습니다.");
        }
    }

    private <T> T resolveField(T newValue, Boolean deleteFlag, T existingValue) {

        if (deleteFlag) {
            return null;
        }

        if (newValue != null) {
            return newValue;
        } else {
            return existingValue;
        }
    }
}
