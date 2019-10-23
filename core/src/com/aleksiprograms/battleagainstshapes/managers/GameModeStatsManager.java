package com.aleksiprograms.battleagainstshapes.managers;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.toolbox.WeaponStats;

/**
 * Manages the stats of the game mode, which the player is currently playing.
 */
public class GameModeStatsManager {

    private TheGame game;
    private long score = 0;
    private long combo = 0;
    private long distance = 0;
    private long fighterKills = 0;
    private WeaponStats statsPriWea;
    private WeaponStats statsSecWea;

    public GameModeStatsManager(TheGame game) {
        this.game = game;
    }

    public void onComplete() {
        long oldScore = game.saveManager.saveData.getLevelScore(game.gameMode.id);
        int stars = 0;
        if ((oldScore < score) || (oldScore == 0)) {
            game.saveManager.saveData.setLevelScore(
                    game.gameMode.id, score);
            if (score >= game.gameMode.scoreToOneStar)
                stars = 1;
            if (score >= game.gameMode.scoreToTwoStar)
                stars = 2;
            if (score >= game.gameMode.scoreToThreeStar)
                stars = 3;
            game.saveManager.saveData.setLevelStars(
                    game.gameMode.id, stars);
            game.gameStateManager.setNewBestScore(true);
        }
        long oldDistance = game.saveManager.saveData.getLevelDistance(game.gameMode.id);
        if ((oldDistance < distance) || (oldDistance == 0)) {
            game.saveManager.saveData.setLevelDistance(
                    game.gameMode.id, distance);
            game.gameStateManager.setNewBestDistance(true);
        }
        saveStats();
    }

    public void onPause() {
        saveStats();
    }

    private void saveStats() {
        game.saveManager.saveData.addToFighterKills(game.player.gameModeStatsManager.getFighterKills());
        game.saveManager.saveData.addToWeaponKills(game.player.gameModeStatsManager.getPriWeaStats().ID, game.player.gameModeStatsManager.getPriWeaStats().kills);
        game.saveManager.saveData.addToWeaponKills(game.player.gameModeStatsManager.getSecWeaStats().ID, game.player.gameModeStatsManager.getSecWeaStats().kills);
        game.saveManager.saveData.addToWeaponAmmunitionsFired(game.player.gameModeStatsManager.getPriWeaStats().ID, game.player.gameModeStatsManager.getPriWeaStats().ammunitionsFired);
        game.saveManager.saveData.addToWeaponAmmunitionsFired(game.player.gameModeStatsManager.getSecWeaStats().ID, game.player.gameModeStatsManager.getSecWeaStats().ammunitionsFired);
        game.saveManager.saveData.addToWeaponAmmunitionsHit(game.player.gameModeStatsManager.getPriWeaStats().ID, game.player.gameModeStatsManager.getPriWeaStats().ammunitionsHit);
        game.saveManager.saveData.addToWeaponAmmunitionsHit(game.player.gameModeStatsManager.getSecWeaStats().ID, game.player.gameModeStatsManager.getSecWeaStats().ammunitionsHit);
        game.player.gameModeStatsManager.resetWeaponStats();
    }

    public void reset() {
        score = 0;
        combo = 0;
        distance = 0;
        fighterKills = 0;
        statsPriWea = new WeaponStats();
        statsSecWea = new WeaponStats();
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

    public WeaponStats getPriWeaStats() {
        return statsPriWea;
    }

    public WeaponStats getSecWeaStats() {
        return statsSecWea;
    }

    public void setPriWeaID(int ID) {
        statsPriWea.ID = ID;
    }

    public void setSecWeaID(int ID) {
        statsSecWea.ID = ID;
    }

    public void resetWeaponStats() {
        fighterKills = 0;
        statsPriWea.kills = 0;
        statsPriWea.ammunitionsFired = 0;
        statsPriWea.ammunitionsHit = 0;
        statsSecWea.kills = 0;
        statsSecWea.ammunitionsFired = 0;
        statsSecWea.ammunitionsHit = 0;
    }

    public void addToFighterKills() {
        fighterKills += 1;
    }

    public void addToPriWeaKills() {
        statsPriWea.kills += 1;
    }

    public void addToSecWeaKills() {
        statsSecWea.kills += 1;
    }

    public void addToPriWeaAmmunitionsFired() {
        statsPriWea.ammunitionsFired += 1;
    }

    public void addToSecWeaAmmunitionsFired() {
        statsSecWea.ammunitionsFired += 1;
    }

    public void addToPriWeaAmmunitionsHit() {
        statsPriWea.ammunitionsHit += 1;
    }

    public void addToSecWeaAmmunitionsHit() {
        statsSecWea.ammunitionsHit += 1;
    }
}