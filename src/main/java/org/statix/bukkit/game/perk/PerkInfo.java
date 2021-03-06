package org.statix.bukkit.game.perk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.statix.base.interfaces.Applicable;

@Getter
@Setter
@AllArgsConstructor
public final class PerkInfo {

    private ItemStack baseItem;

    private String permission;

    private int minLevel, maxLevel, price;

    private Applicable<Player> playerApplicable;

}
