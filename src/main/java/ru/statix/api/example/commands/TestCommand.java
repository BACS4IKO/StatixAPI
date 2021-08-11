package ru.statix.api.example.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.statix.api.base.localization.Localization;
import ru.statix.api.bukkit.command.BaseCommand;
import ru.statix.api.example.menu.TestMenu;

public class TestCommand extends BaseCommand<Player> {

    public TestCommand() {
        super("example", "testing");
    }


    /**
     * Команда для теста локализации и менюшки, как вы понимаете все работает прекрасно :)
     */
    @Override
    protected void onExecute(Player player, String[] args) {
        player.sendMessage(Localization.getString(0, "TEST_MSG"));
        new TestMenu().openInventory(player);
    }

}
