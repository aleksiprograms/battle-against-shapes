package com.aleksiprograms.battleagainstshapes.screens;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class SettingsScreen extends AbstractScreen {

    private Slider sliderSoundVolume;

    public SettingsScreen(TheGame game) {
        super(game);
        initialize();
    }

    @Override
    public void updateData() {
        sliderSoundVolume.setValue(game.getSaveManager().getSaveData().getSoundVolume());
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

    @Override
    public void pause() {
        game.getSaveManager().getSaveData().setSoundVolume(sliderSoundVolume.getValue());
    }

    @Override
    public void hide() {
        game.getSaveManager().getSaveData().setSoundVolume(sliderSoundVolume.getValue());
    }

    private void initialize() {
        final ImageButton buttonMainMenu = new ImageButton(
                game.getResources().getStyles().getImageButtonStyleHome());
        Table tableButtons = new Table();
        tableButtons.add(buttonMainMenu).align(Align.right)
                .width(Constants.BUTTON_WIDTH_IMAGE)
                .height(Constants.BUTTON_HEIGHT_IMAGE);

        Table tableSoundVolume = new Table();
        Label lSoundVolumeTitle = new Label("Volume",
                game.getResources().getStyles().getLabelStyleGreenSmall());
        sliderSoundVolume = new Slider(0.0f, 1.0f, 0.1f, false,
                game.getResources().getStyles().getSliderStyle());
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
        tableContent.add(new Label("SETTINGS",
                game.getResources().getStyles().getLabelStyleBlueHuge()))
                .expandX().align(Align.top).colspan(2);
        tableContent.row();
        tableContent.add(tableSettings).expand().padTop(30);
        tableContent.add(tableButtons).expandY().align(Align.right);

        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_BACKGROUND))));
        table.add(tableContent).align(Align.center).grow().pad(Constants.PAD_SCREEN);

        stage.addActor(table);

        sliderSoundVolume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (sliderSoundVolume.getValue() >
                        game.getSaveManager().getSaveData().getSoundVolume() + 0.001f ||
                        sliderSoundVolume.getValue() <
                                game.getSaveManager().getSaveData().getSoundVolume() - 0.001f) {
                    game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_POS)
                            .play(sliderSoundVolume.getValue());
                }
            }
        });

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