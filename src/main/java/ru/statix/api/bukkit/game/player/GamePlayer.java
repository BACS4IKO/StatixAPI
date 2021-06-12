package ru.statix.api.bukkit.game.player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.statix.api.bukkit.game.enums.GamePlayerType;

public interface GamePlayer {

    String getName();

    Player getPlayer();

    ItemStack getHead();

    GamePlayerType getType();

    double getMultiple();

    boolean isPlayer();

    boolean isSpectator();

    void setSpectator();

    void setMultiple(double multiple);

    void leave();

}
