package org.statix.bukkit.protocollib.team;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.statix.bukkit.StatixAPI;

import java.util.Collection;
import java.util.HashSet;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ProtocolTeamListener implements Listener {

    private final Collection<ProtocolTeam> teamsCollection = new HashSet<>();

    public void addTeam(@NonNull ProtocolTeam protocolTeam) {
        teamsCollection.add(protocolTeam);
    }

    public void removeTeam(@NonNull ProtocolTeam protocolTeam) {
        teamsCollection.remove(protocolTeam);
    }

    public boolean hasTeam(@NonNull ProtocolTeam protocolTeam) {
        return teamsCollection.contains(protocolTeam);
    }


    private void addTeamsReceive(@NonNull Player player) {
        for (ProtocolTeam protocolTeam : teamsCollection) {
            protocolTeam.addReceiver(player);
        }
    }

    private void removeTeamsReceive(@NonNull Player player) {
        for (ProtocolTeam protocolTeam : teamsCollection) {
            protocolTeam.removeReceiver(player);
        }
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {

        // Ну кста, .getInstance() тоже возвращает плагин, так что все норм =))
        Bukkit.getScheduler().runTaskLater(StatixAPI.getInstance(),
                () -> addTeamsReceive(event.getPlayer()), 20);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLeave(PlayerQuitEvent event) {

        removeTeamsReceive(event.getPlayer());
    }
}
