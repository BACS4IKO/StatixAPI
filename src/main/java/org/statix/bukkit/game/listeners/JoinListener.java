package org.statix.bukkit.game.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.statix.bukkit.StatixAPI;
import org.statix.bukkit.game.GameAPI;

public class JoinListener implements Listener {

    private final GameAPI gameAPI = StatixAPI.getGameAPI();

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        event.setResult(gameAPI.getGameSettings().PLAYER_JOIN
                ? PlayerPreLoginEvent.Result.ALLOWED
                : PlayerPreLoginEvent.Result.KICK_OTHER);

        event.setKickMessage("§cНа данный момент невозможно подключиться к арене!");
    }



//=======================================================================================//

    /**
     * @ItzStonlex
     *
     * Это просто нужно, чтобы game-api нормас работало
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        gameAPI.addPlayerInGame(player.getName());
    }

    /**
     * @ItzStonlex
     *
     * Это просто нужно, чтобы game-api нормас работало
     */
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        gameAPI.removePlayerInGame(player.getName());
    }

//=======================================================================================//

}
