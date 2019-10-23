package com.aleksiprograms.battleagainstshapes.screens.huds;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Very basic structure of a hud (just table) and data of what buttons of huds are pressed for hud manager.
 */
public abstract class Hud extends Table {

    TheGame game;

    public boolean btPausePressed  = false;
    public boolean btReplayPressed = false;
    public boolean btPlayPressed   = false;
    public boolean btHomePressed   = false;

    Hud(TheGame game) {
        this.game = game;
    }
}