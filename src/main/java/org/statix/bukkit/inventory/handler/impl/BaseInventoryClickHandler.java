package org.statix.bukkit.inventory.handler.impl;

import lombok.NonNull;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.statix.base.utility.WeakObjectCache;
import org.statix.bukkit.inventory.BaseInventory;
import org.statix.bukkit.inventory.handler.BaseInventoryHandler;

public interface BaseInventoryClickHandler extends BaseInventoryHandler {

    void onClick(@NonNull BaseInventory baseInventory, @NonNull InventoryClickEvent inventoryClickEvent);

    @Override
    default void handle(@NonNull BaseInventory baseInventory,
                        WeakObjectCache objectCache) {

        onClick(baseInventory, objectCache.getObject(InventoryClickEvent.class, "event"));
    }
}
