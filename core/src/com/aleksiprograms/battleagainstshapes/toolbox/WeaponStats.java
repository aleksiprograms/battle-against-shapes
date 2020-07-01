package com.aleksiprograms.battleagainstshapes.toolbox;

public class WeaponStats {

    private int weaponID;
    private long kills;
    private long ammunitionsFired;
    private long ammunitionsHit;

    public WeaponStats(int weaponID) {
        this.weaponID = weaponID;
    }

    public void addOneToKills() {
        kills += 1;
    }

    public void addOneToAmmunitionsFired() {
        ammunitionsFired += 1;
    }

    public void addOneToAmmunitionsHit() {
        ammunitionsHit += 1;
    }

    public int getWeaponID() {
        return weaponID;
    }

    public long getKills() {
        return kills;
    }

    public long getAmmunitionsFired() {
        return ammunitionsFired;
    }

    public long getAmmunitionsHit() {
        return ammunitionsHit;
    }
}