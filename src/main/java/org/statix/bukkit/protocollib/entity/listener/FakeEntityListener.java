package org.statix.bukkit.protocollib.entity.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.statix.bukkit.StatixAPI;
import org.statix.bukkit.protocollib.entity.FakeBaseEntity;
import org.statix.bukkit.protocollib.entity.FakeEntity;
import org.statix.bukkit.protocollib.entity.FakeEntityLiving;
import org.statix.bukkit.protocollib.entity.FakeEntityRegistry;
import org.statix.bukkit.utility.cooldown.PlayerCooldownUtil;

import java.util.function.Consumer;

public class FakeEntityListener extends PacketAdapter
        implements Listener {


    private static final long ENTITY_INTERACT_COOLDOWN = 250;

    public FakeEntityListener() {
        super(StatixAPI.getInstance(),
                PacketType.Play.Client.USE_ENTITY, PacketType.Play.Server.MAP_CHUNK, PacketType.Play.Server.UNLOAD_CHUNK);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        PacketType type = event.getPacketType();
        Player player = event.getPlayer();

        StructureModifier<Integer> integers = event.getPacket().getIntegers();

        int x = integers.read(0);
        int z = integers.read(1);

        if (type == PacketType.Play.Server.UNLOAD_CHUNK) {
            onChunkUnload(player, x, z);
            return;
        }

        onChunkLoad(player, x, z);
    }

    private void onChunkLoad(Player player, int x, int z) {
        getPlugin().getServer().getScheduler().runTaskLater(getPlugin(), () -> {

            for (FakeEntity entity : FakeEntityRegistry.INSTANCE.getReceivableEntities(player)) {
                Chunk chunk = entity.getLocation().getChunk();

                if (chunk.getX() == x && chunk.getZ() == z) {
                    if (entity.hasViewer(player)) {
                        continue;
                    }

                    entity.addViewers(player);
                }
            }

        }, 10L);
    }

    private void onChunkUnload(Player player, int x, int z) {
        for (FakeEntity entity : FakeEntityRegistry.INSTANCE.getReceivableEntities(player)) {
            Chunk chunk = entity.getLocation().getChunk();

            if (chunk.getX() == x && chunk.getZ() == z) {
                if (!entity.hasViewer(player)) {
                    continue;
                }

                entity.removeViewers(player);
            }
        }
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        Player player = event.getPlayer();

        if (PlayerCooldownUtil.hasCooldown("fake_entity_interact", player)) {
            return;
        }

        FakeEntity fakeEntity = FakeEntityRegistry.INSTANCE.getEntityById(event.getPacket().getIntegers().read(0));
        if (!(fakeEntity instanceof FakeEntityLiving)) {
            return;
        }

        EnumWrappers.EntityUseAction entityUseAction = event.getPacket().getEntityUseActions().read(0);
        switch (entityUseAction) {

            case ATTACK: {
                Consumer<Player> attackAction = fakeEntity.getAttackAction();

                if (attackAction != null) {
                    Bukkit.getScheduler().runTask(getPlugin(), () -> attackAction.accept(player));
                }

                break;
            }

            case INTERACT_AT: {
                Consumer<Player> clickAction = fakeEntity.getClickAction();

                if (clickAction != null) {
                    Bukkit.getScheduler().runTask(getPlugin(), () -> clickAction.accept(player));
                }

                break;
            }
        }

        PlayerCooldownUtil.putCooldown("fake_entity_interact", player, ENTITY_INTERACT_COOLDOWN);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        for (FakeEntity fakeEntity : FakeEntityRegistry.INSTANCE.getReceivableEntities(player)) {
            if (fakeEntity == null) {
                continue;
            }

            fakeEntity.removeReceivers(player);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {

            for (FakeEntity fakeEntity : FakeEntityRegistry.INSTANCE.getReceivableEntities(player)) {

                boolean equalsWorld = fakeEntity.getLocation().getWorld().equals(player.getWorld());
                boolean hasViewer = fakeEntity.hasViewer(player);

                if (!hasViewer && equalsWorld) {
                    fakeEntity.addViewers(player);
                }

                if (hasViewer && !equalsWorld) {
                    fakeEntity.removeViewers(player);
                }
            }
        }, 10);
    }

    // В этом плане реализовал даже лучше чем у миши, т.к. он юзает таймер
    // (хотя хз, как будет в плане нагрузки)
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        for (FakeEntity fakeEntity : FakeEntityRegistry.INSTANCE.getReceivableEntities(player)) {
            // заспавлен ли ентити для игрока
            if (fakeEntity.hasViewer(player)) {
                // в одном ли они мире?
                if (fakeEntity.getWorld().getName() != player.getWorld().getName()) {
                    return;
                }
                // сверяем локации
                if (fakeEntity.getLocation().distance(player.getLocation()) > fakeEntity.getAutoLookingDistance()) {
                    fakeEntity.resetLooking();
                    return;
                }
                // TODO 06.10.2021: Желательно добавить еще проверок, чтобы ничего не легло, но я хз на что проверять =))
                fakeEntity.look(player.getLocation());
            }
        }
    }

}