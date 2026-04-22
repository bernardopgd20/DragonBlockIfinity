package com.bernardo.dbi.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class BlockNamekGrass extends Block {

    public BlockNamekGrass() {
        super(BlockBehaviour.Properties.of()
            .mapColor(MapColor.GRASS)
            .strength(0.6f)
            .sound(SoundType.GRASS));
    }
}
