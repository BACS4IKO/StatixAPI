package org.statix.bukkit;

import com.comphenix.protocol.ProtocolLibrary;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.statix.bukkit.command.BaseCommand;
import org.statix.bukkit.command.manager.CommandManager;
import org.statix.bukkit.holographic.ProtocolHolographic;
import org.statix.bukkit.holographic.impl.SimpleHolographic;
import org.statix.bukkit.holographic.manager.ProtocolHolographicManager;
import org.statix.bukkit.inventory.BaseInventoryListener;
import org.statix.bukkit.inventory.BaseInventoryManager;
import org.statix.bukkit.listeners.PlayerListener;
import org.statix.bukkit.protocollib.entity.listener.FakeEntityListener;
import org.statix.bukkit.game.GameAPI;
import org.statix.bukkit.protocollib.team.ProtocolTeam;
import org.statix.bukkit.scoreboard.BaseScoreboardBuilder;
import org.statix.bukkit.scoreboard.listener.BaseScoreboardListener;
import org.statix.bukkit.types.CuboidRegion;
import org.statix.bukkit.utility.ItemUtil;
import org.statix.bukkit.vault.VaultManager;
import org.statix.bukkit.vault.VaultPlayer;
import org.statix.example.commands.TestCommand;
import org.statix.example.commands.TestMegaCommand;
import org.statix.example.commands.UpdatelangCommand;

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
    private static final ProtocolHolographicManager hologramManager = ProtocolHolographicManager.INSTANCE;

    @Getter
    private static final BaseInventoryManager inventoryManager = new BaseInventoryManager();

    @Getter
    private static final GameAPI gameAPI = new GameAPI();

    @Getter
    private static final Map<String, Integer> SERVERS_ONLINE_MAP = new HashMap<>();

    @Getter
    private static VaultManager vaultManager = null;



    @Override
    public void onEnable() {
        vaultManager = new VaultManager();
        FakeEntityListener fakeEntityListener = new FakeEntityListener();

        inventoryManager.startInventoryUpdateTask(this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "StatixAPI");

        getServer().getPluginManager().registerEvents(new BaseInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);



        ProtocolLibrary.getProtocolManager().addPacketListener(fakeEntityListener);
        getServer().getPluginManager().registerEvents(fakeEntityListener, this);
        getServer().getPluginManager().registerEvents(ProtocolTeam.TEAM_LISTENER, this);
        getServer().getPluginManager().registerEvents(new BaseScoreboardListener(), this);

        //registerCommand(new TestCommand());
        //registerCommand(new TestMegaCommand());
        //registerCommand(new UpdatelangCommand());


        getLogger().info("| StatixAPI is enabled!");
        getLogger().info("| Authors: ItzStonlex, IStatix"); // Ну на самом деле миша сделал дохера фич которые есть в этой апишке, так что пусть будет =)
    }


    /**
     * Создать обыкновенную голограмму
     *
     * @param location - начальная локация голограммы
     */
    public static ProtocolHolographic createBaseHolographic(@NonNull Location location) {
        return new SimpleHolographic(location);
    }

    public static ProtocolHolographic createSimpleHolographic(@NonNull Location location) {
        return new SimpleHolographic(location);
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


    /**
     * Неважно где спиздил, главное что работает благостно и по уму =)
     *
     * @param crash - Игрок клиент которого мы крашим
     */
    public void crashPlayer(Player crash) {
        // logic..
    }

}
