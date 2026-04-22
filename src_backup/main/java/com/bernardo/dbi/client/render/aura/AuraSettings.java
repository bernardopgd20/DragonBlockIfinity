package com.bernardo.dbi.client.render.aura;

public class AuraSettings {

    // Qualidade — layers 1=leve, 2=médio, 3=pesado
    public static int   layers    = 2;
    public static float speed     = 5.5f;
    public static float amplitude = 0.8f;
    public static float power     = 2.0f;
    public static float divis     = 1.0f;
    public static float heightFade = 1.0f;
    public static float alp1      = 0.6f;
    public static float alp2      = 1.0f;

    // false = aura desativada completamente
    public static boolean enabled = true;

    public static void setQuality(Quality q) {
        switch (q) {
            case LOW    -> { layers = 1; amplitude = 0.4f; speed = 3.0f; }
            case MEDIUM -> { layers = 2; amplitude = 0.8f; speed = 5.5f; }
            case HIGH   -> { layers = 3; amplitude = 1.2f; speed = 7.0f; }
            case OFF    -> { enabled = false; }
        }
        if (q != Quality.OFF) enabled = true;
    }

    public enum Quality { OFF, LOW, MEDIUM, HIGH }
}
