package com.bernardo.dbi.client.menu.screens;

import com.bernardo.dbi.DragonBlockInfinity;
import com.bernardo.dbi.client.menu.DBIScreen;
import com.bernardo.dbi.client.widget.IconBtn;
import com.bernardo.dbi.client.widget.BtnIcon;
import com.bernardo.dbi.core.PlayerPreview;
import com.bernardo.dbi.core.race.FightingClass;
import com.bernardo.dbi.core.race.PlayerRaceData;
import com.bernardo.dbi.core.race.Race;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class CharacterCreationScreen extends DBIScreen {
    private FightingClass selectedClass = FightingClass.WARRIOR;
    
    // Cores: 0 = Skin, 1 = Armor, 2 = Eyes
    private int[] colors = {0xFFFFFF, 0xCCCCCC, 0xFF0000};
    private int selectedPart = 0; 
    
    private float yaw = 0f;
    private int currentTab = 0;

    public CharacterCreationScreen() {
        super(Component.translatable("screen.dragonblockinfinity.character_creation"));
    }

    @Override
    protected void initGui() {
        clearWidgets();
        int panelX = guiLeft + scaled(140);
        int startY = guiTop + scaled(40);

        if (currentTab == 1) {
            // --- OS 3 QUADRADOS DE SELEÇÃO (BODY, ARMOR, EYES) ---
            addRenderableWidget(Button.builder(Component.literal("BODY"), (btn) -> selectedPart = 0)
                .bounds(panelX, startY - 15, 30, 20).build());
            addRenderableWidget(Button.builder(Component.literal("ARMOR"), (btn) -> selectedPart = 1)
                .bounds(panelX + 35, startY - 15, 35, 20).build());
            addRenderableWidget(Button.builder(Component.literal("EYES"), (btn) -> selectedPart = 2)
                .bounds(panelX + 75, startY - 15, 30, 20).build());

            // --- CONTROLES RGB PARA A PARTE SELECIONADA ---
            int r = (colors[selectedPart] >> 16) & 0xFF;
            int g = (colors[selectedPart] >> 8) & 0xFF;
            int b = colors[selectedPart] & 0xFF;

            addRenderableWidget(new IconBtn(panelX, startY + 20, BtnIcon.ARROW_LEFT_SM, (btn) -> updateColor(selectedPart, -15, 0, 0)));
            addRenderableWidget(new IconBtn(panelX + 80, startY + 20, BtnIcon.ARROW_RIGHT_SM, (btn) -> updateColor(selectedPart, 15, 0, 0)));
            
            addRenderableWidget(new IconBtn(panelX, startY + 40, BtnIcon.ARROW_LEFT_SM, (btn) -> updateColor(selectedPart, 0, -15, 0)));
            addRenderableWidget(new IconBtn(panelX + 80, startY + 40, BtnIcon.ARROW_RIGHT_SM, (btn) -> updateColor(selectedPart, 0, 15, 0)));
            
            addRenderableWidget(new IconBtn(panelX, startY + 60, IconBtn.Direction.LEFT, (btn) -> updateColor(selectedPart, 0, 0, -15)));
            addRenderableWidget(new IconBtn(panelX + 80, startY + 60, IconBtn.Direction.RIGHT, (btn) -> updateColor(selectedPart, 0, 0, 15)));
        }

        // Abas e Accept (Igual ao anterior)
        addRenderableWidget(Button.builder(Component.literal("General"), (btn) -> { currentTab = 0; initGui(); }).bounds(guiLeft+10, guiTop+10, 50, 16).build());
        addRenderableWidget(Button.builder(Component.literal("Design"), (btn) -> { currentTab = 1; initGui(); }).bounds(guiLeft+65, guiTop+10, 50, 16).build());
        addRenderableWidget(Button.builder(Component.literal("ACCEPT"), (btn) -> {
            PlayerRaceData.setAll(net.minecraft.client.Minecraft.getInstance().player.getUUID(), Race.ARCOSIAN_MALE, selectedClass, colors);
            this.onClose();
        }).bounds(guiLeft + guiWidth - 60, guiTop + guiHeight - 25, 50, 20).build());
    }

    private void updateColor(int part, int dr, int dg, int db) {
        int r = Math.max(0, Math.min(255, ((colors[part] >> 16) & 0xFF) + dr));
        int g = Math.max(0, Math.min(255, ((colors[part] >> 8) & 0xFF) + dg));
        int b = Math.max(0, Math.min(255, (colors[part] & 0xFF) + db));
        colors[part] = (r << 16) | (g << 8) | b;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics);
        // Desenha qual parte está selecionada para o usuário não se perder
        String partName = selectedPart == 0 ? "BODY" : (selectedPart == 1 ? "ARMOR" : "EYES");
        graphics.drawString(font, "Editing: " + partName, guiLeft + 140, guiTop + 30, 0xFFFFFF00);
        
        PlayerPreview.render(graphics, guiLeft + 60, guiTop + guiHeight - 30, 60, yaw);
        super.render(graphics, mouseX, mouseY, partialTick);
    }
}
