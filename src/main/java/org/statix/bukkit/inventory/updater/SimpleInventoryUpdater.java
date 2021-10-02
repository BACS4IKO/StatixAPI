package org.statix.bukkit.inventory.updater;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.statix.bukkit.inventory.BaseInventory;
import org.statix.bukkit.inventory.addon.BaseInventoryUpdater;
import org.statix.bukkit.inventory.manager.BukkitInventoryManager;


@Getter
@Setter
@RequiredArgsConstructor
public abstract class SimpleInventoryUpdater implements BaseInventoryUpdater {

    private BaseInventory inventory;

    private final Player player;

    private boolean enable;
    private boolean cancelled;


    @Override
    public void startUpdater(long periodTicks) {
        this.cancelled = !cancelled;

        if (isCancelled()) {
            BukkitInventoryManager.INSTANCE.addInventoryUpdater(this, periodTicks);
        }
    }

    @Override
    public void cancelUpdater() {
        this.cancelled = !cancelled;

        if (!isCancelled()) {
            BukkitInventoryManager.INSTANCE.removeInventoryUpdater(this);
        }
    }

}
