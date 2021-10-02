package org.statix.bukkit.game.kit.manager;

import org.statix.base.types.AbstractCacheManager;
import org.statix.bukkit.game.kit.KitBuilder;
import org.statix.bukkit.game.kit.StatixKit;
import org.statix.bukkit.game.kit.KitInfo;

public final class KitManager extends AbstractCacheManager<StatixKit> {

    /**
     * Кеширование набора в мапу по его имени.
     */
    public void cacheKit(String kitName, StatixKit statixKit) {
        cacheData(kitName.toLowerCase(), statixKit);
    }

    /**
     * Получение набора из кеша по его имени.
     */
    public final StatixKit getKit(String kitName) {
        return getCache(kitName.toLowerCase());
    }


    /**
     * Создание нового Builder
     */
    public KitBuilder newBuilder() {
        return newBuilder("moonapi");
    }

    /**
     * Создание нового Builder с именем набора
     */
    public KitBuilder newBuilder(String kitName) {
        return new KitBuilder(kitName, new KitInfo(null, null, null, null, null, 0, null));
    }

}
