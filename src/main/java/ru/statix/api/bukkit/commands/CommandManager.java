package ru.statix.api.bukkit.commands;

import org.bukkit.plugin.Plugin;
import ru.statix.api.java.types.AbstractCacheManager;

import java.util.Arrays;

public final class CommandManager extends AbstractCacheManager<StatixCommand> {

    /**
     * Регистрация команды, добавление в org.bukkit.command.CommandMap
     */
    public void registerCommand(Plugin plugin, StatixCommand command, String... aliases) {
        CommandRegister.reg(plugin, (sender, command1, s, args) -> {
            try {
                command.execute(sender, args);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }, Arrays.asList(aliases));

        cacheData(aliases[0], command);
    }

    /**
     * Получение самой команды по ее имени
     */
    public StatixCommand getCommandByName(String commandName) {
        return getCache(commandName);
    }

}
