package org.statix.bukkit.inventory;

import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.statix.bukkit.inventory.addon.BasePaginatedInventorySorting;
import org.statix.bukkit.inventory.button.BaseInventoryButton;
import org.statix.bukkit.inventory.button.action.impl.ClickableButtonAction;
import org.statix.bukkit.inventory.button.action.impl.DraggableButtonAction;

public interface BasePaginatedInventory extends BaseInventory {
   List<BaseInventoryButton> getPageButtons();

   BasePaginatedInventorySorting getInventorySort();

   void setInventorySort(BasePaginatedInventorySorting var1);

   void addItemToMarkup(BaseInventoryButton var1);

   void addOriginalItemToMarkup(ItemStack var1);

   void addClickItemToMarkup(ItemStack var1, ClickableButtonAction var2);

   void addDragItemToMarkup(ItemStack var1, DraggableButtonAction var2);

   void setMarkupSlots(Integer... var1);

   void setMarkupSlots(List<Integer> var1);

   void addRowToMarkup(int var1, int var2);

   void backwardPage(Player var1);

   void forwardPage(Player var1, int var2);
}
