package com.bernardo.dbi.stats;
import java.util.Random;
public class Dex {
    private int value;
    private static final Random RNG = new Random();
    public Dex(int value) { this.value = value; }
    public int getValue() { return value; }
    public boolean tryDodge() { return RNG.nextInt(100) < value / 2; }
    public int reduceDamage(int damage) { return Math.max(0, damage - value / 3); }
    @Override public String toString() { return "Dex=" + value; }
}
