package ru.statix.api.bukkit.inventory.handler.impl;

import lombok.NonNull;
import org.bukkit.entity.Player;
import ru.statix.api.bukkit.inventory.BaseInventory;
import ru.statix.api.bukkit.inventory.handler.BaseInventoryHandler;
import ru.statix.api.java.utility.WeakObjectCache;

public interface BaseInventoryDisplayableHandler extends BaseInventoryHandler {

    void onOpen(@NonNull Player player);
    void onClose(@NonNull Player player);

    @Override
    default void handle(@NonNull BaseInventory baseInventory,
                        WeakObjectCache objectCache) {

        Player player = objectCache.getObject(Player.class, "player");
        boolean isOpen = objectCache.getObject(boolean.class, "isOpen");

        if (isOpen) {
            onOpen(player);
        } else {
            onClose(player);
        }
    }
}
