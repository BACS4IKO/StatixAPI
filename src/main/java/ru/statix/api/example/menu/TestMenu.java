package ru.statix.api.example.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import ru.statix.api.bukkit.inventory.impl.BaseSimpleInventory;
import ru.statix.api.bukkit.utility.ItemUtil;

public class TestMenu
        extends BaseSimpleInventory {

    public TestMenu() {
        super("Тестовый инвентарь", 5);
    }

    @Override
    public void drawInventory(Player player) {
        setClickItem(5, ItemUtil.newBuilder(Material.STONE)
                        .setName("§eБаклажан")
                        .build(),

                (player1, event) -> {

                    player.sendMessage("§eКлик прошел, закрываю инвентарь");
                    player.closeInventory();
                });

        setClickItem(6, ItemUtil.newBuilder(Material.CHEST)
                        .setName("§aОбновить инвентарь")
                        .build(),

                (player1, event) -> updateInventory(player));
    }

}
