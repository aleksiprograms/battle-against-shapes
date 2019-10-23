package com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class KnifeThrower extends AmmoHolder {

    public KnifeThrower(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_KNIFE_THROWER),
                game.getTextureRegionByID(Constants.TEX_SRC_KNIFE),
                Constants.KNIFE_THROWER_ID,
                1,
                Constants.KNIFE_THROWER_WIDTH,
                Constants.KNIFE_THROWER_HEIGHT,
                Constants.KNIFE_THROWER_X_OFFSET,
                Constants.KNIFE_THROWER_Y_OFFSET,
                Constants.KNIFE_WIDTH,
                Constants.KNIFE_HEIGHT,
                Constants.KNIFE_X_OFFSET,
                Constants.KNIFE_Y_OFFSET,
                70);
    }

    @Override
    public void shoot() {
        if (timeFromLastShot >= shotDelay) {
            timeFromLastShot = 0;
            game.gameWorld.addKnifeToWorld(
                    game.gameObjectPools.knifePool.obtain(),
                    game.player.fighter.box2DBody.getPosition().x + Constants.KNIFE_X_OFFSET,
                    game.player.fighter.box2DBody.getPosition().y + Constants.KNIFE_Y_OFFSET,
                    70 * MathUtils.degreesToRadians,
                    new Vector2(
                            game.player.fighter.box2DBody.getLinearVelocity().x + Constants.VELOCITY_KNIFE.x,
                            Constants.VELOCITY_BLADE.y),
                    Constants.MAX_HEALTH_KNIFE,
                    Constants.DAMAGE_KNIFE);
            game.player.gameModeStatsManager.addToPriWeaAmmunitionsFired();
        }
    }
}