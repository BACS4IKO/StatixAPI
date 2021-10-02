package org.statix.example.commands;

import org.bukkit.entity.Player;
import org.statix.base.localization.Localization;
import org.statix.bukkit.command.BaseCommand;
import org.statix.example.menu.TestMenu;

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
