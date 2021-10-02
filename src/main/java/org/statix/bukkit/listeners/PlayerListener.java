package org.statix.bukkit.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.statix.bukkit.StatixAPI;
import org.statix.bukkit.vault.VaultPlayer;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        VaultPlayer vaultPlayer = StatixAPI.getVaultManager().getVaultPlayer(player);

        player.setDisplayName(vaultPlayer.getPrefix().concat(player.getName()));
    }

}
