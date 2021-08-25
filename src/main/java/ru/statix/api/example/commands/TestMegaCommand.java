package ru.statix.api.example.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.statix.api.bukkit.StatixAPI;
import ru.statix.api.bukkit.command.BaseMegaCommand;
import ru.statix.api.bukkit.holographic.ProtocolHolographic;
import ru.statix.api.example.menu.TestMenu;

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
        ProtocolHolographic hologram
                = StatixAPI.createSimpleHolographic(p.getLocation());
        Consumer<Player> playerConsumer = player -> { //player = игрок, который кликнул
            player.sendMessage("§cКлик по голограмме прошел, удаляем для вас голограмму З:"); //Отправитб сообщение игроку

            hologram.removeReceivers(player);
        };

        hologram.addClickLine("§6§lStatixAPI", playerConsumer);
        hologram.addEmptyLine();
        hologram.addClickLine("§7Уникальная разработка, StatixAPI", playerConsumer);
        hologram.addClickLine("§7которая создала для вас пакетую голограмму", playerConsumer);
        hologram.addEmptyLine(); //пустая строка
        hologram.addClickLine("§c§lx §cНажмите, чтобы удалить голограмму.", playerConsumer);



// Создание кликабельных голограмм

            hologram.addViewers(p);

        p.sendMessage("§6§lStatixAPI:: §fДля вас была создана голограмма :)");
    }

    @CommandArgument
    protected void gui(Player player, String[] args) {
        new TestMenu().openInventory(player);
        player.sendMessage("§6§lStatixAPI:: §fДля вас был открыт инвентарь :)");
    }

}
