package org.statix.bukkit.holographic;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public interface ProtocolHolographic extends ProtocolHolographicSpawnable {

    Location getLocation();


    Set<Player> getViewers();

    Set<Player> getReceivers();


    List<ProtocolHolographicLine> getLines();

    ProtocolHolographicUpdater getUpdater();

    ProtocolHolographicLine getLine(int lineIndex);


    void setLine(int lineIndex, ProtocolHolographicLine holographicLine);

    void setClickAction(Consumer<Player> action);

    void setTextLine(int lineIndex, String holographicLine);


    void setClickLine(int lineIndex, String holographicLine, Consumer<Player> clickAction);

    void setHeadcLine(int lineIndex, String headTexture, boolean small);

    void setItemLine(int lineIndex, ItemStack itemStack);

    void setEmptyLine(int lineIndex);


    void addLine(ProtocolHolographicLine holographicLine);

    void addTextLine(String holographicLine);

    void addClickLine(String holographicLine, Consumer<Player> clickAction);

    void addHeadLine(String headTexture, boolean small);

    void addItemLine(ItemStack itemStack);

    void addEmptyLine();


    void teleport(Location location);


    void setUpdater(long updateTicks, ProtocolHolographicUpdater holographicUpdater);
}
