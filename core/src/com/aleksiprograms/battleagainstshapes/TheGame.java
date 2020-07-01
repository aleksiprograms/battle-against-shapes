package com.aleksiprograms.battleagainstshapes;

import com.aleksiprograms.battleagainstshapes.game_world.GameWorld;
import com.aleksiprograms.battleagainstshapes.managers.SaveManager;
import com.aleksiprograms.battleagainstshapes.managers.TimeManager;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.resources.Resources;
import com.aleksiprograms.battleagainstshapes.screens.CreditsScreen;
import com.aleksiprograms.battleagainstshapes.screens.GameScreen;
import com.aleksiprograms.battleagainstshapes.screens.LoadingScreen;
import com.aleksiprograms.battleagainstshapes.screens.MainMenuScreen;
import com.aleksiprograms.battleagainstshapes.screens.SettingsScreen;
import com.aleksiprograms.battleagainstshapes.screens.StatsScreen;
import com.aleksiprograms.battleagainstshapes.screens.WeaponsScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * The main class for the game/application.
 * Holds the resources and the screens, which are reused
 * through the live time of the game/application.
 */
public class TheGame extends Game {

    private GameWorld gameWorld;
    private Resources resources;
    private SaveManager saveManager;
    private SpriteBatch spriteBatch;
    private FitViewport viewportUI;
    private TimeManager timeManager;

    private CreditsScreen creditsScreen;
    private GameScreen gameScreen;
    private MainMenuScreen mainMenuScreen;
    private SettingsScreen settingsScreen;
    private StatsScreen statsScreen;
    private WeaponsScreen weaponsScreen;

    public TheGame() {
    }

    @Override
    public void create() {
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        resources = new Resources(this);
        saveManager = new SaveManager();
        spriteBatch = new SpriteBatch();
        viewportUI = new FitViewport(
                Constants.SCREEN_WIDTH_PIXEL,
                Constants.SCREEN_HEIGHT_PIXEL,
                new OrthographicCamera());
        timeManager = new TimeManager(this);
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        gameWorld.getBox2DWorld().dispose();
        resources.dispose();
        spriteBatch.dispose();
        creditsScreen.dispose();
        gameScreen.dispose();
        mainMenuScreen.dispose();
        settingsScreen.dispose();
        statsScreen.dispose();
        weaponsScreen.dispose();
    }

    @Override
    public void pause() {
        super.pause();
        saveManager.save();
    }

    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
        if (saveManager != null) {
            if (saveManager.getSaveData() != null) {
                timeManager.saveTime();
            }
            saveManager.save();
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewportUI.update(width, height, true);
    }

    public void loadRest() {
        gameWorld = new GameWorld(this);
        resources.loadRest();
        creditsScreen = new CreditsScreen(this);
        gameScreen = new GameScreen(this);
        mainMenuScreen = new MainMenuScreen(this);
        settingsScreen = new SettingsScreen(this);
        statsScreen = new StatsScreen(this);
        weaponsScreen = new WeaponsScreen(this);
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public Resources getResources() {
        return resources;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public SaveManager getSaveManager() {
        return saveManager;
    }

    public FitViewport getViewportUI() {
        return viewportUI;
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }

    public CreditsScreen getCreditsScreen() {
        return creditsScreen;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public MainMenuScreen getMainMenuScreen() {
        return mainMenuScreen;
    }

    public SettingsScreen getSettingsScreen() {
        return settingsScreen;
    }

    public StatsScreen getStatsScreen() {
        return statsScreen;
    }

    public WeaponsScreen getWeaponsScreen() {
        return weaponsScreen;
    }
}