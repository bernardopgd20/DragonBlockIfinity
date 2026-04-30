package com.bernardo.dbi.client.render.race;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import java.io.InputStream;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

public class ArcosianTextureManager {
    private static final Map<UUID, ResourceLocation> PLAYER_TEXTURES = new HashMap<>();

    // AQUI VOCÊ INFORMA OS RECORTES (X, Y, Largura, Altura)
    // Baseado na sua textura 256x256 e UVs do GeckoLib
    private static final int[][] SKIN_ZONES = {{0, 72, 32, 32}, {128, 72, 32, 48}}; // Exemplo
    private static final int[][] ARMOR_ZONES = {{128, 0, 64, 64}}; // Exemplo
    private static final int[][] EYE_ZONES = {{0, 0, 32, 32}}; // Exemplo

    public static ResourceLocation getOrCreateTexture(UUID uuid, int skinColor, int armorColor, int eyeColor) {
        if (PLAYER_TEXTURES.containsKey(uuid)) return PLAYER_TEXTURES.get(uuid);

        try {
            // Carrega a sua textura original 256x256
            ResourceLocation original = new ResourceLocation("dragonblockinfinity", "textures/cc/male/ac1b.png");
            InputStream is = Minecraft.getInstance().getResourceManager().getResource(original).get().open();
            NativeImage baseImage = NativeImage.read(is);
            NativeImage newImage = new NativeImage(baseImage.getWidth(), baseImage.getHeight(), true);

            // Loop por todos os pixels
            for (int y = 0; y < baseImage.getHeight(); y++) {
                for (int x = 0; x < baseImage.getWidth(); x++) {
                    int pixel = baseImage.getPixelRGBA(x, y);
                    int colorToApply = 0xFFFFFFFF; // Branco (sem alteração)

                    if (isInZone(x, y, SKIN_ZONES)) colorToApply = skinColor;
                    else if (isInZone(x, y, ARMOR_ZONES)) colorToApply = armorColor;
                    else if (isInZone(x, y, EYE_ZONES)) colorToApply = eyeColor;

                    newImage.setPixelRGBA(x, y, multiplyColors(pixel, colorToApply));
                }
            }

            DynamicTexture dynTex = new DynamicTexture(newImage);
            ResourceLocation loc = Minecraft.getInstance().getTextureManager().register("dbi_player_" + uuid.toString(), dynTex);
            PLAYER_TEXTURES.put(uuid, loc);
            return loc;
        } catch (Exception e) {
            return new ResourceLocation("dragonblockinfinity", "textures/cc/male/ac1b.png");
        }
    }

    private static boolean isInZone(int x, int y, int[][] zones) {
        for (int[] zone : zones) {
            if (x >= zone[0] && x < zone[0] + zone[2] && y >= zone[1] && y < zone[1] + zone[3]) return true;
        }
        return false;
    }

    private static int multiplyColors(int pixel, int tint) {
        int alpha = (pixel >> 24) & 0xff;
        if (alpha == 0) return 0;
        int r = ((pixel & 0xff) * ((tint >> 16) & 0xff)) / 255;
        int g = (((pixel >> 8) & 0xff) * ((tint >> 8) & 0xff)) / 255;
        int b = (((pixel >> 16) & 0xff) * (tint & 0xff)) / 255;
        return (alpha << 24) | (b << 16) | (g << 8) | r;
    }

    public static void clearCache(UUID uuid) {
        PLAYER_TEXTURES.remove(uuid);
    }
}
