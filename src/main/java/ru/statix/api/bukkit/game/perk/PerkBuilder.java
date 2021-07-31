package ru.statix.api.bukkit.game.perk;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.statix.api.bukkit.StatixAPI;
import ru.statix.api.base.interfaces.Applicable;
import ru.statix.api.base.interfaces.Builder;

@AllArgsConstructor
public final class PerkBuilder implements Builder<StatixPerk> {

    private String perkName;

    private PerkInfo perkInfo;


    /**
     * Установка имени перку
     */
    public PerkBuilder setPerkName(String perkName) {
        this.perkName = perkName;

        return this;
    }

    /**
     * Установка базового предмета перку
     */
    public PerkBuilder setBaseItem(ItemStack baseItem) {
        perkInfo.setBaseItem(baseItem);

        return this;
    }

    /**
     * Установка минимального уровня перка
     */
    public PerkBuilder setMinimumLevel(int minLevel) {
        perkInfo.setMinLevel(minLevel);

        return this;
    }

    /**
     * Установка максимального уровня перка
     */
    public PerkBuilder setMaximumLevel(int maxLevel) {
        perkInfo.setMaxLevel(maxLevel);

        return this;
    }

    /**
     * Установка права для перка
     */
    public PerkBuilder setPermission(String permission) {
        perkInfo.setPermission(permission);

        return this;
    }

    /**
     * Установка цены перка
     */
    public PerkBuilder setPrice(int price) {
        perkInfo.setPrice(price);

        return this;
    }

    /**
     * Установка действия применения перка игроку
     */
    public PerkBuilder setPlayerApplicable(Applicable<Player> playerApplicable) {
        perkInfo.setPlayerApplicable(playerApplicable);

        return this;
    }


    /**
     * Построение и создание перка
     */
    @Override
    public StatixPerk build() {
        StatixPerk statixPerk = new StatixPerk(perkName, perkInfo);

        StatixAPI.getGameAPI().getPerkManager()
                .cachePerk(perkName.toLowerCase(), statixPerk);

        return statixPerk;
    }

}
