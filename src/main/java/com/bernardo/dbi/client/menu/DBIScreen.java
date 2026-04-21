package com.bernardo.dbi.client.menu;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public abstract class DBIScreen extends Screen {

    protected int guiWidth;
    protected int guiHeight;
    protected int guiLeft;
    protected int guiTop;

    // Tamanho real da imagem
    protected static final int IMG_W = 256;
    protected static final int IMG_H = 256;
    // Área útil
    protected static final int USEFUL_H = 159;

    protected DBIScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        guiWidth  = this.width  / 2;
        // Mantém proporção da área útil
        guiHeight = (int)(guiWidth * (float)USEFUL_H / IMG_W);
        guiLeft   = (this.width  - guiWidth)  / 2;
        guiTop    = (this.height - guiHeight) / 2;
        initGui();
    }

    protected abstract void initGui();

    protected int scaled(int base) {
        return base * guiWidth / IMG_W;
    }
}
