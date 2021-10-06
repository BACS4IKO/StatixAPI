package org.statix.example.commands;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.statix.bukkit.StatixAPI;
import org.statix.bukkit.command.BaseMegaCommand;
import org.statix.bukkit.holographic.ProtocolHolographicLine;
import org.statix.bukkit.holographic.impl.SimpleHolographic;
import org.statix.bukkit.protocollib.entity.impl.FakePlayer;
import org.statix.bukkit.utility.ItemUtil;
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

    @CommandArgument(aliases = "spawnnpc")
    protected void npc(Player p, String[] args) {
        FakePlayer npc = new FakePlayer("IStatix", p.getLocation());
        //npc.setAlwaysLooking(true, 5);
        npc.addReceivers(p);
    }
    @CommandArgument(aliases = "spawnholo")
    protected void holo(Player p, String[] args) {
        SimpleHolographic simpleHolographic = new SimpleHolographic(p.getLocation());
        simpleHolographic.addOriginalHolographicLine("LOLOL");
        simpleHolographic.addOriginalHolographicLine("ITZ A WORKING");
        simpleHolographic.addOriginalHolographicLine("A");
        simpleHolographic.addOriginalHolographicLine("LOLOL");
        for (ProtocolHolographicLine line : simpleHolographic.getHolographicLines()) {
            line.getFakeArmorStand().setClickAction(player -> {
                player.sendMessage("Воу, это работает, иди поспи =))");
            });
        }
        simpleHolographic.addReceivers(p);
        p.sendMessage("§6§lStatixAPI:: §fВам была заспавнена голограмма");


    }

    @CommandArgument
    protected void gui(Player player, String[] args) {
        new TestMenu().openInventory(player);
        player.sendMessage("§6§lStatixAPI:: §fДля вас был открыт инвентарь :)");
    }

}
