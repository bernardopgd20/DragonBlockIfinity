package com.bernardo.dbi;

import com.bernardo.dbi.client.KeyBindings;
import com.bernardo.dbi.client.menu.MenuRouter;
import com.bernardo.dbi.client.menu.screens.CharacterCreationScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = DragonBlockInfinity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        MenuRouter.register("character_creation", CharacterCreationScreen::new);
        DragonBlockInfinity.LOGGER.info("DBI Client setup completo!");
    }

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        KeyBindings.register(event);
    }
}
