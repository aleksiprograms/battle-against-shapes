package com.aleksiprograms.battleagainstshapes.screens;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class SettingsScreen extends AbstractScreen {

    private Slider sliderSoundVolume;

    public SettingsScreen(TheGame game) {
        super(game);
        initScreen();
    }

    @Override
    public void show() {
        sliderSoundVolume.setValue(game.saveManager.saveData.getSoundVolume());
        super.show();
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

    @Override
    public void pause() {
        game.saveManager.saveData.setSoundVolume(sliderSoundVolume.getValue());
    }

    @Override
    public void hide() {
        game.saveManager.saveData.setSoundVolume(sliderSoundVolume.getValue());
    }

    private void initScreen() {
        final ImageButton btHome = new ImageButton(game.styles.skinImageButtonHome);
        Table tableButtons = new Table();
        tableButtons.add(btHome).width(Constants.BUTTON_WIDTH_IMAGE).height(Constants.BUTTON_HEIGHT_IMAGE).align(Align.right);

        Table tableSoundVolume = new Table();
        Label lSoundVolumeTitle = new Label("Volume", game.styles.skinLabelSettingsTitle);
        sliderSoundVolume = new Slider(0.0f, 1.0f, 0.1f, false, game.styles.skinSlider);
        sliderSoundVolume.getStyle().background.setMinWidth(500);
        sliderSoundVolume.getStyle().background.setMinHeight(15);
        sliderSoundVolume.getStyle().knobBefore.setMinWidth(500);
        sliderSoundVolume.getStyle().knobBefore.setMinHeight(15);
        sliderSoundVolume.getStyle().knob.setMinWidth(50);
        sliderSoundVolume.getStyle().knob.setMinHeight(50);
        tableSoundVolume.add(lSoundVolumeTitle).padRight(30);
        tableSoundVolume.add(sliderSoundVolume).width(500).height(50);

        Table tableSettings = new Table();
        tableSettings.add(tableSoundVolume).padBottom(20);

        Table tableContent = new Table();
        tableContent.add(new Label("SETTINGS", game.styles.skinLabelTitle1)).expandX().align(Align.top).colspan(2);
        tableContent.row();
        tableContent.add(tableSettings).expand().padTop(30);
        tableContent.add(tableButtons).expandY().align(Align.right);

        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_BACKGROUND))));
        table.add(tableContent).align(Align.center).grow().pad(Constants.PAD_SCREEN);

        stage.addActor(table);

        sliderSoundVolume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (sliderSoundVolume.getValue() > game.saveManager.saveData.getSoundVolume() + 0.001f ||
                        sliderSoundVolume.getValue() < game.saveManager.saveData.getSoundVolume() - 0.001f) {
                    game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(sliderSoundVolume.getValue());
                }
            }
        });

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