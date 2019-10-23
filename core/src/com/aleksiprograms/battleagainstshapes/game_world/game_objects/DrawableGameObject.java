package com.aleksiprograms.battleagainstshapes.game_world.game_objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.toolbox.PhysicalDef;

/**
 * Draws and updates the texture/sprite of a game object.
 */
public abstract class DrawableGameObject extends GameObject {

    public Sprite sprite;

    public DrawableGameObject(
            TheGame game,
            TextureRegion textureRegion,
            PhysicalDef physicalDef,
            float width,
            float height,
            boolean breakable) {

        super(game, physicalDef, width, height, breakable);
        sprite = new Sprite(textureRegion);
        sprite.setSize(width, height);
        sprite.setOrigin(width / 2, height / 2);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        sprite.setPosition(
                box2DBody.getPosition().x - width / 2,
                box2DBody.getPosition().y - height / 2);
        sprite.setRotation(MathUtils.radiansToDegrees * box2DBody.getAngle());
    }

    /**
     * Draws the game object or the sprite.
     * @param spriteBatch sprite batch
     */
    public void draw(SpriteBatch spriteBatch) {
        if (!objectFreed)
            sprite.draw(spriteBatch);
    }
}