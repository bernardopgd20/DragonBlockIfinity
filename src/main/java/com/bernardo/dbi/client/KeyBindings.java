package com.bernardo.dbi.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    public static final KeyMapping AURA_KEY = new KeyMapping(
        "key.dragonblockinfinity.aura",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_Z,
        "key.categories.dragonblockinfinity"
    );

    public static final KeyMapping CHARACTER_KEY = new KeyMapping(
        "key.dragonblockinfinity.character_creation",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_O,
        "key.categories.dragonblockinfinity"
    );

    public static void register(RegisterKeyMappingsEvent event) {
        event.register(AURA_KEY);
        event.register(CHARACTER_KEY);
    }
}
