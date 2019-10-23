package com.aleksiprograms.battleagainstshapes.managers;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.toolbox.GameMode;

/**
 * Manages the basic info about the game modes.
 */
public class GameModeManager {

    private TheGame game;
    private GameMode[] gameModes = new GameMode[Constants.GAME_MODES];
    private int[] gameModeIds = {
            Constants.GAME_MODE_ID_EASY,
            Constants.GAME_MODE_ID_MEDIUM,
            Constants.GAME_MODE_ID_HARD};
    private String[] gameModeNames = {"EASY", "MEDIUM", "HARD"};
    private String[] gameModeImageNames = {
            Constants.TEX_SRC_GMAE_MODE_EASY,
            Constants.TEX_SRC_GMAE_MODE_MEDIUM,
            Constants.TEX_SRC_GMAE_MODE_HARD};

    public GameModeManager(TheGame game) {
        this.game = game;
        for (int i = 0; i < Constants.GAME_MODES; i++) {
            gameModes[i] = new GameMode(
                    gameModeIds[i],
                    gameModeNames[i],
                    gameModeImageNames[i],
                    100, 1000, 2000);
        }
    }

    public GameMode[] getGameModes() {
        return gameModes;
    }
}