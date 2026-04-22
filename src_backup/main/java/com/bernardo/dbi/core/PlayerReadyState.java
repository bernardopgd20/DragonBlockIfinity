package com.bernardo.dbi.core;

import java.util.UUID;
import java.util.HashSet;
import java.util.Set;

public class PlayerReadyState {

    private static final Set<UUID> ready = new HashSet<>();

    public static void setReady(UUID uuid) {
        ready.add(uuid);
    }

    public static void reset(UUID uuid) {
        ready.remove(uuid);
    }

    public static boolean isReady(UUID uuid) {
        return ready.contains(uuid);
    }
}
