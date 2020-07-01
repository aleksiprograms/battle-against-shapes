package com.aleksiprograms.battleagainstshapes.game_world.game_objects;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.Vector2;

public class InvisibleWall extends PhysicalGameObject {

    private float y;

    public InvisibleWall(TheGame game) {
        super(
                game,
                game.getResources().getPhysicalDefinitions().getInvisibleWallPhyDef(),
                Constants.WALL_WIDTH,
                Constants.WALL_HEIGHT,
                false);
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
        this.y = y;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        box2DBody.setTransform(
                game.getGameWorld().getPlayer().getFighter().box2DBody.getPosition().x
                        + Constants.SCREEN_WIDTH / 2 - Constants.PLAYER_X_POS_FROM_LEFT,
                y,
                0);
    }
}