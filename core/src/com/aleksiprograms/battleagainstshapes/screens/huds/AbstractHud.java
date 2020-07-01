package com.aleksiprograms.battleagainstshapes.screens.huds;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Very basic structure of a hud (just a table).
 */
public abstract class AbstractHud extends Table {

    public TheGame game;

    AbstractHud(TheGame game) {
        this.game = game;
    }

    protected void initialize() {
    }

    public void updateData() {
    }
}