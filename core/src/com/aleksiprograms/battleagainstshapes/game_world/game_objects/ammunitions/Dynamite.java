package com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.game_world.effects.PhysicalEffects;

public class Dynamite extends Ammunition {

    private Sound sound;
    private boolean blastWaveMade;

    public Dynamite(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_DYNAMITE),
                game.physicalDefinitions.pdDynamite,
                Constants.DYNAMITE_WIDTH,
                Constants.DYNAMITE_HEIGHT,
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
        box2DBody.setAngularVelocity(0);
        box2DBody.applyTorque(-MathUtils.random(5.0f, 8.0f), true);
        sound = game.sounds.getSoundByID(Constants.SOUND_SRC_DYNAMITE_A);
        sound.play(game.saveManager.saveData.getSoundVolume());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        game.gameWorld.addEffect(
                game.particleEffectPools.dynamiteFlamePool.obtain(),
                box2DBody.getPosition().x + 1 / Constants.PPM * MathUtils.cosDeg(box2DBody.getAngle() * MathUtils.radiansToDegrees) - 18 / Constants.PPM * MathUtils.sinDeg(box2DBody.getAngle() * MathUtils.radiansToDegrees),
                box2DBody.getPosition().y + 1 / Constants.PPM * MathUtils.sinDeg(box2DBody.getAngle() * MathUtils.radiansToDegrees) + 18 / Constants.PPM * MathUtils.cosDeg(box2DBody.getAngle() * MathUtils.radiansToDegrees));
        if (exploded && !blastWaveMade) {
            PhysicalEffects.makeDynamiteExplosion(
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
                    game.particleEffectPools.dynamiteExplosionPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}