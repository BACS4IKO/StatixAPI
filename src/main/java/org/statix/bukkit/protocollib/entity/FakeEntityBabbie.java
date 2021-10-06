package org.statix.bukkit.protocollib.entity;

// по моему это со старой стонлекс апи, ну ладно, хоть работает - и то хорошо =)
public interface FakeEntityBabbie extends FakeEntityMob {

    boolean isBaby();

    void setBaby(boolean baby);
}
