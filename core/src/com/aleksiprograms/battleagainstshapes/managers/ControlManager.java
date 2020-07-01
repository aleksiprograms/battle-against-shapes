package com.aleksiprograms.battleagainstshapes.managers;

import com.aleksiprograms.battleagainstshapes.screens.huds.InGameHud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Manages the desktop and android controls of the game.
 * Android control inputs are from the InGameHud-class.
 */
public class ControlManager {

    public boolean btMoveUpPress = false;
    public boolean btMoveDownPress = false;
    public boolean btUsePriWeaPress = false;
    public boolean btUseSecWeaPress = false;
    private float timeFromLastBtMoveUpPress = 0;
    private float timeFromLastBtMoveDownPress = 0;
    private float timeFromLastBtUsePriWeaPress = 0;
    private float timeFromLastBtUseSecWeaPress = 0;

    public ControlManager() {
    }

    public void update(float deltaTime) {
        timeFromLastBtMoveUpPress += deltaTime;
        timeFromLastBtMoveDownPress += deltaTime;
        timeFromLastBtUsePriWeaPress += deltaTime;
        timeFromLastBtUseSecWeaPress += deltaTime;

        if (timeFromLastBtMoveUpPress > 0.1f) {
            btMoveUpPress = false;
        }
        if (timeFromLastBtMoveDownPress > 0.1f) {
            btMoveDownPress = false;
        }
        if (timeFromLastBtUsePriWeaPress > 0.1f) {
            btUsePriWeaPress = false;
        }
        if (timeFromLastBtUseSecWeaPress > 0.1f) {
            btUseSecWeaPress = false;
        }

        // Android and iOS controls
        if (InGameHud.isButtonMoveUpPressed()) {
            timeFromLastBtMoveUpPress = 0;
            if (!btMoveUpPress) {
                btMoveUpPress = true;
            }
        }
        if (InGameHud.isButtonMoveDownPressed()) {
            timeFromLastBtMoveDownPress = 0;
            if (!btMoveDownPress) {
                btMoveDownPress = true;
            }
        }
        if (InGameHud.isButtonUsePriWeaPressed()) {
            timeFromLastBtUsePriWeaPress = 0;
            if (!btUsePriWeaPress) {
                btUsePriWeaPress = true;
            }
        }
        if (InGameHud.isButtonUseSecWeaPressed()) {
            timeFromLastBtUseSecWeaPress = 0;
            if (!btUseSecWeaPress) {
                btUseSecWeaPress = true;
            }
        }

        // Desktop controls
        if (Gdx.input.isKeyPressed(Input.Keys.W)
                || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            timeFromLastBtMoveUpPress = 0;
            if (!btMoveUpPress) {
                btMoveUpPress = true;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)
                || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            timeFromLastBtMoveDownPress = 0;
            if (!btMoveDownPress) {
                btMoveDownPress = true;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            timeFromLastBtUsePriWeaPress = 0;
            if (!btUsePriWeaPress) {
                btUsePriWeaPress = true;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.PERIOD)) {
            timeFromLastBtUseSecWeaPress = 0;
            if (!btUseSecWeaPress) {
                btUseSecWeaPress = true;
            }
        }
    }
}