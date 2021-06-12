package ru.statix.api.bukkit.modules.protocol.entity.impl;

import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import ru.statix.api.bukkit.modules.protocol.entity.StatixFakeEntity;

/**
 * @Author ItzStonlex.
 * @VK https://vk.com/itzstonlex
 * <p>
 * (Created on 14.08.2019 8:32)
 */
public class FakeBat extends StatixFakeEntity {

    @Getter
    private boolean hanging;

    public FakeBat(Location location) {
        super(EntityType.BAT, location);
    }

    public void setHanging(boolean hanging) {
        this.hanging = hanging;

        getDataWatcher().setObject(new WrappedDataWatcher.WrappedDataWatcherObject(12, BYTE_SERIALIZER), generateBitMask());
        sendDataWatcherPacket();
    }

    public byte generateBitMask() {
        return (byte) (hanging ? 0x01 : 0);
    }

}
