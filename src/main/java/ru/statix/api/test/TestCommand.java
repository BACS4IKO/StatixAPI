package ru.statix.api.test;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.statix.api.bukkit.StatixAPI;
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
        //Это я использовал для проверки работоспособности голограммы.
        //StatixAPI.getHologramManager().createHologram("testHologram", ((Player) sender).getLocation(), hologram -> {
        //    hologram.addLine("§6§lStatixAPI");
        //    hologram.addCleanLine();
        //    hologram.addLine("§7Уникальная разработка, StatixAPI");
        //    hologram.addLine("§7имеющая в себе билдер пакетных голограмм");
        //    hologram.addCleanLine(); //пустая строка
        //    hologram.addLine("§c§lx §cНажмите, чтобы удалить голограмму.");


        //    hologram.setClickAction(p -> { //Действие при клике на голограмму
        //        p.sendMessage("§cКлик по голограмме прошел, удаляем для вас голограмму З:"); //Отправитб сообщение игроку

        //        hologram.removeReceiver(p);
        //    });
        //    hologram.addReceiver(player); //Отправляем голграмму игроку
        //});
        new TestPagedMenu().openInventory(player);
    }

}
