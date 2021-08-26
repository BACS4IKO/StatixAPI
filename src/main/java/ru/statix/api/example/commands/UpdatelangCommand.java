package ru.statix.api.example.commands;

import org.bukkit.entity.Player;
import ru.statix.api.base.localization.LocaleStorage;
import ru.statix.api.base.localization.Localization;
import ru.statix.api.bukkit.command.BaseCommand;
import ru.statix.api.bukkit.command.annotation.CommandPermission;

@CommandPermission(permission = "statixapi.langupdate")
public class UpdatelangCommand extends BaseCommand<Player> {

    public UpdatelangCommand() {
        super("langupdate", "localizationupdate", "updatelang");
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        LocaleStorage.updateLocales(); // Обновляем локализацию
        player.sendMessage(Localization.getString(0, "RELOADED_LANG"));
    }

}
