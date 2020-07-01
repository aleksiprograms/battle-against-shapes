package com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class RocketExplosionParticle extends ExplosionParticle {

    public RocketExplosionParticle(TheGame game) {
        super(game, Constants.ROCKET_LAUNCHER_ID);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
    }
}