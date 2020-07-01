package com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class DynamiteExplosionParticle extends ExplosionParticle {

    public DynamiteExplosionParticle(TheGame game) {
        super(game, Constants.DYNAMITE_LAUNCHER_ID);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
    }
}