package com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles;

import com.aleksiprograms.battleagainstshapes.TheGame;

public class FighterExplosionParticle extends Particle {

    public FighterExplosionParticle(TheGame game) {
        super(
                game,
                game.getResources().getPhysicalDefinitions().getFighterExplosionParticlePhyDef(),
                1, 1);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (box2DBody.getLinearVelocity().len() < 5f) {
            freeObject = true;
        }
    }
}