package org.statix.bukkit.game.cage.manager;

import org.statix.bukkit.game.cage.GameCage;
import org.statix.bukkit.game.cage.impl.BlockCage;
import org.statix.bukkit.game.cage.impl.BorderCage;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ItzStonlex.
 * @VK https://vk.com/itzstonlex
 * <p>
 * (Created on 01.08.2019 21:18)
 */
public final class CageManager {

    private final Map<Class<? extends GameCage>, GameCage> cageMap = new HashMap
            <Class<? extends GameCage>, GameCage>() {{

        put(BorderCage.class, new BorderCage());
        put(BlockCage.class, new BlockCage());
    }};


    /**
     * Получение объекта клетки по ее классу
     */
    public <T extends GameCage> T getCage(Class<T> cageClass) {
        return (T) cageMap.get(cageClass);
    }

}
