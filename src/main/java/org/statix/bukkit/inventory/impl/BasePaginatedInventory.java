package org.statix.bukkit.inventory.impl;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.NonNull;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.statix.base.utility.NumberUtil;
import org.statix.bukkit.inventory.addon.BasePaginatedInventorySorting;
import org.statix.bukkit.utility.ItemUtil;
import org.statix.bukkit.inventory.addon.BaseInventoryUpdater;
import org.statix.bukkit.inventory.button.BaseInventoryButton;
import org.statix.bukkit.inventory.button.action.impl.ClickableButtonAction;
import org.statix.bukkit.inventory.button.action.impl.DraggableButtonAction;
import org.statix.bukkit.inventory.button.impl.ActionInventoryButton;
import org.statix.bukkit.inventory.button.impl.DraggableInventoryButton;
import org.statix.bukkit.inventory.button.impl.SimpleInventoryButton;
import org.statix.bukkit.inventory.manager.BukkitInventoryManager;


public abstract class BasePaginatedInventory implements org.statix.bukkit.inventory.BasePaginatedInventory {
   protected String inventoryTitle;
   protected int inventoryRows;
   protected int inventorySize;
   protected int currentPage;
   private final TIntObjectMap<BaseInventoryButton> buttons = new TIntObjectHashMap();
   protected final List<Integer> pageSlots = new LinkedList();
   protected final List<BaseInventoryButton> pageButtons = new LinkedList();
   protected BaseInventoryUpdater inventoryUpdater;
   protected BasePaginatedInventorySorting inventorySort;
   protected Inventory bukkitInventory;

   public BasePaginatedInventory(String inventoryTitle, int inventoryRows) {
      this.inventoryTitle = inventoryTitle;
      this.inventoryRows = inventoryRows;
      this.inventorySize = inventoryRows * 9;
   }

   public void create(Player player, boolean inventoryInitialize) {
      if (inventoryInitialize) {
         this.bukkitInventory = Bukkit.createInventory(player, this.inventorySize, this.inventoryTitle + " | " + (this.currentPage + 1));
      }

      if (player != null) {
         this.buildPaginatedInventory(player);
         this.buttons.forEachEntry((buttonSlot, inventoryButton) -> {
            this.bukkitInventory.setItem(buttonSlot - 1, inventoryButton.getItemStack());
            return true;
         });
      }
   }

   public void openInventory(@NonNull Player player) {
      if (player == null) {
         throw new NullPointerException("player is marked non-null but is null");
      } else {
         player.closeInventory();
         this.create(player, true);
         BukkitInventoryManager.INSTANCE.addOpenInventoryToPlayer((Player)player, this);
         player.openInventory(this.getBukkitInventory());
      }
   }

   public void updateInventory(@NonNull Player player) {
      if (player == null) {
         throw new NullPointerException("player is marked non-null but is null");
      } else {
         this.create(player, false);
      }
   }

   public void clearInventory(@NonNull Player player) {
      if (player == null) {
         throw new NullPointerException("player is marked non-null but is null");
      } else {
         this.getButtons().clear();
         this.getBukkitInventory().clear();
      }
   }

   public void setInventoryTitle(@NonNull String inventoryTitle) {
      if (inventoryTitle == null) {
         throw new NullPointerException("inventoryTitle is marked non-null but is null");
      } else {
         Validate.isTrue(inventoryTitle.length() <= 32, "inventory title length cannot be > 32");
         this.inventoryTitle = inventoryTitle;
         this.create((Player)null, true);
      }
   }

   public void setInventoryRows(int inventoryRows) {
      Validate.isTrue(inventoryRows <= 6, "inventory rows length cannot be > 6");
      this.inventoryRows = inventoryRows;
      this.inventorySize = inventoryRows * 9;
      this.create((Player)null, true);
   }

   public void setInventorySize(int inventorySize) {
      Validate.isTrue(this.inventoryRows % 9 == 0, "Inventory must have a size that is a multiple of 9!");
      this.inventorySize = inventorySize;
      this.inventoryRows = inventorySize / 9;
      this.create((Player)null, true);
   }

   public void setItem(int buttonSlot, @NonNull BaseInventoryButton inventoryButton) {
      if (inventoryButton == null) {
         throw new NullPointerException("inventoryButton is marked non-null but is null");
      } else {
         this.buttons.put(buttonSlot, inventoryButton);
      }
   }

   public void setOriginalItem(int buttonSlot, ItemStack itemStack) {
      BaseInventoryButton inventoryButton = new  SimpleInventoryButton(itemStack);
      this.setItem(buttonSlot, inventoryButton);
   }

   public void addItem(int buttonSlot, ItemStack itemStack, ClickableButtonAction buttonAction) {
      BaseInventoryButton inventoryButton = new ActionInventoryButton(itemStack, buttonAction);
      this.setItem(buttonSlot, inventoryButton);
   }

   public void setDragItem(int buttonSlot, ItemStack itemStack, DraggableButtonAction buttonAction) {
      BaseInventoryButton inventoryButton = new DraggableInventoryButton(itemStack, buttonAction);
      this.setItem(buttonSlot, inventoryButton);
   }

   public void setInventoryUpdater(long updateTicks, @NonNull BaseInventoryUpdater inventoryUpdater) {
      if (inventoryUpdater == null) {
         throw new NullPointerException("inventoryUpdater is marked non-null but is null");
      } else {
         if (inventoryUpdater != null) {
            inventoryUpdater.cancelUpdater();
         }

         this.inventoryUpdater = inventoryUpdater;
         this.getInventoryUpdater().setEnable(true);
         this.getInventoryUpdater().startUpdater(updateTicks);
      }
   }

