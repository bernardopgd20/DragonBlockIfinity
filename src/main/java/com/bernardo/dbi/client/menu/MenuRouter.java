package com.bernardo.dbi.client.menu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MenuRouter {

    private static final Map<String, Supplier<Screen>> ROUTES = new HashMap<>();

    public static void register(String id, Supplier<Screen> screen) {
        ROUTES.put(id, screen);
    }

    public static void open(String id) {
        Supplier<Screen> supplier = ROUTES.get(id);
        if (supplier != null) {
            Minecraft.getInstance().setScreen(supplier.get());
        }
    }

    public static boolean exists(String id) {
        return ROUTES.containsKey(id);
    }
}
