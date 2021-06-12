package ru.statix.api.bukkit.game.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import ru.statix.api.bukkit.StatixAPI;
import ru.statix.api.bukkit.game.GameAPI;

public class GameStatusListener implements Listener {

    @EventHandler
    public void onServerPing(ServerListPingEvent event) {
        GameAPI gameAPI = StatixAPI.getGameAPI();

        event.setMotd(gameAPI.getGameSettings().GAME_STATUS.getMotd());
    }

}
