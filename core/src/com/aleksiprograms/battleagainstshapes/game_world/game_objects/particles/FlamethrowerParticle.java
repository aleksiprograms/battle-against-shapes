package com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles;

import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.Enemy;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyAmmunition;

public class FlamethrowerParticle extends Particle {

    public boolean hit;

    public FlamethrowerParticle(TheGame game) {

        super(
                game,
                game.physicalDefinitions.pdFlamethrowerParticle,
                1,1);

        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);
    }

    @Override
    public void init(float x, float y, float angle, Vector2 velocity, float health, float damage) {
        super.init(x, y, angle, velocity, health, damage);
        hit = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (box2DBody.getPosition().x + (width / 2) >
                game.player.fighter.box2DBody.getPosition().x + 9) {
            freeObject = true;
        }
    }

    @Override
    public void onContact(float damage, GameObject gameObjectA, GameObject gameObjectB) {
        super.onContact(damage, gameObjectA, gameObjectB);
        if (gameObjectA instanceof Enemy || gameObjectB instanceof Enemy ||
                gameObjectA instanceof EnemyAmmunition || gameObjectB instanceof EnemyAmmunition) {
            hit = true;
        }
        freeObject = true;
    }
}
