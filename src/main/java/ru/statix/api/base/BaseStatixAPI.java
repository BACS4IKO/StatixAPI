package ru.statix.api.base;

import lombok.Getter;
import ru.statix.api.base.logger.StatixLogger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class BaseStatixAPI {

    @Getter
    private static final StatixLogger STATIX_LOGGER = new StatixLogger();


    private static final ExecutorService CACHED_POOL_THREAD = Executors.newCachedThreadPool();


    /**
     * Асинхронное выполнение команды
     */
    public static void async(Runnable command) {
        CACHED_POOL_THREAD.submit(command);
    }

}
