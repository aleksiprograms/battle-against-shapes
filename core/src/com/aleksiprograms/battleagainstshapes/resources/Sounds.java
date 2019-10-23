package com.aleksiprograms.battleagainstshapes.resources;

import com.badlogic.gdx.audio.Sound;
import com.aleksiprograms.battleagainstshapes.TheGame;

/**
 * Holds sound effects of the game.
 */
public class Sounds {

    private Sound buttonPosSound;
    private Sound buttonNegSound;
    private Sound enemyHitSound;
    private Sound enemyExplosionSound;
    private Sound enemyAmmunitionSound;
    private Sound explosionSmallASound;
    private Sound explosionSmallBSound;
    private Sound explosionSmallCSound;
    private Sound explosionBigASound;
    private Sound explosionBigBSound;
    private Sound explosionBigCSound;
    private Sound bulletSound;
    private Sound shotSound;
    private Sound knifeSound;
    private Sound grenadeSound;
    private Sound rocketSound;
    private Sound dynamiteASound;
    private Sound dynamiteBSound;
    private Sound dynamiteCSound;
    private Sound bladeSound;

    private int dynamiteSoundIndex;
    private int explosionSmallSoundIndex;
    private int explosionBigSoundIndex;
    
    public Sounds(TheGame game) {
        buttonPosSound = game.assetManager.get(Constants.SOUND_SRC_BUTTON_POS);
        buttonNegSound = game.assetManager.get(Constants.SOUND_SRC_BUTTON_NEG);
        enemyHitSound = game.assetManager.get(Constants.SOUND_SRC_ENEMY_HIT);
        enemyExplosionSound = game.assetManager.get(Constants.SOUND_SRC_ENEMY_EXPLOSION);
        enemyAmmunitionSound = game.assetManager.get(Constants.SOUND_SRC_ENEMY_AMMUNITION);
        explosionSmallASound = game.assetManager.get(Constants.SOUND_SRC_EXPLOSION_SMALL_A);
        explosionSmallBSound = game.assetManager.get(Constants.SOUND_SRC_EXPLOSION_SMALL_B);
        explosionSmallCSound = game.assetManager.get(Constants.SOUND_SRC_EXPLOSION_SMALL_C);
        explosionBigASound = game.assetManager.get(Constants.SOUND_SRC_EXPLOSION_BIG_A);
        explosionBigBSound = game.assetManager.get(Constants.SOUND_SRC_EXPLOSION_BIG_B);
        explosionBigCSound = game.assetManager.get(Constants.SOUND_SRC_EXPLOSION_BIG_C);
        bulletSound = game.assetManager.get(Constants.SOUND_SRC_BULLET);
        shotSound = game.assetManager.get(Constants.SOUND_SRC_SHOT);
        knifeSound = game.assetManager.get(Constants.SOUND_SRC_KNIFE);
        grenadeSound = game.assetManager.get(Constants.SOUND_SRC_GRENADE);
        rocketSound = game.assetManager.get(Constants.SOUND_SRC_ROCKET);
        dynamiteASound = game.assetManager.get(Constants.SOUND_SRC_DYNAMITE_A);
        dynamiteBSound = game.assetManager.get(Constants.SOUND_SRC_DYNAMITE_B);
        dynamiteCSound = game.assetManager.get(Constants.SOUND_SRC_DYNAMITE_C);
        bladeSound = game.assetManager.get(Constants.SOUND_SRC_BLADE);
        dynamiteSoundIndex = 0;
        explosionSmallSoundIndex = 0;
        explosionBigSoundIndex = 0;
    }
    
    public Sound getSoundByID(String soundID) {
        if (soundID.equals(Constants.SOUND_SRC_BUTTON_POS))
            return buttonPosSound;
        if (soundID.equals(Constants.SOUND_SRC_BUTTON_NEG))
            return buttonNegSound;
        if (soundID.equals(Constants.SOUND_SRC_ENEMY_HIT))
            return enemyHitSound;
        if (soundID.equals(Constants.SOUND_SRC_ENEMY_EXPLOSION))
            return enemyExplosionSound;
        if (soundID.equals(Constants.SOUND_SRC_ENEMY_AMMUNITION))
            return enemyAmmunitionSound;
        if (soundID.equals(Constants.SOUND_SRC_EXPLOSION_SMALL_A)) {
            switch (explosionSmallSoundIndex) {
                case 0:
                    explosionSmallSoundIndex += 1;
                    return explosionSmallASound;
                case 1:
                    explosionSmallSoundIndex += 1;
                    return explosionSmallBSound;
                case 2:
                    explosionSmallSoundIndex = 0;
                    return explosionSmallCSound;
                default:
                    return explosionSmallASound;
            }
        }
        if (soundID.equals(Constants.SOUND_SRC_EXPLOSION_BIG_A)) {
            switch (explosionBigSoundIndex) {
                case 0:
                    explosionBigSoundIndex += 1;
                    return explosionBigASound;
                case 1:
                    explosionBigSoundIndex += 1;
                    return explosionBigBSound;
                case 2:
                    explosionBigSoundIndex = 0;
                    return explosionBigCSound;
                default:
                    return explosionBigASound;
            }
        }
        if (soundID.equals(Constants.SOUND_SRC_BULLET))
            return bulletSound;
        if (soundID.equals(Constants.SOUND_SRC_SHOT))
            return shotSound;
        if (soundID.equals(Constants.SOUND_SRC_KNIFE))
            return knifeSound;
        if (soundID.equals(Constants.SOUND_SRC_GRENADE))
            return grenadeSound;
        if (soundID.equals(Constants.SOUND_SRC_ROCKET))
            return rocketSound;
        if (soundID.equals(Constants.SOUND_SRC_DYNAMITE_A)) {
            switch (dynamiteSoundIndex) {
                case 0:
                    dynamiteSoundIndex += 1;
                    return dynamiteASound;
                case 1:
                    dynamiteSoundIndex += 1;
                    return dynamiteBSound;
                case 2:
                    dynamiteSoundIndex = 0;
                    return dynamiteCSound;
                default:
                    return dynamiteASound;
            }
        }
        if (soundID.equals(Constants.SOUND_SRC_BLADE))
            return bladeSound;
        return buttonPosSound;
    }
}