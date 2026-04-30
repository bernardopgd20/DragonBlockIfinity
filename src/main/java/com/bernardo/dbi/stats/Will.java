package com.bernardo.dbi.stats;
public class Will {
    private int value;
    public Will(int value) { this.value = value; }
    public int getValue() { return value; }
    public int reduceKiCost(int cost) { return Math.max(1, cost - value / 10); }
    public int boostUnderPressure(int damage, double hpPercent) {
        if (hpPercent < 0.3) return (int)(damage * (1.0 + value * 0.01));
        return damage;
    }
    @Override public String toString() { return "Will=" + value; }
}
