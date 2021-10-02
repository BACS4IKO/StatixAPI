package org.statix.bukkit.inventory.addon;

import org.bukkit.inventory.ItemStack;

import org.statix.base.utility.query.ResponseHandler;
import org.statix.bukkit.inventory.BasePaginatedInventory;

public interface BasePaginatedInventorySorting {
   BasePaginatedInventory getInventory();

   BasePaginatedInventorySorting sortItem(ResponseHandler<Integer, ItemStack> var1);

   BasePaginatedInventorySorting reversed();

   void rebuildInventory();
}
