package com.bernardo.dbi.client.skill;

import com.bernardo.dbi.core.skill.FlightSkill;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.bernardo.dbi.DragonBlockInfinity;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = DragonBlockInfinity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class FlightKeyHandler {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        long window = mc.getWindow().getWindow();
        boolean xPressed = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_X) == GLFW.GLFW_PRESS;

        if (xPressed && !FlightSkill.isFlying(player)) {
            FlightSkill.setFlying(player, true);
        } else if (!xPressed && FlightSkill.isFlying(player)) {
            FlightSkill.setFlying(player, false);
        }
    }
}
