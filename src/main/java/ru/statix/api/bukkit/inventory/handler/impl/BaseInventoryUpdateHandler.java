package ru.statix.api.bukkit.inventory.handler.impl;

import lombok.NonNull;
import org.bukkit.entity.Player;
import ru.statix.api.bukkit.inventory.BaseInventory;
import ru.statix.api.bukkit.inventory.handler.BaseInventoryHandler;
import ru.statix.api.java.utility.WeakObjectCache;


public interface BaseInventoryUpdateHandler extends BaseInventoryHandler {

    void onUpdate(@NonNull BaseInventory baseInventory, @NonNull Player player);

    @Override
    default void handle(@NonNull BaseInventory baseInventory,
                        WeakObjectCache objectCache) {

        onUpdate(baseInventory, objectCache.getObject(Player.class, "player"));
    }
}
