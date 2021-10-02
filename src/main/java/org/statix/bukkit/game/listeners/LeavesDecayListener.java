package org.statix.bukkit.game.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.statix.bukkit.StatixAPI;
import org.statix.bukkit.game.GameAPI;

public class LeavesDecayListener implements Listener {

    @EventHandler
    public void onDecay(LeavesDecayEvent event) {
        GameAPI gameAPI = StatixAPI.getGameAPI();

        event.setCancelled(!gameAPI.getGameSettings().LEAVES_DECAY);
    }

}
