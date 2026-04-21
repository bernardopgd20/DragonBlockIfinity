package com.bernardo.dbi.core;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;

public abstract class SafeZone {

    protected BlockPos center;
    protected int radiusX;
    protected int radiusY;
    protected int radiusZ;

    protected SafeZone(BlockPos center, int radiusX, int radiusY, int radiusZ) {
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.radiusZ = radiusZ;
    }

    public boolean isInside(BlockPos pos) {
        return Math.abs(pos.getX() - center.getX()) <= radiusX
            && Math.abs(pos.getY() - center.getY()) <= radiusY
            && Math.abs(pos.getZ() - center.getZ()) <= radiusZ;
    }

    public boolean isInside(Entity entity) {
        return isInside(entity.blockPosition());
    }
}
