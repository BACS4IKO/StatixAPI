package org.statix.bukkit.protocollib.entity.impl;

import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.statix.bukkit.protocollib.entity.FakeBaseEntityMob;

public class FakeZombie extends FakeBaseEntityMob {

    public FakeZombie(@NonNull Location location) {
        super(EntityType.ZOMBIE, location);
    }

}
