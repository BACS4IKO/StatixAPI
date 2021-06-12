package ru.statix.api.bungee.inventory.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Event;
import ru.statix.api.bungee.inventory.BungeeStatixInventory;

@RequiredArgsConstructor
@Getter
public abstract class ProxyInventoryEvent extends Event {

    private final BungeeStatixInventory inventory;

    private final ProxiedPlayer player;

}
