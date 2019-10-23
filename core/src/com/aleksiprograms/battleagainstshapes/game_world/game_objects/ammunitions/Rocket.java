package com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.game_world.effects.PhysicalEffects;

public class Rocket extends Ammunition {

    private Sound sound;
    private boolean blastWaveMade;

    public Rocket(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_ROCKET),
                game.physicalDefinitions.pdRocket,
                Constants.ROCKET_WIDTH,
                Constants.ROCKET_HEIGHT,
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
        sound = game.sounds.getSoundByID(Constants.SOUND_SRC_ROCKET);
        sound.play(game.saveManager.saveData.getSoundVolume());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        game.gameWorld.addEffect(
                game.particleEffectPools.rocketSmokePool.obtain(),
                box2DBody.getPosition().x - 35 / Constants.PPM,
                box2DBody.getPosition().y - 5 / Constants.PPM);
        if (exploded && !blastWaveMade) {
            PhysicalEffects.makeRocketExplosion(
                    game,
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
            blastWaveMade = true;
            freeObject = true;
        }
    }

    @Override
    public void onContact(float damage, GameObject gameObjectA, GameObject gameObjectB) {
        if (!exploded) {
            exploded = true;
            sound.stop();
            game.sounds.getSoundByID(Constants.SOUND_SRC_EXPLOSION_BIG_A).play(game.saveManager.saveData.getSoundVolume());
            game.gameWorld.addEffect(
                    game.particleEffectPools.rocketExplosionPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}