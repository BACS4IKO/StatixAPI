package ru.statix.api.bukkit.inventory.addon;

import org.bukkit.inventory.ItemStack;

import ru.statix.api.base.utility.query.ResponseHandler;
import ru.statix.api.bukkit.inventory.BasePaginatedInventory;

public interface BasePaginatedInventorySorting {
   BasePaginatedInventory getInventory();

   BasePaginatedInventorySorting sortItem(ResponseHandler<Integer, ItemStack> var1);

   BasePaginatedInventorySorting reversed();

   void rebuildInventory();
}
