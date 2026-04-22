package com.bernardo.dbi.status;

public class Str {

    private int strength;
    private int attackPower;
    private int currentAttackPower;

    public Str(int strength) {
        this.strength = Math.max(1, strength);
        recalcStats();
        this.currentAttackPower = attackPower;
    }

    // ===== RE-CALC =====
    private void recalcStats() {
        this.attackPower = strength * 2;
    }

    // ===== GETTERS =====
    public int getStrength() {
        return strength;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getCurrentAttackPower() {
        return currentAttackPower;
    }

    // ===== SETTERS =====
    public void setStrength(int value) {
        this.strength = Math.max(1, value);
        recalcStats();

        // mantém current dentro do limite
        currentAttackPower = Math.min(currentAttackPower, attackPower);
    }

    public void setCurrentAttackPower(int value) {
        this.currentAttackPower = Math.max(0, Math.min(value, attackPower));
    }

    // ===== SISTEMA DE ATAQUE =====
    public void useAttack(int cost) {
        if (cost <= 0) return;
        currentAttackPower = Math.max(0, currentAttackPower - cost);
    }

    public void restoreAttack(int amount) {
        if (amount <= 0) return;
        currentAttackPower = Math.min(attackPower, currentAttackPower + amount);
    }

    public double getAttackPercentage() {
        return (double) currentAttackPower / attackPower;
    }

    // ===== BONUS =====
    public int getStrengthBonus() {
        return strength * 2;
    }

    @Override
    public String toString() {
        return "Str{" +
                "STR=" + strength +
                ", ATK=" + currentAttackPower + "/" + attackPower +
                '}';
    }
}
