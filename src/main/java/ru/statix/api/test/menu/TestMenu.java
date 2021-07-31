package ru.statix.api.test.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import ru.statix.api.bukkit.inventory.impl.BaseSimpleInventory;
import ru.statix.api.bukkit.utility.ItemUtil;

public class TestMenu
        extends BaseSimpleInventory {

    public TestMenu() {
        super(5, "Тестовый инвентарь");
    }

    @Override
    public void drawInventory(Player player) {
        addItem(5, ItemUtil.newBuilder(Material.STONE)
                        .setName("§eБаклажан")
                        .build(),

                (player1, event) -> {

                    player.sendMessage("§eКлик прошел, закрываю инвентарь");
                    player.closeInventory();
                });

        addItem(6, ItemUtil.newBuilder(Material.CHEST)
                        .setName("§aОбновить инвентарь")
                        .build(),

                (player1, event) -> updateInventory(player));
    }

}
