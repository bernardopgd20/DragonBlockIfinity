package com.bernardo.dbi.client.menu.screens;

import com.bernardo.dbi.DragonBlockInfinity;
import com.bernardo.dbi.client.menu.DBIScreen;
import com.bernardo.dbi.core.PlayerPreview;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class CharacterCreationScreen extends DBIScreen {

    private static final ResourceLocation GUI_TEX =
        new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/gui/gui.png");

    private float yaw = 0f;
    private float pitch = -10f;
    private boolean dragging = false;
    private double lastMouseX, lastMouseY;

    public CharacterCreationScreen() {
        super(Component.translatable("screen.dragonblockinfinity.character_creation"));
    }

    @Override
    protected void initGui() {}

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics);

        // Renderiza só a área útil da PNG (256x159 de 256x256)
        graphics.blit(GUI_TEX,
            guiLeft, guiTop, guiWidth, guiHeight,
            0, 0, IMG_W, USEFUL_H, IMG_W, IMG_H);

        int previewX = guiLeft + guiWidth / 4;
        int previewY = guiTop  + guiHeight / 2;
        int previewSize = guiWidth / 6;

        PlayerPreview.render(graphics, previewX, previewY, previewSize, yaw, pitch);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        dragging = true;
        lastMouseX = mouseX;
        lastMouseY = mouseY;
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        dragging = false;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (dragging) {
            yaw   += (float)(mouseX - lastMouseX) * 1.5f;
            pitch += (float)(mouseY - lastMouseY) * 1.0f;
            pitch  = Math.max(-30f, Math.min(30f, pitch));
            lastMouseX = mouseX;
            lastMouseY = mouseY;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean isPauseScreen() { return false; }
}
