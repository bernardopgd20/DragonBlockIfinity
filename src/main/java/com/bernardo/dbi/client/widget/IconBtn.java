package com.bernardo.dbi.client.widget;

import com.bernardo.dbi.DragonBlockInfinity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class IconBtn extends AbstractWidget {

    private static final ResourceLocation ICONS =
        new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/gui/icons_btn.png");

    private final BtnIcon icon;
    private boolean pressed = false;
    private final Consumer<IconBtn> onClick;

    public IconBtn(int x, int y, BtnIcon icon, Consumer<IconBtn> onClick) {
        super(x, y, icon.width, icon.height, Component.empty());
        this.icon = icon;
        this.onClick = onClick;
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        int v = pressed ? icon.vPressed : icon.v;
        graphics.blit(ICONS, getX(), getY(), icon.u, v, icon.width, icon.height);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!isActive() || !isHovered()) return false;
        pressed = true;
        onClick.accept(this);
        return true;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        pressed = false;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narration) {}
}
