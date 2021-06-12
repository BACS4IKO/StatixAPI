package ru.statix.api.bukkit.menus.manager;

import org.bukkit.entity.Player;
import ru.statix.api.bukkit.menus.StatixBaseInventory;
import ru.statix.api.java.interfaces.Applicable;
import ru.statix.api.java.types.AbstractCacheManager;

public final class InventoryManager extends AbstractCacheManager<StatixBaseInventory> {

    /**
     * Создание инвентаря без использования абстракции.
     *
     * Все действия можно проводить через специальный для этого
     * Applicable, что указан в аргументах.
     */
    public void createInventory(String inventoryName, String title, int rows, Applicable<StatixBaseInventory> inventoryApplicable) {
        StatixBaseInventory inventory = new StatixBaseInventory(title, rows) {
            @Override
            public void generateInventory(Player player) {
                inventoryApplicable.apply(this);
            }
        };

        cacheInventory(inventoryName, inventory);
    }

    /**
     * Кеширование инвентаря в мапу по его имени.
     */
    public void cacheInventory(String inventoryName, StatixBaseInventory inventory) {
        cacheData(inventoryName.toLowerCase(), inventory);
    }

    /**
     * Получение инвентаря из кеша по его имени.
     */
    public StatixBaseInventory getCachedInventory(String inventoryName) {
        return getCache(inventoryName.toLowerCase());
    }

    /**
     * Получение имени инвентаря
     */
    public String getInventoryName(StatixBaseInventory inventory) {
        for (String cacheName : cacheMap.keySet()) {
            StatixBaseInventory cache = getCache(cacheName);

            if (cache.equals(inventory) || (cache.getInfo().getTitle().equals(inventory.getInfo().getTitle())
                    && cache.getInfo().getRows() == inventory.getInfo().getRows())) {
                return cacheName;
            }
        }
        return null;
    }

}
