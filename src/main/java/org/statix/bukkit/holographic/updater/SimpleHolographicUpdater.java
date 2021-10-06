package org.statix.bukkit.holographic.updater;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.scheduler.BukkitRunnable;
import org.statix.bukkit.StatixAPI;
import org.statix.bukkit.holographic.ProtocolHolographic;
import org.statix.bukkit.holographic.ProtocolHolographicUpdater;

import java.util.function.Consumer;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class SimpleHolographicUpdater extends BukkitRunnable
        implements ProtocolHolographicUpdater, Consumer<ProtocolHolographic> {

    private final ProtocolHolographic holographic;

    private boolean enable;

    @Override
    public void run() {
        if (holographic.getViewers().isEmpty()) {
            return;
        }

        accept(holographic);
    }

    @Override
    public void startUpdater(long periodTick) {
        runTaskTimerAsynchronously(StatixAPI.getInstance(), 0, periodTick);
    }

    @Override
    public void cancelUpdater() {
        cancel();
    }

}
