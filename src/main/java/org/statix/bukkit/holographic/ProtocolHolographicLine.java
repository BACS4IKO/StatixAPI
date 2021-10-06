package org.statix.bukkit.holographic;

import org.bukkit.Location;
import org.statix.bukkit.protocollib.entity.impl.FakeArmorStand;


public interface ProtocolHolographicLine extends ProtocolHolographicSpawnable {

    int getLineIndex();

    Location getLocation();

    ProtocolHolographic getHolographic();

    FakeArmorStand getFakeArmorStand();


    String getLineText();

    void setLineText(String lineText);


    void initialize();

    void update();

    void remove();


    void teleport(Location location);
}
