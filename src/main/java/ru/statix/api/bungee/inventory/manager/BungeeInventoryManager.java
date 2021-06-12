package ru.statix.api.bungee.inventory.manager;

import ru.statix.api.bungee.inventory.BungeeStatixInventory;
import ru.statix.api.java.types.AbstractCacheManager;

public final class BungeeInventoryManager extends AbstractCacheManager<BungeeStatixInventory> {

    /**
     * Получение инвентаря из кеша
     */
    public BungeeStatixInventory getInventory(String inventoryName) {
        return getCache(inventoryName.toLowerCase());
    }

    /**
     * Занесение инвентаря в кеш
     */
    public void cacheInventory(String inventoryName, BungeeStatixInventory moonInventory) {
        cacheData(inventoryName.toLowerCase(), moonInventory);
    }

}
