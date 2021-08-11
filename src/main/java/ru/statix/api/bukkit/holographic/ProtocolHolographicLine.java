package ru.statix.api.bukkit.holographic;

import org.bukkit.Location;
import ru.statix.api.bukkit.protocollib.entity.impl.FakeArmorStand;

public interface ProtocolHolographicLine extends ProtocolHolographicSpawnable {

    int getLineIndex();

    Location getLocation();

    ProtocolHolographic getHolographic();


    String getLineText();

    void setLineText(String lineText);


    void initialize();

    void update();

    void remove();


    void teleport(Location location);

    FakeArmorStand getFakeArmorStand();
}
