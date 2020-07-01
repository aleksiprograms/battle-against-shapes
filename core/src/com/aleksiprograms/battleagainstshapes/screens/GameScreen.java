package com.aleksiprograms.battleagainstshapes.screens;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.managers.InGameStatsManager;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.screens.huds.GameOverHud;
import com.aleksiprograms.battleagainstshapes.screens.huds.InGameHud;
import com.aleksiprograms.battleagainstshapes.screens.huds.PausedHud;
import com.aleksiprograms.battleagainstshapes.toolbox.GameMode;
import com.aleksiprograms.battleagainstshapes.toolbox.GameState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen extends AbstractScreen {

    private OrthographicCamera cameraGame;
    private FitViewport viewportGame;
    private GameMode gameMode;
    private InGameStatsManager inGameStatsManager;
    private FPSLogger fpsLogger;
    private GameState gameState;

    private InGameHud inGameHud;
    private GameOverHud gameOverHud;
    private PausedHud pausedHud;

    public GameScreen(TheGame game) {
        super(game);
        cameraGame = new OrthographicCamera();
        viewportGame = new FitViewport(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                cameraGame);
        inGameStatsManager = new InGameStatsManager(game);
        fpsLogger = new FPSLogger();
        inGameHud = new InGameHud(game);
        gameOverHud = new GameOverHud(game);
        pausedHud = new PausedHud(game);
    }

    @Override
    public void render(float deltaTime) {
        game.getTimeManager().addToAppTime(deltaTime);
        if (gameState.equals(GameState.IN_GAME)) {
            game.getTimeManager().addToGameTime(deltaTime);
        }
        update(Constants.FIXED_TIME_STEP);
        fpsLogger.log();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getSpriteBatch().setProjectionMatrix(cameraGame.combined);
        game.getSpriteBatch().begin();
        game.getSpriteBatch().disableBlending();
        game.getGameWorld().drawBackground(game.getSpriteBatch());
        game.getSpriteBatch().enableBlending();
        game.getGameWorld().drawObjects(game.getSpriteBatch());
        game.getGameWorld().drawEffects(game.getSpriteBatch());
        game.getSpriteBatch().end();

        game.getSpriteBatch().setProjectionMatrix(stage.getCamera().combined);
        stage.act();
        stage.draw();

        if (Constants.DEBUG_DRAW_WORLD) {
            game.getGameWorld().getBox2DDebugRenderer().render(
                    game.getGameWorld().getBox2DWorld(),
                    cameraGame.combined);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewportGame.update(width, height, true);
    }

    public void update(float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)
                || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (gameState.equals(GameState.IN_GAME)) {
                changeGameState(GameState.PAUSED);
            } else if (gameState.equals(GameState.PAUSED)
                    || gameState.equals(GameState.GAME_OVER)) {
                game.getMainMenuScreen().updateData();
                game.setScreen(game.getMainMenuScreen());
            }
        }
        game.getGameWorld().update(deltaTime);
        cameraGame.position.x =
                game.getGameWorld().getPlayer().getFighter().getBox2DBody().getPosition().x
                + Constants.PLAYER_X_POS_FROM_CENTER;
        cameraGame.position.y = Constants.SCREEN_HEIGHT / 2;
        cameraGame.update();
    }

    @Override
    public void updateData() {
        super.updateData();
        gameState = GameState.IN_GAME;
        setPlayerWeapons();
        game.getGameWorld().createWorld(gameMode);
        stage.addActor(inGameHud);
        inGameHud.updateData();
        inGameStatsManager.reset();
        inGameStatsManager.addWeapon(
                game.getGameWorld().getPlayer().getPrimaryWeapon().getWeaponID());
        inGameStatsManager.addWeapon(
                game.getGameWorld().getPlayer().getSecondaryWeapon().getWeaponID());
    }

    public void changeGameState(GameState gameState) {
        this.gameState = gameState;
        stage.clear();
        if (gameState.equals(GameState.PAUSED)) {
            inGameStatsManager.onPause();
            game.getGameWorld().onPause();
            pausedHud.updateData();
            stage.addActor(pausedHud);
        } else if (gameState.equals(GameState.IN_GAME)) {
            game.getGameWorld().onResume();
            inGameHud.updateData();
            stage.addActor(inGameHud);
        } else if (gameState.equals(GameState.GAME_OVER)) {
            inGameStatsManager.onGameOver();
            gameOverHud.updateData();
            stage.addActor(gameOverHud);
        } else if (gameState.equals(GameState.TO_REPLAY)) {
            game.getGameWorld().clearWorld();
            game.getWeaponsScreen().updateData();
            game.setScreen(game.getWeaponsScreen());
        } else if (gameState.equals(GameState.TO_MAIN_MENU)) {
            game.getGameWorld().clearWorld();
            game.getMainMenuScreen().updateData();
            game.setScreen(game.getMainMenuScreen());
        }
    }

    private void setPlayerWeapons() {
        if (game.getSaveManager().getSaveData().getSelectedPrimaryWeapon()
                == Constants.MACHINE_GUN_ID) {
            game.getGameWorld().getPlayer().setPrimaryWeapon(
                    game.getResources().getGameObjectPools().getMachineGunPool().obtain());
        } else if (game.getSaveManager().getSaveData().getSelectedPrimaryWeapon()
                == Constants.SHOTGUN_ID) {
            game.getGameWorld().getPlayer().setPrimaryWeapon(
                    game.getResources().getGameObjectPools().getShotgunPool().obtain());
        } else if (game.getSaveManager().getSaveData().getSelectedPrimaryWeapon()
                == Constants.FLAMETHROWER_ID) {
            game.getGameWorld().getPlayer().setPrimaryWeapon(
                    game.getResources().getGameObjectPools().getFlamethrowerPool().obtain());
        } else if (game.getSaveManager().getSaveData().getSelectedPrimaryWeapon()
                == Constants.KNIFE_THROWER_ID) {
            game.getGameWorld().getPlayer().setPrimaryWeapon(
                    game.getResources().getGameObjectPools().getKnifeThrowerPool().obtain());
        }

        if (game.getSaveManager().getSaveData().getSelectedSecondaryWeapon()
                == Constants.GRENADE_LAUNCHER_ID) {
            game.getGameWorld().getPlayer().setSecondaryWeapon(
                    game.getResources().getGameObjectPools().getGrenadeLauncherPool().obtain());
        } else if (game.getSaveManager().getSaveData().getSelectedSecondaryWeapon()
                == Constants.ROCKET_LAUNCHER_ID) {
            game.getGameWorld().getPlayer().setSecondaryWeapon(
                    game.getResources().getGameObjectPools().getRocketLauncherPool().obtain());
        } else if (game.getSaveManager().getSaveData().getSelectedSecondaryWeapon()
                == Constants.DYNAMITE_LAUNCHER_ID) {
            game.getGameWorld().getPlayer().setSecondaryWeapon(
                    game.getResources().getGameObjectPools().getDynamiteLauncherPool().obtain());
        } else if (game.getSaveManager().getSaveData().getSelectedSecondaryWeapon()
                == Constants.BLADE_LAUNCHER_ID) {
            game.getGameWorld().getPlayer().setSecondaryWeapon(
                    game.getResources().getGameObjectPools().getBladeLauncherPool().obtain());
        }
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public InGameStatsManager getInGameStatsManager() {
        return inGameStatsManager;
    }

    public GameState getGameState() {
        return gameState;
    }

    public InGameHud getInGameHud() {
        return inGameHud;
    }
}