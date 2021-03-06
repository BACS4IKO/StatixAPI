package org.statix.bukkit.game.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.statix.bukkit.StatixAPI;
import org.statix.bukkit.game.GameAPI;

public class BlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        GameAPI gameAPI = StatixAPI.getGameAPI();

        event.setCancelled(!gameAPI.getGameSettings().BLOCK_BREAK);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        GameAPI gameAPI = StatixAPI.getGameAPI();

        event.setCancelled(!gameAPI.getGameSettings().BLOCK_BREAK);
    }

}
