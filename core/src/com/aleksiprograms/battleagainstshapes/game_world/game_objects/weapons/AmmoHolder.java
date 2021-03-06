package com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public abstract class AmmoHolder extends Weapon {

    protected Sprite spriteAmmunition;
    protected float xOffsetAmmunition;
    protected float yOffsetAmmunition;
    protected float angleAmmunition;

    public AmmoHolder(
            TheGame game,
            TextureRegion trWeapon,
            TextureRegion trAmmunition,
            int ID,
            float shotDelay,
            float widthWeapon,
            float heightWeapon,
            float xOffsetWeapon,
            float yOffsetWeapon,
            float widthAmmunition,
            float heightAmmunition,
            float xOffsetAmmunition,
            float yOffsetAmmunition,
            float angleAmmunition) {
        super(
                game,
                trWeapon,
                ID,
                shotDelay,
                widthWeapon,
                heightWeapon,
                xOffsetWeapon,
                yOffsetWeapon);
        spriteAmmunition = new Sprite(trAmmunition);
        spriteAmmunition.setSize(widthAmmunition, heightAmmunition);
        spriteAmmunition.setOrigin(widthAmmunition / 2, heightAmmunition / 2);
        spriteAmmunition.setRotation(angleAmmunition);
        this.xOffsetAmmunition = xOffsetAmmunition;
        this.yOffsetAmmunition = yOffsetAmmunition;
        this.angleAmmunition = angleAmmunition;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float angle = game.getGameWorld().getPlayer().getFighter().getBox2DBody().getAngle()
                * MathUtils.radiansToDegrees;
        float xPos = game.getGameWorld().getPlayer().getFighter().getBox2DBody().getPosition().x
                - spriteAmmunition.getWidth() / 2;
        float yPos = game.getGameWorld().getPlayer().getFighter().getBox2DBody().getPosition().y
                - spriteAmmunition.getHeight() / 2;
        spriteAmmunition.setPosition(
                xPos + xOffsetAmmunition * MathUtils.cosDeg(angle)
                        - yOffsetAmmunition * MathUtils.sinDeg(angle),
                yPos + xOffsetAmmunition * MathUtils.sinDeg(angle)
                        + yOffsetAmmunition * MathUtils.cosDeg(angle));
        spriteAmmunition.setRotation(angle + angleAmmunition);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        if (timeFromLastShot >= shotDelay) {
            spriteAmmunition.draw(spriteBatch);
        }
    }
}