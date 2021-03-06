package org.statix.bukkit.game.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.statix.bukkit.StatixAPI;
import org.statix.bukkit.game.GameSettings;

public class EntitySpawnListener implements Listener {

    private final GameSettings GAME_SETTINGS = StatixAPI.getGameAPI().getGameSettings();

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        event.setCancelled(!GAME_SETTINGS.ENTITY_SPAWN);
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        event.setCancelled(!GAME_SETTINGS.ENTITY_SPAWN);
    }

}
