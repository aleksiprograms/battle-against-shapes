package com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_effects.PhysicalEffects;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Dynamite extends Ammunition {

    private Sound sound;
    private boolean blastWaveMade;

    public Dynamite(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_DYNAMITE),
                game.getResources().getPhysicalDefinitions().getDynamitePhyDef(),
                Constants.DYNAMITE_LAUNCHER_ID,
                Constants.DYNAMITE_WIDTH,
                Constants.DYNAMITE_HEIGHT,
                true);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
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
    public void initialize(
            float x,
            float y,
            float angle,
            Vector2 velocity,
            float health,
            float damage) {
        super.initialize(x, y, angle, velocity, health, damage);
        blastWaveMade = false;
        box2DBody.setAngularVelocity(0);
        box2DBody.applyTorque(-MathUtils.random(5.0f, 8.0f), true);
        sound = game.getResources().getSounds().getSoundByID(Constants.SOUND_DYNAMITE_A);
        sound.play(game.getSaveManager().getSaveData().getSoundVolume());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        game.getGameWorld().addEffectToWorld(
                game.getResources().getParticleEffectPools().getDynamiteFlamePool().obtain(),
                box2DBody.getPosition().x + 1 / Constants.PPM * MathUtils.cosDeg(
                        box2DBody.getAngle() * MathUtils.radiansToDegrees)
                        - 18 / Constants.PPM * MathUtils.sinDeg(box2DBody.getAngle()
                        * MathUtils.radiansToDegrees),
                box2DBody.getPosition().y + 1 / Constants.PPM * MathUtils.sinDeg(
                        box2DBody.getAngle() * MathUtils.radiansToDegrees)
                        + 18 / Constants.PPM * MathUtils.cosDeg(box2DBody.getAngle()
                        * MathUtils.radiansToDegrees));
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
    public void onContact(
            float damage,
            PhysicalGameObject physicalGameObjectA,
            PhysicalGameObject physicalGameObjectB) {
        super.onContact(damage, physicalGameObjectA, physicalGameObjectB);
        if (!exploded) {
            exploded = true;
            sound.stop();
            game.getResources().getSounds().getSoundByID(Constants.SOUND_EXPLOSION_SMALL_A)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
            game.getGameWorld().addEffectToWorld(
                    game.getResources().getParticleEffectPools()
                            .getDynamiteExplosionPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}