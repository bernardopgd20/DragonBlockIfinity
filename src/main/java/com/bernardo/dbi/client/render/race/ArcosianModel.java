package com.bernardo.dbi.client.render.race;

import com.bernardo.dbi.DragonBlockInfinity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.animatable.GeoReplacedEntity;

public class ArcosianModel extends GeoModel<GeoReplacedEntity> {
    @Override
    public ResourceLocation getModelResource(GeoReplacedEntity animatable) {
        return new ResourceLocation(DragonBlockInfinity.MOD_ID, "geo/arcosian.geo.json");
    }
    @Override
    public ResourceLocation getTextureResource(GeoReplacedEntity animatable) {
        return new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/cc/male/ac1b.png");
    }
    @Override
    public ResourceLocation getAnimationResource(GeoReplacedEntity animatable) {
        return new ResourceLocation(DragonBlockInfinity.MOD_ID, "animations/player_fly.animation.json");
    }
}
