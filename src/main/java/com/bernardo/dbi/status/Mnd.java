package com.bernardo.dbi.status;

public class Mnd {

    private int mind;

    private double kiEfficiency;
    private double regenBoost;
    private double controlBonus;

    public Mnd(int mind) {
        this.mind = Math.max(1, mind);
        recalcStats();
    }

    // ===== RECALC =====
    private void recalcStats() {
        // eficiência (mais dano com menos KI)
        this.kiEfficiency = Math.min(mind * 0.01, 0.7);

        // boost de regen
        this.regenBoost = Math.min(mind * 0.005, 0.5);

        // controle (menos desperdício)
        this.controlBonus = Math.min(mind * 0.003, 0.4);
    }

    // ===== GETTERS =====
    public int getMind() { return mind; }
    public double getKiEfficiency() { return kiEfficiency; }
    public double getRegenBoost() { return regenBoost; }
    public double getControlBonus() { return controlBonus; }

    // ===== SET =====
    public void setMind(int value) {
        this.mind = Math.max(1, value);
        recalcStats();
    }

    // ===== EFEITOS =====
    public int reduceWaste(int cost) {
        return (int)(cost * (1 - controlBonus));
    }

    public int boostRegen(int baseRegen) {
        return (int)(baseRegen * (1 + regenBoost));
    }

    public double boostSkillPower(double basePower) {
        return basePower * (1 + kiEfficiency);
    }

    @Override
    public String toString() {
        return "Mnd{" +
                "MND=" + mind +
                ", Eff=" + (int)(kiEfficiency * 100) + "%" +
                ", Regen=" + (int)(regenBoost * 100) + "%" +
                '}';
    }
}
