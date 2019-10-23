package com.aleksiprograms.battleagainstshapes.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class StartScreen extends AbstractScreen implements InputProcessor {

    private boolean tapSoundPlayed;

    public StartScreen(TheGame game) {
        super(game);
        initScreen();
    }

    @Override
    public void show() {
        tapSoundPlayed = false;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        game.appTime += deltaTime;
        if (Gdx.input.isTouched()) {
            if (!tapSoundPlayed) {
                tapSoundPlayed = true;
                game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
            }
            stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    game.setScreen(game.homeScreen);
                }
            })));
        }
    }

    private void initScreen() {
        Image imageGame = new Image(new TextureRegionDrawable(new TextureRegion(game.getStartTexture("game_logo"))));
        Label lInstruction = new Label("Tap the screen to start", game.styles.skinLabelTitle3);

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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.BACK){
            stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    Gdx.app.exit();
                }
            })));
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}