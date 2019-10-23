package com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class FighterFlameParticle extends Particle {

    public FighterFlameParticle(TheGame game) {

        super(
                game,
                game.physicalDefinitions.pdFighterFlameParticle,
                1,1);

        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (box2DBody.getPosition().x + (width / 2) <
                game.player.fighter.box2DBody.getPosition().x - Constants.PLAYER_X_POS_FROM_LEFT) {
            freeObject = true;
        }
    }
}