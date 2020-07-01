package com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class BladeLauncher extends AmmoHolder {

    public BladeLauncher(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_BLADE_LAUNCHER),
                game.getResources().getTextureRegionByID(Constants.TEXTURE_BLADE),
                Constants.BLADE_LAUNCHER_ID,
                4,
                Constants.BLADE_LAUNCHER_WIDTH,
                Constants.BLADE_LAUNCHER_HEIGHT,
                Constants.BLADE_LAUNCHER_X_OFFSET,
                Constants.BLADE_LAUNCHER_Y_OFFSET,
                Constants.BLADE_WIDTH,
                Constants.BLADE_HEIGHT,
                Constants.BLADE_X_OFFSET,
                Constants.BLADE_Y_OFFSET,
                0);
    }

    @Override
    public void shoot() {
        if (timeFromLastShot >= shotDelay) {
            timeFromLastShot = 0;
            float[] angles = {
                    -6 * MathUtils.degreesToRadians,
                    -4 * MathUtils.degreesToRadians,
                    -2 * MathUtils.degreesToRadians,
                    0 * MathUtils.degreesToRadians,
                    2 * MathUtils.degreesToRadians,
                    4 * MathUtils.degreesToRadians,
                    6 * MathUtils.degreesToRadians};

            Vector2 velocity;
            for (float angle : angles) {
                velocity = new Vector2(MathUtils.cos(angle), MathUtils.sin(angle)).scl(25);
                game.getGameWorld().addGameObjectToWorld(
                        game.getResources().getGameObjectPools().getBladePool().obtain(),
                        game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                                .getPosition().x + Constants.BLADE_X_OFFSET,
                        game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                                .getPosition().y + Constants.BLADE_Y_OFFSET,
                        0,
                        velocity,
                        Constants.MAX_HEALTH_BLADE,
                        Constants.DAMAGE_BLADE);
                game.getGameScreen().getInGameStatsManager().addToWeaponAmmunitionsFired(weaponID);
            }
        }
    }
}