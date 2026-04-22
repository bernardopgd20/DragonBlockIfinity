package com.bernardo.dbi.status;

public class Con {
    private int constitution;
    private int currentHealth;
    private int maxHealth;
    private int currentStamina;
    private int maxStamina;

    private double healthRegenRate;
    private double staminaRegenRate;

    public Con(int constitution) {
        this.constitution = constitution;
        this.maxHealth = constitution * 5;
        this.currentHealth = maxHealth;
        this.maxStamina = constitution * 5;
        this.currentStamina = maxStamina;

        this.healthRegenRate = 0.01;
        this.staminaRegenRate = 0.03;
    }

    // ===== GETTERS =====
    public int getConstitution() { return constitution; }
    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }
    public int getCurrentStamina() { return currentStamina; }
    public int getMaxStamina() { return maxStamina; }

    public double getHealthRegenRate() { return healthRegenRate; }
    public double getStaminaRegenRate() { return staminaRegenRate; }

    // ===== SET CONSTITUTION =====
    public void setConstitution(int value) {
        this.constitution = Math.max(1, value);

        this.maxHealth = constitution * 5;
        this.maxStamina = constitution * 5;

        currentHealth = Math.min(currentHealth, maxHealth);
        currentStamina = Math.min(currentStamina, maxStamina);
    }

    // ===== VIDA =====
    public void heal(int amount) {
        if (amount <= 0) return;
        currentHealth = Math.min(maxHealth, currentHealth + amount);
    }

    public void takeDamage(int amount) {
        if (amount <= 0) return;
        currentHealth = Math.max(0, currentHealth - amount);
    }

    public double getHealthPercentage() {
        return (double) currentHealth / maxHealth;
    }

    // ===== STAMINA =====
    public void restoreStamina(int amount) {
        if (amount <= 0) return;
        currentStamina = Math.min(maxStamina, currentStamina + amount);
    }

    public void consumeStamina(int amount) {
        if (amount <= 0) return;
        currentStamina = Math.max(0, currentStamina - amount);
    }

    public double getStaminaPercentage() {
        return (double) currentStamina / maxStamina;
    }

    // ===== REGEN =====
    public void regenerateHealth() {
        int regen = (int)(maxHealth * healthRegenRate);
        heal(regen);
    }

    public void regenerateStamina() {
        int regen = (int)(maxStamina * staminaRegenRate);
        restoreStamina(regen);
    }

    public void tickRegeneration() {
        regenerateHealth();
        regenerateStamina();
    }

    // ===== BONUS =====
    public int getLifeBonus() {
        return constitution * 5;
    }

    public int getStaminaBonus() {
        return constitution * 5;
    }

    public void boostRegenFromConstitution() {
        double bonus = constitution * 0.001;
        healthRegenRate = Math.min(healthRegenRate + bonus, 0.2);
        staminaRegenRate = Math.min(staminaRegenRate + bonus, 0.3);
    }

    @Override
    public String toString() {
        return "Con{" +
                "Con=" + constitution +
                ", HP=" + currentHealth + "/" + maxHealth +
                ", STM=" + currentStamina + "/" + maxStamina +
                '}';
    }
}
