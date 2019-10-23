package com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.Vector2;

public class Blade extends Ammunition {

    public Blade(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_BLADE),
                game.physicalDefinitions.pdBlade,
                Constants.BLADE_WIDTH,
                Constants.BLADE_HEIGHT,
                false);

        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);
    }

    @Override
    public void init(float x, float y, float angle, Vector2 velocity, float health, float damage) {
        super.init(x, y, angle, velocity, health, damage);
        box2DBody.setAngularVelocity(0);
        box2DBody.applyTorque(-50, true);
        game.sounds.getSoundByID(Constants.SOUND_SRC_BLADE).play(game.saveManager.saveData.getSoundVolume());
    }
}