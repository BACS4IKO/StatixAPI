package ru.statix.api.bukkit.game.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import ru.statix.api.bukkit.StatixAPI;
import ru.statix.api.bukkit.game.factory.AbstractGameFactory;
import ru.statix.api.bukkit.game.player.GamePlayer;

/**
 * @Author ItzStonlex.
 * @VK https://vk.com/itzstonlex
 * <p>
 * (Created on 14.08.2019 8:44)
 */
public class PlayerDeathListener implements Listener {

    /**
     * Данный листенер не предусматривает смерть наблюдателя,
     * это должен делать сам разработчик, так как каждый это
     * может реализовать по своему.
     */

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        GamePlayer gamePlayer = StatixAPI.getGameAPI().getGamePlayer(event.getEntity());

        if (gamePlayer.isSpectator()) {
            return;
        }

        AbstractGameFactory gameFactory = StatixAPI.getGameAPI().getGameFactory();

        gameFactory.onDeath(gamePlayer.getPlayer());
    }

}
