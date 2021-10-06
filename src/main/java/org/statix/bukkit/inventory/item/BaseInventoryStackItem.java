package org.statix.bukkit.inventory.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;
import org.statix.bukkit.inventory.BaseInventory;
import org.statix.bukkit.inventory.BaseInventoryItem;

@AllArgsConstructor
@Getter
public class BaseInventoryStackItem implements BaseInventoryItem {

    @Setter
    private int slot;

    private final ItemStack itemStack;

    @Override
    public void onDraw(@NonNull BaseInventory baseInventory) {
        // не важно
    }

}
