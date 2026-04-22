package com.bernardo.dbi;

import com.bernardo.dbi.block.DBIBlocks;
import com.bernardo.dbi.item.DBIItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DragonBlockInfinity.MOD_ID)
public class DragonBlockInfinity {

    public static final String MOD_ID = "dragonblockinfinity";
    public static final Logger LOGGER = LogManager.getLogger();

    public DragonBlockInfinity() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        DBIBlocks.register(bus);
        DBIItems.register(bus);
        DBICreativeTab.register(bus);

        bus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("DBI Common setup completo!");
    }
}
