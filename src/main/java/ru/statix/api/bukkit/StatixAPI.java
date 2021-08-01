package ru.statix.api.bukkit;

import com.comphenix.protocol.ProtocolLibrary;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.statix.api.bukkit.command.BaseCommand;
import ru.statix.api.bukkit.command.manager.CommandManager;
import ru.statix.api.bukkit.hologram.HologramManager;
import ru.statix.api.bukkit.inventory.listener.SimpleInventoryListener;
import ru.statix.api.bukkit.inventory.manager.BukkitInventoryManager;
import ru.statix.api.bukkit.listeners.PlayerListener;
import ru.statix.api.bukkit.messaging.MessagingManager;
import ru.statix.api.bukkit.protocollib.entity.listeners.FakeEntityClickListener;
import ru.statix.api.bukkit.board.manager.SidebarManager;
import ru.statix.api.bukkit.event.EventRegisterManager;
import ru.statix.api.bukkit.game.GameAPI;
import ru.statix.api.bukkit.vault.VaultManager;
import ru.statix.api.bukkit.tag.manager.TagManager;

public final class StatixAPI extends JavaPlugin {

    private static StatixAPI instance;

    public StatixAPI() {
        instance = this;
    }

    public static StatixAPI getInstance() {
        return instance;
    }

    public static final String PLUGIN_MESSAGE_CHANNEL = "StatixAPI";

    @Getter
    private static final CommandManager COMMAND_MANAGER = CommandManager.INSTANCE;

    @Getter
    private static final HologramManager hologramManager = new HologramManager();

    @Getter
    private static final BukkitInventoryManager inventoryManager = BukkitInventoryManager.INSTANCE;

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
        getLogger().info("Successful start Inventory Update Task");

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "StatixAPI");
        getLogger().info("Successful register Outgoing Plugin Channels");

        getServer().getPluginManager().registerEvents(new SimpleInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getLogger().info("Successful register Listeners");
        messagingManager = new MessagingManager();
        /**
         * @ItzStatix: в ближайшее время изменений в VaultManager не планируется,
         * ведь меня там все устраивает. Если вы хотите чтобы я что-то добавил - пишите в ВК.
         */
        vaultManager = new VaultManager();

        ProtocolLibrary.getProtocolManager().addPacketListener(new FakeEntityClickListener(this));
        getLogger().info("Successful register PacketListener");


        //TEST API
        //registerCommand(new TestCommand());
        //registerCommand(new TestMegaCommand());
        //getLogger().info("Successful start register TestCommands");
    }

    /**
     * Зарегистрировать наследник {@link BaseCommand}
     * как bukkit команду на {@link StatixAPI}
     *
     * @param baseCommand - команда
     */
    public static void registerCommand(@NonNull BaseCommand<?> baseCommand) {
        COMMAND_MANAGER.registerCommand(baseCommand, baseCommand.getName(), baseCommand.getAliases().toArray(new String[0]));
    }

    /**
     * Зарегистрировать наследник {@link BaseCommand}
     * как bukkit команду на {@link StatixAPI}
     *
     * @param baseCommand    - команда
     * @param commandName    - основная команда
     * @param commandAliases - дополнительные команды, обрабатывающие тот же класс (алиасы)
     */
    public static void registerCommand(@NonNull BaseCommand<?> baseCommand, @NonNull String commandName, @NonNull String... commandAliases) {
        COMMAND_MANAGER.registerCommand(baseCommand, commandName, commandAliases);
    }

    /**
     * Зарегистрировать наследник {@link BaseCommand}
     * как bukkit команду
     *
     * @param plugin      - плагин, на который регистрировать команду
     * @param baseCommand - команда
     */
    public static void registerCommand(@NonNull Plugin plugin, @NonNull BaseCommand<?> baseCommand) {
        COMMAND_MANAGER.registerCommand(plugin, baseCommand, baseCommand.getName(), baseCommand.getAliases().toArray(new String[0]));
    }

    /**
     * Зарегистрировать наследник {@link BaseCommand}
     * как bukkit команду
     *
     * @param plugin         - плагин, на который регистрировать команду
     * @param baseCommand    - команда
     * @param commandName    - основная команда
     * @param commandAliases - дополнительные команды, обрабатывающие тот же класс (алиасы)
     */
    public static void registerCommand(@NonNull Plugin plugin, @NonNull BaseCommand<?> baseCommand, @NonNull String commandName, @NonNull String... commandAliases) {
        COMMAND_MANAGER.registerCommand(plugin, baseCommand, commandName, commandAliases);
    }

}
