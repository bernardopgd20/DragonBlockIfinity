package com.bernardo.dbi.core;

import com.bernardo.dbi.core.race.PlayerRaceData;
import com.bernardo.dbi.core.race.Race;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.player.Player;
import org.joml.Quaternionf;

public class PlayerPreview {

    // Textura temporária para o menu — null = usa skin normal
    public static Race previewRace = null;

    public static void render(GuiGraphics graphics, int x, int y, int size, float yaw) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        previewRace = PlayerRaceData.getRace(player.getUUID());

        PoseStack poseStack = graphics.pose();
        poseStack.pushPose();
        poseStack.translate(x, y, 50);
        poseStack.scale(size, size, -size);
        poseStack.mulPose(new Quaternionf().rotateZ((float) Math.PI));

        float savedYBodyRot  = player.yBodyRot;
        float savedYRot      = player.getYRot();
        float savedYHeadRot  = player.yHeadRot;
        float savedYHeadRotO = player.yHeadRotO;
        float savedXRot      = player.getXRot();

        player.yBodyRot  = 180f + yaw;
        player.setYRot(180f + yaw);
        player.setXRot(0f);
        player.yHeadRot  = 180f + yaw;
        player.yHeadRotO = 180f + yaw;

        EntityRenderDispatcher dispatcher = mc.getEntityRenderDispatcher();
        dispatcher.setRenderShadow(false);
        RenderSystem.runAsFancy(() -> {
            dispatcher.render(player, 0, 0, 0, 0f, 1f, poseStack, graphics.bufferSource(), 0xF000F0);
        });
        graphics.flush();
        dispatcher.setRenderShadow(true);

        player.yBodyRot  = savedYBodyRot;
        player.setYRot(savedYRot);
        player.setXRot(savedXRot);
        player.yHeadRot  = savedYHeadRot;
        player.yHeadRotO = savedYHeadRotO;

        poseStack.popPose();
        previewRace = null;
    }
}
