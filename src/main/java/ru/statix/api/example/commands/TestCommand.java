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
        new TestMenu().openInventory(player);
    }

}
