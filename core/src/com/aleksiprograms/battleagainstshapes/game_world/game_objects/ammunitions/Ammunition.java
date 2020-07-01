package com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.DrawablePhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.Enemy;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyAmmunition;
import com.aleksiprograms.battleagainstshapes.toolbox.PhysicalDef;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Basic structure of an ammunition, which player can shoot.
 */
public abstract class Ammunition extends DrawablePhysicalGameObject {

    protected boolean exploded;
    protected boolean hit;
    protected int weaponID;

    public Ammunition(
            TheGame game,
            TextureRegion textureRegion,
            PhysicalDef physicalDef,
            int weaponID,
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
        this.weaponID = weaponID;
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
        exploded = false;
        hit = false;
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
    }

    public boolean isHit() {
        return hit;
    }

    public int getWeaponID() {
        return weaponID;
    }
}