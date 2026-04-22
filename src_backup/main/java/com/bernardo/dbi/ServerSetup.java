package com.bernardo.dbi;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

@Mod.EventBusSubscriber(modid = DragonBlockInfinity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ServerSetup {

    @SubscribeEvent
    public static void init(final FMLDedicatedServerSetupEvent event) {
        DragonBlockInfinity.LOGGER.info("DBI Server setup completo!");
    }
}
