package ru.statix.api.bukkit;

import com.comphenix.protocol.ProtocolLibrary;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import ru.statix.api.bukkit.commands.CommandManager;
import ru.statix.api.bukkit.hologram.HologramManager;
import ru.statix.api.bukkit.listeners.BungeeMessageListener;
import ru.statix.api.bukkit.listeners.PlayerListener;
import ru.statix.api.bukkit.messaging.MessagingManager;
import ru.statix.api.bukkit.modules.protocol.entity.listeners.FakeEntityClickListener;
import ru.statix.api.test.TestCommand;
import ru.statix.api.bukkit.board.manager.SidebarManager;
import ru.statix.api.bukkit.event.EventRegisterManager;
import ru.statix.api.bukkit.game.GameAPI;
import ru.statix.api.bukkit.menus.listener.InventoryListener;
import ru.statix.api.bukkit.menus.manager.InventoryManager;
import ru.statix.api.bukkit.modules.vault.VaultManager;
import ru.statix.api.bukkit.tag.manager.TagManager;

public final class StatixAPI extends JavaPlugin {

    public static final String PLUGIN_MESSAGE_CHANNEL = "StatixAPI";

    @Getter
    private static final CommandManager commandManager = new CommandManager();

    @Getter
    private static final HologramManager hologramManager = new HologramManager();

    @Getter
    private static final InventoryManager inventoryManager = new InventoryManager();

    @Getter
    private static final GameAPI gameAPI = new GameAPI();

    @Getter
    private static final EventRegisterManager eventManager = new EventRegisterManager();

    @Getter
    private static final SidebarManager sidebarManager = new SidebarManager();

    @Getter
    private static final TagManager tagManager = new TagManager();

    @Getter
    private static MessagingManager messagingManager = null;

    @Getter
    private static VaultManager vaultManager = null;

    @Override
    public void onEnable() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "StatixAPI");
        getServer().getMessenger().registerIncomingPluginChannel(this, "StatixAPI", new BungeeMessageListener());

        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        ProtocolLibrary.getProtocolManager().addPacketListener(new FakeEntityClickListener(this));

        messagingManager   = new MessagingManager(this);
        vaultManager       = new VaultManager();

        commandManager.registerCommand(this, new TestCommand(), "test", "тест");
    }

}
