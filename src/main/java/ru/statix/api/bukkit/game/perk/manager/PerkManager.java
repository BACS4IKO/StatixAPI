package ru.statix.api.bukkit.game.perk.manager;

import ru.statix.api.bukkit.game.perk.PerkBuilder;
import ru.statix.api.bukkit.game.perk.PerkInfo;
import ru.statix.api.bukkit.game.perk.StatixPerk;
import ru.statix.api.base.types.AbstractCacheManager;

public final class PerkManager extends AbstractCacheManager<StatixPerk> {

    /**
     * Кеширование перка в мапу по его имени.
     */
    public void cachePerk(String perkName, StatixPerk statixPerk) {
        cacheData(perkName.toLowerCase(), statixPerk);
    }

    /**
     * Получение перка из кеша по его имени.
     */
    public final StatixPerk getPerk(String perkName) {
        return getCache(perkName.toLowerCase());
    }


    /**
     * Создание нового Builder
     */
    public PerkBuilder newBuilder() {
        return newBuilder("moonapi");
    }

    /**
     * Создание нового Builder с именем перка
     */
    public PerkBuilder newBuilder(String perkName) {
        return new PerkBuilder(perkName, new PerkInfo(null, null, 0, 1, 0, null));
    }

}
