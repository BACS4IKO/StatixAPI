package org.statix.bungee;

import net.md_5.bungee.api.plugin.Plugin;

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
