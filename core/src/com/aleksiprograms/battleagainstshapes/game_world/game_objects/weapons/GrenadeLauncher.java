package com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.Vector2;

public class GrenadeLauncher extends Weapon {

    public GrenadeLauncher(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_GRENADE_LAUNCHER),
                Constants.GRENADE_LAUNCHER_ID,
                2,
                Constants.GRENADE_LAUNCHER_WIDTH,
                Constants.GRENADE_LAUNCHER_HEIGHT,
                Constants.GRENADE_LAUNCHER_X_OFFSET,
                Constants.GRENADE_LAUNCHER_Y_OFFSET);
    }

    @Override
    public void shoot() {
        if (timeFromLastShot >= shotDelay) {
            timeFromLastShot = 0;
            game.getGameWorld().addGameObjectToWorld(
                    game.getResources().getGameObjectPools().getGrenadePool().obtain(),
                    game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                            .getPosition().x + Constants.GRENADE_X_OFFSET,
                    game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                            .getPosition().y + Constants.GRENADE_Y_OFFSET,
                    0,
                    new Vector2(
                            game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                                    .getLinearVelocity().x + Constants.VELOCITY_GRENADE.x,
                            Constants.VELOCITY_GRENADE.y),
                    Constants.MAX_HEALTH_GRENADE,
                    Constants.DAMAGE_GRENADE);
            game.getGameScreen().getInGameStatsManager().addToWeaponAmmunitionsFired(weaponID);
        }
    }
}