package com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_effects.PhysicalEffects;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

public class Rocket extends Ammunition {

    private Sound sound;
    private boolean blastWaveMade;

    public Rocket(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_ROCKET),
                game.getResources().getPhysicalDefinitions().getRocketPhyDef(),
                Constants.ROCKET_LAUNCHER_ID,
                Constants.ROCKET_WIDTH,
                Constants.ROCKET_HEIGHT,
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
        sound = game.getResources().getSounds().getSoundByID(Constants.SOUND_ROCKET);
        sound.play(game.getSaveManager().getSaveData().getSoundVolume());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        game.getGameWorld().addEffectToWorld(
                game.getResources().getParticleEffectPools().getRocketSmokePool().obtain(),
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
    public void onContact(
            float damage,
            PhysicalGameObject physicalGameObjectA,
            PhysicalGameObject physicalGameObjectB) {
        if (!exploded) {
            exploded = true;
            sound.stop();
            game.getResources().getSounds().getSoundByID(Constants.SOUND_EXPLOSION_BIG_A)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
            game.getGameWorld().addEffectToWorld(
                    game.getResources().getParticleEffectPools()
                            .getRocketExplosionPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}