package com.bernardo.dbi.stats;
public class Str {
    private int value;
    public Str(int value) { this.value = value; }
    public int getValue() { return value; }
    public int getAttackPower() { return value * 2; }
    public int getCurrentAttackPower() { return value * 2; }
    public void useAttack(int cost) { value = Math.max(0, value - cost); }
    @Override public String toString() { return "Str=" + value; }
}
