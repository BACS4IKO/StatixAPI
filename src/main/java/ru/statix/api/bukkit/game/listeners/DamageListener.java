package ru.statix.api.bukkit.game.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import ru.statix.api.bukkit.StatixAPI;
import ru.statix.api.bukkit.game.GameSettings;

public class DamageListener implements Listener {

    private final GameSettings GAME_SETTINGS = StatixAPI.getGameAPI().getGameSettings();

    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        if (event.getEntity() instanceof Player) {
            event.setCancelled(!GAME_SETTINGS.PLAYER_DAMAGE);
            return;
        }

        event.setCancelled(!GAME_SETTINGS.ENTITY_DAMAGE);
    }

}
