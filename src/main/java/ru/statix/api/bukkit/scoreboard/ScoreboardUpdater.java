package ru.statix.api.bukkit.scoreboard;

import lombok.NonNull;
import org.bukkit.entity.Player;
/**
 * Это я взял из апишки одного очень хорошего человека
 * @ItzStonlex
 */
public interface ScoreboardUpdater {

    void onUpdate(@NonNull BaseScoreboard baseScoreboard, @NonNull Player player);
}
