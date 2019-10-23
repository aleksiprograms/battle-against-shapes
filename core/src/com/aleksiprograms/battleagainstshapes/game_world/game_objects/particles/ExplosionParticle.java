package com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles;

import com.aleksiprograms.battleagainstshapes.TheGame;

public abstract class ExplosionParticle extends Particle {

    public ExplosionParticle(TheGame game) {

        super(
                game,
                game.physicalDefinitions.pdExplosionParticle,
                1,1);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(box2DBody.getLinearVelocity().len() < 3f) {
            freeObject = true;
        }
    }
}