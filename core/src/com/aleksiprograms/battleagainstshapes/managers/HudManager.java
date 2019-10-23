package com.aleksiprograms.battleagainstshapes.managers;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.screens.HomeScreen;
import com.aleksiprograms.battleagainstshapes.screens.huds.GameOverHud;
import com.aleksiprograms.battleagainstshapes.screens.huds.Hud;
import com.aleksiprograms.battleagainstshapes.screens.huds.InGameHud;
import com.aleksiprograms.battleagainstshapes.screens.huds.PausedHud;
import com.aleksiprograms.battleagainstshapes.toolbox.LevelState;

/**
 * Manages the huds of the game screen. Changes the hud when game state is changed
 * and does the actions for the buttons on the huds.
 */
public class HudManager {

    private TheGame game;
    public Stage stage;
    public Viewport viewport;
    private Array<Hud> huds = new Array<Hud>();
    private boolean updateHud;
    private LevelState currentLevelState;
    private LevelState previousLevelState;

    public InGameHud inGameHud;
    public GameOverHud gameOverHud;
    public PausedHud pausedHud;

    public HudManager(TheGame game) {
        this.game = game;
        viewport = new FitViewport(
                Constants.SCREEN_WIDTH_PIXEL,
                Constants.SCREEN_HEIGHT_PIXEL,
                new OrthographicCamera());
        stage = new Stage(
                viewport,
                game.spriteBatch);
        updateHud = false;
        currentLevelState = LevelState.GAMING;
        previousLevelState = currentLevelState;

        inGameHud    = new InGameHud(game);
        gameOverHud = new GameOverHud(game);
        pausedHud    = new PausedHud(game);

        huds.add(inGameHud);
        huds.add(gameOverHud);
        huds.add(pausedHud);

        stage.addActor(inGameHud);
    }

    public void setHudToGaming() {
        currentLevelState = LevelState.GAMING;
        for (int i = 0; i < stage.getActors().size; i++) {
            stage.getActors().get(i).remove();
        }
        stage.addActor(inGameHud);
    }

    public void update() {
        currentLevelState = game.gameStateManager.getLevelState();
        if (!previousLevelState.equals(currentLevelState)) {
            updateHud = true;
            previousLevelState = currentLevelState;
        }
        for(Hud hud : huds) {
            if (hud.btPausePressed) {
                game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                hud.btPausePressed = false;
                game.gameStateManager.setLevelPaused();
            }
            if (hud.btReplayPressed) {
                game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                hud.btReplayPressed = false;
                game.gameWorld.clearWorld();
                game.setScreen(game.weaponsScreen);
            }
            if (hud.btPlayPressed) {
                game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                hud.btPlayPressed = false;
                game.gameStateManager.setLevelGaming();
            }
            if (hud.btHomePressed) {
                game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(game.saveManager.saveData.getSoundVolume());
                hud.btHomePressed = false;
                game.gameWorld.clearWorld();
                game.setScreen(new HomeScreen(game));
            }
        }
        if(updateHud) {
            if (game.gameStateManager.getLevelState().equals(LevelState.GAMING)) {
                for (int i = 0; i < stage.getActors().size; i++) {
                    stage.getActors().get(i).remove();
                }
                stage.addActor(inGameHud);
            }
            if (game.gameStateManager.getLevelState().equals(LevelState.PAUSED)) {
                for (int i = 0; i < stage.getActors().size; i++) {
                    stage.getActors().get(i).remove();
                }
                pausedHud.init(
                        game.saveManager.saveData.getLevelScore(game.gameMode.id),
                        game.saveManager.saveData.getLevelDistance(game.gameMode.id));
                stage.addActor(pausedHud);
            }
            if (game.gameStateManager.getLevelState().equals(LevelState.COMPLETED)) {
                for (int i = 0; i < stage.getActors().size; i++) {
                    stage.getActors().get(i).remove();
                }
                gameOverHud.init(
                        game.player.gameModeStatsManager.getScore(),
                        game.player.gameModeStatsManager.getDistance());
                stage.addActor(gameOverHud);
            }
            updateHud = false;
        }
    }
}