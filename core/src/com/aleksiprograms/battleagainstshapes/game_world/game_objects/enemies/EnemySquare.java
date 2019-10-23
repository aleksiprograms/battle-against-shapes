package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.badlogic.gdx.math.MathUtils;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class EnemySquare extends Enemy {

    public EnemySquare(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_ENEMY_SQUARE),
                game.physicalDefinitions.pdEnemySquare,
                Constants.ENEMY_SQUARE_WIDTH,
                Constants.ENEMY_SQUARE_HEIGHT);

        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if (!super.objectFreed) {
            float xPosition = box2DBody.getPosition().x - sprite.getWidth() / 2;
            float yPosition = box2DBody.getPosition().y - sprite.getWidth() / 2;
            float targetXPosition = game.player.fighter.box2DBody.getPosition().x;
            float targetYPosition = game.player.fighter.box2DBody.getPosition().y;
            float angle = MathUtils.atan2(targetYPosition - yPosition, targetXPosition - xPosition);

            box2DBody.setLinearVelocity(
                    MathUtils.cos(angle) * Constants.VELOCITY_ENEMY_SQUARE.len(),
                    MathUtils.sin(angle) * Constants.VELOCITY_ENEMY_SQUARE.len());
            box2DBody.setTransform(box2DBody.getPosition(), angle);
        }
    }

    @Override
    public void onContact(float damage, GameObject gameObjectA, GameObject gameObjectB) {
        super.onContact(damage, gameObjectA, gameObjectB);
        if (health <= 0 && !dead) {
            dead = true;
            game.sounds.getSoundByID(Constants.SOUND_SRC_ENEMY_EXPLOSION).play(game.saveManager.saveData.getSoundVolume());
            game.gameWorld.addEffect(
                    game.particleEffectPools.enemySquareExplosionPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        } else {
            game.sounds.getSoundByID(Constants.SOUND_SRC_ENEMY_HIT).play(game.saveManager.saveData.getSoundVolume());
            game.gameWorld.addEffect(
                    game.particleEffectPools.enemySquareHitPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}