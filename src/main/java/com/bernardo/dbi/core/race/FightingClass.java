package com.bernardo.dbi.core.race;

public enum FightingClass {
    WARRIOR("Warrior"), 
    MARTIAL_ARTIST("Martial Artist"), 
    SPIRITUALIST("Spiritualist");

    private final String name;
    FightingClass(String name) { this.name = name; }
    public String getName() { return name; }
}
