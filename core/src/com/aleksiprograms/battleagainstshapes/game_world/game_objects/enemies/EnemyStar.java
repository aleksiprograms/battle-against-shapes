package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class EnemyStar extends Enemy {

    private float x;
    private float y;
    private float shotDelay;
    private float timeFromLastShot;

    public EnemyStar(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_ENEMY_STAR),
                game.physicalDefinitions.pdEnemyStar,
                Constants.ENEMY_STAR_WIDTH,
                Constants.ENEMY_STAR_HEIGHT);

        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);
    }

    @Override
    public void init(float x, float y, float angle, Vector2 velocity, float health, float damage) {
        super.init(x, y, angle, velocity, health, damage);
        this.x = x;
        this.y = y;
        shotDelay = 1;
        timeFromLastShot = 0;
        box2DBody.setAngularVelocity(0);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (!super.objectFreed) {
            timeFromLastShot += deltaTime;

            box2DBody.setAngularVelocity(3);
            box2DBody.setTransform(x, y, box2DBody.getAngle());

            if (timeFromLastShot >= shotDelay) {
                shotDelay = 1;
                timeFromLastShot = 0;
                game.sounds.getSoundByID(Constants.SOUND_SRC_ENEMY_AMMUNITION).play(game.saveManager.saveData.getSoundVolume());
                float angle = box2DBody.getAngle();
                for (int i = 0; i < 5; i++) {
                    float angleAmmunition = ((i / (float) 5) * 360 * MathUtils.degreesToRadians) - MathUtils.PI + angle;
                    game.gameWorld.addEnemyStarAmmunitionToWorld(
                            game.gameObjectPools.enemyStarAmmunitionPool.obtain(),
                            box2DBody.getPosition().x,
                            box2DBody.getPosition().y,
                            - MathUtils.PI / 2,
                            new Vector2(
                                    MathUtils.cos(angleAmmunition) * Constants.VELOCITY_ENEMY_STAR_AMMUNITION.len(),
                                    MathUtils.sin(angleAmmunition) * Constants.VELOCITY_ENEMY_STAR_AMMUNITION.len()),
                            Constants.MAX_HEALTH_ENEMY_STAR_AMMUNITION,
                            Constants.DAMAGE_ENEMY_STAR_AMMUNITION);
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
                    game.particleEffectPools.enemyStarExplosionPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        } else {
            game.sounds.getSoundByID(Constants.SOUND_SRC_ENEMY_HIT).play(game.saveManager.saveData.getSoundVolume());
            game.gameWorld.addEffect(
                    game.particleEffectPools.enemyStarHitPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}