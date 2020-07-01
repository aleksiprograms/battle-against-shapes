package com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles;

import com.aleksiprograms.battleagainstshapes.TheGame;

public abstract class ExplosionParticle extends Particle {

    protected int weaponID;

    public ExplosionParticle(TheGame game, int weaponID) {
        super(
                game,
                game.getResources().getPhysicalDefinitions().getExplosionParticlePhyDef(),
                1, 1);
        this.weaponID = weaponID;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (box2DBody.getLinearVelocity().len() < 3f) {
            freeObject = true;
        }
    }

    public int getWeaponID() {
        return weaponID;
    }
}