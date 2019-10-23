package com.aleksiprograms.battleagainstshapes.screens;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class CreditsScreen extends AbstractScreen {

    public CreditsScreen(TheGame game) {
        super(game);
        initScreen();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        game.appTime += deltaTime;
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    game.setScreen(game.homeScreen);
                }
            })));
        }
    }

    public void initScreen() {
        final ImageButton btHome = new ImageButton(game.styles.skinImageButtonHome);
        Table tableButtons = new Table();
        tableButtons.add(btHome).width(Constants.BUTTON_WIDTH_IMAGE).height(Constants.BUTTON_HEIGHT_IMAGE).align(Align.right);

        Table tableCredits = new Table();
        tableCredits.top();
        int pB = 10;
        int pB_Sub = 0;
        int pR = 20;
        tableCredits.add(new Label("Creator", game.styles.skinLabelCreditTitle)).padBottom(pB).padRight(pR).align(Align.right);
        tableCredits.add(new Label("Aleksi Tolvanen", game.styles.skinLabelCreditText)).padBottom(pB).align(Align.left);
        tableCredits.row();
        tableCredits.add(new Label("Powered By", game.styles.skinLabelCreditTitle)).padBottom(pB_Sub).padRight(pR).align(Align.right);
        tableCredits.add(new Label("libGDX", game.styles.skinLabelCreditText)).padBottom(pB_Sub).align(Align.left);
        tableCredits.row();
        tableCredits.add().padBottom(pB).padRight(pR).align(Align.right);
        tableCredits.add(new Label("Box2D", game.styles.skinLabelCreditText)).padBottom(pB).align(Align.left);
        tableCredits.row();
        tableCredits.add(new Label("Font", game.styles.skinLabelCreditTitle)).padBottom(pB).padRight(pR).align(Align.right);
        tableCredits.add(new Label("Roboto Condensed by Christian Robertson", game.styles.skinLabelCreditText)).padBottom(pB).align(Align.left);

        Table tableContent = new Table();
        tableContent.add(new Label("CREDITS", game.styles.skinLabelTitle1)).expandX().align(Align.top).colspan(2);
        tableContent.row();
        tableContent.add(tableCredits).expand().padTop(30);
        tableContent.add(tableButtons).expandY().align(Align.right);

        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_BACKGROUND))));
        table.add(tableContent).align(Align.center).grow().pad(Constants.PAD_SCREEN);

        stage.addActor(table);

        btHome.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < + btHome.getHeight()) {
                    if (x > 0 && x < btHome.getWidth()) {
                        game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(game.saveManager.saveData.getSoundVolume());
                        stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(game.homeScreen);
                            }
                        })));
                    }
                }
            }
        });
    }
}