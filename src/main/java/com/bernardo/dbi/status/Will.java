package com.bernardo.dbi.status;

public class Will {

    private int willpower;

    private double mentalResistance;
    private double kiStability;
    private double pressureResistance;

    public Will(int willpower) {
        this.willpower = Math.max(1, willpower);
        recalcStats();
    }

    // ===== RECALC =====
    private void recalcStats() {
        // resistência mental (máx 80%)
        this.mentalResistance = Math.min(willpower * 0.01, 0.8);

        // estabilidade de KI (menos gasto em combate)
        this.kiStability = Math.min(willpower * 0.005, 0.5);

        // resistência sob pressão (low HP boost)
        this.pressureResistance = Math.min(willpower * 0.003, 0.4);
    }

    // ===== GETTERS =====
    public int getWillpower() { return willpower; }
    public double getMentalResistance() { return mentalResistance; }
    public double getKiStability() { return kiStability; }
    public double getPressureResistance() { return pressureResistance; }

    // ===== SET =====
    public void setWillpower(int value) {
        this.willpower = Math.max(1, value);
        recalcStats();
    }

    // ===== EFEITOS =====
    public boolean resistEffect(double chance) {
        return Math.random() < mentalResistance * chance;
    }

    public int reduceKiCost(int cost) {
        return (int)(cost * (1 - kiStability));
    }

    public int boostUnderPressure(int value, double hpPercent) {
        if (hpPercent < 0.3) {
            return (int)(value * (1 + pressureResistance));
        }
        return value;
    }

    @Override
    public String toString() {
        return "Will{" +
                "WILL=" + willpower +
                ", Resist=" + (int)(mentalResistance * 100) + "%" +
                ", Stability=" + (int)(kiStability * 100) + "%" +
                '}';
    }
}
