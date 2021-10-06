package org.statix.bukkit.holographic;

import lombok.NonNull;
import org.bukkit.entity.Player;

import java.util.Set;

public interface ProtocolHolographicSpawnable {

    boolean hasReceiver(@NonNull Player player);

    void addReceivers(@NonNull Player... receivers);

    void removeReceivers(@NonNull Player... receivers);


    boolean hasViewer(@NonNull Player player);

    void addViewers(@NonNull Player... viewers);

    void removeViewers(@NonNull Player... viewers);


    void spawn();

    void remove();

    void update();
}
