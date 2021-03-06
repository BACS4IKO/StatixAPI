package org.statix.bukkit.game.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GameStatus {

    WAITING_PLAYERS("§aОжидание..."),
    TIMER_STARTED("§eСтарт игры"),
    GAME_STARTED("§cИдет игра"),
    RESTART("§dПерезагрузка...");

    private final String motd;

}
