package com.bernardo.dbi.client.render.aura.shader;

import com.bernardo.dbi.DragonBlockInfinity;
import com.bernardo.dbi.client.render.aura.AuraSettings;
import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterShadersEvent;
import org.joml.Matrix4f;

import java.io.IOException;

public class AuraShader {

    private static ShaderInstance instance;

    public static void onRegisterShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(
            new ShaderInstance(event.getResourceProvider(),
                new ResourceLocation(DragonBlockInfinity.MOD_ID, "aura"),
                DefaultVertexFormat.POSITION_COLOR),
            shader -> instance = shader
        );
    }

    public static ShaderInstance get() { return instance; }

    public static void apply(float[] color, float time, float auravar, Matrix4f model, Matrix4f proj) {
        if (instance == null) return;
        setUniform3("color1",     color);
        setUniform3("color2",     color);
        setUniform1("alp1",       AuraSettings.alp1);
        setUniform1("alp2",       AuraSettings.alp2);
        setUniform1("power",      AuraSettings.power);
        setUniform1("divis",      AuraSettings.divis);
        setUniform1("heightFade", AuraSettings.heightFade);
        setUniform1("time",       time);
        setUniform1("auravar",    auravar);
        setUniform1("speed",      AuraSettings.speed);
        setUniform1("amplitude",  AuraSettings.amplitude);
        setUniformMat4("modelMatrix", model);
        setUniformMat4("ProjMat",     proj);
        instance.apply();
    }

    public static void clear() {
        if (instance != null) instance.clear();
    }

    private static void setUniform1(String name, float value) {
        Uniform u = instance.getUniform(name);
        if (u != null) u.set(value);
    }

    private static void setUniform3(String name, float[] v) {
        Uniform u = instance.getUniform(name);
        if (u != null) u.set(v[0], v[1], v[2]);
    }

    private static void setUniformMat4(String name, Matrix4f mat) {
        Uniform u = instance.getUniform(name);
        if (u != null) u.set(mat);
    }
}
