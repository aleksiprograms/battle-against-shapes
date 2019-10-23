package com.aleksiprograms.battleagainstshapes.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.toolbox.LevelState;

public class GameScreen extends AbstractScreen {

    private OrthographicCamera camera;
    private FPSLogger fpsLogger = new FPSLogger();

    public GameScreen(TheGame game) {
        super(game);
        initScreen();
    }

    @Override
    public void show() {
        game.gameStateManager.setNewBestScore(false);
        game.gameStateManager.setLevelGaming();
        game.hudManager.setHudToGaming();
        game.gameWorld.createWorld(game.gameMode);
        game.hudManager.inGameHud.setHealth(Constants.MAX_HEALTH_PLAYER);
        game.hudManager.inGameHud.setScore(0);
        game.hudManager.inGameHud.setCombo(0);
        game.hudManager.inGameHud.setDistance(0);
        Gdx.input.setInputProcessor(game.hudManager.stage);
    }

    @Override
    public void render(float deltaTime) {
        game.appTime += deltaTime;
        if (game.gameStateManager.getLevelState().equals(LevelState.GAMING)) {
            game.gameTime += deltaTime;
        }
        update(Constants.FIXED_TIME_STEP);
        fpsLogger.log();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.spriteBatch.setProjectionMatrix(camera.combined);

        game.spriteBatch.begin();
        game.spriteBatch.disableBlending();
        game.gameWorld.drawBackground(game.spriteBatch);
        game.spriteBatch.enableBlending();
        game.gameWorld.drawObjects(game.spriteBatch);
        game.gameWorld.drawEffects(game.spriteBatch);
        game.spriteBatch.end();

        game.spriteBatch.setProjectionMatrix(game.hudManager.stage.getCamera().combined);
        game.hudManager.stage.act();
        game.hudManager.stage.draw();

        //game.gameWorld.box2DDebugRenderer.render(game.gameWorld.box2DWorld, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        game.hudManager.viewport.update(width, height);
    }

    public void update(float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (game.gameStateManager.getLevelState().equals(LevelState.GAMING)) {
                game.gameStateManager.setLevelPaused();
            } else if (game.gameStateManager.getLevelState().equals(LevelState.PAUSED)) {
                game.gameStateManager.setLevelGaming();
            } else if (game.gameStateManager.getLevelState().equals(LevelState.COMPLETED)) {
                game.setScreen(new HomeScreen(game));
            }
        }
        game.hudManager.update();
        game.gameWorld.update(deltaTime);
        camera.position.x = game.player.fighter.box2DBody.getPosition().x + Constants.PLAYER_X_POS_FROM_CENTER;
        camera.position.y = Constants.SCREEN_HEIGHT / 2;
        camera.update();

        if (game.gameStateManager.getLevelState().equals(LevelState.GAMING)) {
            if (game.gameStateManager.isUpdateInGameHud()) {
                game.hudManager.inGameHud.setHealth(game.player.fighter.health);
                game.hudManager.inGameHud.setScore(game.player.gameModeStatsManager.getScore());
                game.hudManager.inGameHud.setCombo(game.player.gameModeStatsManager.getCombo());
                game.hudManager.inGameHud.setDistance(game.player.gameModeStatsManager.getDistance());
                game.gameStateManager.setUpdateInGameHud(false);
            }
        }
    }

    private void initScreen() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                camera);
        camera.position.set(
                viewport.getWorldWidth() / 2,
                viewport.getWorldHeight() / 2, 0);
    }
}