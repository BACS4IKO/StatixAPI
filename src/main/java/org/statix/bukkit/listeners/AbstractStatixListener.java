package org.statix.bukkit.listeners;

import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public abstract class AbstractStatixListener implements Listener {

    @Getter
    private final Plugin plugin;

    /**
     * При вызове этого класса, он автоматически регистрируется
     * как листенер в ядре.
     */
    public AbstractStatixListener(Plugin plugin) {
        this.plugin = plugin;

        if (!plugin.isEnabled()) {
            return;
        }

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

}
