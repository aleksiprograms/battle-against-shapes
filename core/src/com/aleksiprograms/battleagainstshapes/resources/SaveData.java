package com.aleksiprograms.battleagainstshapes.resources;

import com.badlogic.gdx.utils.Array;

import java.io.Serializable;

/**
 * Holds game data, which are saved to the file.
 */
public class SaveData implements Serializable {

    private static class GameModeData {
        int index;
        long score;
        long distance;
        int stars;
    }

    private static class WeaponData {
        int ID;
        boolean unlocked;
        long kills;
        long ammunitionsFired;
        long ammunitionsHit;
    }

    private long appTime;
    private long gameTime;
    private float homeScreenScrollX;
    private float priWeaScrollY;
    private float secWeaScrollY;
    private int primaryWeapon;
    private int secondaryWeapon;
    private long fighterKills;
    private float soundVolume;
    private Array<GameModeData> levelDataArray;
    private Array<WeaponData> weaponDataArray;

    public SaveData() {
        appTime = 0;
        gameTime = 0;
        homeScreenScrollX = 0;
        primaryWeapon = 0;
        secondaryWeapon = 1;
        fighterKills = 0;
        soundVolume = 0.5f;

        levelDataArray = new Array<GameModeData>();
        for (int i = 0; i < Constants.GAME_MODES; i++) {
            GameModeData gameModeData = new GameModeData();
            gameModeData.index = i;
            gameModeData.score = 0;
            gameModeData.distance = 0;
            gameModeData.stars = 0;
            levelDataArray.add(gameModeData);
        }
        weaponDataArray = new Array<WeaponData>();
        for (int i = 0; i < 8; i++) {
            WeaponData weaponData = new WeaponData();
            weaponData.ID = i;
            weaponData.unlocked = false;
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

    public long getTotalScore() {
        int score = 0;
        for (int i = 0; i < levelDataArray.size; i++) {
            score += levelDataArray.get(i).score;
        }
        return score;
    }

    public long getTotalStars() {
        int stars = 0;
        for (int i = 0; i < levelDataArray.size; i++) {
            stars += levelDataArray.get(i).stars;
        }
        return stars;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        if (soundVolume < 0)
            soundVolume = 0;
        if (soundVolume > 1)
            soundVolume = 1;
        this.soundVolume = soundVolume;
    }

    public int getPrimaryWeapon() {
        return primaryWeapon;
    }

    public void setPrimaryWeapon(int primaryWeapon) {
        this.primaryWeapon = primaryWeapon;
    }

    public int getSecondaryWeapon() {
        return secondaryWeapon;
    }

    public void setSecondaryWeapon(int secondaryWeapon) {
        this.secondaryWeapon = secondaryWeapon;
    }

    public float getHomeScreenScrollX() {
        return homeScreenScrollX;
    }

    public void setHomeScreenScrollX(float homeScreenScrollX) {
        this.homeScreenScrollX = homeScreenScrollX;
    }

    public float getPriWeaScrollY() {
        return priWeaScrollY;
    }

    public void setPriWeaScrollY(float priWeaScrollY) {
        this.priWeaScrollY = priWeaScrollY;
    }

    public float getSecWeaScrollY() {
        return secWeaScrollY;
    }

    public void setSecWeaScrollY(float secWeaScrollY) {
        this.secWeaScrollY = secWeaScrollY;
    }

    public long getLevelScore(int levelIndex) {
        for(GameModeData gameModeData : levelDataArray) {
            if(gameModeData.index == levelIndex) {
                return gameModeData.score;
            }
        }
        return 0;
    }

    public void setLevelScore(int levelIndex, long score) {
        for(GameModeData gameModeData : levelDataArray) {
            if(gameModeData.index == levelIndex) {
                gameModeData.score = score;
                break;
            }
        }
    }

    public long getLevelDistance(int levelIndex) {
        for(GameModeData gameModeData : levelDataArray) {
            if(gameModeData.index == levelIndex) {
                return gameModeData.distance;
            }
        }
        return 0;
    }

    public void setLevelDistance(int levelIndex, long distance) {
        for(GameModeData gameModeData : levelDataArray) {
            if(gameModeData.index == levelIndex) {
                gameModeData.distance = distance;
                break;
            }
        }
    }

    public long getLevelStars(int levelIndex) {
        for(GameModeData gameModeData : levelDataArray) {
            if(gameModeData.index == levelIndex) {
                return gameModeData.stars;
            }
        }
        return 0;
    }

    public void setLevelStars(int levelIndex, int stars) {
        for(GameModeData gameModeData : levelDataArray) {
            if(gameModeData.index == levelIndex) {
                gameModeData.stars = stars;
                break;
            }
        }
    }

    public long getTotalKills() {
        long kills = fighterKills;
        for(WeaponData weaponData : weaponDataArray) {
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

    public boolean isWeaponUnlocked(int ID) {
        for(WeaponData weaponData : weaponDataArray) {
            if(weaponData.ID == ID) {
                return weaponData.unlocked;
            }
        }
        return false;
    }

    public long getWeaponKills(int ID) {
        for(WeaponData weaponData : weaponDataArray) {
            if(weaponData.ID == ID) {
                return weaponData.kills;
            }
        }
        return 0;
    }

    public float getWeaponAccuracy(int ID) {
        for(WeaponData weaponData : weaponDataArray) {
            if(weaponData.ID == ID) {
                if (weaponData.ammunitionsFired > 0) {
                    return ((float)weaponData.ammunitionsHit / (float)weaponData.ammunitionsFired) * 100;
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

    public void setWeaponUnlocked(int ID, boolean unlocked) {
        for(WeaponData weaponData : weaponDataArray) {
            if(weaponData.ID == ID) {
                weaponData.unlocked = unlocked;
                break;
            }
        }
    }

    public void addToWeaponKills(int ID, long kills) {
        for(WeaponData weaponData : weaponDataArray) {
            if(weaponData.ID == ID) {
                weaponData.kills += kills;
                break;
            }
        }
    }

    public void addToWeaponAmmunitionsFired(int ID, long ammunitionsFired) {
        for(WeaponData weaponData : weaponDataArray) {
            if(weaponData.ID == ID) {
                weaponData.ammunitionsFired += ammunitionsFired;
                break;
            }
        }
    }

    public void addToWeaponAmmunitionsHit(int ID, long ammunitionsHit) {
        for(WeaponData weaponData : weaponDataArray) {
            if(weaponData.ID == ID) {
                weaponData.ammunitionsHit += ammunitionsHit;
                break;
            }
        }
    }
}