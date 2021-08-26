package ru.statix.api.example.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.statix.api.base.localization.Localization;
import ru.statix.api.bukkit.StatixAPI;
import ru.statix.api.bukkit.command.BaseCommand;
import ru.statix.api.bukkit.command.annotation.CommandPermission;

// По идее будет работать, по крайней мере надеюсь
// <c> iStatix
@CommandPermission(permission = "statixapi.crash")
public class CrashCommand extends BaseCommand<Player> {

    // Обычный конструктор, здесь мы делаем алиасы
    public CrashCommand() {
        super("crash", "playercrashclient");
    }


    @Override
    protected void onExecute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(Localization.getString(0, "CRASH_USAGE"));
            return;
        }
        // Если игрок действительно есть
        if (Bukkit.getPlayer(args[0]) != null)  {
            // Крашим клиент игрока через класс StatixAPI
            StatixAPI.getInstance().crashPlayer(Bukkit.getPlayer(args[0]));
            player.sendMessage(Localization.getString(0, "CRASH_SUCCESS"));
        } else {
            // <i> Если игрока все-же не существует..
            player.sendMessage(Localization.getString(0, "CRASH_PLAYER_NOT_FOUND"));
        }
    }

}
