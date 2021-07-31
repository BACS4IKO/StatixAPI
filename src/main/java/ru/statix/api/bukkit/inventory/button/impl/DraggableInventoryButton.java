package ru.statix.api.bukkit.inventory.button.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;
import ru.statix.api.bukkit.inventory.button.BaseInventoryButton;
import ru.statix.api.bukkit.inventory.button.action.impl.DraggableButtonAction;

@Getter
@RequiredArgsConstructor
public class DraggableInventoryButton implements BaseInventoryButton {

    private final ItemStack itemStack;

    private final DraggableButtonAction buttonAction;

}
