package ru.statix.api.bukkit.menus.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.statix.api.java.interfaces.Clickable;

public interface InventoryButton {

    Clickable<Player> getCommand();
    ItemStack getItem();
}
