package ru.statix.api.bukkit.inventory;

import org.bukkit.entity.Player;

//todo: в разработке...
public interface BaseInventoryViewer {

    Player getViewPlayer();

    BaseInventory getInventory();
}
