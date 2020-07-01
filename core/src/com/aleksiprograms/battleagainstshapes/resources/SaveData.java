package com.aleksiprograms.battleagainstshapes.resources;

import com.badlogic.gdx.utils.Array;

import java.io.Serializable;

/**
 * Holds game data, which are saved to the file.
 */
public class SaveData implements Serializable {

    private static class GameModeData {
        int gameModeID;
        long score;
        long distance;
        int stars;
    }

    private static class WeaponData {
        int weaponID;
        long kills;
        long ammunitionsFired;
        long ammunitionsHit;
    }

    private long appTime;
    private long gameTime;
    private int selectedPrimaryWeapon;
    private int selectedSecondaryWeapon;
    private long fighterKills;
    private float soundVolume;
    private Array<GameModeData> gameModeDataArray;
    private Array<WeaponData> weaponDataArray;

    public SaveData() {
        appTime = 0;
        gameTime = 0;
        selectedPrimaryWeapon = 0;
        selectedSecondaryWeapon = 1;
        fighterKills = 0;
        soundVolume = 0.5f;

        gameModeDataArray = new Array<GameModeData>();
        for (int i = 0; i < Constants.GAME_MODES; i++) {
            GameModeData gameModeData = new GameModeData();
            gameModeData.gameModeID = i;
            gameModeData.score = 0;
            gameModeData.distance = 0;
            gameModeData.stars = 0;
            this.gameModeDataArray.add(gameModeData);
        }
        weaponDataArray = new Array<WeaponData>();
        for (int i = 0; i < 8; i++) {
            WeaponData weaponData = new WeaponData();
            weaponData.weaponID = i;
            weaponData.kills = 0;
            weaponData.ammunitionsFired = 0;
            weaponData.ammunitionsHit = 0;
            weaponDataArray.add(weaponData);
        }
    }

    public long getAppTime() {
        return appTime;
    }

    public void addToAppTime(long appTime) {
        this.appTime += appTime;
    }

    public long getGameTime() {
        return gameTime;
    }

    public void addToGameTime(long gameTime) {
        this.gameTime += gameTime;
    }

    public long getTotalStars() {
        int stars = 0;
        for (int i = 0; i < gameModeDataArray.size; i++) {
            stars += gameModeDataArray.get(i).stars;
        }
        return stars;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        if (soundVolume < 0) {
            soundVolume = 0;
        }
        if (soundVolume > 1) {
            soundVolume = 1;
        }
        this.soundVolume = soundVolume;
    }

    public int getSelectedPrimaryWeapon() {
        return selectedPrimaryWeapon;
    }

    public void setSelectedPrimaryWeapon(int selectedPrimaryWeapon) {
        this.selectedPrimaryWeapon = selectedPrimaryWeapon;
    }

    public int getSelectedSecondaryWeapon() {
        return selectedSecondaryWeapon;
    }

    public void setSelectedSecondaryWeapon(int selectedSecondaryWeapon) {
        this.selectedSecondaryWeapon = selectedSecondaryWeapon;
    }

    public long getGameModeScore(int gameModeID) {
        for (GameModeData gameModeData : gameModeDataArray) {
            if (gameModeData.gameModeID == gameModeID) {
                return gameModeData.score;
            }
        }
        return 0;
    }

    public void setGameModeScore(int gameModeID, long score) {
        for (GameModeData gameModeData : gameModeDataArray) {
            if (gameModeData.gameModeID == gameModeID) {
                gameModeData.score = score;
                break;
            }
        }
    }

    public long getGameModeDistance(int gameModeID) {
        for (GameModeData gameModeData : gameModeDataArray) {
            if (gameModeData.gameModeID == gameModeID) {
                return gameModeData.distance;
            }
        }
        return 0;
    }

    public void setGameModeDistance(int gameModeID, long distance) {
        for (GameModeData gameModeData : gameModeDataArray) {
            if (gameModeData.gameModeID == gameModeID) {
                gameModeData.distance = distance;
                break;
            }
        }
    }

    public long getGameModeStars(int gameModeID) {
        for (GameModeData gameModeData : gameModeDataArray) {
            if (gameModeData.gameModeID == gameModeID) {
                return gameModeData.stars;
            }
        }
        return 0;
    }

    public void setGameModeStars(int gameModeID, int stars) {
        for (GameModeData gameModeData : gameModeDataArray) {
            if (gameModeData.gameModeID == gameModeID) {
                gameModeData.stars = stars;
                break;
            }
        }
    }

    public long getTotalKills() {
        long kills = fighterKills;
        for (WeaponData weaponData : weaponDataArray) {
            kills += weaponData.kills;
        }
        return kills;
    }

    public float getTotalAccuracy() {
        int ammunitionsFired = 0;
        int ammunitionsHit = 0;
        for (WeaponData weaponData : weaponDataArray) {
            ammunitionsFired += weaponData.ammunitionsFired;
            ammunitionsHit += weaponData.ammunitionsHit;
        }
        if (ammunitionsFired > 0) {
            return ((float) ammunitionsHit / (float) ammunitionsFired) * 100;
        } else {
            return 0;
        }
    }

    public long getFighterKills() {
        return fighterKills;
    }

    public long getWeaponKills(int weaponID) {
        for (WeaponData weaponData : weaponDataArray) {
            if (weaponData.weaponID == weaponID) {
                return weaponData.kills;
            }
        }
        return 0;
    }

    public float getWeaponAccuracy(int weaponID) {
        for (WeaponData weaponData : weaponDataArray) {
            if (weaponData.weaponID == weaponID) {
                if (weaponData.ammunitionsFired > 0) {
                    return ((float) weaponData.ammunitionsHit / (float) weaponData.ammunitionsFired) * 100;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    public void addToFighterKills(long kills) {
        fighterKills += kills;
    }

    public void addToWeaponKills(int weaponID, long kills) {
        for (WeaponData weaponData : weaponDataArray) {
            if (weaponData.weaponID == weaponID) {
                weaponData.kills += kills;
                break;
            }
        }
    }

    public void addToWeaponAmmunitionsFired(int weaponID, long ammunitionsFired) {
        for (WeaponData weaponData : weaponDataArray) {
            if (weaponData.weaponID == weaponID) {
                weaponData.ammunitionsFired += ammunitionsFired;
                break;
            }
        }
    }

    public void addToWeaponAmmunitionsHit(int weaponID, long ammunitionsHit) {
        for (WeaponData weaponData : weaponDataArray) {
            if (weaponData.weaponID == weaponID) {
                weaponData.ammunitionsHit += ammunitionsHit;
                break;
            }
        }
    }
}