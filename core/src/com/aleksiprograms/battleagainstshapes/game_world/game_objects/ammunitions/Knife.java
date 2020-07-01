package com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

public class Knife extends Ammunition {

    private Sound sound;

    public Knife(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_KNIFE),
                game.getResources().getPhysicalDefinitions().getKnifePhyDef(),
                Constants.KNIFE_THROWER_ID,
                Constants.KNIFE_WIDTH,
                Constants.KNIFE_HEIGHT,
                false);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        sound.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        sound.resume();
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
        box2DBody.applyTorque(-80, true);
        sound = game.getResources().getSounds().getSoundByID(Constants.SOUND_KNIFE);
        sound.play(game.getSaveManager().getSaveData().getSoundVolume());
    }
}