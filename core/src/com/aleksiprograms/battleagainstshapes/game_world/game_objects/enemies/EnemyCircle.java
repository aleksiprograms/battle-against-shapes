package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class EnemyCircle extends Enemy {

    private int explosionDistance;

    public EnemyCircle(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_ENEMY_CIRCLE),
                game.getResources().getPhysicalDefinitions().getEnemyCirclePhyDef(),
                Constants.ENEMY_CIRCLE_WIDTH,
                Constants.ENEMY_CIRCLE_HEIGHT);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
        explosionDistance = 250;
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
                    .getPlayer().getFighter().getBox2DBody().getPosition().y - 35 / Constants.PPM;
            double distance = Math.sqrt((
                    targetXPosition - xPosition) * (targetXPosition - xPosition)
                    + (targetYPosition - yPosition) * (targetYPosition - yPosition));

            if (game.getGameWorld().getPlayer().getFighter().getBox2DBody().getPosition().y >
                    (box2DBody.getPosition().y + 35 / Constants.PPM) + 0.5f) {
                box2DBody.applyForceToCenter(0, 10, true);
            } else if (game.getGameWorld().getPlayer().getFighter().getBox2DBody().getPosition().y <
                    (box2DBody.getPosition().y + 35 / Constants.PPM) - 0.5f) {
                box2DBody.applyForceToCenter(0, -10, true);
            }
            box2DBody.applyForceToCenter(-3, 0, true);

            if (distance < (explosionDistance / Constants.PPM)) {
                float angle = MathUtils.atan2(
                        targetYPosition - yPosition,
                        targetXPosition - xPosition);
                float[] angles = {
                        angle - 30 * MathUtils.degreesToRadians,
                        angle - 15 * MathUtils.degreesToRadians,
                        angle,
                        angle + 15 * MathUtils.degreesToRadians,
                        angle + 30 * MathUtils.degreesToRadians};
                freeObject = true;
                game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_EXPLOSION)
                        .play(game.getSaveManager().getSaveData().getSoundVolume());
                for (int i = 0; i < angles.length; i++) {
                    game.getGameWorld().addGameObjectToWorld(
                            game.getResources().getGameObjectPools()
                                    .getEnemyCircleAmmunitionPool().obtain(),
                            box2DBody.getPosition().x,
                            box2DBody.getPosition().y,
                            angles[i] + MathUtils.PI,
                            new Vector2(
                                    MathUtils.cos(angles[i])
                                            * Constants.VELOCITY_ENEMY_CIRCLE_AMMUNITION.len(),
                                    MathUtils.sin(angles[i])
                                            * Constants.VELOCITY_ENEMY_CIRCLE_AMMUNITION.len()),
                            Constants.MAX_HEALTH_ENEMY_CIRCLE_AMMUNITION,
                            Constants.DAMAGE_ENEMY_CIRCLE_AMMUNITION);
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
                            .getEnemyCircleExplosionPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        } else {
            game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_HIT)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
            game.getGameWorld().addEffectToWorld(
                    game.getResources().getParticleEffectPools()
                            .getEnemyCircleHitPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}