package com.aleksiprograms.battleagainstshapes.managers;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.toolbox.WeaponStats;
import com.badlogic.gdx.utils.Array;

/**
 * Manages the stats of the game mode, which the player is currently playing.
 */
public class InGameStatsManager {

    private TheGame game;
    private long score;
    private long combo;
    private long distance;
    private long fighterKills;
    private Array<WeaponStats> statsForWeapons;
    private boolean newBestScore;
    private boolean newBestDistance;

    public InGameStatsManager(TheGame game) {
        this.game = game;
    }

    public void onGameOver() {
        long oldScore = game.getSaveManager().getSaveData().getGameModeScore(
                game.getGameScreen().getGameMode().getGameModeID());
        int stars = 0;
        if ((oldScore < score) || (oldScore == 0)) {
            game.getSaveManager().getSaveData().setGameModeScore
                    (game.getGameScreen().getGameMode().getGameModeID(), score);
            if (score >= game.getGameScreen().getGameMode().getScoreToOneStar()) {
                stars = 1;
            }
            if (score >= game.getGameScreen().getGameMode().getScoreToTwoStar()) {
                stars = 2;
            }
            if (score >= game.getGameScreen().getGameMode().getScoreToThreeStar()) {
                stars = 3;
            }
            game.getSaveManager().getSaveData().setGameModeStars(
                    game.getGameScreen().getGameMode().getGameModeID(), stars);
            newBestScore = true;
        }
        long oldDistance = game.getSaveManager().getSaveData().getGameModeDistance(
                game.getGameScreen().getGameMode().getGameModeID());
        if ((oldDistance < distance) || (oldDistance == 0)) {
            game.getSaveManager().getSaveData().setGameModeDistance(
                    game.getGameScreen().getGameMode().getGameModeID(), distance);
            newBestDistance = true;
        }
        saveStats();
    }

    public void onPause() {
        saveStats();
    }

    private void saveStats() {
        game.getSaveManager().getSaveData().addToFighterKills(getFighterKills());
        for (WeaponStats weaponStats : statsForWeapons) {
            game.getSaveManager().getSaveData().addToWeaponKills(
                    weaponStats.getWeaponID(), weaponStats.getKills());
            game.getSaveManager().getSaveData().addToWeaponAmmunitionsFired(
                    weaponStats.getWeaponID(), weaponStats.getAmmunitionsFired());
            game.getSaveManager().getSaveData().addToWeaponAmmunitionsHit(
                    weaponStats.getWeaponID(), weaponStats.getAmmunitionsHit());
        }
        resetWeaponStats();
    }

    public void reset() {
        score = 0;
        combo = 0;
        distance = 0;
        fighterKills = 0;
        statsForWeapons = new Array<WeaponStats>();
        newBestScore = false;
        newBestDistance = false;
    }

    public void resetWeaponStats() {
        fighterKills = 0;
        statsForWeapons.clear();
    }

    public void addToScore() {
        combo++;
        score += combo * 1;
    }

    public void addToDistance() {
        distance++;
    }

    public void breakCombo() {
        combo = 0;
    }

    public long getScore() {
        return score;
    }

    public long getCombo() {
        return combo;
    }

    public long getDistance() {
        return distance;
    }

    public long getFighterKills() {
        return fighterKills;
    }

    public void addWeapon(int weaponID) {
        statsForWeapons.add(new WeaponStats(weaponID));
    }

    public WeaponStats getWeaponStats(int weaponID) {
        for (WeaponStats weaponStats : statsForWeapons) {
            if (weaponID == weaponStats.getWeaponID()) {
                return weaponStats;
            }
        }
        return null;
    }

    public boolean isNewBestScore() {
        return newBestScore;
    }

    public boolean isNewBestDistance() {
        return newBestDistance;
    }

    public void addToFighterKills() {
        fighterKills += 1;
    }

    public void addToWeaponKills(int weaponID) {
        for (WeaponStats weaponStats : statsForWeapons) {
            if (weaponID == weaponStats.getWeaponID()) {
                weaponStats.addOneToKills();
            }
        }
    }

    public void addToWeaponAmmunitionsFired(int weaponID) {
        for (WeaponStats weaponStats : statsForWeapons) {
            if (weaponID == weaponStats.getWeaponID()) {
                weaponStats.addOneToAmmunitionsFired();
            }
        }
    }

    public void addToWeaponAmmunitionsHit(int weaponID) {
        for (WeaponStats weaponStats : statsForWeapons) {
            if (weaponID == weaponStats.getWeaponID()) {
                weaponStats.addOneToAmmunitionsHit();
            }
        }
    }
}