package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class EnemyPentagonAmmunition extends EnemyAmmunition {

    public EnemyPentagonAmmunition(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_ENEMY_PENTAGON),
                game.getResources().getPhysicalDefinitions().getEnemyPentagonAmmunitionPhyDef(),
                Constants.ENEMY_PENTAGON_AMMUNITION_WIDTH,
                Constants.ENEMY_PENTAGON_AMMUNITION_HEIGHT);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (box2DBody.getLinearVelocity().len() < 1f && !dead) {
            dead = true;
            game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_EXPLOSION)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
            game.getGameWorld().addEffectToWorld(
                    game.getResources().getParticleEffectPools()
                            .getEnemyPentagonHitPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
            freeObject = true;
        }
    }

    @Override
    public void onContact(
            float damage,
            PhysicalGameObject physicalGameObjectA,
            PhysicalGameObject physicalGameObjectB) {
        super.onContact(damage, physicalGameObjectA, physicalGameObjectB);
        if (!dead) {
            dead = true;
            game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_EXPLOSION)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
            game.getGameWorld().addEffectToWorld(
                    game.getResources().getParticleEffectPools()
                            .getEnemyPentagonHitPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}