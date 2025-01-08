package com.flab.jangteoapi.item.repository;

import com.flab.jangteoapi.item.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ItemRepository {

    public Item save(Item item) {
        return item;
    }

    public Item findById(UUID itemId) {
        return new Item();
    }

    public Boolean existsById(UUID itemId) {
        return true;
    }

    public void deleteById(UUID itemId) {
    }
}
