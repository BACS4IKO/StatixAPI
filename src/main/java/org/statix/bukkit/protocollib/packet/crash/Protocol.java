package org.statix.bukkit.protocollib.packet.crash;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Protocol {
    default void execute(Player player, PacketContainer container) {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, container);
        } catch (InvocationTargetException var4) {
            var4.printStackTrace();
        }

    }

    void spawnEntity(Player var1, Location var2, int var3);

    void setPassenger(Player var1, int var2, int var3);

    void teleport(Player var1, int var2, Location var3);
}
