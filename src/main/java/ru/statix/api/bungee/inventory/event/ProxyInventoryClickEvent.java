package ru.statix.api.bungee.inventory.event;

import lombok.Getter;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import ru.statix.api.bungee.inventory.BungeeStatixInventory;
import ru.statix.api.bungee.inventory.item.BungeeItemStack;

@Getter
public class ProxyInventoryClickEvent extends ProxyInventoryEvent {

    private final int slot;

    private final BungeeItemStack currentItem;


    public ProxyInventoryClickEvent(BungeeStatixInventory inventory, ProxiedPlayer player,
                                    int slot, BungeeItemStack currentItem) {
        super(inventory, player);

        this.slot = slot;
        this.currentItem = currentItem;
    }

}
