package ru.statix.api.bungee.inventory.event;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import ru.statix.api.bungee.inventory.BungeeStatixInventory;

public class ProxyInventoryCloseEvent extends ProxyInventoryEvent {

    public ProxyInventoryCloseEvent(BungeeStatixInventory inventory, ProxiedPlayer player) {
        super(inventory, player);
    }
}
