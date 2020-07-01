package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.DrawablePhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.toolbox.PhysicalDef;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class EnemyAmmunition extends DrawablePhysicalGameObject {

    protected boolean dead;

    public EnemyAmmunition(
            TheGame game,
            TextureRegion textureRegion,
            PhysicalDef physicalDef,
            float width,
            float height) {
        super(
                game,
                textureRegion,
                physicalDef,
                width,
                height,
                true);
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
        dead = false;
    }
}