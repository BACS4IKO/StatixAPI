package ru.statix.api.bukkit.game.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import ru.statix.api.bukkit.StatixAPI;
import ru.statix.api.bukkit.game.GameAPI;

public class LeavesDecayListener implements Listener {

    @EventHandler
    public void onDecay(LeavesDecayEvent event) {
        GameAPI gameAPI = StatixAPI.getGameAPI();

        event.setCancelled(!gameAPI.getGameSettings().LEAVES_DECAY);
    }

}
