package com.aleksiprograms.battleagainstshapes.screens;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class CreditsScreen extends AbstractScreen {

    public CreditsScreen(TheGame game) {
        super(game);
        initialize();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        game.getTimeManager().addToAppTime(deltaTime);
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)
                || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.getMainMenuScreen().updateData();
            game.setScreen(game.getMainMenuScreen());
        }
    }

    public void initialize() {
        final ImageButton buttonMainMenu = new ImageButton(
                game.getResources().getStyles().getImageButtonStyleHome());
        Table tableButtons = new Table();
        tableButtons.add(buttonMainMenu).align(Align.right)
                .width(Constants.BUTTON_WIDTH_IMAGE)
                .height(Constants.BUTTON_HEIGHT_IMAGE);

        Table tableCredits = new Table();
        tableCredits.top();
        int pB = 10;
        int pB_Sub = 0;
        int pR = 20;
        tableCredits.add(new Label("Creator",
                game.getResources().getStyles().getLabelStyleGreenSmall()))
                .padBottom(pB).padRight(pR).align(Align.right);
        tableCredits.add(new Label("Aleksi Tolvanen",
                game.getResources().getStyles().getLabelStyleWhiteSmall()))
                .padBottom(pB).align(Align.left);
        tableCredits.row();
        tableCredits.add(new Label("Powered By",
                game.getResources().getStyles().getLabelStyleGreenSmall()))
                .padBottom(pB_Sub).padRight(pR).align(Align.right);
        tableCredits.add(new Label("libGDX",
                game.getResources().getStyles().getLabelStyleWhiteSmall()))
                .padBottom(pB_Sub).align(Align.left);
        tableCredits.row();
        tableCredits.add().padBottom(pB).padRight(pR).align(Align.right);
        tableCredits.add(new Label("Box2D",
                game.getResources().getStyles().getLabelStyleWhiteSmall()))
                .padBottom(pB).align(Align.left);
        tableCredits.row();
        tableCredits.add(new Label("Font",
                game.getResources().getStyles().getLabelStyleGreenSmall()))
                .padBottom(pB).padRight(pR).align(Align.right);
        tableCredits.add(new Label("Roboto Condensed by Christian Robertson",
                game.getResources().getStyles().getLabelStyleWhiteSmall()))
                .padBottom(pB).align(Align.left);

        Table tableContent = new Table();
        tableContent.add(new Label("CREDITS",
                game.getResources().getStyles().getLabelStyleBlueHuge()))
                .expandX().align(Align.top).colspan(2);
        tableContent.row();
        tableContent.add(tableCredits).expand().padTop(30);
        tableContent.add(tableButtons).expandY().align(Align.right);

        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_BACKGROUND))));
        table.add(tableContent).align(Align.center).grow().pad(Constants.PAD_SCREEN);

        stage.addActor(table);

        buttonMainMenu.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonMainMenu.getWidth()) {
                    if (y > 0 && y < buttonMainMenu.getHeight()) {
                        game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_NEG)
                                .play(game.getSaveManager().getSaveData().getSoundVolume());
                        game.getMainMenuScreen().updateData();
                        game.setScreen(game.getMainMenuScreen());
                    }
                }
            }
        });
    }
}