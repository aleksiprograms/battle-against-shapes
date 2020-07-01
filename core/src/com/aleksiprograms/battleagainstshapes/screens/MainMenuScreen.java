package com.aleksiprograms.battleagainstshapes.screens;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.managers.GameModeManager;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.toolbox.GameMode;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
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

public class MainMenuScreen extends AbstractScreen {

    static class GameModeInfo {
        Label labelScore;
        Label labelDistance;
        Image imageStar1;
        Image imageStar2;
        Image imageStar3;
    }

    private GameMode[] gameModes;
    private Table[] gameModeButtons = new Table[Constants.GAME_MODES];
    private boolean quitBoxShown;
    private GameModeInfo[] gameModeInfos = new GameModeInfo[3];

    public MainMenuScreen(TheGame game) {
        super(game);
        GameModeManager gameModeManager = new GameModeManager();
        gameModes = gameModeManager.getGameModes();
        for (int i = 0; i < 3; i++) {
            gameModeInfos[i] = new GameModeInfo();
        }
        initialize();
    }

    @Override
    public void updateData() {
        quitBoxShown = false;
        for (int i = 0; i < gameModes.length; i++) {
            gameModeButtons[i].background(new TextureRegionDrawable(
                    game.getResources().getTextureRegionByID(
                            Constants.TEXTURE_UI_RECTANGLE_GREY)));
            gameModeInfos[i].labelScore.setText(game.getResources().getStyles()
                    .getFormattedScore(game.getSaveManager().getSaveData().getGameModeScore(
                            gameModes[i].getGameModeID())));
            gameModeInfos[i].labelDistance.setText(game.getResources().getStyles()
                    .getFormattedScore(game.getSaveManager().getSaveData().getGameModeDistance(
                            gameModes[i].getGameModeID())) + " m");
            if (game.getSaveManager().getSaveData().getGameModeStars(
                    gameModes[i].getGameModeID()) >= 1) {
                gameModeInfos[i].imageStar1.setDrawable(new TextureRegionDrawable(
                        new TextureRegion(game.getResources().getTextureRegionByID(
                                Constants.TEXTURE_UI_STAR_UNLOCKED))));
            } else {
                gameModeInfos[i].imageStar1.setDrawable(new TextureRegionDrawable(
                        new TextureRegion(game.getResources().getTextureRegionByID(
                                Constants.TEXTURE_UI_STAR_LOCKED))));
            }
            if (game.getSaveManager().getSaveData().getGameModeStars(
                    gameModes[i].getGameModeID()) >= 2) {
                gameModeInfos[i].imageStar2.setDrawable(new TextureRegionDrawable(
                        new TextureRegion(game.getResources().getTextureRegionByID(
                                Constants.TEXTURE_UI_STAR_UNLOCKED))));
            } else {
                gameModeInfos[i].imageStar2.setDrawable(new TextureRegionDrawable(
                        new TextureRegion(game.getResources().getTextureRegionByID(
                                Constants.TEXTURE_UI_STAR_LOCKED))));
            }
            if (game.getSaveManager().getSaveData().getGameModeStars(
                    gameModes[i].getGameModeID()) >= 3) {
                gameModeInfos[i].imageStar3.setDrawable(new TextureRegionDrawable(
                        new TextureRegion(game.getResources().getTextureRegionByID(
                                Constants.TEXTURE_UI_STAR_UNLOCKED))));
            } else {
                gameModeInfos[i].imageStar3.setDrawable(new TextureRegionDrawable(
                        new TextureRegion(game.getResources().getTextureRegionByID(
                                Constants.TEXTURE_UI_STAR_LOCKED))));
            }
        }
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        game.getTimeManager().addToAppTime(deltaTime);
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)
                || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            showQuitBox();
        }
    }

    private void initialize() {
        Label labelScreenTitle = new Label("MAIN MENU",
                game.getResources().getStyles().getLabelStyleBlueHuge());
        Table tableTop = new Table();
        tableTop.add(labelScreenTitle).align(Align.top).expandX();

        final ImageButton buttonStats = new ImageButton(
                game.getResources().getStyles().getImageButtonStyleStats());
        final ImageButton buttonSettings = new ImageButton(
                game.getResources().getStyles().getImageButtonStyleSettings());
        final ImageButton buttonCredits = new ImageButton(
                game.getResources().getStyles().getImageButtonStyleCredits());
        Table tableButtons = new Table();
        tableButtons.add(buttonStats).padBottom(10).align(Align.right)
                .width(Constants.BUTTON_WIDTH_IMAGE)
                .height(Constants.BUTTON_HEIGHT_IMAGE);
        tableButtons.row();
        tableButtons.add(buttonSettings).padBottom(10).align(Align.right)
                .width(Constants.BUTTON_WIDTH_IMAGE)
                .height(Constants.BUTTON_HEIGHT_IMAGE);
        tableButtons.row();
        tableButtons.add(buttonCredits).align(Align.right)
                .width(Constants.BUTTON_WIDTH_IMAGE)
                .height(Constants.BUTTON_HEIGHT_IMAGE);

        Table tableLevels = new Table();
        tableLevels.add(initializeGameModeTable(gameModes[0], 0))
                .align(Align.bottomLeft).expandY();
        tableLevels.add(initializeGameModeTable(gameModes[1], 1))
                .align(Align.bottom).expand();
        tableLevels.add(initializeGameModeTable(gameModes[2], 2))
                .align(Align.bottomRight).expandY();

        Table tableContent = new Table();
        tableContent.add(tableTop).growX().align(Align.top).colspan(2);
        tableContent.row();
        tableContent.add(tableLevels).fill().expand().align(Align.center);
        tableContent.add(tableButtons).padLeft(30).expandY().align(Align.right);

        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_BACKGROUND))));
        table.add(tableContent).align(Align.center).grow().pad(Constants.PAD_SCREEN);

        stage.addActor(table);

        buttonStats.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, final int button) {
                if (x > 0 && x < buttonStats.getWidth()) {
                    if (y > 0 && y < buttonStats.getHeight()) {
                        game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_NEG)
                                .play(game.getSaveManager().getSaveData().getSoundVolume());
                        game.getStatsScreen().updateData();
                        game.setScreen(game.getStatsScreen());
                    }
                }
            }
        });

        buttonSettings.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonSettings.getWidth()) {
                    if (y > 0 && y < buttonSettings.getHeight()) {
                        game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_NEG)
                                .play(game.getSaveManager().getSaveData().getSoundVolume());
                        game.getSettingsScreen().updateData();
                        game.setScreen(game.getSettingsScreen());
                    }
                }
            }
        });

        buttonCredits.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonCredits.getWidth()) {
                    if (y > 0 && y < buttonCredits.getHeight()) {
                        game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_NEG)
                                .play(game.getSaveManager().getSaveData().getSoundVolume());
                        game.getCreditsScreen().updateData();
                        game.setScreen(game.getCreditsScreen());
                    }
                }
            }
        });
    }

    private Table initializeGameModeTable(final GameMode gameMode, final int index) {
        Table tableTop = new Table();
        tableTop.background(new TextureRegionDrawable(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_RECTANGLE_BLACK)));
        Label labelTitle = new Label(gameMode.getName(),
                game.getResources().getStyles().getLabelStyleGreenBig());
        tableTop.add(labelTitle).expandX().align(Align.center);

        Image imageBackground = new Image(new TextureRegionDrawable(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_RECTANGLE_BLACK)));
        Image imageGameMode = new Image(new TextureRegionDrawable(
                game.getResources().getTextureRegionByID(gameMode.getImageName())));
        Table tableImage = new Table();
        tableImage.add(imageGameMode).width(300).height(150).expand().align(Align.bottom);
        Table tableInfo = new Table();
        tableInfo.setWidth(310);
        tableInfo.setHeight(380);
        Image imageScore = new Image();
        imageScore.setDrawable(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_SCORE))));
        gameModeInfos[index].labelScore = new Label("0",
                game.getResources().getStyles().getLabelStyleWhiteMedium());
        Image imageDistance = new Image();
        imageDistance.setDrawable(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_DISTANCE))));
        gameModeInfos[index].labelDistance = new Label("0",
                game.getResources().getStyles().getLabelStyleWhiteMedium());
        gameModeInfos[index].imageStar1 = new Image();
        gameModeInfos[index].imageStar2 = new Image();
        gameModeInfos[index].imageStar3 = new Image();

        Table tableScore = new Table();
        tableScore.add(imageScore)
                .width(gameModeInfos[index].labelScore.getHeight())
                .height(gameModeInfos[index].labelScore.getHeight());
        tableScore.add(gameModeInfos[index].labelScore).padLeft(10);
        Table tableDistance = new Table();
        tableDistance.add(imageDistance)
                .width(gameModeInfos[index].labelScore.getHeight())
                .height(gameModeInfos[index].labelScore.getHeight());
        tableDistance.add(gameModeInfos[index].labelDistance).padLeft(10);
        Table tableStars = new Table();
        tableStars.add(gameModeInfos[index].imageStar1)
                .width(gameModeInfos[index].labelScore.getHeight())
                .height(gameModeInfos[index].labelScore.getHeight());
        tableStars.add(gameModeInfos[index].imageStar2).padLeft(2)
                .width(gameModeInfos[index].labelScore.getHeight())
                .height(gameModeInfos[index].labelScore.getHeight());
        tableStars.add(gameModeInfos[index].imageStar3).padLeft(2)
                .width(gameModeInfos[index].labelScore.getHeight())
                .height(gameModeInfos[index].labelScore.getHeight());

        tableInfo.add(tableScore).align(Align.top).padTop(30).expandX();
        tableInfo.row();
        tableInfo.add(tableDistance).align(Align.top).padTop(10).expandX();
        tableInfo.row();
        tableInfo.add(tableStars).align(Align.top).padTop(10).expand();
        Stack stackInfo = new Stack();
        stackInfo.add(imageBackground);
        stackInfo.add(tableImage);
        stackInfo.add(tableInfo);

        gameModeButtons[index] = new Table();
        gameModeButtons[index].pad(10);
        gameModeButtons[index].background(new TextureRegionDrawable(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_RECTANGLE_GREY)));
        gameModeButtons[index].setTouchable(Touchable.enabled);
        gameModeButtons[index].add(tableTop).height(70).fill().expandX();
        gameModeButtons[index].row();
        gameModeButtons[index].add(stackInfo).align(Align.center).width(300).height(380).padTop(10);

        gameModeButtons[index].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameModeButtons[index].background(new TextureRegionDrawable(
                        game.getResources().getTextureRegionByID(
                                Constants.TEXTURE_UI_RECTANGLE_BLUE)));

                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (!(x > 0 && x < gameModeButtons[index].getWidth())
                        || !(y > 0 && y < gameModeButtons[index].getHeight())) {
                    gameModeButtons[index].background(new TextureRegionDrawable(
                            game.getResources().getTextureRegionByID(
                                    Constants.TEXTURE_UI_RECTANGLE_GREY)));
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < gameModeButtons[index].getWidth()) {
                    if (y > 0 && y < gameModeButtons[index].getHeight()) {
                        game.getGameScreen().setGameMode(gameMode);
                        game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_POS)
                                .play(game.getSaveManager().getSaveData().getSoundVolume());
                        game.getWeaponsScreen().updateData();
                        game.setScreen(game.getWeaponsScreen());
                    }
                } else {
                    gameModeButtons[index].background(new TextureRegionDrawable(
                            game.getResources().getTextureRegionByID(
                                    Constants.TEXTURE_UI_RECTANGLE_GREY)));
                }
            }
        });
        return gameModeButtons[index];
    }

    private void showQuitBox() {
        if (!quitBoxShown) {
            quitBoxShown = true;
            final Dialog dialog = new Dialog("",
                    game.getResources().getStyles().getDialogBoxStyle()) {
                @Override
                protected void result(Object object) {
                }
            };
            dialog.padTop(20);
            Label labelText = new Label("Do you want to quit the game?",
                    game.getResources().getStyles().getLabelStyleDialogText());
            labelText.setWrap(true);
            labelText.setAlignment(Align.center);
            dialog.getContentTable().add(labelText)
                    .align(Align.center).width(550).height(250).padLeft(20).padRight(20);
            final TextButton buttonNo = new TextButton("No",
                    game.getResources().getStyles().getTextButtonStyleDialog());
            final TextButton buttonYes = new TextButton("Yes",
                    game.getResources().getStyles().getTextButtonStyleDialog());
            dialog.getButtonTable().pad(20);
            dialog.getButtonTable().add(buttonNo).padRight(20)
                    .width(Constants.BUTTON_WIDTH_DIALOG)
                    .height(Constants.BUTTON_HEIGHT_DIALOG);
            dialog.getButtonTable().add(buttonYes)
                    .width(Constants.BUTTON_WIDTH_DIALOG)
                    .height(Constants.BUTTON_HEIGHT_DIALOG);

            buttonNo.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y,
                                         int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y,
                                    int pointer, final int button) {
                    if (x > 0 && x < buttonNo.getWidth()) {
                        if (y > 0 && y < buttonNo.getHeight()) {
                            quitBoxShown = false;
                            game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_NEG)
                                    .play(game.getSaveManager().getSaveData().getSoundVolume());
                            dialog.hide();
                        }
                    }
                }
            });

            buttonYes.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y,
                                         int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int
                        pointer, final int button) {
                    if (x > 0 && x < buttonYes.getWidth()) {
                        if (y > 0 && y < buttonYes.getHeight()) {
                            game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_POS)
                                    .play(game.getSaveManager().getSaveData().getSoundVolume());
                            Gdx.app.exit();
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