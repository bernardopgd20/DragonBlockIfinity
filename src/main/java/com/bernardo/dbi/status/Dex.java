package com.bernardo.dbi.status;

import java.util.Random;

public class Dex {

    private int dexterity;

    private double dodgeChance;      // chance de esquiva
    private double speedMultiplier;  // velocidade
    private double damageReduction;  // redução de dano

    private Random random;

    public Dex(int dexterity) {
        this.dexterity = Math.max(1, dexterity);
        this.random = new Random();
        recalcStats();
    }

    // ===== RECALC =====
    private void recalcStats() {
        // esquiva (máx 50%)
        this.dodgeChance = Math.min(0.05 + dexterity * 0.005, 0.5);

        // velocidade (máx 2x)
        this.speedMultiplier = Math.min(1.0 + dexterity * 0.02, 2.0);

        // redução de dano (máx 40%)
        this.damageReduction = Math.min(dexterity * 0.003, 0.4);
    }

    // ===== GETTERS =====
    public int getDexterity() {
        return dexterity;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    public double getDamageReduction() {
        return damageReduction;
    }

    // ===== SET =====
    public void setDexterity(int value) {
        this.dexterity = Math.max(1, value);
        recalcStats();
    }

    // ===== SISTEMA DE DEFESA =====
    public boolean tryDodge() {
        return random.nextDouble() < dodgeChance;
    }

    public int reduceDamage(int damage) {
        if (damage <= 0) return 0;

        int reduced = (int)(damage * (1 - damageReduction));
        return Math.max(0, reduced);
    }

    // ===== DEBUG =====
    @Override
    public String toString() {
        return "Dex{" +
                "DEX=" + dexterity +
                ", Dodge=" + (int)(dodgeChance * 100) + "%" +
                ", Speed=" + String.format("%.2f", speedMultiplier) +
                ", Def=" + (int)(damageReduction * 100) + "%" +
                '}';
    }
}
