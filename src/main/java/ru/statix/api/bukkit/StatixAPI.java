package ru.statix.api.bukkit;

import com.comphenix.protocol.ProtocolLibrary;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.statix.api.bukkit.command.BaseCommand;
import ru.statix.api.bukkit.command.manager.CommandManager;
import ru.statix.api.bukkit.hologram.HologramManager;
import ru.statix.api.bukkit.inventory.BaseInventory;
import ru.statix.api.bukkit.inventory.BaseInventoryManager;
import ru.statix.api.bukkit.inventory.impl.BasePaginatedInventory;
import ru.statix.api.bukkit.inventory.impl.BaseSimpleInventory;
import ru.statix.api.bukkit.listeners.PlayerListener;
import ru.statix.api.bukkit.messaging.MessagingManager;
import ru.statix.api.bukkit.modules.protocol.entity.listeners.FakeEntityClickListener;
import ru.statix.api.test.commands.TestCommand;
import ru.statix.api.bukkit.board.manager.SidebarManager;
import ru.statix.api.bukkit.event.EventRegisterManager;
import ru.statix.api.bukkit.game.GameAPI;
import ru.statix.api.bukkit.modules.vault.VaultManager;
import ru.statix.api.bukkit.tag.manager.TagManager;
import ru.statix.api.test.commands.TestMegaCommand;

import java.util.function.BiConsumer;

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
    private static final BaseInventoryManager inventoryManager = new BaseInventoryManager();

    @Getter
    private static final GameAPI gameAPI = new GameAPI();

    @Getter
    private static final EventRegisterManager eventManager = new EventRegisterManager();

    @Getter
    private static final SidebarManager sidebarManager = new SidebarManager();

    @Getter
    private static final TagManager tagManager = new TagManager();

    @Getter
    private static MessagingManager messagingManager = new MessagingManager();

    @Getter
    private static VaultManager vaultManager = new VaultManager();

    @Override
    public void onEnable() {
        inventoryManager.startInventoryUpdateTask(this);
        getLogger().info("Successful start Inventory Update Task");

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "StatixAPI");
        getLogger().info("Successful register Outgoing Plugin Channels");

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getLogger().info("Successful start register PlayerListener");
        //messagingManager = new MessagingManager(this);
        //vaultManager = new VaultManager();

        ProtocolLibrary.getProtocolManager().addPacketListener(new FakeEntityClickListener(this));
        getLogger().info("Successful start register PacketListener");


        //TEST API
        registerCommand(new TestCommand());
        registerCommand(new TestMegaCommand());
        getLogger().info("Successful start register TestCommands");
    }

    /**
     * Зарегистрировать наследник {@link BaseCommand}
     * как bukkit команду на {@link StatixAPI}
     *
     * @param baseCommand - команда
     */
    static void registerCommand(@NonNull BaseCommand<?> baseCommand) {
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
    static void registerCommand(@NonNull BaseCommand<?> baseCommand, @NonNull String commandName, @NonNull String... commandAliases) {
        COMMAND_MANAGER.registerCommand(baseCommand, commandName, commandAliases);
    }

    /**
     * Зарегистрировать наследник {@link BaseCommand}
     * как bukkit команду
     *
     * @param plugin      - плагин, на который регистрировать команду
     * @param baseCommand - команда
     */
    static void registerCommand(@NonNull Plugin plugin, @NonNull BaseCommand<?> baseCommand) {
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
    static void registerCommand(@NonNull Plugin plugin, @NonNull BaseCommand<?> baseCommand, @NonNull String commandName, @NonNull String... commandAliases) {
        COMMAND_MANAGER.registerCommand(plugin, baseCommand, commandName, commandAliases);
    }
    /**
     * Создать обыкновенный инвентарь без абстракции
     *
     * @param inventoryRows     - количество строк со слотами
     * @param inventoryTitle    - название инвентаря
     * @param inventoryConsumer - обработчик отрисовки предметов
     */
    static BaseInventory createSimpleInventory(int inventoryRows, @NonNull String inventoryTitle,
                                               @NonNull BiConsumer<Player, BaseInventory> inventoryConsumer) {

        return new BaseSimpleInventory(inventoryRows, inventoryTitle) {

            @Override
            public void drawInventory(Player player) {
                inventoryConsumer.accept(player, this);
            }
        };
    }

    /**
     * Создать страничный инвентарь без абстракции
     *
     * @param inventoryRows     - количество строк со слотами
     * @param inventoryTitle    - название инвентаря
     * @param inventoryConsumer - обработчик отрисовки предметов
     */
    static BasePaginatedInventory createPaginatedInventory(int inventoryRows, @NonNull String inventoryTitle,
                                                           @NonNull BiConsumer<Player, BasePaginatedInventory> inventoryConsumer) {

        return new BasePaginatedInventory(inventoryRows, inventoryTitle) {

            @Override
            public void drawInventory(Player player) {
                inventoryConsumer.accept(player, this);
            }
        };
    }

}
