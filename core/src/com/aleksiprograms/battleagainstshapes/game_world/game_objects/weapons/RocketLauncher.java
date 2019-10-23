package com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class RocketLauncher extends AmmoHolder {

    public RocketLauncher(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_ROCKET_LAUNCHER),
                game.getTextureRegionByID(Constants.TEX_SRC_ROCKET),
                Constants.ROCKET_LAUNCHER_ID,
                4,
                Constants.ROCKET_LAUNCHER_WIDTH,
                Constants.ROCKET_LAUNCHER_HEIGHT,
                Constants.ROCKET_LAUNCHER_X_OFFSET,
                Constants.ROCKET_LAUNCHER_Y_OFFSET,
                Constants.ROCKET_WIDTH,
                Constants.ROCKET_HEIGHT,
                Constants.ROCKET_X_OFFSET,
                Constants.ROCKET_Y_OFFSET,
                0);
    }

    @Override
    public void shoot() {
        if (timeFromLastShot >= shotDelay) {
            timeFromLastShot = 0;
            game.gameWorld.addRocketToWorld(
                    game.gameObjectPools.rocketPool.obtain(),
                    game.player.fighter.box2DBody.getPosition().x + Constants.ROCKET_X_OFFSET,
                    game.player.fighter.box2DBody.getPosition().y + Constants.ROCKET_Y_OFFSET,
                    0,
                    new Vector2(
                            game.player.fighter.box2DBody.getLinearVelocity().x + Constants.VELOCITY_ROCKET.x,
                            Constants.VELOCITY_ROCKET.y),
                    Constants.MAX_HEALTH_ROCKET,
                    Constants.DAMAGE_ROCKET);
            game.player.gameModeStatsManager.addToSecWeaAmmunitionsFired();
        }
    }
}