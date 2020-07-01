package com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.Vector2;

public class RocketLauncher extends AmmoHolder {

    public RocketLauncher(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_ROCKET_LAUNCHER),
                game.getResources().getTextureRegionByID(Constants.TEXTURE_ROCKET),
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
            game.getGameWorld().addGameObjectToWorld(
                    game.getResources().getGameObjectPools().getRocketPool().obtain(),
                    game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                            .getPosition().x + Constants.ROCKET_X_OFFSET,
                    game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                            .getPosition().y + Constants.ROCKET_Y_OFFSET,
                    0,
                    new Vector2(
                            game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                                    .getLinearVelocity().x + Constants.VELOCITY_ROCKET.x,
                            Constants.VELOCITY_ROCKET.y),
                    Constants.MAX_HEALTH_ROCKET,
                    Constants.DAMAGE_ROCKET);
            game.getGameScreen().getInGameStatsManager().addToWeaponAmmunitionsFired(weaponID);
        }
    }
}