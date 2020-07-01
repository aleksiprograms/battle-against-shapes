package com.aleksiprograms.battleagainstshapes.screens;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class StartScreen extends AbstractScreen {

    public StartScreen(TheGame game) {
        super(game);
        initialize();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_POS)
                        .play(game.getSaveManager().getSaveData().getSoundVolume());
                game.getMainMenuScreen().updateData();
                game.setScreen(game.getMainMenuScreen());
                return true;
            }
        });
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        game.getTimeManager().addToAppTime(deltaTime);
    }

    private void initialize() {
        Image imageGame = new Image(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureBeforeAllLoaded("game_logo"))));
        Label lInstruction = new Label("Tap the screen to start",
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