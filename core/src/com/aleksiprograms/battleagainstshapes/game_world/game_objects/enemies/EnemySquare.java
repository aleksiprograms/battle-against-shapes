package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.MathUtils;

public class EnemySquare extends Enemy {

    public EnemySquare(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_ENEMY_SQUARE),
                game.getResources().getPhysicalDefinitions().getEnemySquarePhyDef(),
                Constants.ENEMY_SQUARE_WIDTH,
                Constants.ENEMY_SQUARE_HEIGHT);
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
                    MathUtils.cos(angle) * Constants.VELOCITY_ENEMY_SQUARE.len(),
                    MathUtils.sin(angle) * Constants.VELOCITY_ENEMY_SQUARE.len());
            box2DBody.setTransform(box2DBody.getPosition(), angle);
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
                            .getEnemySquareExplosionPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        } else {
            game.getResources().getSounds().getSoundByID(Constants.SOUND_ENEMY_HIT)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
            game.getGameWorld().addEffectToWorld(
                    game.getResources().getParticleEffectPools()
                            .getEnemySquareHitPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
    }
}