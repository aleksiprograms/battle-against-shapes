package com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles;

import com.aleksiprograms.battleagainstshapes.TheGame;

public class DynamiteExplosionParticle extends ExplosionParticle {

    public DynamiteExplosionParticle(TheGame game) {
        super(game);
        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);
    }
}