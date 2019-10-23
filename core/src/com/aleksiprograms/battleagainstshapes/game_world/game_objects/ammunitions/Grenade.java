package com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.audio.Sound;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.game_world.effects.PhysicalEffects;

public class Grenade extends Ammunition {

    private Sound sound;
    private boolean blastWaveMade;

    public Grenade(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_GRENADE),
                game.physicalDefinitions.pdGrenade,
                Constants.GRENADE_WIDTH,
                Constants.GRENADE_HEIGHT,
                true);

        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        sound.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        sound.resume();
    }

    @Override
    public void init(float x, float y, float angle, Vector2 velocity, float health, float damage) {
        super.init(x, y, angle, velocity, health, damage);
        blastWaveMade = false;
        sound = game.sounds.getSoundByID(Constants.SOUND_SRC_GRENADE);
        sound.play(game.saveManager.saveData.getSoundVolume());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        game.gameWorld.addEffect(
                game.particleEffectPools.grenadeSmokePool.obtain(),
                box2DBody.getPosition().x - 28 / Constants.PPM,
                box2DBody.getPosition().y - 5 / Constants.PPM);
        if (exploded && !blastWaveMade) {
            PhysicalEffects.makeGrenadeExplosion(
                    game,
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
            blastWaveMade = true;
            freeObject = true;
        }
    }

    @Override
    public void onContact(float damage, GameObject gameObjectA, GameObject gameObjectB) {
        super.onContact(damage, gameObjectA, gameObjectB);
        if (!exploded) {
            exploded = true;
            sound.stop();
            game.sounds.getSoundByID(Constants.SOUND_SRC_EXPLOSION_SMALL_A).play(game.saveManager.saveData.getSoundVolume());
            game.gameWorld.addEffect(
                    game.particleEffectPools.grenadeExplosionPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}