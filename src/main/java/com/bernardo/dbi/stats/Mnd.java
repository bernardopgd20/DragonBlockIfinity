package com.bernardo.dbi.stats;
public class Mnd {
    private int value;
    public Mnd(int value) { this.value = value; }
    public int getValue() { return value; }
    public int boostRegen(int regen) { return (int)(regen * (1.0 + value * 0.005)); }
    public int reduceWaste(int cost) { return Math.max(1, cost - value / 20); }
    @Override public String toString() { return "Mnd=" + value; }
}
