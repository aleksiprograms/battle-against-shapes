package com.aleksiprograms.battleagainstshapes.screens;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class LoadingScreen extends AbstractScreen {

    private ShapeRenderer shapeRenderer;
    private float progress;

    public LoadingScreen(TheGame game) {
        super(game);
        initialize();
        shapeRenderer = new ShapeRenderer();
        progress = 0f;
        game.getResources().loadAssets();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        game.getTimeManager().addToAppTime(deltaTime);
        progress = game.getResources().getAssetManager().getProgress();
        if (game.getResources().getAssetManager().update()) {
            game.loadRest();
            game.setScreen(new StartScreen(game));
        } else {
            shapeRenderer.setProjectionMatrix(game.getSpriteBatch().getProjectionMatrix());
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(new Color(0x323232ff));
            shapeRenderer.rect(
                    game.getViewportUI().getWorldWidth() / 4,
                    game.getViewportUI().getWorldHeight() / 8,
                    game.getViewportUI().getWorldWidth() / 2,
                    15);
            shapeRenderer.setColor(new Color(0x5050c8ff));
            shapeRenderer.rect(
                    game.getViewportUI().getWorldWidth() / 4,
                    game.getViewportUI().getWorldHeight() / 8,
                    progress * (game.getViewportUI().getWorldWidth() / 2),
                    15);
            shapeRenderer.end();
        }
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        super.dispose();
    }

    private void initialize() {
        Image imageGame = new Image(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureBeforeAllLoaded("game_logo"))));
        Label lInstruction = new Label("Loading...",
                game.getResources().getStyles().getLabelStyleRedMedium());

        Table tableContent = new Table();
        tableContent.add(imageGame).align(Align.center).expand().width(300).height(300);
        tableContent.row();
        tableContent.add(lInstruction).colspan(2).align(Align.bottom).padBottom(55);

        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureBeforeAllLoaded("background"))));
        table.add(tableContent).align(Align.center).grow().pad(Constants.PAD_SCREEN);

        stage.addActor(table);
    }
}