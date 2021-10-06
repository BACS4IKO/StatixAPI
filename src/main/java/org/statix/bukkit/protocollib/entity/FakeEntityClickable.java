package org.statix.bukkit.protocollib.entity;

import lombok.NonNull;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

// по моему это со старой стонлекс апи, ну ладно, хоть работает - и то хорошо =)

public interface FakeEntityClickable {

    void setClickAction(@NonNull Consumer<Player> playerConsumer);
}
