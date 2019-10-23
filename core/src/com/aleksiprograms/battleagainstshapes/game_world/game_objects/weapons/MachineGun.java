package com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons;

import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class MachineGun extends Weapon {

    public MachineGun(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_MACHINE_GUN),
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
            game.gameWorld.addBulletToWorld(
                    game.gameObjectPools.bulletPool.obtain(),
                    game.player.fighter.box2DBody.getPosition().x + Constants.BULLET_X_OFFSET,
                    game.player.fighter.box2DBody.getPosition().y + Constants.BULLET_Y_OFFSET,
                    0,
                    new Vector2(
                            game.player.fighter.box2DBody.getLinearVelocity().x + Constants.VELOCITY_BULLET.x,
                            Constants.VELOCITY_BULLET.y),
                    Constants.MAX_HEALTH_BULLET,
                    Constants.DAMAGE_BULLET);
            game.player.gameModeStatsManager.addToPriWeaAmmunitionsFired();
            game.sounds.getSoundByID(Constants.SOUND_SRC_BULLET).play(game.saveManager.saveData.getSoundVolume());
        }
    }
}