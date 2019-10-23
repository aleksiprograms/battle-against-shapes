package com.aleksiprograms.battleagainstshapes.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class LoadingScreen extends AbstractScreen {

    private ShapeRenderer shapeRenderer;
    private float progress;

    public LoadingScreen(TheGame game) {
        super(game);
        initScreen();
        shapeRenderer = new ShapeRenderer();
        progress = 0f;
        game.loadAssets();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        game.appTime += deltaTime;
        progress = game.assetManager.getProgress();
        if (game.assetManager.update()) {
            game.loadClasses();
            game.setScreen(new StartScreen(game));
        } else {
            shapeRenderer.setProjectionMatrix(game.spriteBatch.getProjectionMatrix());
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(new Color(0x323232ff));
            shapeRenderer.rect(viewport.getWorldWidth() / 4, viewport.getWorldHeight() / 8, viewport.getWorldWidth() / 2, 15);
            shapeRenderer.setColor(new Color(0x5050c8ff));
            shapeRenderer.rect(viewport.getWorldWidth() / 4, viewport.getWorldHeight() / 8, progress * (viewport.getWorldWidth() / 2), 15);
            shapeRenderer.end();
        }
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        super.dispose();
    }

    private void initScreen() {
        Image imageGame = new Image(new TextureRegionDrawable(new TextureRegion(game.getStartTexture("game_logo"))));
        Label lInstruction = new Label("Loading...", game.styles.skinLabelTitle3);

        Table tableContent = new Table();
        tableContent.add(imageGame).align(Align.center).expand().width(300).height(300);
        tableContent.row();
        tableContent.add(lInstruction).colspan(2).align(Align.bottom).padBottom(55);

        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(game.getStartTexture("background"))));
        table.add(tableContent).align(Align.center).grow().pad(Constants.PAD_SCREEN);

        stage.addActor(table);
    }
}