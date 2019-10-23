package com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class Bullet extends Ammunition {

    public Bullet(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_BULLET),
                game.physicalDefinitions.pdBullet,
                Constants.BULLET_WIDTH,
                Constants.BULLET_HEIGHT,
                true);

        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);
    }
}