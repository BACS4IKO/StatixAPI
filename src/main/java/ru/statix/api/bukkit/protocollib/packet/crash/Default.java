package ru.statix.api.bukkit.protocollib.packet.crash;

import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Default implements Protocol {
    public void spawnEntity(Player player, final Location location, int id) {
        PacketContainer container = new PacketContainer(Server.SPAWN_ENTITY_LIVING) {
            {
                this.getModifier().writeDefaults();
                this.getIntegers().write(1, 120);
                this.getDoubles().write(0, location.getX());
                this.getDoubles().write(1, location.getY());
                this.getDoubles().write(2, location.getZ());
            }
        };
        container.getIntegers().write(0, id);
        this.execute(player, container);
    }

    public void setPassenger(Player player, int vehicleId, int passengerId) {
        PacketContainer container = new PacketContainer(Server.MOUNT);
        container.getIntegers().write(0, vehicleId);
        container.getIntegerArrays().write(0, new int[]{passengerId});
        this.execute(player, container);
    }

    public void teleport(Player player, int entityId, Location location) {
        PacketContainer container = new PacketContainer(Server.ENTITY_TELEPORT);
        container.getIntegers().write(0, entityId);
        container.getDoubles().write(0, location.getX());
        container.getDoubles().write(1, location.getY());
        container.getDoubles().write(2, location.getZ());
        this.execute(player, container);
    }
}