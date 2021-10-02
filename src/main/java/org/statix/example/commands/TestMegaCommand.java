package org.statix.example.commands;

import org.bukkit.entity.Player;
import org.statix.bukkit.StatixAPI;
import org.statix.bukkit.command.BaseMegaCommand;
import org.statix.bukkit.holographic.ProtocolHolographic;
import org.statix.example.menu.TestMenu;

import java.util.function.Consumer;

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
    protected void holo(Player p, String[] args) {
        ProtocolHolographic hologram = StatixAPI.createSimpleHolographic(p.getLocation());

        hologram.addTextLine("§6§lStatixAPI");
        hologram.addEmptyLine();
        hologram.addTextLine("§7Уникальная разработка, StatixAPI");
        hologram.addTextLine("§7которая создала для вас пакетую голограмму");
        hologram.addEmptyLine(); //пустая строка
        hologram.addTextLine("§c§lx §cНажмите, чтобы удалить голограмму.");
        hologram.setClickAction(player -> {
            player.sendMessage("§cКлик по голограмме прошел, удаляем для вас голограмму З:"); //Отправитб сообщение игроку
            hologram.removeReceivers(player);
        });
        hologram.addViewers(p);
        p.sendMessage("§6§lStatixAPI:: §fДля вас была создана голограмма :)");
    }

    @CommandArgument
    protected void gui(Player player, String[] args) {
        new TestMenu().openInventory(player);
        player.sendMessage("§6§lStatixAPI:: §fДля вас был открыт инвентарь :)");
    }

}
