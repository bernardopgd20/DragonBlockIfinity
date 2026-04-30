package com.bernardo.dbi.stats;
public class Spi {
    private int value;
    private int currentKi;
    public Spi(int value) { this.value = value; this.currentKi = value * 10; }
    public int getValue() { return value; }
    public int getMaxKi() { return value * 10; }
    public int getCurrentKi() { return currentKi; }
    public float getKiRegenRate() { return value * 0.01f; }
    public boolean hasKi(int amount) { return currentKi >= amount; }
    public void useKi(int amount) { currentKi = Math.max(0, currentKi - amount); }
    public void restoreKi(int amount) { currentKi = Math.min(value * 10, currentKi + amount); }
    @Override public String toString() { return "Spi=" + value; }
}
