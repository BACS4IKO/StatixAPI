package org.statix.bukkit.inventory.handler;

import lombok.NonNull;
import org.statix.base.utility.WeakObjectCache;
import org.statix.bukkit.inventory.BaseInventory;

public interface BaseInventoryHandler {

    void handle(@NonNull BaseInventory baseInventory, WeakObjectCache objectCache);
}
