package org.statix.bukkit.protocollib.entity;

// по моему это со старой стонлекс апи, ну ладно, хоть работает - и то хорошо =)
public interface FakeEntityMob extends FakeEntityLiving {

    void setAgressive(boolean agressive);

    void setNoAI(boolean noAI);

    boolean isAgressive();

    boolean isNoAI();
}
