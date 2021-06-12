package ru.statix.api.test;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.statix.api.bukkit.commands.StatixCommand;
import ru.statix.api.test.menu.TestPagedMenu;

public class TestCommand implements StatixCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!senderIsPlayer(sender)) {
            return;
        }

        Player player = (Player) sender;
        player.sendMessage(ChatColor.GREEN + "Лол, это работает, открываю инвентарь :)");
        new TestPagedMenu().openInventory(player);
    }

}
