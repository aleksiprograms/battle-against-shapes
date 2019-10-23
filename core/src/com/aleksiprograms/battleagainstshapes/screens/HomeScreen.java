package com.aleksiprograms.battleagainstshapes.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.toolbox.GameMode;

public class HomeScreen extends AbstractScreen {

    private GameMode[] gameModes;
    private Table[] btGameMode = new Table[Constants.GAME_MODES];
    private boolean quitBoxShown;

    class GameModeInfo {
        Label labelScore;
        Label labelDistance;
        Image imageStar1;
        Image imageStar2;
        Image imageStar3;
    }
    private GameModeInfo[] gameModeInfos = new GameModeInfo[3];

    public HomeScreen(TheGame game) {
        super(game);
        gameModes = game.gameModeManager.getGameModes();
        for (int i = 0; i < 3; i++) {
            gameModeInfos[i] = new GameModeInfo();
        }
        initScreen();
    }

    @Override
    public void show() {
        quitBoxShown = false;
        updateGameModeData();
        super.show();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        game.appTime += deltaTime;
        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            showQuitBox();
        }
    }

    private void initScreen() {
        Label lScreenTitle = new Label("HOME", game.styles.skinLabelTitle1);
        Table tableTop = new Table();
        tableTop.add(lScreenTitle).align(Align.top).expandX();

        final ImageButton btStats = new ImageButton(game.styles.skinImageButtonStats);
        final ImageButton btSettings = new ImageButton(game.styles.skinImageButtonSettings);
        final ImageButton btCredits = new ImageButton(game.styles.skinImageButtonCredits);
        Table tableButtons = new Table();
        tableButtons.add(btStats).padBottom(10).width(Constants.BUTTON_WIDTH_IMAGE).height(Constants.BUTTON_HEIGHT_IMAGE).align(Align.right);
        tableButtons.row();
        tableButtons.add(btSettings).padBottom(10).width(Constants.BUTTON_WIDTH_IMAGE).height(Constants.BUTTON_HEIGHT_IMAGE).align(Align.right);
        tableButtons.row();
        tableButtons.add(btCredits).width(Constants.BUTTON_WIDTH_IMAGE).height(Constants.BUTTON_HEIGHT_IMAGE).align(Align.right);

        Table tableLevels = new Table();
        tableLevels.add(initGameModeTable(gameModes[0], 0)).align(Align.bottomLeft).expandY();
        tableLevels.add(initGameModeTable(gameModes[1], 1)).align(Align.bottom).expand();
        tableLevels.add(initGameModeTable(gameModes[2], 2)).align(Align.bottomRight).expandY();

        Table tableContent = new Table();
        tableContent.add(tableTop).growX().align(Align.top).colspan(2);
        tableContent.row();
        tableContent.add(tableLevels).fill().expand().align(Align.center);
        tableContent.add(tableButtons).padLeft(30).expandY().align(Align.right);

        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_BACKGROUND))));
        table.add(tableContent).align(Align.center).grow().pad(Constants.PAD_SCREEN);

        stage.addActor(table);

        btStats.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, final int button) {
                if (y > 0 && y < + btStats.getHeight()) {
                    if (x > 0 && x < btStats.getWidth()) {
                        game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                        stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(game.statsScreen);
                            }
                        })));
                    }
                }
            }
        });

        btSettings.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < + btSettings.getHeight()) {
                    if (x > 0 && x < btSettings.getWidth()) {
                        game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                        stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(game.settingsScreen);
                            }
                        })));
                    }
                }
            }
        });

        btCredits.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < + btCredits.getHeight()) {
                    if (x > 0 && x < btCredits.getWidth()) {
                        game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                        stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(game.creditsScreen);
                            }
                        })));
                    }
                }
            }
        });
    }

    private Table initGameModeTable(final GameMode gameMode, final int index) {
        Table tableTop = new Table();
        tableTop.background(new TextureRegionDrawable(
                game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_BLACK)));
        Label labelTitle = new Label(gameMode.name, game.styles.skinLabelTitle2);
        tableTop.add(labelTitle).expandX().align(Align.center);

        Image imageBackground = new Image(new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_BLACK)));
        Image imageGameMode = new Image(new TextureRegionDrawable(game.getTextureRegionByID(gameMode.imageName)));
        Table tableImage = new Table();
        tableImage.add(imageGameMode).width(300).height(150).expand().align(Align.bottom);
        Table tableInfo = new Table();
        tableInfo.setWidth(310);
        tableInfo.setHeight(380);
        Image imageScore = new Image();
        imageScore.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_SCORE))));
        gameModeInfos[index].labelScore = new Label("0", game.styles.skinLabelNumberText);
        Image imageDistance = new Image();
        imageDistance.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_DISTANCE))));
        gameModeInfos[index].labelDistance = new Label("0", game.styles.skinLabelNumberText);
        gameModeInfos[index].imageStar1 = new Image();
        gameModeInfos[index].imageStar2 = new Image();
        gameModeInfos[index].imageStar3 = new Image();

        Table tableScore = new Table();
        tableScore.add(imageScore).width(gameModeInfos[index].labelScore.getHeight()).height(gameModeInfos[index].labelScore.getHeight());
        tableScore.add(gameModeInfos[index].labelScore).padLeft(10);
        Table tableDistance = new Table();
        tableDistance.add(imageDistance).width(gameModeInfos[index].labelScore.getHeight()).height(gameModeInfos[index].labelScore.getHeight());
        tableDistance.add(gameModeInfos[index].labelDistance).padLeft(10);
        Table tableStars = new Table();
        tableStars.add(gameModeInfos[index].imageStar1).width(gameModeInfos[index].labelScore.getHeight()).height(gameModeInfos[index].labelScore.getHeight());
        tableStars.add(gameModeInfos[index].imageStar2).padLeft(2).width(gameModeInfos[index].labelScore.getHeight()).height(gameModeInfos[index].labelScore.getHeight());
        tableStars.add(gameModeInfos[index].imageStar3).padLeft(2).width(gameModeInfos[index].labelScore.getHeight()).height(gameModeInfos[index].labelScore.getHeight());

        tableInfo.add(tableScore).align(Align.top).padTop(30).expandX();
        tableInfo.row();
        tableInfo.add(tableDistance).align(Align.top).padTop(10).expandX();
        tableInfo.row();
        tableInfo.add(tableStars).align(Align.top).padTop(10).expand();
        Stack stackInfo = new Stack();
        stackInfo.add(imageBackground);
        stackInfo.add(tableImage);
        stackInfo.add(tableInfo);

        btGameMode[index] = new Table();
        btGameMode[index].pad(10);
        btGameMode[index].background(new TextureRegionDrawable(
                game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_GREY)));
        btGameMode[index].setTouchable(Touchable.enabled);
        btGameMode[index].add(tableTop).height(70).fill().expandX();
        btGameMode[index].row();
        btGameMode[index].add(stackInfo).align(Align.center).width(300).height(380).padTop(10);

        btGameMode[index].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btGameMode[index].background(new TextureRegionDrawable(
                        game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_BLUE)));

                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if(!(y > 0 && y < + btGameMode[index].getHeight()) || !(x > 0 && x < btGameMode[index].getWidth())) {
                    btGameMode[index].background(new TextureRegionDrawable(
                            game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_GREY)));
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < + btGameMode[index].getHeight() && x > 0 && x < btGameMode[index].getWidth()) {
                    game.gameMode = gameMode;
                    game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                    stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            game.setScreen(game.weaponsScreen);
                        }
                    })));
                } else {
                    btGameMode[index].background(new TextureRegionDrawable(
                            game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_GREY)));
                }
            }
        });
        return btGameMode[index];
    }

    private void updateGameModeData() {
        for (int i = 0; i < gameModes.length; i++) {
            btGameMode[i].background(new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_GREY)));
            gameModeInfos[i].labelScore.setText(game.styles.getFormattedScore(game.saveManager.saveData.getLevelScore(gameModes[i].id)));
            gameModeInfos[i].labelDistance.setText(game.styles.getFormattedScore(game.saveManager.saveData.getLevelDistance(gameModes[i].id)) + " m");
            if (game.saveManager.saveData.getLevelStars(gameModes[i].id) >= 1)
                gameModeInfos[i].imageStar1.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))));
            else
                gameModeInfos[i].imageStar1.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED))));
            if (game.saveManager.saveData.getLevelStars(gameModes[i].id) >= 2)
                gameModeInfos[i].imageStar2.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))));
            else
                gameModeInfos[i].imageStar2.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED))));
            if (game.saveManager.saveData.getLevelStars(gameModes[i].id) >= 3)
                gameModeInfos[i].imageStar3.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))));
            else
                gameModeInfos[i].imageStar3.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED))));
        }
    }

    private void showQuitBox() {
        if (!quitBoxShown) {
            quitBoxShown = true;
            final Dialog dialog = new Dialog("", game.styles.skinDialogBox) {
                @Override
                protected void result(Object object) {
                }
            };
            dialog.padTop(20);
            Label labelText = new Label("Do you want to quit the game?", game.styles.skinLabelDialogText);
            labelText.setWrap(true);
            labelText.setAlignment(Align.center);
            dialog.getContentTable().add(labelText).align(Align.center).width(550).height(250).padLeft(20).padRight(20);
            final TextButton btNo = new TextButton("No", game.styles.skinButtonDialog);
            final TextButton btYes = new TextButton("Yes", game.styles.skinButtonDialog);
            dialog.getButtonTable().pad(20);
            dialog.getButtonTable().add(btNo).padRight(20).width(Constants.BUTTON_WIDTH_DIALOG).height(Constants.BUTTON_HEIGHT_DIALOG);
            dialog.getButtonTable().add(btYes).width(Constants.BUTTON_WIDTH_DIALOG).height(Constants.BUTTON_HEIGHT_DIALOG);

            btNo.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, final int button) {
                    if (y > 0 && y < + btNo.getHeight()) {
                        if (x > 0 && x < btNo.getWidth()) {
                            quitBoxShown = false;
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(game.saveManager.saveData.getSoundVolume());
                            dialog.hide();
                        }
                    }
                }
            });

            btYes.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, final int button) {
                    if (y > 0 && y < + btYes.getHeight()) {
                        if (x > 0 && x < btYes.getWidth()) {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                            stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    Gdx.app.exit();
                                }
                            })));
                        }
                    }
                }
            });

            dialog.setModal(true);
            dialog.setMovable(false);
            dialog.setResizable(false);
            dialog.invalidateHierarchy();
            dialog.invalidate();
            dialog.layout();
            dialog.show(stage);
        }
    }
}