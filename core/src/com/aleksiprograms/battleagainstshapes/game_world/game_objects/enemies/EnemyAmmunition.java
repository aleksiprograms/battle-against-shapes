package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.DrawableGameObject;
import com.aleksiprograms.battleagainstshapes.toolbox.PhysicalDef;

public abstract class EnemyAmmunition extends DrawableGameObject {

    public boolean dead;

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
    public void init(float x, float y, float angle, Vector2 velocity, float health, float damage) {
        super.init(x, y, angle, velocity, health, damage);
        dead = false;
    }
}