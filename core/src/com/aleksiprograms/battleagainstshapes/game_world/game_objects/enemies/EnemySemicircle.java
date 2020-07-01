package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class EnemySemicircle extends Enemy {

    private float x;
    private float y;
    private float shotDelay;
    private float timeFromLastShot;

    public EnemySemicircle(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_ENEMY_SEMICIRCLE),
                game.getResources().getPhysicalDefinitions().getEnemySemicirclePhyDef(),
                Constants.ENEMY_SEMICIRCLE_WIDTH,
                Constants.ENEMY_SEMICIRCLE_HEIGHT);
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
        shotDelay = MathUtils.random(0.5f, 1.5f);
        timeFromLastShot = 0;
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
            float angle = MathUtils.atan2(
                    targetYPosition - yPosition,
                    targetXPosition - xPosition);

            box2DBody.setTransform(x, y, angle + MathUtils.PI);

            timeFromLastShot += deltaTime;
            if (timeFromLastShot >= shotDelay) {
                shotDelay = MathUtils.random(0.5f, 1.5f);
                timeFromLastShot = 0;
                game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_AMMUNITION)
                        .play(game.getSaveManager().getSaveData().getSoundVolume());
                float[] angleAmmunition = {
                        angle - 8 * MathUtils.degreesToRadians,
                        angle,
                        angle + 8 * MathUtils.degreesToRadians};
                for (int i = 0; i < 3; i++) {
                    game.getGameWorld().addGameObjectToWorld(
                            game.getResources().getGameObjectPools()
                                    .getEnemySemicircleAmmunitionPool().obtain(),
                            box2DBody.getPosition().x,
                            box2DBody.getPosition().y,
                            angleAmmunition[i] + MathUtils.PI,
                            new Vector2(
                                    MathUtils.cos(angleAmmunition[i])
                                            * Constants.VELOCITY_ENEMY_SEMICIRCLE_AMMUNITION.len(),
                                    MathUtils.sin(angleAmmunition[i])
                                            * Constants.VELOCITY_ENEMY_SEMICIRCLE_AMMUNITION.len()),
                            Constants.MAX_HEALTH_ENEMY_SEMICIRCLE_AMMUNITION,
                            Constants.DAMAGE_ENEMY_SEMICIRCLE_AMMUNITION);
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
                            .getEnemySemicircleExplosionPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        } else {
            game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_HIT)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
            game.getGameWorld().addEffectToWorld(
                    game.getResources().getParticleEffectPools()
                            .getEnemySemicircleHitPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}