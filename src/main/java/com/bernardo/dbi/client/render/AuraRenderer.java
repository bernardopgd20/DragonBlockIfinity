package com.bernardo.dbi.client.render;

import com.bernardo.dbi.DragonBlockInfinity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class AuraRenderer {

    private static final ResourceLocation AURA_TEX =
        new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/fx/aura.png");

    private static float pulse = 0f;
    private static boolean growing = true;

    public static void tick() {
        if (growing) {
            pulse += 0.04f;
            if (pulse >= 1f) growing = false;
        } else {
            pulse -= 0.04f;
            if (pulse <= 0.3f) growing = true;
        }
    }

    // color = {r, g, b} valores de 0.0 a 1.0
    public static void render(Player player, float partialTick,
                              PoseStack poseStack,
                              MultiBufferSource bufferSource,
                              Camera camera,
                              float[] color) {

        double dx = player.xo + (player.getX() - player.xo) * partialTick - camera.getPosition().x;
        double dy = player.yo + (player.getY() - player.yo) * partialTick - camera.getPosition().y;
        double dz = player.zo + (player.getZ() - player.zo) * partialTick - camera.getPosition().z;

        poseStack.pushPose();
        poseStack.translate(dx, dy + player.getBbHeight() / 2.0, dz);
        poseStack.mulPose(camera.rotation());

        float size = 1.2f;
        float alpha = 0.4f + pulse * 0.4f;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, AURA_TEX);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.depthMask(false);
        RenderSystem.setShaderColor(color[0], color[1], color[2], alpha);

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
