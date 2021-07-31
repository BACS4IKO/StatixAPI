package ru.statix.api.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import ru.statix.api.bukkit.StatixAPI;

public final class BungeeStatixAPI extends Plugin {
    /**
     * BungeeAPI будет написан заного, инвентари будут выводиться на пакетах с Proxy
     * уже в ближайшем будуйщем.
     */
    @Override
    public void onEnable() {
        getLogger().info("API is enabled");
    }

}
