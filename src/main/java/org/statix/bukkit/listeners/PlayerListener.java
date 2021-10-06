package org.statix.bukkit.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.statix.bukkit.StatixAPI;
import org.statix.bukkit.event.EntityDamageByPlayerEvent;
import org.statix.bukkit.event.PlayerDamageByEntityEvent;
import org.statix.bukkit.event.PlayerDamageByPlayerEvent;
import org.statix.bukkit.event.PlayerDamageEvent;
import org.statix.bukkit.vault.VaultPlayer;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        VaultPlayer vaultPlayer = StatixAPI.getVaultManager().getVaultPlayer(player);
        //player.setDisplayName(vaultPlayer.getPrefix().concat(player.getName()));
        // объективно, зачем?
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity target = event.getEntity();

        if (damager instanceof Player && target instanceof Player) {
            PlayerDamageByPlayerEvent playerDamageByPlayerEvent = new PlayerDamageByPlayerEvent((Player) damager, (Player) target, event.getCause(), event.getDamage());
            Bukkit.getPluginManager().callEvent(playerDamageByPlayerEvent);

            event.setCancelled(playerDamageByPlayerEvent.isCancelled());
        }

        else if (damager instanceof Player) {
            EntityDamageByPlayerEvent entityDamageByPlayerEvent = new EntityDamageByPlayerEvent((Player) damager, target, event.getCause(), event.getDamage());
            Bukkit.getPluginManager().callEvent(entityDamageByPlayerEvent);

            event.setCancelled(entityDamageByPlayerEvent.isCancelled());
        }

        else if (target instanceof Player) {
            PlayerDamageByEntityEvent playerDamageByEntityEvent = new PlayerDamageByEntityEvent(damager, (Player) target, event.getCause(), event.getDamage());
            Bukkit.getPluginManager().callEvent(playerDamageByEntityEvent);

            event.setCancelled(playerDamageByEntityEvent.isCancelled());
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        EntityDamageEvent.DamageCause damageCause = event.getCause();

        if (entity instanceof Player) {
            PlayerDamageEvent playerDamageEvent = new PlayerDamageEvent(((Player) entity), damageCause, event.getDamage());
            Bukkit.getPluginManager().callEvent(playerDamageEvent);

            event.setCancelled(playerDamageEvent.isCancelled());
        }

    }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerClickToEntityEvent(PlayerInteractAtEntityEvent event) {
        //next..
    }

}
