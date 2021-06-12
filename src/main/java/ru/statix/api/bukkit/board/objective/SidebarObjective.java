package ru.statix.api.bukkit.board.objective;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import ru.statix.api.bukkit.board.StatixSidebar;
import ru.statix.api.bukkit.modules.protocol.packet.scoreboard.WrapperPlayServerScoreboardDisplayObjective;
import ru.statix.api.bukkit.modules.protocol.packet.scoreboard.WrapperPlayServerScoreboardObjective;

@Getter
@AllArgsConstructor
public class SidebarObjective {

    private static final int SIDEBAR = 1;

    private final String name;
    private String displayName;

    public void setDisplayName(String displayName, StatixSidebar sidebar) {
        this.displayName = displayName;

        WrapperPlayServerScoreboardObjective packet = getPacket();
        packet.setMode(WrapperPlayServerScoreboardObjective.Mode.UPDATE_VALUE);
        sidebar.broadcastPacket(packet);
    }

    public void create(Player player) {
        WrapperPlayServerScoreboardObjective packet = getPacket();
        packet.setMode(WrapperPlayServerScoreboardObjective.Mode.ADD_OBJECTIVE);

        packet.sendPacket(player);
    }

    public void remove(Player player) {
        WrapperPlayServerScoreboardObjective packet = getPacket();
        packet.setMode(WrapperPlayServerScoreboardObjective.Mode.REMOVE_OBJECTIVE);

        packet.sendPacket(player);
    }

    public void show(Player player) {
        WrapperPlayServerScoreboardDisplayObjective displayObjective = new WrapperPlayServerScoreboardDisplayObjective();
        displayObjective.setPosition(SIDEBAR);
        displayObjective.setScoreName(name);

        displayObjective.sendPacket(player);
    }

    private WrapperPlayServerScoreboardObjective getPacket() {
        WrapperPlayServerScoreboardObjective packet = new WrapperPlayServerScoreboardObjective();
        packet.setDisplayName(displayName);
        packet.setName(name);
        packet.setHealthDisplay(WrapperPlayServerScoreboardObjective.HealthDisplay.INTEGER);
        return packet;
    }
}