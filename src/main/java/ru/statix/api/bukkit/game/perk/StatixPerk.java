package ru.statix.api.bukkit.game.perk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Getter
public class StatixPerk {

    private final String perkName;

    @Setter
    private PerkInfo perkInfo;


    /**
     * Применить перк игроку
     */
    public void applyToPlayer(Player player) {
        perkInfo.getPlayerApplicable().apply(player);
    }

}