package ru.statix.api.bukkit.pinger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerPinger {
    private String name;
    private String mode;
    private String address;
    private int port;
    private int pingVersion;
    private int protocolVersion;
    private String gameVersion;
    private String motd;
    private int playersOnline;
    private int maxPlayers;
    private boolean enabled;
    private static List<ServerPinger> servers = new ArrayList();

    public static List<ServerPinger> getServers() {
        return servers;
    }

    public ServerPinger(String mode, String name, String address, int port) {
        this.mode = mode;
        this.name = name;
        this.motd = "Offline";
        this.pingVersion = -1;
        this.protocolVersion = -1;
        this.playersOnline = -1;
        this.maxPlayers = -1;
        this.address = address;
        this.port = port;
        this.enabled = this.fetchData();
        if (!servers.contains(this)) {
            servers.add(this);
        }
    }

    public String getMode() {
        return this.mode;
    }

    public String getName() {
        return this.name;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public String getAddress() {
        return this.address;
    }

    public int getPort() {
        return this.port;
    }

    private void setPingVersion(int pingVersion) {
        this.pingVersion = pingVersion;
    }

    public int getPingVersion() {
        return this.pingVersion;
    }

    private void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public int getProtocolVersion() {
        return this.protocolVersion;
    }

    private void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public String getGameVersion() {
        return this.gameVersion;
    }

    private void setMotd(String motd) {
        this.motd = motd;
    }

    public String getMotd() {
        return this.motd;
    }

    private void setPlayersOnline(int playersOnline) {
        this.playersOnline = playersOnline;
    }

    public int getPlayersOnline() {
        return this.playersOnline;
    }

    private void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public boolean fetchData() {
        try {
            Socket sock = new Socket();
            sock.setSoTimeout(2);
            sock.connect(new InetSocketAddress(this.address, this.port), 2);
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            DataInputStream in = new DataInputStream(sock.getInputStream());
            out.write(254);
            StringBuilder str = new StringBuilder();

            int b;
            while((b = in.read()) != -1) {
                if (b != 0 && b > 16 && b != 255 && b != 23 && b != 24) {
                    str.append((char)b);
                }
            }

            String[] data = str.toString().split(String.valueOf('§'));
            this.setMotd(data[0]);
            this.setPlayersOnline(Integer.parseInt(data[1]));
            this.setMaxPlayers(Integer.parseInt(data[2]));
            sock.close();
            return true;
        } catch (IOException var7) {
            return false;
        }
    }
}
