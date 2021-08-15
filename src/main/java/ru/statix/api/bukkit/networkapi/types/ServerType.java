package ru.statix.api.bukkit.networkapi.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
/**
 * Типы Bukkit-серверов, наверняка можно как-то использовать в связке с
 * кором/главным плагином
 *
 * P.S. > Если у вас есть идеи как это можно использовать пишите мне, обязательно реализую :)
 */
public enum ServerType {
    /**
     * Сервера лобби, как обычные, так и служебные (Limbo и Auth)
     */
    LOBBY,
    GAME_LOBBY,
    AUTH,
    LIMBO,
    /**
     * Сервера игровые
     */
    GAME, //Динамические игровые сервера, по типу EggWars Solo арены
    SOLITARY, //Выживательные/Другие режимы по типу Survival/Prizon/
    /**
     * Уникальные сервера, обычно создаються для строителей или ивентов
     */
    RECORD,
    DEV,
    BUILD
    ;
}
