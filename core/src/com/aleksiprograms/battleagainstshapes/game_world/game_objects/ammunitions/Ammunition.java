package com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.DrawableGameObject;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.Enemy;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyAmmunition;
import com.aleksiprograms.battleagainstshapes.toolbox.PhysicalDef;

/**
 * Manages ammunition, when it hits something (enemy).
 */
public abstract class Ammunition extends DrawableGameObject {

    public boolean exploded;
    public boolean hit;

    public Ammunition(
            TheGame game,
            TextureRegion textureRegion,
            PhysicalDef physicalDef,
            float width,
            float height,
            boolean breakable) {

        super(
                game,
                textureRegion,
                physicalDef,
                width,
                height,
                breakable);
    }

    @Override
    public void init(float x, float y, float angle, Vector2 velocity, float health, float damage) {
        super.init(x, y, angle, velocity, health, damage);
        exploded = false;
        hit = false;
    }

    @Override
    public void onContact(float damage, GameObject gameObjectA, GameObject gameObjectB) {
        super.onContact(damage, gameObjectA, gameObjectB);
        if (gameObjectA instanceof Enemy || gameObjectB instanceof Enemy ||
                gameObjectA instanceof EnemyAmmunition || gameObjectB instanceof EnemyAmmunition) {
            hit = true;
        }
    }
}