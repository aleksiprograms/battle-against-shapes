package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class EnemyRectangle extends Enemy {

    private float x;
    private float y;
    private float shotDelay;
    private float timeFromLastShot;

    public EnemyRectangle(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_ENEMY_RECTANGLE),
                game.physicalDefinitions.pdEnemyRectangle,
                Constants.ENEMY_RECTANGLE_WIDTH,
                Constants.ENEMY_RECTANGLE_HEIGHT);

        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);
    }

    @Override
    public void init(float x, float y, float angle, Vector2 velocity, float health, float damage) {
        super.init(x, y, angle, velocity, health, damage);
        this.x = x;
        this.y = y;
        shotDelay = MathUtils.random(0.5f, 1f);
        timeFromLastShot = 0;
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

            box2DBody.setTransform(x, y, angle + MathUtils.PI);

            timeFromLastShot += deltaTime;
            if (timeFromLastShot >= shotDelay) {
                shotDelay = MathUtils.random(2f, 3f);
                timeFromLastShot = 0;
                game.sounds.getSoundByID(Constants.SOUND_SRC_ENEMY_AMMUNITION).play(game.saveManager.saveData.getSoundVolume());
                game.gameWorld.addEnemyRectangleAmmunitionToWorld(
                        game.gameObjectPools.enemyRectangleAmmunitionPool.obtain(),
                        box2DBody.getPosition().x,
                        box2DBody.getPosition().y,
                        angle + MathUtils.PI,
                        new Vector2(
                                MathUtils.cos(angle) * Constants.VELOCITY_ENEMY_RECTANGLE_AMMUNITION.len(),
                                MathUtils.sin(angle) * Constants.VELOCITY_ENEMY_RECTANGLE_AMMUNITION.len()),
                        Constants.MAX_HEALTH_ENEMY_RECTANGLE_AMMUNITION,
                        Constants.DAMAGE_ENEMY_RECTANGLE_AMMUNITION);
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
                    game.particleEffectPools.enemyRectangleExplosionPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        } else {
            game.sounds.getSoundByID(Constants.SOUND_SRC_ENEMY_HIT).play(game.saveManager.saveData.getSoundVolume());
            game.gameWorld.addEffect(
                    game.particleEffectPools.enemyRectangleHitPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}