package ru.statix.api.test.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.statix.api.bukkit.inventory.impl.BasePaginatedInventory;
import ru.statix.api.bukkit.inventory.markup.BaseInventoryBlockMarkup;
import ru.statix.api.bukkit.utility.ItemUtil;
import ru.statix.api.java.interfaces.Clickable;

import java.util.*;

public class TestPagedMenu extends BasePaginatedInventory {

    public TestPagedMenu() {
        super(4, "Тестовое страничное меню");
    }

    @Override
    public void drawInventory(Player player) {
        setItemMarkup(new BaseInventoryBlockMarkup(inventoryRows));

        addItemToMarkup(new ItemStack(Material.STONE), (player1, event) -> player.closeInventory());
        addItemToMarkup(new ItemStack(Material.DIAMOND), (player1, event) -> player.closeInventory());
        addItemToMarkup(new ItemStack(Material.BANNER), (player1, event) -> player.closeInventory());
        addItemToMarkup(new ItemStack(Material.BARRIER), (player1, event) -> player.closeInventory());
        addItemToMarkup(new ItemStack(Material.CACTUS), (player1, event) -> player.closeInventory());

        addItem(5, ItemUtil.newBuilder(Material.SIGN)
                .setName("§aИнформация")
                .setLore("§7Страница: §e" + (currentPage + 1)).build());
    }

}
