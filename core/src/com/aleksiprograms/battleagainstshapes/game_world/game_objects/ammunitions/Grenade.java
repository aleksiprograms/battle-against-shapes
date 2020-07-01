package com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_effects.PhysicalEffects;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

public class Grenade extends Ammunition {

    private Sound sound;
    private boolean blastWaveMade;

    public Grenade(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_GRENADE),
                game.getResources().getPhysicalDefinitions().getGrenadePhyDef(),
                Constants.GRENADE_LAUNCHER_ID,
                Constants.GRENADE_WIDTH,
                Constants.GRENADE_HEIGHT,
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
        sound = game.getResources().getSounds().getSoundByID(Constants.SOUND_GRENADE);
        sound.play(game.getSaveManager().getSaveData().getSoundVolume());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        game.getGameWorld().addEffectToWorld(
                game.getResources().getParticleEffectPools().getGrenadeSmokePool().obtain(),
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
                            .getGrenadeExplosionPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}