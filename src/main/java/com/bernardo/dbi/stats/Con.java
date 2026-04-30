package com.bernardo.dbi.stats;
public class Con {
    private int value;
    private int currentHealth;
    private int stamina;
    public Con(int value) { this.value = value; this.currentHealth = value * 10; this.stamina = value * 5; }
    public int getValue() { return value; }
    public int getCurrentHealth() { return currentHealth; }
    public double getHealthPercentage() { return (double) currentHealth / (value * 10); }
    public void takeDamage(int dmg) { currentHealth = Math.max(0, currentHealth - dmg); }
    public void heal(int amount) { currentHealth = Math.min(value * 10, currentHealth + amount); }
    public int getCurrentStamina() { return stamina; }
    public void consumeStamina(int cost) { stamina = Math.max(0, stamina - cost); }
    public void tickRegeneration() { stamina = Math.min(value * 5, stamina + 1); }
    public int reduceDamage(int damage) { return Math.max(0, damage - value / 2); }
    @Override public String toString() { return "Con=" + value; }
}
