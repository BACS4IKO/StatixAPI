package ru.statix.api.bukkit;

import com.comphenix.protocol.ProtocolLibrary;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.statix.api.bukkit.command.BaseCommand;
import ru.statix.api.bukkit.command.manager.CommandManager;
import ru.statix.api.bukkit.holographic.ProtocolHolographic;
import ru.statix.api.bukkit.holographic.impl.MegaHolographic;
import ru.statix.api.bukkit.holographic.impl.SimpleHolographic;
import ru.statix.api.bukkit.holographic.manager.ProtocolHolographicManager;
import ru.statix.api.bukkit.inventory.listener.SimpleInventoryListener;
import ru.statix.api.bukkit.inventory.manager.BukkitInventoryManager;
import ru.statix.api.bukkit.listeners.PlayerListener;
import ru.statix.api.bukkit.protocollib.entity.listener.FakeEntityListener;
import ru.statix.api.bukkit.event.EventRegisterManager;
import ru.statix.api.bukkit.game.GameAPI;
import ru.statix.api.bukkit.protocollib.team.ProtocolTeam;
import ru.statix.api.bukkit.scoreboard.BaseScoreboardBuilder;
import ru.statix.api.bukkit.scoreboard.listener.BaseScoreboardListener;
import ru.statix.api.bukkit.types.CuboidRegion;
import ru.statix.api.bukkit.utility.ItemUtil;
import ru.statix.api.bukkit.vault.VaultManager;
import ru.statix.api.bukkit.vault.VaultPlayer;
import ru.statix.api.example.commands.TestCommand;
import ru.statix.api.example.commands.TestMegaCommand;

import java.util.HashMap;
import java.util.Map;

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
    private static final ProtocolHolographicManager hologramManager = (ProtocolHolographicManager.INSTANCE);

    @Getter
    private static final BukkitInventoryManager inventoryManager = BukkitInventoryManager.INSTANCE;

    @Getter
    private static final GameAPI gameAPI = new GameAPI();

    @Getter
    private static final EventRegisterManager eventManager = new EventRegisterManager();

    @Getter
    private static final Map<String, Integer> SERVERS_ONLINE_MAP = new HashMap<>();

    @Getter
    private static VaultManager vaultManager = null;



    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("Successful load config.yml file");

        inventoryManager.startInventoryUpdaters();
        getLogger().info("Successful start Inventory Update");

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "StatixAPI");
        getLogger().info("Successful register Outgoing Plugin Channels");

        getServer().getPluginManager().registerEvents(new SimpleInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getLogger().info("Successful register Listeners");

        vaultManager = new VaultManager();
        getLogger().info("Successful register Vault");

        FakeEntityListener fakeEntityListener = new FakeEntityListener();

        ProtocolLibrary.getProtocolManager().addPacketListener(fakeEntityListener);
        getServer().getPluginManager().registerEvents(fakeEntityListener, this);
        getLogger().info("Successful register FakeEntityListener");
        getServer().getPluginManager().registerEvents(ProtocolTeam.TEAM_LISTENER, this);
        getLogger().info("Successful register ProtocolTeamListener");
        getServer().getPluginManager().registerEvents(new BaseScoreboardListener(), this);
        getLogger().info("Successful register ScoreboardListener");

        //Наверное да..
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getLogger().info("Successful start cho-to tam =)");

        if (getConfig().getBoolean("StatixAPI.TestCommands")) {
            registerCommand(new TestCommand());
            registerCommand(new TestMegaCommand());
            getLogger().info("Successful start register TestCommands");
        }
    }




    /**
     * Получить онлайн сервера, имя которого
     * указано в аргументе
     *
     * @param serverName - имя сервера
     */
    public static int getServerOnline(@NonNull String serverName) {
        return SERVERS_ONLINE_MAP.getOrDefault(serverName.toLowerCase(), -1);
    }

    /**
     * Получить общую сумму онлайна всех
     * подключенных серверов по Bungee
     *
     * @return - Общий онлайн Bungee
     */
    public static int getGlobalOnline() {
        return getServerOnline("GLOBAL");
    }


    /**
     * Создать обыкновенную голограмму
     *
     * @param location - начальная локация голограммы
     */
    public static ProtocolHolographic createSimpleHolographic(@NonNull Location location) {
        return new SimpleHolographic(location);
    }
    /**
     * Создать обыкновенную голограмму
     *
     * @param location - начальная локация голограммы
     */
    public static ProtocolHolographic createMegaHolographic(@NonNull Location location) {
        return new MegaHolographic(location);
    }


    /**
     * Получить игрока с данными библиотеки Vault
     *
     * @param playerName - ник игрока
     */
    public static VaultPlayer getVaultPlayer(@NonNull String playerName) {
        return getVaultManager().getVaultPlayer(playerName);
    }

    /**
     * Получить игрока с данными библиотеки Vault
     *
     * @param player - онлайн игрок
     */
    public static VaultPlayer getVaultPlayer(@NonNull Player player) {
        return getVaultManager().getVaultPlayer(player);
    }


    /**
     * Создать кубоид блоков из двух по
     * двум начальным локациям
     *
     * @param location1 - начальная локация №1
     * @param location2 - начальная локация №2
     */
    public static CuboidRegion createCuboid(@NonNull Location location1, @NonNull Location location2) {
        return new CuboidRegion(location1, location2);
    }


    /**
     * Создание {@link ItemStack} по Builder паттерну
     *
     * @param material - начальный тип предмета
     */
    public static ItemUtil.ItemBuilder newItemBuilder(@NonNull Material material) {
        return ItemUtil.newBuilder(material);
    }

    /**
     * Создание {@link ItemStack} по Builder паттерну
     *
     * @param materialData - начальная дата предмета
     */
    public static ItemUtil.ItemBuilder newItemBuilder(@NonNull MaterialData materialData) {
        return ItemUtil.newBuilder(materialData);
    }

    /**
     * Создание {@link ItemStack} по Builder паттерну
     *
     * @param itemStack - готовый {@link ItemStack} на клонирование и переработку
     */
    public static ItemUtil.ItemBuilder newItemBuilder(@NonNull ItemStack itemStack) {
        return ItemUtil.newBuilder(itemStack);
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
    /**
     * Создание по Builder паттерну
     * с рандомным названием objective
     */
    public static BaseScoreboardBuilder newScoreboardBuilder() {
        return BaseScoreboardBuilder.newScoreboardBuilder();
    }

    /**
     * @param objectiveName - название scoreboard objective
     */
    public static BaseScoreboardBuilder newScoreboardBuilder(@NonNull String objectiveName) {
        return BaseScoreboardBuilder.newScoreboardBuilder(objectiveName);
    }

}
