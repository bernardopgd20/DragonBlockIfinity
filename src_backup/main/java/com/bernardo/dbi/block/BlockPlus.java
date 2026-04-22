package com.bernardo.dbi.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.Tags;

public class BlockPlus extends Block {

    public BlockPlus(MapColor color) {
        super(BlockBehaviour.Properties.of()
            .mapColor(color)
            .requiresCorrectToolForDrops()
            .strength(1.5f, 6.0f)
            .sound(SoundType.STONE));
    }
}
