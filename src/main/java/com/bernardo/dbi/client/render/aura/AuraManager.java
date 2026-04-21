package com.bernardo.dbi.client.render.aura;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AuraManager {

    private static final Set<UUID> ACTIVE = new HashSet<>();

    public static void showAura(UUID id) { ACTIVE.add(id); }
    public static void hideAura(UUID id) { ACTIVE.remove(id); }
    public static boolean isAuraActive(UUID id) { return ACTIVE.contains(id); }
    public static void clearAll() { ACTIVE.clear(); }
}
