package com.bernardo.dbi.status;

public class Stamina {
    private int stamina;

    public Stamina() {
        this.stamina = 0;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int value) {
        this.stamina = Math.max(0, value);
    }

    public void addStamina(int value) {
        this.stamina = Math.max(0, this.stamina + value);
    }

    public int getStaminaBonus() {
        return stamina * 6;
    }
}
