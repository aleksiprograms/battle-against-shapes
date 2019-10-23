package com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class BladeLauncher extends AmmoHolder {

    public BladeLauncher(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_BLADE_LAUNCHER),
                game.getTextureRegionByID(Constants.TEX_SRC_BLADE),
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
                game.gameWorld.addBladeToWorld(
                        game.gameObjectPools.bladePool.obtain(),
                        game.player.fighter.box2DBody.getPosition().x + Constants.BLADE_X_OFFSET,
                        game.player.fighter.box2DBody.getPosition().y + Constants.BLADE_Y_OFFSET,
                        0,
                        velocity,
                        Constants.MAX_HEALTH_BLADE,
                        Constants.DAMAGE_BLADE);
                game.player.gameModeStatsManager.addToSecWeaAmmunitionsFired();
            }
        }
    }
}