package ru.statix.api.bukkit.scoreboard;

import java.util.Collection;
/**
 * Это я взял из апишки одного очень хорошего человека
 * @ItzStonlex
 */
public interface ScoreboardDisplayAnimation {

    Collection<String> getDisplayAnimation();

    String getCurrentDisplay();

    void nextDisplay();
}
