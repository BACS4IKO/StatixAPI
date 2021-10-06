package org.statix.bukkit.holographic.impl;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.statix.bukkit.StatixAPI;
import org.statix.bukkit.holographic.ProtocolHolographic;
import org.statix.bukkit.holographic.ProtocolHolographicLine;
import org.statix.bukkit.holographic.ProtocolHolographicUpdater;
import org.statix.bukkit.holographic.line.*;
import org.statix.bukkit.protocollib.entity.FakeEntityScope;

import java.util.*;
import java.util.function.Consumer;

@Getter
public class SimpleHolographic implements ProtocolHolographic {

    private Location location;

    private ProtocolHolographicUpdater updater;

    private final List<ProtocolHolographicLine> lines = new LinkedList<>();

    private final Set<Player> receivers    = new LinkedHashSet<>();
    private final Set<Player> viewers      = new LinkedHashSet<>();


    public SimpleHolographic(Location location) {
        this.location = location;
    }


    @Override
    public ProtocolHolographicLine getLine(int lineIndex) {
        if (lineIndex >= lines.size())
            return null;

        return lines.get(lineIndex);
    }


    @Override
    public void setLine(int lineIndex, @NonNull ProtocolHolographicLine holographicLine) {
        if (lineIndex >= lines.size()) {
            addLine(holographicLine);
            return;
        }

        ProtocolHolographicLine oldLine = getLine(lineIndex);
        if (oldLine != null && oldLine.getClass().equals(holographicLine.getClass())) {
                oldLine.setLineText(holographicLine.getLineText());
                oldLine.update();
            return;
        }

        lines.set(lineIndex, holographicLine);

        holographicLine.initialize();

        if (oldLine != null) {

            Collection<Player> receiverCollection = oldLine.getFakeArmorStand().getReceiverCollection();
            oldLine.remove();

            if (oldLine.getFakeArmorStand().getEntityScope().equals(FakeEntityScope.PUBLIC)) {
                holographicLine.spawn();

            } else {

                holographicLine.addReceivers(receiverCollection.toArray(new Player[0]));
            }
        }
    }

    @Override
    public void setClickAction(Consumer<Player> action) {
        for (ProtocolHolographicLine line : lines){
            line.getFakeArmorStand().setClickAction(action);
        }
    }

    @Override
    public void setTextLine(int lineIndex, String holographicLine) {
        setLine(lineIndex, new SimpleHolographicLine(lineIndex, holographicLine, this));
    }



    @Override
    public void setClickLine(int lineIndex, String holographicLine, Consumer<Player> clickAction) {
        setLine(lineIndex, new ActionHolographicLine(lineIndex, holographicLine, this, clickAction));
    }

    @Override
    public void setHeadcLine(int lineIndex, String headTexture, boolean small) {
        setLine(lineIndex, new HeadHolographicLine(lineIndex, headTexture, small, this));
    }

    @Override
    public void setItemLine(int lineIndex, ItemStack itemStack) {
        setLine(lineIndex, new ItemHolographicLine(lineIndex, itemStack, this));
    }

    @Override
    public void setEmptyLine(int lineIndex) {
        setLine(lineIndex, new EmptyHolographicLine(lineIndex, this));
    }


    @Override
    public void addLine(@NonNull ProtocolHolographicLine holographicLine) {
        holographicLine.initialize();

        lines.add(holographicLine);

        if (isPublic) {
            holographicLine.spawn();

        } else {

            holographicLine.addReceivers(receivers.toArray(new Player[0]));
        }
    }

    @Override
    public void addTextLine(String holographicLine) {
        addLine(new SimpleHolographicLine(lines.size(), holographicLine, this));
    }


    @Override
    public void addClickLine(String holographicLine, Consumer<Player> clickAction) {
        addLine(new ActionHolographicLine(lines.size(), holographicLine, this, clickAction));
    }

    @Override
    public void addHeadLine(String headTexture, boolean small) {
        addLine(new HeadHolographicLine(lines.size(), headTexture, small, this));
    }

    @Override
    public void addItemLine(ItemStack itemStack) {
        addLine(new ItemHolographicLine(lines.size(), itemStack, this));
    }

    @Override
    public void addEmptyLine() {
        addLine(new EmptyHolographicLine(lines.size(), this));
    }


    @Override
    public boolean hasReceiver(@NonNull Player player) {
        return receivers.contains(player);
    }

    @Override
    public void addReceivers(@NonNull Player... players) {
        for (Player player : players) {
            StatixAPI.getHologramManager().addProtocolHolographic(player, this);
        }

        receivers.addAll(Arrays.asList(players));
        addViewers(players);

        for (ProtocolHolographicLine holographicLine : lines) {
            holographicLine.addReceivers(players);
        }
    }

    @Override
    public void removeReceivers(@NonNull Player... players) {
        for (Player player : players) {
            StatixAPI.getHologramManager().getPlayerHolographics().remove(player, this);
        }

        receivers.removeAll(Arrays.asList(players));
        removeViewers(players);

        for (ProtocolHolographicLine holographicLine : lines) {
            holographicLine.removeReceivers(players);
        }
    }

    @Override
    public boolean hasViewer(@NonNull Player player) {
        return viewers.contains(player);
    }

    @Override
    public void addViewers(@NonNull Player... players) {
        viewers.addAll(Arrays.asList(players));

        for (ProtocolHolographicLine holographicLine : lines) {
            holographicLine.addViewers(players);
        }
    }

    @Override
    public void removeViewers(@NonNull Player... players) {
        viewers.removeAll(Arrays.asList(players));

        for (ProtocolHolographicLine holographicLine : lines) {
            holographicLine.removeViewers(players);
        }
    }


    private boolean isPublic;

    @Override
    public void spawn() {
        isPublic = true;

        for (ProtocolHolographicLine holographicLine : lines)
            holographicLine.spawn();
    }

    @Override
    public void remove() {
        isPublic = false;

        for (ProtocolHolographicLine holographicLine : lines)
            holographicLine.remove();
    }

    @Override
    public void update() {
        for (ProtocolHolographicLine holographicLine : lines) {

            holographicLine.addReceivers(receivers.toArray(new Player[0]));
            holographicLine.addViewers(viewers.toArray(new Player[0]));

            holographicLine.update();
        }
    }


    @Override
    public void teleport(@NonNull Location location) {
        this.location = location;

        for (ProtocolHolographicLine holographicLine : lines) {
            holographicLine.teleport(location);
        }
    }

    @Override
    public void setUpdater(long updateTicks, @NonNull ProtocolHolographicUpdater holographicUpdater) {
        this.updater = holographicUpdater;

        holographicUpdater.setEnable(true);
        holographicUpdater.startUpdater(updateTicks);
    }

}
