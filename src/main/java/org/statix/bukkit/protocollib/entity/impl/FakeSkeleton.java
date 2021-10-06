package org.statix.bukkit.protocollib.entity.impl;

import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.statix.bukkit.protocollib.entity.FakeBaseEntityMob;

public class FakeSkeleton extends FakeBaseEntityMob {

    public FakeSkeleton(@NonNull Location location) {
        super(EntityType.SKELETON, location);
    }

}
