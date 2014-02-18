package com.example.repository;

import com.example.models.FridgeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public final class FridgeRepository {

    private static FridgeRepository repository;
    private final ConcurrentHashMap<String, FridgeItem> fridgeItems = new ConcurrentHashMap<>();

    private FridgeRepository() {}

    public static FridgeRepository fridge() {
        if (repository == null) repository = new FridgeRepository();
        return repository;
    }

    public synchronized void update(List<FridgeItem> items) {

        // Previous items are removed from the collection every time we update it.
        fridgeItems.clear();

        // Duplicated items are overridden
        for (FridgeItem item : items)
            fridgeItems.put(item.getItem(), item);
    }

    public synchronized List<FridgeItem> get() {
        return new ArrayList<>(fridgeItems.values());
    }

}
