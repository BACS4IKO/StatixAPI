package ru.statix.api.bukkit.inventory.button.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;
import ru.statix.api.bukkit.inventory.button.BaseInventoryButton;

@RequiredArgsConstructor
@Getter
public class SimpleInventoryButton implements BaseInventoryButton {

    private final ItemStack itemStack;

    @Override
    public IInventoryButtonAction getButtonAction() {
        return null;
    }
}
