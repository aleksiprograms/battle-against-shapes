package com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class FighterFlameParticle extends Particle {

    public FighterFlameParticle(TheGame game) {
        super(
                game,
                game.getResources().getPhysicalDefinitions().getFighterFlameParticlePhyDef(),
                1, 1);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (box2DBody.getPosition().x + (width / 2) <
                game.getGameWorld().getPlayer().getFighter().getBox2DBody().getPosition().x
                        - Constants.PLAYER_X_POS_FROM_LEFT) {
            freeObject = true;
        }
    }
}