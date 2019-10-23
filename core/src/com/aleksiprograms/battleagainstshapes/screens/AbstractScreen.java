
package com.aleksiprograms.battleagainstshapes.screens;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Manages the basic structure of a screen.
 */
public abstract class AbstractScreen implements Screen {

    public TheGame game;
    public Viewport viewport;
    public Stage stage;

    public AbstractScreen(TheGame game) {
        this.game = game;
        viewport = new FitViewport(
                Constants.SCREEN_WIDTH_PIXEL,
                Constants.SCREEN_HEIGHT_PIXEL,
                new OrthographicCamera());
        stage = new Stage(viewport, game.spriteBatch);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        if (!(this instanceof LoadingScreen || this instanceof StartScreen)) {
            stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        if (stage != null)
            stage.dispose();
    }
}