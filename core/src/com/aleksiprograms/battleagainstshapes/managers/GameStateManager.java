package com.aleksiprograms.battleagainstshapes.managers;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.toolbox.LevelState;

/**
 * Manages the game state, in other words is game paused or in gaming state or the game is over.
 * It also tells, if there is new best score or distance for the game mode or if the hud needs an update.
 */
public class GameStateManager {

    private TheGame game;
    private LevelState levelState;
    private boolean updateInGameHud;
    private boolean newBestScore;
    private boolean newBestDistance;

    public GameStateManager(TheGame game) {
        this.game = game;
        levelState      = LevelState.GAMING;
        updateInGameHud = false;
        newBestScore    = false;
        newBestDistance = false;
    }

    public void setLevelGaming() {
        if (!levelState.equals(LevelState.GAMING)) {
            levelState = LevelState.GAMING;
            game.gameWorld.onResume();
        }
    }

    public void setLevelPaused() {
        if (!levelState.equals(LevelState.PAUSED)) {
            levelState = LevelState.PAUSED;
            game.gameWorld.onPause();
            game.player.gameModeStatsManager.onPause();
        }
    }

    public void setLevelCompleted() {
        if (!levelState.equals(LevelState.COMPLETED)) {
            levelState = LevelState.COMPLETED;
            game.player.gameModeStatsManager.onComplete();
        }
    }

    public LevelState getLevelState() {
        return levelState;
    }

    public boolean isUpdateInGameHud() {
        return updateInGameHud;
    }

    public boolean isNewBestScore() {
        return newBestScore;
    }

    public boolean isNewBestDistance() {
        return newBestDistance;
    }

    public void setUpdateInGameHud(boolean updateInGameHud) {
        this.updateInGameHud = updateInGameHud;
    }

    public void setNewBestScore(boolean newBestScore) {
        this.newBestScore = newBestScore;
    }

    public void setNewBestDistance(boolean newBestDistance) {
        this.newBestDistance = newBestDistance;
    }
}