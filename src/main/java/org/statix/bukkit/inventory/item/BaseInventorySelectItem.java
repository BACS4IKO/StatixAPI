package org.statix.bukkit.inventory.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;
import org.statix.bukkit.inventory.BaseInventory;
import org.statix.bukkit.inventory.BaseInventoryItem;
import org.statix.bukkit.inventory.handler.impl.BaseInventoryClickHandler;

@AllArgsConstructor
@Getter
public class BaseInventorySelectItem implements BaseInventoryItem {

    @Setter
    private int slot;

    private final ItemStack itemStack;

    private final BaseInventoryClickHandler inventoryClickHandler;
    private final boolean enchanting;

    @Setter
    private boolean selected;

    @Override
    public void onDraw(@NonNull BaseInventory baseInventory) {
        // не важно
    }

}
