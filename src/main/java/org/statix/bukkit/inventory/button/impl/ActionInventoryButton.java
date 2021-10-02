package org.statix.bukkit.inventory.button.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;
import org.statix.bukkit.inventory.button.BaseInventoryButton;
import org.statix.bukkit.inventory.button.action.impl.ClickableButtonAction;

@RequiredArgsConstructor
@Getter
public class ActionInventoryButton implements BaseInventoryButton {

    private final ItemStack itemStack;

    private final ClickableButtonAction buttonAction;

}
