package ru.statix.api.bukkit.board;

import lombok.NonNull;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.entity.Player;
import ru.statix.api.bukkit.board.objective.SidebarObjective;

import java.util.function.Consumer;

public class StatixSidebarBuilder {

    private final StatixSidebar sidebar;


    public StatixSidebarBuilder() {
        this.sidebar = new StatixSidebar();

        SidebarObjective sidebarObjective = new SidebarObjective(RandomStringUtils.randomAlphabetic(16), "§6§lMoonStudio");

        sidebar.setObjective(sidebarObjective);
    }


    public StatixSidebarBuilder setDisplayName(String displayName) {
        sidebar.setDisplayName(displayName);

        return this;
    }

    public StatixSidebarBuilder setLine(int index, String line) {
        sidebar.setLine(index, line);

        return this;
    }

    public StatixSidebarBuilder newUpdater(@NonNull Consumer<StatixSidebar> task, long delay) {
        sidebar.getUpdater().newTask(task, delay);

        return this;
    }

    public void showToPlayer(Player player) {
        StatixSidebar oldSidebar = StatixSidebar.getPlayerSidebarsMap().get(player.getName().toLowerCase());

        if (oldSidebar != null) {
            oldSidebar.hide();
        }

        sidebar.send(player);

        sidebar.getUpdater().start();
    }

}