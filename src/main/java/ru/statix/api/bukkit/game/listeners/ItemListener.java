package ru.statix.api.bukkit.game.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import ru.statix.api.bukkit.StatixAPI;
import ru.statix.api.bukkit.game.GameSettings;

public class ItemListener implements Listener {

    private final GameSettings GAME_SETTINGS = StatixAPI.getGameAPI().getGameSettings();

    @EventHandler
    public void onEntitySpawn(PlayerPickupItemEvent event) {
        event.setCancelled(!GAME_SETTINGS.PLAYER_PICKUP_ITEM);
    }

    @EventHandler
    public void onEntitySpawn(PlayerDropItemEvent event) {
        event.setCancelled(!GAME_SETTINGS.PLAYER_DROP_ITEM);
    }

}
