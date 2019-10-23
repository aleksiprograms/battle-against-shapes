package com.aleksiprograms.battleagainstshapes.toolbox;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.general_objects.Fighter;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.Weapon;
import com.aleksiprograms.battleagainstshapes.managers.ControlManager;
import com.aleksiprograms.battleagainstshapes.managers.GameModeStatsManager;

/**
 * Manages the data of the player and controls the player.
 */
public class Player {

    public TheGame game;
    public ControlManager controlManager;
    public GameModeStatsManager gameModeStatsManager;
    public Fighter fighter;
    public Weapon primaryWeapon;
    public Weapon secondaryWeapon;

    public Player(TheGame game) {
        this.game = game;
        controlManager = new ControlManager();
        gameModeStatsManager = new GameModeStatsManager(game);
    }

    public void update(float deltaTime) {
        //if (primaryWeapon instanceof Flamethrower)
        //    ((Flamethrower) primaryWeapon).setDestroy(true);
        controlManager.update(deltaTime);
        fighter.update(deltaTime);
        primaryWeapon.update(deltaTime);
        secondaryWeapon.update(deltaTime);

        if (controlManager.btMoveUpPress)
            fighter.box2DBody.applyForceToCenter(0, 120, true);
        if (controlManager.btMoveDownPress)
            fighter.box2DBody.applyForceToCenter(0, -120, true);
        if (controlManager.btUsePriWeaPress)
            primaryWeapon.shoot();
        if (controlManager.btUseSecWeaPress)
            secondaryWeapon.shoot();
    }

    public void draw(SpriteBatch spriteBatch) {
        fighter.draw(spriteBatch);
        primaryWeapon.draw(spriteBatch);
        secondaryWeapon.draw(spriteBatch);
    }
}