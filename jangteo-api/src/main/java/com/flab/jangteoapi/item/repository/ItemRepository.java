package com.flab.jangteoapi.item.repository;

import com.flab.jangteoapi.item.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ItemRepository {

    private final Map<UUID, Item> itemDB = new HashMap<>();


    public Item save(Item item) {

        itemDB.put(item.getItemId(), item);

        return itemDB.get(item.getItemId());
    }

    public Optional<Item> findById(UUID itemId) {

        return Optional.ofNullable(itemDB.get(itemId));
    }

    public Boolean existsById(UUID itemId) {

        return itemDB.containsKey(itemId);
    }

    public void deleteById(UUID itemId) {

        itemDB.remove(itemId);
    }
}
