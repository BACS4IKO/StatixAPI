package ru.statix.api.bukkit.game.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import ru.statix.api.bukkit.StatixAPI;
import ru.statix.api.bukkit.game.GameSettings;

public class MoveListener implements Listener {

    private final GameSettings GAME_SETTINGS = StatixAPI.getGameAPI().getGameSettings();

    @EventHandler
    public void onEntitySpawn(PlayerMoveEvent event) {
        if (GAME_SETTINGS.PLAYER_MOVE) {
            return;
        }

        Player player = event.getPlayer();

        player.teleport(event.getFrom());
    }

}
