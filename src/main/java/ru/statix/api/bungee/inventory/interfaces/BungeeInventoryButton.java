package ru.statix.api.bungee.inventory.interfaces;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import ru.statix.api.bungee.inventory.item.BungeeItemStack;
import ru.statix.api.java.interfaces.Clickable;

public interface BungeeInventoryButton {

    Clickable<ProxiedPlayer> getClickCommand();
    BungeeItemStack getItemStack();
}
