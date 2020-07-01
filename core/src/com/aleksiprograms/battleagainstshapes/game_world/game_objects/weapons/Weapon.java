package com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;

public abstract class Weapon implements Pool.Poolable {

    protected TheGame game;
    protected Sprite sprite;
    protected int weaponID;
    protected float xOffset;
    protected float yOffset;
    protected float shotDelay;
    protected float timeFromLastShot;
    protected boolean freeObject;
    protected boolean objectFreed;

    public Weapon(
            TheGame game,
            TextureRegion textureRegion,
            int weaponID,
            float shotDelay,
            float width,
            float height,
            float xOffset,
            float yOffset) {
        this.game = game;
        this.weaponID = weaponID;
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

    public void update(float deltaTime) {
        timeFromLastShot += deltaTime;
        float angle = game.getGameWorld().getPlayer().getFighter().getBox2DBody().getAngle()
                * MathUtils.radiansToDegrees;
        float xPos = game.getGameWorld().getPlayer().getFighter().getBox2DBody().getPosition().x
                - sprite.getWidth() / 2;
        float yPos = game.getGameWorld().getPlayer().getFighter().getBox2DBody().getPosition().y
                - sprite.getHeight() / 2;
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

    public int getWeaponID() {
        return weaponID;
    }
}