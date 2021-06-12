package ru.statix.api.bukkit.modules.protocol.entity.impl;

import com.comphenix.protocol.wrappers.*;
import lombok.Getter;
import ru.statix.api.bukkit.modules.protocol.entity.StatixFakeEntity;
import ru.statix.api.bukkit.modules.protocol.packet.entity.WrapperPlayServerNamedEntitySpawn;
import ru.statix.api.bukkit.modules.protocol.packet.entity.WrapperPlayServerPlayerInfo;
import ru.statix.api.bukkit.modules.protocol.packet.scoreboard.WrapperPlayServerScoreboardTeam;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import ru.statix.api.bukkit.utility.MojangUtil;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class FakePlayer extends StatixFakeEntity {

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    private final UUID uuid;
    private final MojangUtil.Skin skin;

    private final String name;

    public FakePlayer(String skin, Location location) {
        super(EntityType.PLAYER, location);

        this.name = "§8NPC [" + RANDOM.nextInt(999999) + "]";
        this.uuid = UUID.randomUUID();
        this.skin = MojangUtil.getSkinTextures(skin);
    }

    @Override
    public void sendSpawnPacket(Player player) {
        String teamName = getTeamName();

        sendTeamPacket(teamName, player, WrapperPlayServerScoreboardTeam.Mode.TEAM_CREATED);
        sendPlayerInfoPacket(EnumWrappers.PlayerInfoAction.ADD_PLAYER, player);

        WrapperPlayServerNamedEntitySpawn spawned = new WrapperPlayServerNamedEntitySpawn();

        spawned.setEntityID(getId());
        spawned.setPosition(getLocation().toVector());
        spawned.setPlayerUUID(uuid);

        spawned.setPitch(getLocation().getPitch());
        spawned.setYaw(getLocation().getYaw());

        spawned.sendPacket(player);

        sendEntityLookPacket(player);
        sendHeadRotationPacket(player);

        sendTeamPacket(teamName, player, WrapperPlayServerScoreboardTeam.Mode.PLAYERS_ADDED);
    }

    private void sendPlayerInfoPacket(EnumWrappers.PlayerInfoAction action, Player player) {
        WrapperPlayServerPlayerInfo playerInfoPacket = new WrapperPlayServerPlayerInfo();

        WrappedGameProfile wrappedGameProfile = new WrappedGameProfile(uuid, name);

        if (skin != null && action == EnumWrappers.PlayerInfoAction.ADD_PLAYER) {
            wrappedGameProfile.getProperties().put("textures", new WrappedSignedProperty("textures",
                    skin.getValue(), skin.getSignature()));
        }

        PlayerInfoData playerInfoData = new PlayerInfoData(wrappedGameProfile, 0,
                EnumWrappers.NativeGameMode.ADVENTURE, WrappedChatComponent.fromText(name));

        playerInfoPacket.setAction(action);
        playerInfoPacket.setData(Collections.singletonList(playerInfoData));

        playerInfoPacket.sendPacket(player);
    }

    private void sendTeamPacket(String teamName, Player player, int mode) {
        WrapperPlayServerScoreboardTeam scoreboardTeam = new WrapperPlayServerScoreboardTeam();

        scoreboardTeam.setName(teamName);
        scoreboardTeam.setMode(mode);

        scoreboardTeam.setCollisionRule("never");
        scoreboardTeam.setNameTagVisibility("never");

        if (mode == WrapperPlayServerScoreboardTeam.Mode.TEAM_CREATED || mode == WrapperPlayServerScoreboardTeam.Mode.TEAM_UPDATED) {
            scoreboardTeam.setDisplayName(teamName);
            scoreboardTeam.setPrefix(getGlowingColor() == null ? "§8" : getGlowingColor().toString());
            scoreboardTeam.setPackOptionData(0);
            scoreboardTeam.setColor(0);
        } else {
            scoreboardTeam.setPlayers(Collections.singletonList(name));
        }

        scoreboardTeam.sendPacket(player);
    }

    @Override
    public void setGlowingColor(ChatColor glowingColor) {
        super.setGlowingColor(glowingColor);

        getReceivers().forEach(receiver -> sendTeamPacket(getTeamName(), receiver, WrapperPlayServerScoreboardTeam.Mode.TEAM_UPDATED));
    }

    private String getTeamName() {
        String teamName = name + "_TEAM";

        if (teamName.length() > 16) {
            teamName = teamName.substring(0, 16);
        }

        return teamName;
    }
}
