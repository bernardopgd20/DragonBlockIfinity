package com.bernardo.dbi.client.widget;

import com.bernardo.dbi.DragonBlockInfinity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public abstract class DBIBtnManager extends AbstractWidget {
    protected static final ResourceLocation ICONS = new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/gui/icons_btn.png");
    protected final int u, v, vPressed, width, height;
    protected boolean isPressed = false;
    protected final Runnable onClickAction;

    public DBIBtnManager(int x, int y, int width, int height, int u, int v, int vPressed, Runnable onClick) {
        super(x, y, width, height, Component.empty());
        this.width = width; this.height = height;
        this.u = u; this.v = v; this.vPressed = vPressed;
        this.onClickAction = onClick;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        int currentV = isPressed ? vPressed : v;
        graphics.blit(ICONS, getX(), getY(), u, currentV, width, height, 256, 256);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        this.isPressed = true;
        if(this.onClickAction != null) this.onClickAction.run();
    }

    @Override
    public void onRelease(double mouseX, double mouseY) { this.isPressed = false; }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {}
}
