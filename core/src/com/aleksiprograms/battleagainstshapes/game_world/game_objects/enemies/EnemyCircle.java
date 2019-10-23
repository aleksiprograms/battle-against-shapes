package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class EnemyCircle extends Enemy {

    private int explosionDistance;

    public EnemyCircle(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_ENEMY_CIRCLE),
                game.physicalDefinitions.pdEnemyCircle,
                Constants.ENEMY_CIRCLE_WIDTH,
                Constants.ENEMY_CIRCLE_HEIGHT);

        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);
        explosionDistance = 250;
    }

    @Override
    public void init(float x, float y, float angle, Vector2 velocity, float health, float damage) {
        super.init(x, y, angle, velocity, health, damage);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (!super.objectFreed) {
            float xPosition = box2DBody.getPosition().x - sprite.getWidth() / 2;
            float yPosition = box2DBody.getPosition().y - sprite.getWidth() / 2;
            float targetXPosition = game.player.fighter.box2DBody.getPosition().x;
            float targetYPosition = game.player.fighter.box2DBody.getPosition().y - 35 / Constants.PPM;
            double distance = Math.sqrt((targetXPosition - xPosition) * (targetXPosition - xPosition) + (targetYPosition - yPosition) * (targetYPosition - yPosition));

            if (game.player.fighter.box2DBody.getPosition().y > (box2DBody.getPosition().y + 35 / Constants.PPM) + 0.5f) {
                box2DBody.applyForceToCenter(0, 10, true);
            } else if (game.player.fighter.box2DBody.getPosition().y < (box2DBody.getPosition().y + 35 / Constants.PPM) - 0.5f) {
                box2DBody.applyForceToCenter(0, -10, true);
            }
            box2DBody.applyForceToCenter(-3, 0, true);

            if (distance < (explosionDistance / Constants.PPM)) {
                float angle = MathUtils.atan2(targetYPosition - yPosition, targetXPosition - xPosition);
                float[] angles = {
                        angle - 30 * MathUtils.degreesToRadians,
                        angle - 15 * MathUtils.degreesToRadians,
                        angle,
                        angle + 15 * MathUtils.degreesToRadians,
                        angle + 30 * MathUtils.degreesToRadians};
                freeObject = true;
                game.sounds.getSoundByID(Constants.SOUND_SRC_ENEMY_EXPLOSION).play(game.saveManager.saveData.getSoundVolume());
                for (int i = 0; i < angles.length; i++) {
                    game.gameWorld.addEnemyCircleAmmunitionToWorld(
                            game.gameObjectPools.enemyCircleAmmunitionPool.obtain(),
                            box2DBody.getPosition().x,
                            box2DBody.getPosition().y,
                            angles[i] + MathUtils.PI,
                            new Vector2(
                                    MathUtils.cos(angles[i]) * Constants.VELOCITY_ENEMY_CIRCLE_AMMUNITION.len(),
                                    MathUtils.sin(angles[i]) * Constants.VELOCITY_ENEMY_CIRCLE_AMMUNITION.len()),
                            Constants.MAX_HEALTH_ENEMY_CIRCLE_AMMUNITION,
                            Constants.DAMAGE_ENEMY_CIRCLE_AMMUNITION);
                }
            }
        }
    }

    @Override
    public void onContact(float damage, GameObject gameObjectA, GameObject gameObjectB) {
        super.onContact(damage, gameObjectA, gameObjectB);
        if (health <= 0 && !dead) {
            dead = true;
            game.sounds.getSoundByID(Constants.SOUND_SRC_ENEMY_EXPLOSION).play(game.saveManager.saveData.getSoundVolume());
            game.gameWorld.addEffect(
                    game.particleEffectPools.enemyCircleExplosionPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        } else {
            game.sounds.getSoundByID(Constants.SOUND_SRC_ENEMY_HIT).play(game.saveManager.saveData.getSoundVolume());
            game.gameWorld.addEffect(
                    game.particleEffectPools.enemyCircleHitPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}