package org.statix.bukkit.inventory.button.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;
import org.statix.bukkit.inventory.button.BaseInventoryButton;
import org.statix.bukkit.inventory.button.action.impl.DraggableButtonAction;

@Getter
@RequiredArgsConstructor
public class DraggableInventoryButton implements BaseInventoryButton {

    private final ItemStack itemStack;

    private final DraggableButtonAction buttonAction;

}
