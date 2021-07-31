package ru.statix.api.bukkit.inventory.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;
import ru.statix.api.bukkit.inventory.BaseInventory;
import ru.statix.api.bukkit.inventory.BaseInventoryItem;
import ru.statix.api.bukkit.inventory.handler.impl.BaseInventoryClickHandler;

@AllArgsConstructor
@Getter
public class BaseInventoryClickItem implements BaseInventoryItem {

    @Setter
    private int slot;

    private final ItemStack itemStack;
    private final BaseInventoryClickHandler inventoryClickHandler;

    @Override
    public void onDraw(@NonNull BaseInventory baseInventory) {
        // не важно
    }

}
