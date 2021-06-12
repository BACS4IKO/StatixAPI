package ru.statix.api.bukkit.messaging;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.statix.api.bukkit.StatixAPI;

@RequiredArgsConstructor
public final class MessagingManager {
    
    private final StatixAPI statixAPI;

    public void redirectPlayer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("Connect");
        out.writeUTF(server);

        player.sendPluginMessage(statixAPI, "BungeeCord", out.toByteArray());
    }

    public void sendMessage(String playerName, String message) {
        Player playerExact = Bukkit.getPlayerExact(playerName);

        if (playerExact != null && playerExact.isOnline()) {
            playerExact.sendMessage(message);
            return;
        }

        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("Message");
        out.writeUTF(playerName);
        out.writeUTF(message);

        statixAPI.getServer().sendPluginMessage(statixAPI, "BungeeCord", out.toByteArray());
    }

    public void kickProxyPlayer(String playerName, String reason) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("KickPlayer");
        out.writeUTF(playerName);
        out.writeUTF(ChatColor.translateAlternateColorCodes('&', reason));

        statixAPI.getServer().sendPluginMessage(statixAPI, "BungeeCord", out.toByteArray());
    }

    public ServerPing getServer(String address, int port) {
        return new ServerPing(address, port);
    }

}
