package ru.statix.api.bungee.listeners;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public abstract class BungeeAbstractListener implements Listener {

    @Getter
    private final Plugin plugin;

    /**
     * При вызове этого класса, он автоматически регистрируется
     * как листенер в ядре.
     */
    public BungeeAbstractListener(Plugin plugin) {
        this.plugin = plugin;

        plugin.getProxy().getPluginManager().registerListener(plugin, this);
    }

}
