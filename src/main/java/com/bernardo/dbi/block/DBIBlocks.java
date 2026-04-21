package com.bernardo.dbi.block;

import com.bernardo.dbi.DragonBlockInfinity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DBIBlocks {

    public static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(ForgeRegistries.BLOCKS, DragonBlockInfinity.MOD_ID);

    public static final RegistryObject<Block> BLOCK_PLUS_WHITE      = BLOCKS.register("block_plus_white",      () -> new BlockPlus(MapColor.SNOW));
    public static final RegistryObject<Block> BLOCK_PLUS_ORANGE     = BLOCKS.register("block_plus_orange",     () -> new BlockPlus(MapColor.COLOR_ORANGE));
    public static final RegistryObject<Block> BLOCK_PLUS_MAGENTA    = BLOCKS.register("block_plus_magenta",    () -> new BlockPlus(MapColor.COLOR_MAGENTA));
    public static final RegistryObject<Block> BLOCK_PLUS_LIGHT_BLUE = BLOCKS.register("block_plus_light_blue", () -> new BlockPlus(MapColor.COLOR_LIGHT_BLUE));
    public static final RegistryObject<Block> BLOCK_PLUS_YELLOW     = BLOCKS.register("block_plus_yellow",     () -> new BlockPlus(MapColor.COLOR_YELLOW));
    public static final RegistryObject<Block> BLOCK_PLUS_LIME       = BLOCKS.register("block_plus_lime",       () -> new BlockPlus(MapColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> BLOCK_PLUS_PINK       = BLOCKS.register("block_plus_pink",       () -> new BlockPlus(MapColor.COLOR_PINK));
    public static final RegistryObject<Block> BLOCK_PLUS_GRAY       = BLOCKS.register("block_plus_gray",       () -> new BlockPlus(MapColor.COLOR_GRAY));
    public static final RegistryObject<Block> BLOCK_PLUS_LIGHT_GRAY = BLOCKS.register("block_plus_light_gray", () -> new BlockPlus(MapColor.COLOR_LIGHT_GRAY));
    public static final RegistryObject<Block> BLOCK_PLUS_CYAN       = BLOCKS.register("block_plus_cyan",       () -> new BlockPlus(MapColor.COLOR_CYAN));
    public static final RegistryObject<Block> BLOCK_PLUS_PURPLE     = BLOCKS.register("block_plus_purple",     () -> new BlockPlus(MapColor.COLOR_PURPLE));
    public static final RegistryObject<Block> BLOCK_PLUS_BLUE       = BLOCKS.register("block_plus_blue",       () -> new BlockPlus(MapColor.COLOR_BLUE));
    public static final RegistryObject<Block> BLOCK_PLUS_BROWN      = BLOCKS.register("block_plus_brown",      () -> new BlockPlus(MapColor.COLOR_BROWN));
    public static final RegistryObject<Block> BLOCK_PLUS_GREEN      = BLOCKS.register("block_plus_green",      () -> new BlockPlus(MapColor.COLOR_GREEN));
    public static final RegistryObject<Block> BLOCK_PLUS_RED        = BLOCKS.register("block_plus_red",        () -> new BlockPlus(MapColor.COLOR_RED));
    public static final RegistryObject<Block> BLOCK_PLUS_BLACK      = BLOCKS.register("block_plus_black",      () -> new BlockPlus(MapColor.COLOR_BLACK));
    public static final RegistryObject<Block> BLOCK_NAMEKGRASS      = BLOCKS.register("block_namekgrass",      () -> new BlockNamekGrass());
    public static final RegistryObject<Block> BLOCK_NAMEKDIRT = BLOCKS.register("block_namekdirt", () -> new com.bernardo.dbi.block.blocks.BlockNamekDirt());

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
