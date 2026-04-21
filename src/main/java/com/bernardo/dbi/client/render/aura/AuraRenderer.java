package com.bernardo.dbi.client.render.aura;

import com.bernardo.dbi.DragonBlockInfinity;
import com.bernardo.dbi.client.TempAuraColor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

@Mod.EventBusSubscriber(modid = DragonBlockInfinity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class AuraRenderer {

    private static final ResourceLocation AURA_TEX =
        new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/fx/aura.png");

    private static float pulse = 0f;
    private static boolean growing = true;

    public static void tick() {
        if (!AuraSettings.enabled) return;
        float spd = 0.02f * (AuraSettings.speed / 5.5f);
        if (growing) { pulse += spd; if (pulse >= 1f) growing = false; }
        else         { pulse -= spd; if (pulse <= 0.3f) growing = true; }
    }

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Post event) {
        if (!AuraSettings.enabled) return;
        if (!AuraManager.isAuraActive(event.getEntity().getUUID())) return;

        Player player = event.getEntity();
        Minecraft mc = Minecraft.getInstance();
        boolean firstPerson = mc.options.getCameraType().isFirstPerson() && player == mc.player;
        float alpha = firstPerson ? 0.55f : 0.70f;
        float[] color = TempAuraColor.COLOR;

        tick();

        PoseStack poseStack = event.getPoseStack();
        poseStack.pushPose();
        poseStack.translate(0, player.getBbHeight() / 2.0, 0);

        Quaternionf camRot = mc.getEntityRenderDispatcher().cameraOrientation();
        poseStack.mulPose(camRot);

        float size = (1.0f + pulse * 0.05f) * AuraSettings.amplitude;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, AURA_TEX);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.depthMask(false);
        RenderSystem.setShaderColor(color[0], color[1], color[2], alpha * AuraSettings.alp2);

        BufferBuilder buf = Tesselator.getInstance().getBuilder();
        buf.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        Matrix4f mat = poseStack.last().pose();
        buf.vertex(mat, -size, -size, 0).uv(0, 1).endVertex();
        buf.vertex(mat,  size, -size, 0).uv(1, 1).endVertex();
        buf.vertex(mat,  size,  size, 0).uv(1, 0).endVertex();
        buf.vertex(mat, -size,  size, 0).uv(0, 0).endVertex();
        Tesselator.getInstance().end();

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();

        poseStack.popPose();
    }
}
