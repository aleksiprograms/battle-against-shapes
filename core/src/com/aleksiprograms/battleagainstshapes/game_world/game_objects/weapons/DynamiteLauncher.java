package com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class DynamiteLauncher extends AmmoHolder {

    public DynamiteLauncher(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_DYNAMITE_LAUNCHER),
                game.getResources().getTextureRegionByID(Constants.TEXTURE_DYNAMITE),
                Constants.DYNAMITE_LAUNCHER_ID,
                4,
                Constants.DYNAMITE_LAUNCHER_WIDTH,
                Constants.DYNAMITE_LAUNCHER_HEIGHT,
                Constants.DYNAMITE_LAUNCHER_X_OFFSET,
                Constants.DYNAMITE_LAUNCHER_Y_OFFSET,
                Constants.DYNAMITE_WIDTH,
                Constants.DYNAMITE_HEIGHT,
                Constants.DYNAMITE_X_OFFSET,
                Constants.DYNAMITE_Y_OFFSET,
                106);
    }

    @Override
    public void shoot() {
        if (timeFromLastShot >= shotDelay) {
            timeFromLastShot = 0;
            float[] angles = {
                    11 * MathUtils.degreesToRadians,
                    16 * MathUtils.degreesToRadians,
                    21 * MathUtils.degreesToRadians};

            Vector2 velocity;
            for (int i = 0; i < angles.length; i++) {
                velocity = new Vector2(MathUtils.cos(angles[i]), MathUtils.sin(angles[i]))
                        .scl(Constants.VELOCITY_DYNAMITE.len());
                game.getGameWorld().addGameObjectToWorld(
                        game.getResources().getGameObjectPools().getDynamitePool().obtain(),
                        game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                                .getPosition().x + Constants.DYNAMITE_X_OFFSET,
                        game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                                .getPosition().y + Constants.DYNAMITE_Y_OFFSET,
                        0,
                        velocity,
                        Constants.MAX_HEALTH_DYNAMITE,
                        Constants.DAMAGE_DYNAMITE);
                game.getGameScreen().getInGameStatsManager().addToWeaponAmmunitionsFired(weaponID);
            }
        }
    }
}