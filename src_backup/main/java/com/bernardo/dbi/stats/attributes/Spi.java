package com.bernardo.dbi.stats.attributes;

public class Spi {

    private int spirit;

    private int currentKi;
    private int maxKi;

    private double kiRegenRate;
    private double skillPowerMultiplier;
    private double kiCostReduction;

    public Spi(int spirit) {
        this.spirit = Math.max(1, spirit);
        recalcStats();
        this.currentKi = maxKi;
    }

    // ===== RECALC =====
    private void recalcStats() {
        // KI total
        this.maxKi = spirit * 10;

        // regeneração (máx 10%)
        this.kiRegenRate = Math.min(0.02 + spirit * 0.001, 0.1);

        // poder de habilidade (máx 3x)
        this.skillPowerMultiplier = Math.min(1.0 + spirit * 0.03, 3.0);

        // redução de custo (máx 50%)
        this.kiCostReduction = Math.min(spirit * 0.002, 0.5);
    }

    // ===== GETTERS =====
    public int getSpirit() {
        return spirit;
    }

    public int getCurrentKi() {
        return currentKi;
    }

    public int getMaxKi() {
        return maxKi;
    }

    public double getKiRegenRate() {
        return kiRegenRate;
    }

    public double getSkillPowerMultiplier() {
        return skillPowerMultiplier;
    }

    public double getKiCostReduction() {
        return kiCostReduction;
    }

    // ===== SET =====
    public void setSpirit(int value) {
        this.spirit = Math.max(1, value);
        recalcStats();

        currentKi = Math.min(currentKi, maxKi);
    }

    // ===== KI SYSTEM =====
    public boolean hasKi(int cost) {
        int finalCost = getReducedCost(cost);
        return currentKi >= finalCost;
    }

    public void useKi(int cost) {
        int finalCost = getReducedCost(cost);
        currentKi = Math.max(0, currentKi - finalCost);
    }

    public void restoreKi(int amount) {
        if (amount <= 0) return;
        currentKi = Math.min(maxKi, currentKi + amount);
    }

    private int getReducedCost(int cost) {
        return (int)(cost * (1 - kiCostReduction));
    }

    public double getKiPercentage() {
        return (double) currentKi / maxKi;
    }

    // ===== REGEN =====
    public void regenerateKi() {
        int regen = (int)(maxKi * kiRegenRate);
        restoreKi(regen);
    }

    // ===== TICK =====
    public void tick() {
        regenerateKi();
    }

    // ===== DEBUG =====
    @Override
    public String toString() {
        return "Spi{" +
                "SPI=" + spirit +
                ", KI=" + currentKi + "/" + maxKi +
                ", Regen=" + (int)(kiRegenRate * 100) + "%" +
                ", Power=" + String.format("%.2f", skillPowerMultiplier) +
                '}';
    }
}
