package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.MathUtils;

public class EnemyRectangleAmmunition extends EnemyAmmunition {

    public EnemyRectangleAmmunition(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_ENEMY_RECTANGLE),
                game.getResources().getPhysicalDefinitions().getEnemyRectangleAmmunitionPhyDef(),
                Constants.ENEMY_RECTANGLE_AMMUNITION_WIDTH,
                Constants.ENEMY_RECTANGLE_AMMUNITION_HEIGHT);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
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

            box2DBody.setLinearVelocity(
                    MathUtils.cos(angle)
                            * Constants.VELOCITY_ENEMY_RECTANGLE_AMMUNITION.len(),
                    MathUtils.sin(angle)
                            * Constants.VELOCITY_ENEMY_RECTANGLE_AMMUNITION.len());
            box2DBody.setTransform(box2DBody.getPosition(), angle);
        }
    }

    @Override
    public void onContact(
            float damage,
            PhysicalGameObject physicalGameObjectA,
            PhysicalGameObject physicalGameObjectB) {
        super.onContact(damage, physicalGameObjectA, physicalGameObjectB);
        if (!dead) {
            dead = true;
            game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_EXPLOSION)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
            game.getGameWorld().addEffectToWorld(
                    game.getResources().getParticleEffectPools()
                            .getEnemyRectangleHitPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}