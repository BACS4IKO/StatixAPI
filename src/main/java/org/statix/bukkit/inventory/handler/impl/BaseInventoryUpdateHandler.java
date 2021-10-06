package org.statix.bukkit.inventory.handler.impl;

import lombok.NonNull;
import org.bukkit.entity.Player;
import org.statix.base.utility.WeakObjectCache;
import org.statix.bukkit.inventory.BaseInventory;
import org.statix.bukkit.inventory.handler.BaseInventoryHandler;

public interface BaseInventoryUpdateHandler extends BaseInventoryHandler {

    void onUpdate(@NonNull BaseInventory baseInventory, @NonNull Player player);

    @Override
    default void handle(@NonNull BaseInventory baseInventory,
                        WeakObjectCache objectCache) {

        onUpdate(baseInventory, objectCache.getObject(Player.class, "player"));
    }
}
