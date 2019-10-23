package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class EnemyTriangleAmmunition extends EnemyAmmunition {

    public EnemyTriangleAmmunition(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_ENEMY_TRIANGLE),
                game.physicalDefinitions.pdEnemyTriangleAmmunition,
                Constants.ENEMY_TRIANGLE_AMMUNITION_WIDTH,
                Constants.ENEMY_TRIANGLE_AMMUNITION_HEIGHT);

        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);
    }

    @Override
    public void onContact(float damage, GameObject gameObjectA, GameObject gameObjectB) {
        super.onContact(damage, gameObjectA, gameObjectB);
        if (!dead) {
            dead = true;
            game.sounds.getSoundByID(Constants.SOUND_SRC_ENEMY_EXPLOSION).play(game.saveManager.saveData.getSoundVolume());
            game.gameWorld.addEffect(
                    game.particleEffectPools.enemyTriangleHitPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}