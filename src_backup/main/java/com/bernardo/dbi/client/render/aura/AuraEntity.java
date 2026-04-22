package com.bernardo.dbi.client.render.aura;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class AuraEntity implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private float[] color = {1f, 1f, 1f};

    public void setColor(float r, float g, float b) {
        this.color = new float[]{r, g, b};
    }

    public float[] getColor() {
        return color;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar registrar) {
        registrar.add(new AnimationController<>(this, "pulse", 0, state -> {
            state.getController().setAnimation(
                RawAnimation.begin().thenLoop("animation.aura.pulse")
            );
            return state.setAndContinue(RawAnimation.begin().thenLoop("animation.aura.pulse"));
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
