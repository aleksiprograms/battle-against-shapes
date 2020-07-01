package com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Shotgun extends Weapon {

    public Shotgun(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_SHOTGUN),
                Constants.SHOTGUN_ID,
                0.5f,
                Constants.SHOTGUN_WIDTH,
                Constants.SHOTGUN_HEIGHT,
                Constants.SHOTGUN_X_OFFSET,
                Constants.SHOTGUN_Y_OFFSET);
    }

    @Override
    public void shoot() {
        if (timeFromLastShot >= shotDelay) {
            timeFromLastShot = 0;
            for (float i = -0.5f; i <= 0.51f; i += 0.1f) {
                game.getGameWorld().addGameObjectToWorld(
                        game.getResources().getGameObjectPools().getShotPool().obtain(),
                        game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                                .getPosition().x + Constants.SHOT_X_OFFSET
                                + MathUtils.random(-15, 15) / Constants.PPM,
                        game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                                .getPosition().y + Constants.SHOT_Y_OFFSET,
                        0,
                        new Vector2(
                                game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                                        .getLinearVelocity().x + Constants.VELOCITY_SHOT.x,
                                i),
                        Constants.MAX_HEALTH_SHOT,
                        Constants.DAMAGE_SHOT);
                game.getGameScreen().getInGameStatsManager().addToWeaponAmmunitionsFired(weaponID);
            }
            game.getResources().getSounds().getSoundByID(Constants.SOUND_SHOT)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
        }
    }
}