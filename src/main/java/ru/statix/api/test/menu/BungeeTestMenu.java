package ru.statix.api.test.menu;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import ru.statix.api.bungee.inventory.BungeeStatixInventory;
import ru.statix.api.bungee.inventory.item.BungeeMaterial;
import ru.statix.api.bungee.utility.BungeeItemUtil;

public class BungeeTestMenu extends BungeeStatixInventory {

    public BungeeTestMenu() {
        super("test", "Тестовый инвентарь на BungeeCord", 3);
    }

    @Override
    public void createInventory(ProxiedPlayer player) {
        setItem(14, BungeeItemUtil.getItemStack(BungeeMaterial.WOOD, "§eТестовый предмет",
                "§7Тестовая строка 1",
                "§8Тестовая строка 2",
                "§9Тестовая строка 3"), player1 -> {
            player.sendMessage("ого, ты че, кликнул? ну все пока я закрываю тебе инвентарь лошара");

            closeInventory(player);
        });
    }

}
