package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class EnemyStar extends Enemy {

    private float x;
    private float y;
    private float shotDelay;
    private float timeFromLastShot;

    public EnemyStar(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_ENEMY_STAR),
                game.getResources().getPhysicalDefinitions().getEnemyStarPhyDef(),
                Constants.ENEMY_STAR_WIDTH,
                Constants.ENEMY_STAR_HEIGHT);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
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
                game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_AMMUNITION)
                        .play(game.getSaveManager().getSaveData().getSoundVolume());
                float angle = box2DBody.getAngle();
                for (int i = 0; i < 5; i++) {
                    float angleAmmunition = ((i / (float) 5)
                            * 360 * MathUtils.degreesToRadians) - MathUtils.PI + angle;
                    game.getGameWorld().addGameObjectToWorld(
                            game.getResources().getGameObjectPools()
                                    .getEnemyStarAmmunitionPool().obtain(),
                            box2DBody.getPosition().x,
                            box2DBody.getPosition().y,
                            -MathUtils.PI / 2,
                            new Vector2(
                                    MathUtils.cos(angleAmmunition)
                                            * Constants.VELOCITY_ENEMY_STAR_AMMUNITION.len(),
                                    MathUtils.sin(angleAmmunition)
                                            * Constants.VELOCITY_ENEMY_STAR_AMMUNITION.len()),
                            Constants.MAX_HEALTH_ENEMY_STAR_AMMUNITION,
                            Constants.DAMAGE_ENEMY_STAR_AMMUNITION);
                }
            }
        }
    }

    @Override
    public void onContact(
            float damage,
            PhysicalGameObject physicalGameObjectA,
            PhysicalGameObject physicalGameObjectB) {
        super.onContact(damage, physicalGameObjectA, physicalGameObjectB);
        if (health <= 0 && !dead) {
            dead = true;
            game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_EXPLOSION)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
            game.getGameWorld().addEffectToWorld(
                    game.getResources().getParticleEffectPools()
                            .getEnemyStarExplosionPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        } else {
            game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_HIT)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
            game.getGameWorld().addEffectToWorld(
                    game.getResources().getParticleEffectPools()
                            .getEnemyStarHitPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}