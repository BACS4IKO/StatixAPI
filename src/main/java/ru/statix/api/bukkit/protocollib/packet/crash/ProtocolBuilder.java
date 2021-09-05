package ru.statix.api.bukkit.protocollib.packet.crash;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.Bukkit;

public class ProtocolBuilder {
    private Protocol currentVersion;
    private static ProtocolBuilder builder = new ProtocolBuilder();

    private ProtocolBuilder() {
        String v = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        String str = v.substring(0, v.lastIndexOf(95));

        try {
            String name = Protocol.class.getPackage().getName() + ".versions." + str;
            Class<? extends Protocol> clazz = (Class<? extends Protocol>) Class.forName(name);
            Constructor<? extends Protocol> constr = clazz.getConstructor();
            this.currentVersion = (Protocol)constr.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException var6) {
            this.currentVersion = new Default();
        }

    }

    public static Protocol getInstance() {
        return builder.currentVersion;
    }
}