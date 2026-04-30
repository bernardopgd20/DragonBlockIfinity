package com.bernardo.dbi.client.render.race;

import com.bernardo.dbi.DragonBlockInfinity;
import com.bernardo.dbi.core.race.PlayerRaceData;
import com.bernardo.dbi.core.race.Race;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(modid = DragonBlockInfinity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class RaceLayerRenderer {
    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
        Player player = event.getEntity();
        Race race = PlayerRaceData.getRace(player.getUUID());
        
        if (race != Race.ARCOSIAN_MALE) return;

        int color = PlayerRaceData.getColor(player.getUUID());
        float r = ((color >> 16) & 0xFF) / 255f;
        float g = ((color >> 8) & 0xFF) / 255f;
        float b = (color & 0xFF) / 255f;
        
        // Renderiza o modelo básico com a cor escolhida
        event.getRenderer().getModel().renderToBuffer(event.getPoseStack(), 
            event.getMultiBufferSource().getBuffer(RenderType.entityTranslucent(race.getTexture())),
            event.getPackedLight(), OverlayTexture.NO_OVERLAY, r, g, b, 1.0f);
            
        event.setCanceled(true);
    }
}
