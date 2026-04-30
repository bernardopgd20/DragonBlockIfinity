package com.bernardo.dbi.core.race;

import com.bernardo.dbi.DragonBlockInfinity;
import net.minecraft.resources.ResourceLocation;

public enum Race {
    HUMAN(null),
    ARCOSIAN_MALE(new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/cc/male/ac1b.png"));

    private final ResourceLocation texture;

    Race(ResourceLocation texture) {
        this.texture = texture;
    }

    public ResourceLocation getTexture() { return texture; }
    public boolean hasTexture() { return texture != null; }
}
