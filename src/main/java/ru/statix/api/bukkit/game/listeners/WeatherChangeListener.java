package ru.statix.api.bukkit.game.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import ru.statix.api.bukkit.StatixAPI;
import ru.statix.api.bukkit.game.GameAPI;

public class WeatherChangeListener implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        GameAPI gameAPI = StatixAPI.getGameAPI();

        event.setCancelled(!gameAPI.getGameSettings().WEATHER_CHANGE);
    }

}
