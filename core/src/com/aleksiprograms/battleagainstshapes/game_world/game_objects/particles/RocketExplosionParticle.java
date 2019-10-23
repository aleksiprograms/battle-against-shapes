package com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles;

import com.aleksiprograms.battleagainstshapes.TheGame;

public class RocketExplosionParticle extends ExplosionParticle {

    public RocketExplosionParticle(TheGame game) {
        super(game);
        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);
    }
}