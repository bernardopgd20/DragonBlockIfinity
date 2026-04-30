package com.bernardo.dbi.client.render.race;

import com.bernardo.dbi.core.race.PlayerRaceData;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.animatable.GeoReplacedEntity;

public class ArcosianRenderer extends GeoReplacedEntityRenderer<Player, GeoReplacedEntity> {

    public ArcosianRenderer(net.minecraft.client.renderer.entity.EntityRendererProvider.Context renderManager) {
        super(renderManager, new ArcosianModel(), null);
    }

    @Override
    public void renderRecursively(PoseStack poseStack, GeoReplacedEntity animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isRebind, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Player player = Minecraft.getInstance().level.getPlayerByUUID(animatable.getUUID());
        if (player == null) {
            super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isRebind, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
            return;
        }

        int[] colors = PlayerRaceData.getColors(player.getUUID());
        float r = 1f, g = 1f, b = 1f;

        // LÓGICA DE PINTURA POR OSSO
        if (bone.getName().toLowerCase().contains("head")) {
            r = ((colors[2] >> 16) & 0xFF) / 255f; // Olhos na cabeça
            g = ((colors[2] >> 8) & 0xFF) / 255f;
            b = (colors[2] & 0xFF) / 255f;
        } else if (bone.getName().toLowerCase().contains("body") || bone.getName().toLowerCase().contains("tail")) {
            r = ((colors[1] >> 16) & 0xFF) / 255f; // Armadura e Cauda
            g = ((colors[1] >> 8) & 0xFF) / 255f;
            b = (colors[1] & 0xFF) / 255f;
        } else {
            r = ((colors[0] >> 16) & 0xFF) / 255f; // Pele (Braços e Pernas)
            g = ((colors[0] >> 8) & 0xFF) / 255f;
            b = (colors[0] & 0xFF) / 255f;
        }

        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isRebind, partialTick, packedLight, packedOverlay, r, g, b, alpha);
    }
}
