package ru.statix.api.bukkit.inventory.handler;

import lombok.NonNull;
import ru.statix.api.bukkit.inventory.BaseInventory;
import ru.statix.api.java.utility.WeakObjectCache;


public interface BaseInventoryHandler {

    void handle(@NonNull BaseInventory baseInventory, WeakObjectCache objectCache);
}
