package com.bernardo.dbi.entity.ki;

import com.bernardo.dbi.status.StatsManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityKiBlast extends EntityThrowable {

    private int damage = 10;
    private StatsManager stats;

    public EntityKiBlast(World world) {
        super(world);
    }

    public EntityKiBlast(World world, EntityLivingBase shooter, StatsManager stats) {
        super(world, shooter);
        this.stats = stats;

        // dano baseado no SPI
        if (stats != null) {
            this.damage = (int)(10 * stats.getSpi().getSkillPowerMultiplier());
        }
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {

        if (!this.worldObj.isRemote) {

            if (mop.entityHit != null && mop.entityHit instanceof EntityLivingBase) {
                EntityLivingBase target = (EntityLivingBase) mop.entityHit;

                target.attackEntityFrom(
                    net.minecraft.util.DamageSource.magic,
                    damage
                );
            }

            this.setDead();
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        // mata depois de um tempo (não ficar infinito)
        if (this.ticksExisted > 40) {
            this.setDead();
        }
    }
}
