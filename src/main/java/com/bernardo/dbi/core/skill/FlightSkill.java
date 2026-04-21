package com.bernardo.dbi.core.skill;

import com.bernardo.dbi.core.PlayerReadyState;
import net.minecraft.world.entity.player.Player;

public class FlightSkill {

    public enum FlyState {
        IDLE, MOVE_FORWARD, MOVE_BACK, SPRINT, UP, DOWN
    }

    private static final java.util.Map<java.util.UUID, Boolean> FLYING =
        new java.util.HashMap<>();

    public static void setFlying(Player player, boolean flying) {
        if (!PlayerReadyState.isReady(player.getUUID())) return;
        FLYING.put(player.getUUID(), flying);
        player.getAbilities().mayfly = flying;
        player.getAbilities().flying = flying;
        player.onUpdateAbilities();
    }

    public static boolean isFlying(Player player) {
        return FLYING.getOrDefault(player.getUUID(), false);
    }

    public static FlyState getFlyState(Player player) {
        if (!isFlying(player)) return FlyState.IDLE;
        if (player.isSprinting()) return FlyState.SPRINT;
        if (player.getDeltaMovement().y > 0.05) return FlyState.UP;
        if (player.getDeltaMovement().y < -0.05) return FlyState.DOWN;
        double dx = player.getDeltaMovement().x;
        double dz = player.getDeltaMovement().z;
        double speed = Math.sqrt(dx*dx + dz*dz);
        if (speed > 0.05) {
            // Detecta se vai para frente ou para trás baseado no yaw
            float yaw = player.getYRot();
            double motionAngle = Math.toDegrees(Math.atan2(-dx, dz));
            double diff = ((motionAngle - yaw) % 360 + 360) % 360;
            if (diff > 180) return FlyState.MOVE_BACK;
            return FlyState.MOVE_FORWARD;
        }
        return FlyState.IDLE;
    }
}
