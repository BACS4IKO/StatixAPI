package ru.statix.api.bukkit.board.manager;

import ru.statix.api.bukkit.board.StatixSidebar;
import ru.statix.api.bukkit.board.StatixSidebarBuilder;
import ru.statix.api.java.types.AbstractCacheManager;

public final class SidebarManager extends AbstractCacheManager<StatixSidebar> {

    /**
     * Вызов нового билдера
     */
    public StatixSidebarBuilder newBuilder() {
        return new StatixSidebarBuilder();
    }


    /**
     * Кеширование скорборда
     *
     * @param sidebarName - Кешированное имя скорборда.
     * @param statixSidebar - Скорборд, который нужно закешировать.
     */
    public void cacheSidebar(String sidebarName, StatixSidebar statixSidebar) {
        cacheData(sidebarName.toLowerCase(), statixSidebar);
    }

    /**
     * Получение скорборда из кеша
     *
     * @param sidebarName - Имя получаемого скорборда
     */
    public StatixSidebar getSidebar(String sidebarName) {
        return getCache(sidebarName.toLowerCase());
    }

}