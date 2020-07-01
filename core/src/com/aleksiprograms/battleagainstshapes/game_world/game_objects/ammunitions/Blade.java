package com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.Vector2;

public class Blade extends Ammunition {

    public Blade(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_BLADE),
                game.getResources().getPhysicalDefinitions().getBladePhyDef(),
                Constants.BLADE_LAUNCHER_ID,
                Constants.BLADE_WIDTH,
                Constants.BLADE_HEIGHT,
                false);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
    }

    @Override
    public void initialize(
            float x,
            float y,
            float angle,
            Vector2 velocity,
            float health,
            float damage) {
        super.initialize(x, y, angle, velocity, health, damage);
        box2DBody.setAngularVelocity(0);
        box2DBody.applyTorque(-50, true);
        game.getResources().getSounds().getSoundByID(Constants.SOUND_BLADE)
                .play(game.getSaveManager().getSaveData().getSoundVolume());
    }
}