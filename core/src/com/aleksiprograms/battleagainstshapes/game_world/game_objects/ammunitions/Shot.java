package com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class Shot extends Ammunition {

    public Shot(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_SHOT),
                game.getResources().getPhysicalDefinitions().getShotPhyDef(),
                Constants.SHOTGUN_ID,
                Constants.SHOT_WIDTH,
                Constants.SHOT_HEIGHT,
                true);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
    }
}