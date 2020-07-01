package com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.Enemy;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyAmmunition;
import com.badlogic.gdx.math.Vector2;

public class FlamethrowerParticle extends Particle {

    public boolean hit;

    public FlamethrowerParticle(TheGame game) {
        super(
                game,
                game.getResources().getPhysicalDefinitions().getFlamethrowerParticlePhyDef(),
                1, 1);
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
        hit = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (box2DBody.getPosition().x + (width / 2) >
                game.getGameWorld().getPlayer().getFighter().getBox2DBody().getPosition().x + 9) {
            freeObject = true;
        }
    }

    @Override
    public void onContact(
            float damage,
            PhysicalGameObject physicalGameObjectA,
            PhysicalGameObject physicalGameObjectB) {
        super.onContact(damage, physicalGameObjectA, physicalGameObjectB);
        if (physicalGameObjectA instanceof Enemy ||
                physicalGameObjectB instanceof Enemy ||
                physicalGameObjectA instanceof EnemyAmmunition ||
                physicalGameObjectB instanceof EnemyAmmunition) {
            hit = true;
        }
        freeObject = true;
    }
}
