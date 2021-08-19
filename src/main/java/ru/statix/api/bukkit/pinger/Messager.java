package ru.statix.api.bukkit.pinger;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.statix.api.bukkit.StatixAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Будем верить в то что этот класс окажется гораздо лучше чем предыдущая его версия,
 * хоть изменено было не так уж и много..
 */
public class Messager {
    public static void redirect(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(StatixAPI.getInstance(), "BungeeCord", out.toByteArray());
    }

    public static List<String> getOnlinePlayers(String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerList");
        out.writeUTF(server);
        Bukkit.getServer().sendPluginMessage(StatixAPI.getInstance(), "BungeeCord", out.toByteArray());
        String[] playerList = ByteStreams.newDataInput((byte[])out.toByteArray()).readUTF().split(", ");
        return new ArrayList(Arrays.asList(playerList));
    }

    public static void sendMessage(String player, String message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Message");
        out.writeUTF(player);
        out.writeUTF(ChatColor.translateAlternateColorCodes('&', message));
        Bukkit.getPlayerExact(player).sendPluginMessage(StatixAPI.getInstance(), "BungeeCord", out.toByteArray());
    }

    public static int getOnline(String server) {
        return getOnlinePlayers(server).size();
    }

    public static void kickPlayer(String player, String reason) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("KickPlayer");
        out.writeUTF(player);
        out.writeUTF(ChatColor.translateAlternateColorCodes('&', reason));
        Bukkit.getPlayerExact(player).sendPluginMessage(StatixAPI.getInstance(), "BungeeCord", out.toByteArray());
    }
}
