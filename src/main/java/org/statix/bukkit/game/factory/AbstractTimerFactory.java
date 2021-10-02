package org.statix.bukkit.game.factory;

import lombok.Getter;
import org.bukkit.scheduler.BukkitRunnable;
import org.statix.bukkit.StatixAPI;
import org.statix.bukkit.game.GameAPI;
import org.statix.bukkit.game.GameSettings;

@Getter
public abstract class AbstractTimerFactory {

    private boolean timerWorked;

    private BukkitRunnable timerTask;


//---------------------------------------------------------------//
    protected final GameAPI GAME_API = StatixAPI.getGameAPI();

    protected final GameSettings GAME_SETTINGS = GAME_API.getGameSettings();
//---------------------------------------------------------------//


    /**
     * Вызывается, когда таймер в лобби тикает
     *
     * @param secondsLeft - Оставшееся время
     */
    public abstract void onTimerWork(int secondsLeft);


    /**
     * Запуск таймера в лобби
     */
    public void start() {
        this.timerWorked = true;

        this.timerTask = new BukkitRunnable() {

            int secondsLeft = StatixAPI.getGameAPI().getGameSettings().LOBBY_TIMER_START_SECONDS;

            @Override
            public void run() {
                AbstractGameFactory gameFactory = StatixAPI.getGameAPI().getGameFactory();
                GameSettings gameSettings = gameFactory.GAME_SETTINGS;

                secondsLeft--;

                /**
                 * Если время таймера истекло, то мы устанавливаем
                 * ему стандартное значение и запускаем игру
                 */
                if (secondsLeft <= 0) {
                    gameFactory.onStartGame();

                    secondsLeft = gameSettings.LOBBY_TIMER_START_SECONDS;

                    cancel();

                    return;
                }

                onTimerWork(secondsLeft);
            }
        };

        timerTask.runTaskTimer(StatixAPI.getPlugin(StatixAPI.class), 0, 20);
    }

    /**
     * Выключение таймера в лобби.
     */
    public void stop() {
        this.timerWorked = false;

        timerTask.cancel();
    }

}
