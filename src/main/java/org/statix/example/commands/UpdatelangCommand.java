package org.statix.example.commands;

import org.bukkit.entity.Player;
import org.statix.base.localization.LocaleStorage;
import org.statix.base.localization.Localization;
import org.statix.bukkit.command.BaseCommand;
import org.statix.bukkit.command.annotation.CommandPermission;

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
