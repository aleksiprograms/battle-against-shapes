package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class EnemyTriangleAmmunition extends EnemyAmmunition {

    public EnemyTriangleAmmunition(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_ENEMY_TRIANGLE),
                game.getResources().getPhysicalDefinitions().getEnemyTriangleAmmunitionPhyDef(),
                Constants.ENEMY_TRIANGLE_AMMUNITION_WIDTH,
                Constants.ENEMY_TRIANGLE_AMMUNITION_HEIGHT);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
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
                            .getEnemyTriangleHitPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}