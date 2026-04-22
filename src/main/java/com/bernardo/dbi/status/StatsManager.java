package com.bernardo.dbi.status;

public class StatsManager {

    private Str str;
    private Con con;
    private Dex dex;
    private Spi spi;
    private Will will;
    private Mnd mnd;

    public StatsManager(int strength, int constitution, int dexterity, int spirit, int willpower, int mind) {
        this.str = new Str(strength);
        this.con = new Con(constitution);
        this.dex = new Dex(dexterity);
        this.spi = new Spi(spirit);
        this.will = new Will(willpower);
        this.mnd = new Mnd(mind);
    }

    // ===== GETTERS =====
    public Str getStr() { return str; }
    public Con getCon() { return con; }
    public Dex getDex() { return dex; }
    public Spi getSpi() { return spi; }
    public Will getWill() { return will; }
    public Mnd getMnd() { return mnd; }

    // ===== TICK =====
    public void tick() {
        con.tickRegeneration();

        // regen KI melhorada pelo MND
        int regen = (int)(spi.getMaxKi() * spi.getKiRegenRate());
        regen = mnd.boostRegen(regen);
        spi.restoreKi(regen);
    }

    // ===== DANO =====
    public void dealDamage(int baseDamage) {
        if (baseDamage <= 0) return;

        // esquiva (DEX)
        if (dex.tryDodge()) return;

        int damage = baseDamage + str.getAttackPower();

        // redução de dano (DEX)
        damage = dex.reduceDamage(damage);

        // resistência sob pressão (WILL)
        double hpPercent = con.getHealthPercentage();
        damage = will.boostUnderPressure(damage, hpPercent);

        con.takeDamage(damage);
    }

    // ===== ATAQUE =====
    public boolean canAttack(int cost) {
        return str.getCurrentAttackPower() >= cost;
    }

    public void useAttack(int cost) {
        if (!canAttack(cost)) return;
        str.useAttack(cost);
    }

    // ===== STAMINA =====
    public boolean canUseStamina(int cost) {
        return con.getCurrentStamina() >= cost;
    }

    public void useStamina(int cost) {
        if (!canUseStamina(cost)) return;
        con.consumeStamina(cost);
    }

    // ===== KI =====
    public boolean canUseKi(int cost) {
        int finalCost = will.reduceKiCost(cost);
        finalCost = mnd.reduceWaste(finalCost);
        return spi.hasKi(finalCost);
    }

    public void useKi(int cost) {
        int finalCost = will.reduceKiCost(cost);
        finalCost = mnd.reduceWaste(finalCost);

        if (spi.hasKi(finalCost)) {
            spi.useKi(finalCost);
        }
    }

    // ===== CURA =====
    public void heal(int amount) {
        con.heal(amount);
    }

    // ===== STATUS =====
    public boolean isDead() {
        return con.getCurrentHealth() <= 0;
    }

    // ===== DEBUG =====
    @Override
    public String toString() {
        return "StatsManager{" +
                "\\n  " + str.toString() +
                "\\n  " + con.toString() +
                "\\n  " + dex.toString() +
                "\\n  " + spi.toString() +
                "\\n  " + will.toString() +
                "\\n  " + mnd.toString() +
                "\\n}";
    }
}
