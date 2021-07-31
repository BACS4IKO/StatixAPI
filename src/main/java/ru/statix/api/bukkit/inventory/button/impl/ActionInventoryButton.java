package ru.statix.api.bukkit.inventory.button.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;
import ru.statix.api.bukkit.inventory.button.BaseInventoryButton;
import ru.statix.api.bukkit.inventory.button.action.impl.ClickableButtonAction;

@RequiredArgsConstructor
@Getter
public class ActionInventoryButton implements BaseInventoryButton {

    private final ItemStack itemStack;

    private final ClickableButtonAction buttonAction;

}
