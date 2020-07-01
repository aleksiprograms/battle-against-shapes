package com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class Bullet extends Ammunition {

    public Bullet(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_BULLET),
                game.getResources().getPhysicalDefinitions().getBulletPhyDef(),
                Constants.MACHINE_GUN_ID,
                Constants.BULLET_WIDTH,
                Constants.BULLET_HEIGHT,
                true);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
    }
}