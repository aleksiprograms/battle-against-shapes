package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class EnemyRectangle extends Enemy {

    private float x;
    private float y;
    private float shotDelay;
    private float timeFromLastShot;

    public EnemyRectangle(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_ENEMY_RECTANGLE),
                game.getResources().getPhysicalDefinitions().getEnemyRectanglePhyDef(),
                Constants.ENEMY_RECTANGLE_WIDTH,
                Constants.ENEMY_RECTANGLE_HEIGHT);
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
        shotDelay = MathUtils.random(0.5f, 1f);
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
                shotDelay = MathUtils.random(2f, 3f);
                timeFromLastShot = 0;
                game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_AMMUNITION)
                        .play(game.getSaveManager().getSaveData().getSoundVolume());
                game.getGameWorld().addGameObjectToWorld(
                        game.getResources().getGameObjectPools()
                                .getEnemyRectangleAmmunitionPool().obtain(),
                        box2DBody.getPosition().x,
                        box2DBody.getPosition().y,
                        angle + MathUtils.PI,
                        new Vector2(
                                MathUtils.cos(angle)
                                        * Constants.VELOCITY_ENEMY_RECTANGLE_AMMUNITION.len(),
                                MathUtils.sin(angle)
                                        * Constants.VELOCITY_ENEMY_RECTANGLE_AMMUNITION.len()),
                        Constants.MAX_HEALTH_ENEMY_RECTANGLE_AMMUNITION,
                        Constants.DAMAGE_ENEMY_RECTANGLE_AMMUNITION);
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
                            .getEnemyRectangleExplosionPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        } else {
            game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_HIT)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
            game.getGameWorld().addEffectToWorld(
                    game.getResources().getParticleEffectPools()
                            .getEnemyRectangleHitPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}