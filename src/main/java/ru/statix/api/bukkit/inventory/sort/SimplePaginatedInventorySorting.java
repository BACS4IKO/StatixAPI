package ru.statix.api.bukkit.inventory.sort;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.inventory.ItemStack;
import ru.statix.api.base.utility.query.ResponseHandler;
import ru.statix.api.bukkit.inventory.BasePaginatedInventory;
import ru.statix.api.bukkit.inventory.addon.BasePaginatedInventorySorting;
import ru.statix.api.bukkit.inventory.button.BaseInventoryButton;


public class SimplePaginatedInventorySorting implements BasePaginatedInventorySorting {
   private final BasePaginatedInventory inventory;
   private final List<BaseInventoryButton> sortedButtons = new LinkedList();
   private boolean reversed;

   public BasePaginatedInventorySorting sortItem(ResponseHandler<Integer, ItemStack> responseHandler) {
      this.sortedButtons.clear();
      this.sortedButtons.addAll((Collection)this.inventory.getPageButtons().stream().sorted(Comparator.comparing((inventoryButton) -> {
         return (Integer)responseHandler.handleResponse(inventoryButton.getItemStack());
      })).collect(Collectors.toList()));
      return this;
   }

   public BasePaginatedInventorySorting reversed() {
      this.reversed = !this.reversed;
      return this;
   }

   public void rebuildInventory() {
      this.inventory.getPageButtons().clear();
      if (this.reversed) {
         for(int i = this.sortedButtons.size(); i > 0; --i) {
            BaseInventoryButton inventoryButton = (BaseInventoryButton)this.sortedButtons.get(i - 1);
            if (inventoryButton != null) {
               this.inventory.getPageButtons().add(inventoryButton);
            }
         }

      } else {
         this.sortedButtons.forEach((inventoryButtonx) -> {
            this.inventory.getPageButtons().add(inventoryButtonx);
         });
      }
   }

   public SimplePaginatedInventorySorting(BasePaginatedInventory inventory) {
      this.inventory = inventory;
   }

   public BasePaginatedInventory getInventory() {
      return this.inventory;
   }

   public List<BaseInventoryButton> getSortedButtons() {
      return this.sortedButtons;
   }
}
