package org.statix.bukkit.utility;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;
import java.util.Objects;

@UtilityClass
public class LocationUtil {

    /**
     * Преобразование локации в строку подобного вида:
     *  - 'world_name, x, y, z, yaw, pitch'
     */
    public String locationToString(Location location) {
        return String.format("%s, %s, %s, %s, %s, %s", location.getWorld().getName(),
                location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    /**
     * Преобразование строки с координатами в саму локацию
     */
    public Location stringToLocation(String locString) {
        String[] locData = locString.split(", ");

        World world = Bukkit.getWorld(locData[0]);

        Objects.requireNonNull(world, "world is null");

        return new Location(world, Double.parseDouble(locData[1]), Double.parseDouble(locData[2]), Double.parseDouble(locData[3]),
                Float.parseFloat(locData[4]), Float.parseFloat(locData[5]));
    }

    /**
     * Проверка дистанции локаций
     */
    public boolean isDistance(Location location1, Location location2, double distance) {
        return location1.distance(location2) <= distance;
    }

    public Location getCenter(List<Location> locations) {
        double x = 0;
        double y = 0;
        double z = 0;
        World world = locations.stream().findFirst().get().getWorld();

        float yaw = 0.0f;
        float pitch = 0.0f;

        for (Location location : locations) {
            x += location.getX();
            y += location.getY();
            z += location.getZ();
            yaw = location.getYaw();
            pitch = location.getPitch();
        }

        int size = locations.size();

        return new Location(world, x / size, y / size, z / size, yaw, pitch);
    }

}
