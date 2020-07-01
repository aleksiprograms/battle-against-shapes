package com.aleksiprograms.battleagainstshapes.resources;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.audio.Sound;

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
        buttonPosSound = game.getResources().getAssetManager().get(
                Constants.SOUND_BUTTON_POS);
        buttonNegSound = game.getResources().getAssetManager().get(
                Constants.SOUND_BUTTON_NEG);
        enemyHitSound = game.getResources().getAssetManager().get(
                Constants.SOUND_ENEMY_HIT);
        enemyExplosionSound = game.getResources().getAssetManager().get(
                Constants.SOUND_ENEMY_EXPLOSION);
        enemyAmmunitionSound = game.getResources().getAssetManager().get(
                Constants.SOUND_ENEMY_AMMUNITION);
        explosionSmallASound = game.getResources().getAssetManager().get(
                Constants.SOUND_EXPLOSION_SMALL_A);
        explosionSmallBSound = game.getResources().getAssetManager().get(
                Constants.SOUND_EXPLOSION_SMALL_B);
        explosionSmallCSound = game.getResources().getAssetManager().get(
                Constants.SOUND_EXPLOSION_SMALL_C);
        explosionBigASound = game.getResources().getAssetManager().get(
                Constants.SOUND_EXPLOSION_BIG_A);
        explosionBigBSound = game.getResources().getAssetManager().get(
                Constants.SOUND_EXPLOSION_BIG_B);
        explosionBigCSound = game.getResources().getAssetManager().get(
                Constants.SOUND_EXPLOSION_BIG_C);
        bulletSound = game.getResources().getAssetManager().get(
                Constants.SOUND_BULLET);
        shotSound = game.getResources().getAssetManager().get(
                Constants.SOUND_SHOT);
        knifeSound = game.getResources().getAssetManager().get(
                Constants.SOUND_KNIFE);
        grenadeSound = game.getResources().getAssetManager().get(
                Constants.SOUND_GRENADE);
        rocketSound = game.getResources().getAssetManager().get(
                Constants.SOUND_ROCKET);
        dynamiteASound = game.getResources().getAssetManager().get(
                Constants.SOUND_DYNAMITE_A);
        dynamiteBSound = game.getResources().getAssetManager().get(
                Constants.SOUND_DYNAMITE_B);
        dynamiteCSound = game.getResources().getAssetManager().get(
                Constants.SOUND_DYNAMITE_C);
        bladeSound = game.getResources().getAssetManager().get(
                Constants.SOUND_BLADE);
        dynamiteSoundIndex = 0;
        explosionSmallSoundIndex = 0;
        explosionBigSoundIndex = 0;
    }

    public Sound getSoundByID(String soundID) {
        if (soundID.equals(Constants.SOUND_BUTTON_POS)) {
            return buttonPosSound;
        } else if (soundID.equals(Constants.SOUND_BUTTON_NEG)) {
            return buttonNegSound;
        } else if (soundID.equals(Constants.SOUND_ENEMY_HIT)) {
            return enemyHitSound;
        } else if (soundID.equals(Constants.SOUND_ENEMY_EXPLOSION)) {
            return enemyExplosionSound;
        } else if (soundID.equals(Constants.SOUND_ENEMY_AMMUNITION)) {
            return enemyAmmunitionSound;
        } else if (soundID.equals(Constants.SOUND_EXPLOSION_SMALL_A)) {
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
        } else if (soundID.equals(Constants.SOUND_EXPLOSION_BIG_A)) {
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
        } else if (soundID.equals(Constants.SOUND_BULLET)) {
            return bulletSound;
        } else if (soundID.equals(Constants.SOUND_SHOT)) {
            return shotSound;
        } else if (soundID.equals(Constants.SOUND_KNIFE)) {
            return knifeSound;
        } else if (soundID.equals(Constants.SOUND_GRENADE)) {
            return grenadeSound;
        } else if (soundID.equals(Constants.SOUND_ROCKET)) {
            return rocketSound;
        } else if (soundID.equals(Constants.SOUND_DYNAMITE_A)) {
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
        } else if (soundID.equals(Constants.SOUND_BLADE)) {
            return bladeSound;
        } else {
            return buttonPosSound;
        }
    }
}