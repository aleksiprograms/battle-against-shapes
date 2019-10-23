package com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;

public abstract class Weapon implements Pool.Poolable {

    public TheGame game;
    public Sprite sprite;
    public int ID;
    public float xOffset;
    public float yOffset;
    public float shotDelay;
    public float timeFromLastShot;
    public boolean freeObject;
    public boolean objectFreed;

    public Weapon(
            TheGame game,
            TextureRegion textureRegion,
            int ID,
            float shotDelay,
            float width,
            float height,
            float xOffset,
            float yOffset) {

        this.game = game;
        this.ID = ID;
        this.shotDelay = shotDelay;
        timeFromLastShot = shotDelay;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        sprite = new Sprite(textureRegion);
        sprite.setSize(width, height);
        sprite.setOrigin(width / 2, height / 2);
    }

    @Override
    public void reset() {
        freeObject = false;
        objectFreed = false;
    }

    public void init() {}

    public void update(float deltaTime) {
        timeFromLastShot += deltaTime;
        float angle = game.player.fighter.box2DBody.getAngle() * MathUtils.radiansToDegrees;
        float xPos = game.player.fighter.box2DBody.getPosition().x - sprite.getWidth() / 2;
        float yPos = game.player.fighter.box2DBody.getPosition().y - sprite.getHeight() / 2;
        sprite.setPosition(
                xPos + xOffset * MathUtils.cosDeg(angle) - yOffset * MathUtils.sinDeg(angle),
                yPos + xOffset * MathUtils.sinDeg(angle) + yOffset * MathUtils.cosDeg(angle));
        sprite.setRotation(angle);
    }

    public void draw(SpriteBatch spriteBatch) {
        if (!objectFreed)
            sprite.draw(spriteBatch);
    }

    public abstract void shoot();
}