package com.bernardo.dbi.client.render.aura;

import com.bernardo.dbi.DragonBlockInfinity;
import com.bernardo.dbi.client.TempAuraColor;
import com.bernardo.dbi.client.render.aura.shader.AuraShader;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Matrix4f;

@Mod.EventBusSubscriber(modid = DragonBlockInfinity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class AuraRenderer {

    private static float time = 0f;

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Post event) {
        if (!AuraSettings.enabled) return;
        if (!AuraManager.isAuraActive(event.getEntity().getUUID())) return;
        if (AuraShader.get() == null) return;

        Player player = event.getEntity();
        Minecraft mc = Minecraft.getInstance();
        boolean firstPerson = mc.options.getCameraType().isFirstPerson() && player == mc.player;
        float auravar = firstPerson ? 0.55f : 0.70f;
        float[] color = TempAuraColor.COLOR;

        time += 0.016f;

        PoseStack poseStack = event.getPoseStack();
        poseStack.pushPose();

        Matrix4f modelMatrix = new Matrix4f(poseStack.last().pose());
        Matrix4f projMatrix  = RenderSystem.getProjectionMatrix();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.depthMask(false);

        AuraShader.apply(color, time, auravar, modelMatrix, projMatrix);

        BufferBuilder buf = Tesselator.getInstance().getBuilder();
        buf.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

        float r = color[0], g = color[1], b = color[2];
        int segments = 16;
        float radius = 0.6f;
        float height = player.getBbHeight();
        Matrix4f mat = poseStack.last().pose();

        for (int i = 0; i < segments; i++) {
            double a1 = Math.PI * 2 * i / segments;
            double a2 = Math.PI * 2 * (i + 1) / segments;
            float x1 = (float)(Math.cos(a1) * radius);
            float z1 = (float)(Math.sin(a1) * radius);
            float x2 = (float)(Math.cos(a2) * radius);
            float z2 = (float)(Math.sin(a2) * radius);

            buf.vertex(mat, x1, 0,      z1).color(r, g, b, 0.0f).endVertex();
            buf.vertex(mat, x2, 0,      z2).color(r, g, b, 0.0f).endVertex();
            buf.vertex(mat, x2, height, z2).color(r, g, b, 0.8f).endVertex();
            buf.vertex(mat, x1, height, z1).color(r, g, b, 0.8f).endVertex();
        }

        Tesselator.getInstance().end();
        AuraShader.clear();

        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();

        poseStack.popPose();
    }
}