   public void setInventorySort(@NonNull BasePaginatedInventorySorting inventorySort) {
      if (inventorySort == null) {
         throw new NullPointerException("inventorySort is marked non-null but is null");
      } else {
         this.inventorySort = inventorySort;
         inventorySort.rebuildInventory();
      }
   }

   public void addItemToMarkup(@NonNull BaseInventoryButton inventoryButton) {
      if (inventoryButton == null) {
         throw new NullPointerException("inventoryButton is marked non-null but is null");
      } else {
         this.pageButtons.add(inventoryButton);
      }
   }

   public void addOriginalItemToMarkup(ItemStack itemStack) {
      BaseInventoryButton inventoryButton = new SimpleInventoryButton(itemStack);
      this.pageButtons.add(inventoryButton);
   }

   public void addClickItemToMarkup(ItemStack itemStack, ClickableButtonAction buttonAction) {
      BaseInventoryButton inventoryButton = new ActionInventoryButton(itemStack, buttonAction);
      this.pageButtons.add(inventoryButton);
   }

   public void addDragItemToMarkup(ItemStack itemStack, DraggableButtonAction buttonAction) {
      BaseInventoryButton inventoryButton = new DraggableInventoryButton(itemStack, buttonAction);
      this.pageButtons.add(inventoryButton);
   }

   public void setMarkupSlots(@NonNull Integer... slotArray) {
      if (slotArray == null) {
         throw new NullPointerException("slotArray is marked non-null but is null");
      } else {
         this.pageSlots.clear();
         Collections.addAll(this.pageSlots, slotArray);
      }
   }

   public void setMarkupSlots(List<Integer> slotList) {
      this.setMarkupSlots((Integer[])slotList.toArray(new Integer[0]));
   }

   public void addRowToMarkup(int rowIndex, int sideTab) {
      if (rowIndex <= 0) {
         throw new IllegalArgumentException("row index must be > 0");
      } else if (rowIndex >= 7) {
         throw new IllegalArgumentException("row index must be < 6");
      } else {
         int startSlotIndex = rowIndex * 9 - 8;
         int endSlotIndex = startSlotIndex + 9;
         if (sideTab < 0) {
            throw new IllegalArgumentException("side tab must be > 0");
         } else {
            startSlotIndex += sideTab;
            endSlotIndex -= sideTab;
            int[] var5 = NumberUtil.toManyArray(startSlotIndex, endSlotIndex);
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               int slotIndex = var5[var7];
               this.pageSlots.add(slotIndex);
            }

         }
      }
   }

   public void backwardPage(@NonNull Player player) {
      if (player == null) {
         throw new NullPointerException("player is marked non-null but is null");
      } else if (this.currentPage - 1 < 0) {
         throw new RuntimeException(String.format("Page cannot be < 0 (%s - 1 < 0)", this.currentPage));
      } else {
         --this.currentPage;
         this.create(player, true);
         this.openInventory(player);
      }
   }

   public void forwardPage(@NonNull Player player, int allPagesCount) {
      if (player == null) {
         throw new NullPointerException("player is marked non-null but is null");
      } else if (this.currentPage >= allPagesCount) {
         throw new RuntimeException(String.format("Page cannot be >= max pages count (%s >= %s)", this.currentPage, allPagesCount));
      } else {
         ++this.currentPage;
         this.create(player, true);
         this.openInventory(player);
      }
   }

   public void onOpen(Player player) {
   }

   public void onClose(Player player) {
   }

   private void buildPaginatedInventory(@NonNull Player player) {
      if (player == null) {
         throw new NullPointerException("player is marked non-null but is null");
      } else {
         this.pageSlots.clear();
         this.pageButtons.clear();
         this.clearInventory(player);
         this.drawInventory(player);
         if (this.pageSlots.isEmpty()) {
            throw new IllegalArgumentException("markup slots length == 0");
         } else {
            int allPagesCount = this.pageButtons.size() / this.pageSlots.size();
            if (this.currentPage < allPagesCount) {
               this.addItem(this.inventorySize - 3, ItemUtil.newBuilder(Material.ARROW).setName("§aСледующая страница").build(), (clickedPlayer, event) -> {
                  this.forwardPage(clickedPlayer, allPagesCount);
               });
            }

            if (this.currentPage - 1 >= 0) {
               this.addItem(this.inventorySize - 5, ItemUtil.newBuilder(Material.ARROW).setName("§aПредыдущая страница").build(), (clickedPlayer, event) -> {
                  this.backwardPage(clickedPlayer);
               });
            }

            for(int i = 0; i < this.pageSlots.size(); ++i) {
               int itemIndex = this.currentPage * this.pageSlots.size() + i;
               if (this.pageButtons.size() <= itemIndex) {
                  break;
               }

               int buttonSlot = (Integer)this.pageSlots.get(i);
               this.setItem(buttonSlot, (BaseInventoryButton)this.pageButtons.get(itemIndex));
            }

         }
      }
   }

   public String getInventoryTitle() {
      return this.inventoryTitle;
   }

   public int getInventoryRows() {
      return this.inventoryRows;
   }

   public int getInventorySize() {
      return this.inventorySize;
   }

   public int getCurrentPage() {
      return this.currentPage;
   }

   public TIntObjectMap<BaseInventoryButton> getButtons() {
      return this.buttons;
   }

   public List<Integer> getPageSlots() {
      return this.pageSlots;
   }

   public List<BaseInventoryButton> getPageButtons() {
      return this.pageButtons;
   }

   public BaseInventoryUpdater getInventoryUpdater() {
      return this.inventoryUpdater;
   }

   public BasePaginatedInventorySorting getInventorySort() {
      return this.inventorySort;
   }

   public Inventory getBukkitInventory() {
      return this.bukkitInventory;
   }
}
