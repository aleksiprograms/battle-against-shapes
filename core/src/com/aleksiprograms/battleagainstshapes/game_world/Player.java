package com.aleksiprograms.battleagainstshapes.game_world;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.Fighter;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.Weapon;
import com.aleksiprograms.battleagainstshapes.managers.ControlManager;
import com.aleksiprograms.battleagainstshapes.toolbox.GameState;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Manages the data of the player and controls the player.
 */
public class Player {

    private TheGame game;
    private ControlManager controlManager;
    private Fighter fighter;
    private Weapon primaryWeapon;
    private Weapon secondaryWeapon;

    public Player(TheGame game) {
        this.game = game;
        controlManager = new ControlManager();
    }

    public void update(float deltaTime) {
        controlManager.update(deltaTime);
        fighter.update(deltaTime);
        primaryWeapon.update(deltaTime);
        secondaryWeapon.update(deltaTime);

        if (!game.getGameScreen().getGameState().equals(GameState.GAME_OVER)) {
            if (controlManager.btMoveUpPress) {
                fighter.getBox2DBody().applyForceToCenter(0, 120, true);
            }
            if (controlManager.btMoveDownPress) {
                fighter.getBox2DBody().applyForceToCenter(0, -120, true);
            }
            if (controlManager.btUsePriWeaPress) {
                primaryWeapon.shoot();
            }
            if (controlManager.btUseSecWeaPress) {
                secondaryWeapon.shoot();
            }
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        fighter.draw(spriteBatch);
        primaryWeapon.draw(spriteBatch);
        secondaryWeapon.draw(spriteBatch);
    }

    public Fighter getFighter() {
        return fighter;
    }

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }

    public Weapon getPrimaryWeapon() {
        return primaryWeapon;
    }

    public void setPrimaryWeapon(Weapon primaryWeapon) {
        this.primaryWeapon = primaryWeapon;
    }

    public Weapon getSecondaryWeapon() {
        return secondaryWeapon;
    }

    public void setSecondaryWeapon(Weapon secondaryWeapon) {
        this.secondaryWeapon = secondaryWeapon;
    }
}