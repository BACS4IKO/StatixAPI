package ru.statix.api.example.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.statix.api.bukkit.command.BaseCommand;
import ru.statix.api.example.menu.TestMenu;

public class TestCommand extends BaseCommand<Player> {

    public TestCommand() {
        super("example", "testing");
    }


    @Override
    protected void onExecute(Player player, String[] args) {
        player.sendMessage(ChatColor.GREEN + "Лол, это работает, открываю инвентарь :)");

        new TestMenu().openInventory(player);
    }

}
