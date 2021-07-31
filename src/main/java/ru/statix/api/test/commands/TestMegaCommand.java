package ru.statix.api.test.commands;

import org.bukkit.entity.Player;
import ru.statix.api.bukkit.StatixAPI;
import ru.statix.api.bukkit.command.BaseMegaCommand;
import ru.statix.api.test.menu.TestMenu;
import ru.statix.api.test.menu.TestPagedMenu;

public class TestMegaCommand extends BaseMegaCommand<Player> {

    public TestMegaCommand() {
        super("megatest", "megatesting");
    }

    @Override
    protected void onUsage(Player player) {
        player.sendMessage("§6§lStatixAPI:: §fСписок доступных подкоманд:");
        player.sendMessage(" §e /megatest spawnholo");
        player.sendMessage(" §e /megatest gui");
    }

    @CommandArgument(aliases = "spawnholo")
    protected void holo(Player player, String[] args) {
        StatixAPI.getHologramManager().createHologram("testHologram", (player).getLocation(), hologram -> {
            hologram.addLine("§6§lStatixAPI");
            hologram.addCleanLine();
            hologram.addLine("§7Уникальная разработка, StatixAPI");
            hologram.addLine("§7которая создала для вас пакетую голограмму");
            hologram.addCleanLine(); //пустая строка
            hologram.addLine("§c§lx §cНажмите, чтобы удалить голограмму.");


            hologram.setClickAction(p -> { //Действие при клике на голограмму
                p.sendMessage("§cКлик по голограмме прошел, удаляем для вас голограмму З:"); //Отправитб сообщение игроку

                hologram.removeReceiver(p);
            });
            hologram.addReceiver(player); //Отправляем голграмму игроку
        });
        player.sendMessage("§6§lStatixAPI:: §fДля вас была создана голограмма :)");
    }

    @CommandArgument
    protected void gui(Player player, String[] args) {
        new TestMenu().openInventory(player);
        player.sendMessage("§6§lStatixAPI:: §fДля вас был открыт инвентарь :)");
    }

}
