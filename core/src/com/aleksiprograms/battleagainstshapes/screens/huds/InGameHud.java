package com.aleksiprograms.battleagainstshapes.screens.huds;

import com.aleksiprograms.battleagainstshapes.TheGame;
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
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class InGameHud extends Hud {

    private Image imageHealth;
    private Label labelScore;
    private Label labelCombo;
    private Label labelDistance;

    public static boolean btMoveUpPressed    = false;
    public static boolean btMoveDownPressed  = false;
    public static boolean btUsePriWeaPressed = false;
    public static boolean btUseSecWeaPressed = false;

    public InGameHud(TheGame game) {
        super(game);

        Label labelHealthTitle = new Label("HEALTH", game.styles.skinLabelInGameTitle);

        Image healthBackground = new Image();
        healthBackground.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_HEALTH_BAR_BACKGROUND), 0, 0, 512, 32)));
        healthBackground.setScaling(Scaling.fit);
        healthBackground.setAlign(Align.left);
        imageHealth = new Image();
        imageHealth.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_HEALTH_BAR_CONDITION), 0, 0, 512, 20)));
        imageHealth.setScaling(Scaling.none);
        imageHealth.setAlign(Align.left);
        Stack stackHealthBar = new Stack();
        stackHealthBar.add(healthBackground);
        stackHealthBar.add(imageHealth);

        Image healthBackgroundLeftEnd = new Image();
        healthBackgroundLeftEnd.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_HEALTH_BAR_END), 0, 0, 8, 32)));
        healthBackgroundLeftEnd.setScaling(Scaling.fit);
        healthBackgroundLeftEnd.setAlign(Align.left);
        Image healthBackgroundRightEnd = new Image();
        healthBackgroundRightEnd.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_HEALTH_BAR_END), 0, 0, 8, 32)));
        healthBackgroundRightEnd.setScaling(Scaling.fit);
        healthBackgroundRightEnd.setAlign(Align.left);

        Table tableHealth = new Table();
        tableHealth.top();
        tableHealth.left();
        tableHealth.add(healthBackgroundLeftEnd).align(Align.left);
        tableHealth.add(stackHealthBar).align(Align.left);
        tableHealth.add(healthBackgroundRightEnd).align(Align.left);

        Label labelScoreTitle = new Label("SCORE", game.styles.skinLabelInGameTitle);
        labelScore = new Label("0", game.styles.skinLabelInGameText);

        Label labelComboTitle = new Label("COMBO", game.styles.skinLabelInGameTitle);
        labelCombo = new Label("0", game.styles.skinLabelInGameText);

        Label labelDistanceTitle = new Label("DISTANCE", game.styles.skinLabelInGameTitle);
        labelDistance = new Label("0 m", game.styles.skinLabelInGameText);

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

        if(Gdx.app.getType() == Application.ApplicationType.Android ||
                Gdx.app.getType() != Application.ApplicationType.iOS) {

            final ImageButton btPause = new ImageButton(
                    game.styles.skinImageButtonPause);

            ImageButton btMoveUp = new ImageButton(
                    game.styles.skinImageButtonMoveUp);
            ImageButton btMoveDown = new ImageButton(
                    game.styles.skinImageButtonMoveDown);
            ImageButton btUseSecWea = new ImageButton(
                    game.styles.skinImageButtonUseSecWea);
            ImageButton btUsePriWea = new ImageButton(
                    game.styles.skinImageButtonUsePriWea);

            super.add();
            super.add(btPause).align(Align.topRight).width(Constants.BUTTON_WIDTH_IMAGE).height(Constants.BUTTON_HEIGHT_IMAGE);
            super.row();
            super.add(btMoveUp).width(Constants.BUTTON_WIDTH_GAME).height(Constants.BUTTON_HEIGHT_GAME).expandY().align(Align.bottomLeft).padBottom(20);
            super.row();
            super.add(btMoveDown).width(Constants.BUTTON_WIDTH_GAME).height(Constants.BUTTON_HEIGHT_GAME).align(Align.bottomLeft);
            super.add(btUseSecWea).width(Constants.BUTTON_WIDTH_GAME).height(Constants.BUTTON_HEIGHT_GAME).expandX().align(Align.bottomRight).padRight(20);
            super.add(btUsePriWea).width(Constants.BUTTON_WIDTH_GAME).height(Constants.BUTTON_HEIGHT_GAME).align(Align.bottomRight);

            btPause.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if (y > 0 && y < + btPause.getHeight()) {
                        if (x > 0 && x < btPause.getWidth()) {
                            btPausePressed = true;
                        }
                    }
                }
            });

            btMoveUp.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    btMoveUpPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    btMoveUpPressed = false;
                }
            });

            btMoveDown.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    btMoveDownPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    btMoveDownPressed = false;
                }
            });

            btUsePriWea.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    btUsePriWeaPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    btUsePriWeaPressed = false;
                }
            });

            btUseSecWea.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    btUseSecWeaPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    btUseSecWeaPressed = false;
                }
            });
        }
    }

    public void setHealth(float health) {
        if(health <= 0)
            health = 0;
        imageHealth.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_HEALTH_BAR_CONDITION
        ), 0, 0, (int)health, 20)));
    }

    public void setScore(long score) {
        labelScore.setText(game.styles.getFormattedScore(score));
    }

    public void setCombo(long combo) {
        labelCombo.setText(game.styles.getFormattedScore(combo));
    }

    public void setDistance(long distance) {
        labelDistance.setText(game.styles.getFormattedScore(distance) + " m");
    }
}