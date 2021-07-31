package ru.statix.api.bukkit.inventory.handler.impl;

import lombok.NonNull;
import org.bukkit.event.inventory.InventoryClickEvent;
import ru.statix.api.bukkit.inventory.BaseInventory;
import ru.statix.api.bukkit.inventory.handler.BaseInventoryHandler;
import ru.statix.api.java.utility.WeakObjectCache;


public interface BaseInventoryClickHandler extends BaseInventoryHandler {

    void onClick(@NonNull BaseInventory baseInventory, @NonNull InventoryClickEvent inventoryClickEvent);

    @Override
    default void handle(@NonNull BaseInventory baseInventory,
                        WeakObjectCache objectCache) {

        onClick(baseInventory, objectCache.getObject(InventoryClickEvent.class, "event"));
    }
}
