package com.bernardo.dbi.core;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.player.Player;
import org.joml.Quaternionf;

public class PlayerPreview {

    public static void render(GuiGraphics graphics, int x, int y, int size, float yaw, float pitch) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        PoseStack poseStack = graphics.pose();
        poseStack.pushPose();
        poseStack.translate(x, y, 50);
        poseStack.scale(size, size, -size);

        Quaternionf rot = new Quaternionf().rotateZ((float) Math.PI);
        Quaternionf pitchRot = new Quaternionf().rotateX(pitch * (float)(Math.PI / 180));
        rot.mul(pitchRot);
        poseStack.mulPose(rot);

        // Salva rotações originais
        float savedYBodyRot   = player.yBodyRot;
        float savedYRot       = player.getYRot();
        float savedXRot       = player.getXRot();
        float savedYHeadRotO  = player.yHeadRotO;
        float savedYHeadRot   = player.yHeadRot;

        // Força rotações do menu — cabeça igual ao corpo
        player.yBodyRot  = 180f + yaw;
        player.setYRot(180f + yaw);
        player.setXRot(-pitch);
        player.yHeadRot  = 180f + yaw; // cabeça segue o yaw do menu
        player.yHeadRotO = 180f + yaw;

        EntityRenderDispatcher dispatcher = mc.getEntityRenderDispatcher();
        dispatcher.setRenderShadow(false);
        RenderSystem.runAsFancy(() -> {
            dispatcher.render(player, 0, 0, 0, 0f, 1f,
                poseStack, graphics.bufferSource(), 0xF000F0);
        });
        graphics.flush();
        dispatcher.setRenderShadow(true);

        // Restaura rotações originais
        player.yBodyRot  = savedYBodyRot;
        player.setYRot(savedYRot);
        player.setXRot(savedXRot);
        player.yHeadRotO = savedYHeadRotO;
        player.yHeadRot  = savedYHeadRot;

        poseStack.popPose();
    }
}
