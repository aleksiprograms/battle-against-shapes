package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class EnemyPentagon extends Enemy {

    private int explosionParticles;
    private int explosionPower;
    private int explosionDistance;

    public EnemyPentagon(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_ENEMY_PENTAGON),
                game.getResources().getPhysicalDefinitions().getEnemyPentagonPhyDef(),
                Constants.ENEMY_PENTAGON_WIDTH,
                Constants.ENEMY_PENTAGON_HEIGHT);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
        explosionParticles = 15;
        explosionPower = 20;
        explosionDistance = 150;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (!super.objectFreed) {
            float xPosition = box2DBody.getPosition().x - sprite.getWidth() / 2;
            float yPosition = box2DBody.getPosition().y - sprite.getWidth() / 2;
            float targetXPosition = game.getGameWorld()
                    .getPlayer().getFighter().getBox2DBody().getPosition().x;
            float targetYPosition = game.getGameWorld()
                    .getPlayer().getFighter().getBox2DBody().getPosition().y;
            double distance = Math.sqrt((
                    targetXPosition - xPosition) * (targetXPosition - xPosition)
                    + (targetYPosition - yPosition) * (targetYPosition - yPosition));
            float angle = MathUtils.atan2(
                    targetYPosition - yPosition,
                    targetXPosition - xPosition);

            box2DBody.setLinearVelocity(
                    MathUtils.cos(angle) * Constants.VELOCITY_ENEMY_PENTAGON.len(),
                    MathUtils.sin(angle) * Constants.VELOCITY_ENEMY_PENTAGON.len());
            box2DBody.setTransform(box2DBody.getPosition(), angle + MathUtils.PI);

            if (distance < (explosionDistance / Constants.PPM)) {
                freeObject = true;
                game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_EXPLOSION)
                        .play(game.getSaveManager().getSaveData().getSoundVolume());
                float angleAmmunition;
                Vector2 velocity;
                for (int i = 0; i < explosionParticles; i++) {
                    angleAmmunition = (i / (float) explosionParticles)
                            * 360 * MathUtils.degreesToRadians;
                    velocity = new Vector2(
                            MathUtils.sin(angleAmmunition),
                            MathUtils.cos(angleAmmunition))
                            .scl(Constants.VELOCITY_ENEMY_PENTAGON_AMMUNITION.len());
                    game.getGameWorld().addGameObjectToWorld(
                            game.getResources().getGameObjectPools()
                                    .getEnemyPentagonAmmunitionPool().obtain(),
                            box2DBody.getPosition().x,
                            box2DBody.getPosition().y,
                            angleAmmunition,
                            velocity,
                            Constants.MAX_HEALTH_ENEMY_PENTAGON_AMMUNITION,
                            Constants.DAMAGE_ENEMY_PENTAGON_AMMUNITION);
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
                            .getEnemyPentagonExplosionPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        } else {
            game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_HIT)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
            game.getGameWorld().addEffectToWorld(
                    game.getResources().getParticleEffectPools()
                            .getEnemyPentagonHitPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}