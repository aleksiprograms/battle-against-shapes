package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class EnemyPentagonAmmunition extends EnemyAmmunition {

    public EnemyPentagonAmmunition(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_ENEMY_PENTAGON),
                game.physicalDefinitions.pdEnemyPentagonAmmunition,
                Constants.ENEMY_PENTAGON_AMMUNITION_WIDTH,
                Constants.ENEMY_PENTAGON_AMMUNITION_HEIGHT);

        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (box2DBody.getLinearVelocity().len() < 1f && !dead) {
            dead = true;
            game.sounds.getSoundByID(Constants.SOUND_SRC_ENEMY_EXPLOSION).play(game.saveManager.saveData.getSoundVolume());
            game.gameWorld.addEffect(
                    game.particleEffectPools.enemyPentagonHitPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
            freeObject = true;
        }
    }

    @Override
    public void onContact(float damage, GameObject gameObjectA, GameObject gameObjectB) {
        super.onContact(damage, gameObjectA, gameObjectB);
        if (!dead) {
            dead = true;
            game.sounds.getSoundByID(Constants.SOUND_SRC_ENEMY_EXPLOSION).play(game.saveManager.saveData.getSoundVolume());
            game.gameWorld.addEffect(
                    game.particleEffectPools.enemyPentagonHitPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}