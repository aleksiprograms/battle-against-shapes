package com.aleksiprograms.battleagainstshapes.game_world.game_objects.general_objects;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class Wall extends GameObject {

    private float y;

    public Wall(TheGame game) {

        super(
                game,
                game.physicalDefinitions.pdWall,
                Constants.WALL_WIDTH,
                Constants.WALL_HEIGHT,
                false);

        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);
    }

    @Override
    public void init(float x, float y, float angle, Vector2 velocity, float health, float damage) {
        super.init(x, y, angle, velocity, health, damage);
        this.y = y;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        box2DBody.setTransform(game.player.fighter.box2DBody.getPosition().x + Constants.SCREEN_WIDTH / 2 - Constants.PLAYER_X_POS_FROM_LEFT, y, 0);
    }
}