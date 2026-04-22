package com.bernardo.dbi.block.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class BlockNamekDirt extends Block {
    public BlockNamekDirt() {
        super(BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_GREEN)
            .strength(0.5f)
            .sound(SoundType.GRAVEL));
    }
}
