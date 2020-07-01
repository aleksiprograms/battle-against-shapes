package com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.Vector2;

public class MachineGun extends Weapon {

    public MachineGun(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_MACHINE_GUN),
                Constants.MACHINE_GUN_ID,
                0.1f,
                Constants.MACHINE_GUN_WIDTH,
                Constants.MACHINE_GUN_HEIGHT,
                Constants.MACHINE_GUN_X_OFFSET,
                Constants.MACHINE_GUN_Y_OFFSET);
    }

    @Override
    public void shoot() {
        if (timeFromLastShot >= shotDelay) {
            timeFromLastShot = 0;
            game.getGameWorld().addGameObjectToWorld(
                    game.getResources().getGameObjectPools().getBulletPool().obtain(),
                    game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                            .getPosition().x + Constants.BULLET_X_OFFSET,
                    game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                            .getPosition().y + Constants.BULLET_Y_OFFSET,
                    0,
                    new Vector2(
                            game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                                    .getLinearVelocity().x + Constants.VELOCITY_BULLET.x,
                            Constants.VELOCITY_BULLET.y),
                    Constants.MAX_HEALTH_BULLET,
                    Constants.DAMAGE_BULLET);
            game.getGameScreen().getInGameStatsManager().addToWeaponAmmunitionsFired(weaponID);
            game.getResources().getSounds().getSoundByID(Constants.SOUND_BULLET)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
        }
    }
}