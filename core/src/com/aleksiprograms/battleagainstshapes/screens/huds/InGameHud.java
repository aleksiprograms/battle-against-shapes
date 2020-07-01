package com.aleksiprograms.battleagainstshapes.screens.huds;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.toolbox.GameState;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

public class InGameHud extends AbstractHud {

    private static boolean buttonMoveUpPressed = false;
    private static boolean buttonMoveDownPressed = false;
    private static boolean buttonUsePriWeaPressed = false;
    private static boolean buttonUseSecWeaPressed = false;
    private Image imageHealth;
    private Label labelScore;
    private Label labelCombo;
    private Label labelDistance;

    public InGameHud(final TheGame game) {
        super(game);
        initialize();
    }

    @Override
    public void updateData() {
        float health = game.getGameWorld().getPlayer().getFighter().getHealth();
        if (health <= 0) {
            health = 0;
        }
        imageHealth.setDrawable(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_HEALTH_BAR_CONDITION),
                0, 0, (int) health, 20)));
        labelScore.setText(game.getResources().getStyles().getFormattedScore(
                game.getGameScreen().getInGameStatsManager().getScore()));
        labelCombo.setText(game.getResources().getStyles().getFormattedScore(
                game.getGameScreen().getInGameStatsManager().getCombo()));
        labelDistance.setText(game.getResources().getStyles().getFormattedScore(
                game.getGameScreen().getInGameStatsManager().getDistance()) + " m");
    }

    @Override
    protected void initialize() {
        super.initialize();

        Label labelHealthTitle = new Label("HEALTH",
                game.getResources().getStyles().getLabelStyleBlueSmall());

        Image healthBackground = new Image();
        healthBackground.setDrawable(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_HEALTH_BAR_BACKGROUND),
                0, 0, 512, 32)));
        healthBackground.setScaling(Scaling.fit);
        healthBackground.setAlign(Align.left);
        imageHealth = new Image();
        imageHealth.setDrawable(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_HEALTH_BAR_CONDITION),
                0, 0, 512, 20)));
        imageHealth.setScaling(Scaling.none);
        imageHealth.setAlign(Align.left);
        Stack stackHealthBar = new Stack();
        stackHealthBar.add(healthBackground);
        stackHealthBar.add(imageHealth);

        Image healthBackgroundLeftEnd = new Image();
        healthBackgroundLeftEnd.setDrawable(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_HEALTH_BAR_END),
                0, 0, 8, 32)));
        healthBackgroundLeftEnd.setScaling(Scaling.fit);
        healthBackgroundLeftEnd.setAlign(Align.left);
        Image healthBackgroundRightEnd = new Image();
        healthBackgroundRightEnd.setDrawable(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_HEALTH_BAR_END),
                0, 0, 8, 32)));
        healthBackgroundRightEnd.setScaling(Scaling.fit);
        healthBackgroundRightEnd.setAlign(Align.left);

        Table tableHealth = new Table();
        tableHealth.top();
        tableHealth.left();
        tableHealth.add(healthBackgroundLeftEnd).align(Align.left);
        tableHealth.add(stackHealthBar).align(Align.left);
        tableHealth.add(healthBackgroundRightEnd).align(Align.left);

        Label labelScoreTitle = new Label("SCORE",
                game.getResources().getStyles().getLabelStyleBlueSmall());
        labelScore = new Label("0",
                game.getResources().getStyles().getLabelStyleWhiteSmall());

        Label labelComboTitle = new Label("COMBO",
                game.getResources().getStyles().getLabelStyleBlueSmall());
        labelCombo = new Label("0",
                game.getResources().getStyles().getLabelStyleWhiteSmall());

        Label labelDistanceTitle = new Label("DISTANCE",
                game.getResources().getStyles().getLabelStyleBlueSmall());
        labelDistance = new Label("0 m",
                game.getResources().getStyles().getLabelStyleWhiteSmall());

        Table tableInfo = new Table();
        tableInfo.add(labelHealthTitle).align(Align.left).padRight(25).padBottom(10);
        tableInfo.add(tableHealth).align(Align.left).padBottom(10);
        tableInfo.row();
        tableInfo.add(labelScoreTitle).align(Align.left).padRight(25).padBottom(10);
        tableInfo.add(labelScore).align(Align.left).padBottom(10);
        tableInfo.row();
        tableInfo.add(labelComboTitle).align(Align.left).padRight(25).padBottom(10);
        tableInfo.add(labelCombo).align(Align.left).padBottom(10);
        tableInfo.row();
        tableInfo.add(labelDistanceTitle).align(Align.left).padRight(25);
        tableInfo.add(labelDistance).align(Align.left);

        super.pad(Constants.PAD_SCREEN);
        super.top();
        super.left();
        super.setFillParent(true);

        super.add(tableInfo).align(Align.left);

        if (Gdx.app.getType() == Application.ApplicationType.Android ||
                Gdx.app.getType() != Application.ApplicationType.iOS) {

            final ImageButton buttonPause = new ImageButton(
                    game.getResources().getStyles().getImageButtonStylePause());

            ImageButton buttonMoveUp = new ImageButton(
                    game.getResources().getStyles().getImageButtonStyleMoveUp());
            ImageButton buttonMoveDown = new ImageButton(
                    game.getResources().getStyles().getImageButtonStyleMoveDown());
            ImageButton buttonUseSecWea = new ImageButton(
                    game.getResources().getStyles().getImageButtonStyleUseSecWea());
            ImageButton buttonUsePriWea = new ImageButton(
                    game.getResources().getStyles().getImageButtonStyleUsePriWea());

            super.add();
            super.add(buttonPause).align(Align.topRight)
                    .width(Constants.BUTTON_WIDTH_IMAGE)
                    .height(Constants.BUTTON_HEIGHT_IMAGE);
            super.row();
            super.add(buttonMoveUp).expandY().align(Align.bottomLeft).padBottom(20)
                    .width(Constants.BUTTON_WIDTH_GAME)
                    .height(Constants.BUTTON_HEIGHT_GAME);
            super.row();
            super.add(buttonMoveDown).align(Align.bottomLeft)
                    .width(Constants.BUTTON_WIDTH_GAME)
                    .height(Constants.BUTTON_HEIGHT_GAME);
            super.add(buttonUseSecWea).expandX().align(Align.bottomRight).padRight(20)
                    .width(Constants.BUTTON_WIDTH_GAME)
                    .height(Constants.BUTTON_HEIGHT_GAME);
            super.add(buttonUsePriWea).align(Align.bottomRight)
                    .width(Constants.BUTTON_WIDTH_GAME)
                    .height(Constants.BUTTON_HEIGHT_GAME);

            buttonPause.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y,
                                         int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y,
                                    int pointer, int button) {
                    if (x > 0 && x < buttonPause.getWidth()) {
                        if (y > 0 && y < buttonPause.getHeight()) {
                            game.getGameScreen().changeGameState(GameState.PAUSED);
                        }
                    }
                }
            });

            buttonMoveUp.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y,
                                         int pointer, int button) {
                    buttonMoveUpPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y,
                                    int pointer, int button) {
                    buttonMoveUpPressed = false;
                }
            });

            buttonMoveDown.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y,
                                         int pointer, int button) {
                    buttonMoveDownPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y,
                                    int pointer, int button) {
                    buttonMoveDownPressed = false;
                }
            });

            buttonUsePriWea.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y,
                                         int pointer, int button) {
                    buttonUsePriWeaPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y,
                                    int pointer, int button) {
                    buttonUsePriWeaPressed = false;
                }
            });

            buttonUseSecWea.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y,
                                         int pointer, int button) {
                    buttonUseSecWeaPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y,
                                    int pointer, int button) {
                    buttonUseSecWeaPressed = false;
                }
            });
        }
    }

    public static boolean isButtonMoveUpPressed() {
        return buttonMoveUpPressed;
    }

    public static boolean isButtonMoveDownPressed() {
        return buttonMoveDownPressed;
    }

    public static boolean isButtonUsePriWeaPressed() {
        return buttonUsePriWeaPressed;
    }

    public static boolean isButtonUseSecWeaPressed() {
        return buttonUseSecWeaPressed;
    }
}