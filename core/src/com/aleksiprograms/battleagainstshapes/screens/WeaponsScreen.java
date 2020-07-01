package com.aleksiprograms.battleagainstshapes.screens;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class WeaponsScreen extends AbstractScreen {

    private TextButton buttonMachineGun;
    private TextButton buttonShotgun;
    private TextButton buttonFlamethrower;
    private TextButton buttonKnifeThrower;
    private TextButton buttonGrenadeLauncher;
    private TextButton buttonRocketLauncher;
    private TextButton buttonDynamiteLauncher;
    private TextButton buttonBladeLauncher;
    private Label labelLevelTitle;
    private Label labelBestScore;
    private Label labelBestDistance;
    private Image imageStar1;
    private Image imageStar2;
    private Image imageStar3;
    private Label labelStarLimit1;
    private Label labelStarLimit2;
    private Label labelStarLimit3;

    private TextureRegionDrawable imageStarLocked1;
    private TextureRegionDrawable imageStarUnlocked1;
    private TextureRegionDrawable imageStarLocked2;
    private TextureRegionDrawable imageStarUnlocked2;
    private TextureRegionDrawable imageStarLocked3;
    private TextureRegionDrawable imageStarUnlocked3;

    public WeaponsScreen(TheGame game) {
        super(game);
        initialize();
    }

    @Override
    public void updateData() {
        super.updateData();
        if (game.getSaveManager().getSaveData().getSelectedPrimaryWeapon()
                == Constants.MACHINE_GUN_ID) {
            buttonMachineGun.setChecked(true);
        }
        if (game.getSaveManager().getSaveData().getSelectedPrimaryWeapon()
                == Constants.SHOTGUN_ID) {
            buttonShotgun.setChecked(true);
        }
        if (game.getSaveManager().getSaveData().getSelectedPrimaryWeapon()
                == Constants.FLAMETHROWER_ID) {
            buttonFlamethrower.setChecked(true);
        }
        if (game.getSaveManager().getSaveData().getSelectedPrimaryWeapon()
                == Constants.KNIFE_THROWER_ID) {
            buttonKnifeThrower.setChecked(true);
        }
        if (game.getSaveManager().getSaveData().getSelectedSecondaryWeapon()
                == Constants.GRENADE_LAUNCHER_ID) {
            buttonGrenadeLauncher.setChecked(true);
        }
        if (game.getSaveManager().getSaveData().getSelectedSecondaryWeapon()
                == Constants.ROCKET_LAUNCHER_ID) {
            buttonRocketLauncher.setChecked(true);
        }
        if (game.getSaveManager().getSaveData().getSelectedSecondaryWeapon()
                == Constants.DYNAMITE_LAUNCHER_ID) {
            buttonDynamiteLauncher.setChecked(true);
        }
        if (game.getSaveManager().getSaveData().getSelectedSecondaryWeapon()
                == Constants.BLADE_LAUNCHER_ID) {
            buttonBladeLauncher.setChecked(true);
        }

        labelLevelTitle.setText(game.getGameScreen().getGameMode().getName());
        labelBestScore.setText(game.getResources().getStyles().getFormattedScore(
                game.getSaveManager().getSaveData().getGameModeScore(
                        game.getGameScreen().getGameMode().getGameModeID())));
        labelBestDistance.setText(game.getResources().getStyles().getFormattedScore(
                game.getSaveManager().getSaveData().getGameModeDistance(
                        game.getGameScreen().getGameMode().getGameModeID())) + " m");
        if (game.getSaveManager().getSaveData().getGameModeStars(
                game.getGameScreen().getGameMode().getGameModeID()) >= 1) {
            imageStar1.setDrawable(imageStarUnlocked1);
        } else {
            imageStar1.setDrawable(imageStarLocked1);
        }
        if (game.getSaveManager().getSaveData().getGameModeStars(
                game.getGameScreen().getGameMode().getGameModeID()) >= 2) {
            imageStar2.setDrawable(imageStarUnlocked2);
        } else {
            imageStar2.setDrawable(imageStarLocked2);
        }
        if (game.getSaveManager().getSaveData().getGameModeStars(
                game.getGameScreen().getGameMode().getGameModeID()) >= 3) {
            imageStar3.setDrawable(imageStarUnlocked3);
        } else {
            imageStar3.setDrawable(imageStarLocked3);
        }
        labelStarLimit1.setText(game.getResources().getStyles().getFormattedScore(
                game.getGameScreen().getGameMode().getScoreToOneStar()));
        labelStarLimit2.setText(game.getResources().getStyles().getFormattedScore(
                game.getGameScreen().getGameMode().getScoreToTwoStar()));
        labelStarLimit3.setText(game.getResources().getStyles().getFormattedScore(
                game.getGameScreen().getGameMode().getScoreToThreeStar()));
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

    private void initialize() {
        final ImageButton buttonPlay = new ImageButton(
                game.getResources().getStyles().getImageButtonStylePlay());
        final ImageButton buttonMainMenu = new ImageButton(
                game.getResources().getStyles().getImageButtonStyleHome());
        Table tableButtons = new Table();
        tableButtons.add(buttonPlay).align(Align.right).padBottom(10)
                .width(Constants.BUTTON_WIDTH_IMAGE)
                .height(Constants.BUTTON_HEIGHT_IMAGE);
        tableButtons.row();
        tableButtons.add(buttonMainMenu).align(Align.right).padBottom(10)
                .width(Constants.BUTTON_WIDTH_IMAGE)
                .height(Constants.BUTTON_HEIGHT_IMAGE);

        Table tablePriWea = new Table();
        tablePriWea.pad(5);
        tablePriWea.background(new TextureRegionDrawable(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_RECTANGLE_GREY)));

        Table tableSecWea = new Table();
        tableSecWea.pad(5);
        tableSecWea.background(new TextureRegionDrawable(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_RECTANGLE_GREY)));

        Label labelPriWeapon = new Label("Primary weapon",
                game.getResources().getStyles().getLabelStyleRedMedium());
        Label labelSecWeapon = new Label("Secondary weapon",
                game.getResources().getStyles().getLabelStyleRedMedium());

        buttonMachineGun = new TextButton("MACHINE GUN",
                game.getResources().getStyles().getTextButtonStyleWeaponSelect());
        buttonShotgun = new TextButton("SHOTGUN",
                game.getResources().getStyles().getTextButtonStyleWeaponSelect());
        buttonFlamethrower = new TextButton("FLAMETHROWER",
                game.getResources().getStyles().getTextButtonStyleWeaponSelect());
        buttonKnifeThrower = new TextButton("KNIFE THROWER",
                game.getResources().getStyles().getTextButtonStyleWeaponSelect());
        buttonGrenadeLauncher = new TextButton("GRENADE LAUNCHER",
                game.getResources().getStyles().getTextButtonStyleWeaponSelect());
        buttonRocketLauncher = new TextButton("ROCKET LAUNCHER",
                game.getResources().getStyles().getTextButtonStyleWeaponSelect());
        buttonDynamiteLauncher = new TextButton("DYNAMITE LAUNCHER",
                game.getResources().getStyles().getTextButtonStyleWeaponSelect());
        buttonBladeLauncher = new TextButton("BLADE LAUNCHER",
                game.getResources().getStyles().getTextButtonStyleWeaponSelect());

        ButtonGroup<TextButton> btgPriWea = new ButtonGroup<TextButton>(
                buttonMachineGun,
                buttonShotgun,
                buttonFlamethrower,
                buttonKnifeThrower);
        btgPriWea.setMaxCheckCount(1);
        btgPriWea.setMinCheckCount(1);
        btgPriWea.setUncheckLast(true);
        ButtonGroup<TextButton> btgSecWea = new ButtonGroup<TextButton>(
                buttonGrenadeLauncher,
                buttonRocketLauncher,
                buttonDynamiteLauncher,
                buttonBladeLauncher);
        btgSecWea.setMaxCheckCount(1);
        btgSecWea.setMinCheckCount(1);
        btgSecWea.setUncheckLast(true);

        tablePriWea.add(buttonMachineGun).pad(5)
                .width(Constants.BUTTON_WIDTH_WEA_SEL)
                .height(Constants.BUTTON_HEIGHT_WEA_SEL);
        tablePriWea.row();
        tablePriWea.add(buttonShotgun).pad(5)
                .width(Constants.BUTTON_WIDTH_WEA_SEL)
                .height(Constants.BUTTON_HEIGHT_WEA_SEL);
        tablePriWea.row();
        tablePriWea.add(buttonFlamethrower).pad(5)
                .width(Constants.BUTTON_WIDTH_WEA_SEL)
                .height(Constants.BUTTON_HEIGHT_WEA_SEL);
        tablePriWea.row();
        tablePriWea.add(buttonKnifeThrower).pad(5)
                .width(Constants.BUTTON_WIDTH_WEA_SEL)
                .height(Constants.BUTTON_HEIGHT_WEA_SEL);

        tableSecWea.add(buttonGrenadeLauncher).pad(5)
                .width(Constants.BUTTON_WIDTH_WEA_SEL)
                .height(Constants.BUTTON_HEIGHT_WEA_SEL);
        tableSecWea.row();
        tableSecWea.add(buttonRocketLauncher).pad(5)
                .width(Constants.BUTTON_WIDTH_WEA_SEL)
                .height(Constants.BUTTON_HEIGHT_WEA_SEL);
        tableSecWea.row();
        tableSecWea.add(buttonDynamiteLauncher).pad(5)
                .width(Constants.BUTTON_WIDTH_WEA_SEL)
                .height(Constants.BUTTON_HEIGHT_WEA_SEL);
        tableSecWea.row();
        tableSecWea.add(buttonBladeLauncher).pad(5)
                .width(Constants.BUTTON_WIDTH_WEA_SEL)
                .height(Constants.BUTTON_HEIGHT_WEA_SEL);

        imageStarLocked1 = new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_STAR_LOCKED)));
        imageStarUnlocked1 = new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_STAR_UNLOCKED)));
        imageStarLocked2 = new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_STAR_LOCKED)));
        imageStarUnlocked2 = new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_STAR_UNLOCKED)));
        imageStarLocked3 = new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_STAR_LOCKED)));
        imageStarUnlocked3 = new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_STAR_UNLOCKED)));

        labelLevelTitle = new Label("",
                game.getResources().getStyles().getLabelStyleGreenBig());
        Image imageBestScore = new Image();
        imageBestScore.setDrawable(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_SCORE))));
        labelBestScore = new Label(" ",
                game.getResources().getStyles().getLabelStyleWhiteMedium());
        Image imageBestDistance = new Image();
        imageBestDistance.setDrawable(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_DISTANCE))));
        labelBestDistance = new Label(" ",
                game.getResources().getStyles().getLabelStyleWhiteMedium());
        imageStar1 = new Image();
        imageStar2 = new Image();
        imageStar3 = new Image();
        float starWidth = labelBestScore.getHeight() * 0.8f;
        float starHeight = labelBestScore.getHeight() * 0.8f;
        Table tableScoreLimitStars1 = new Table();
        tableScoreLimitStars1.add(new Image(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_STAR_UNLOCKED)))))
                .width(starWidth).height(starHeight);
        labelStarLimit1 = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        Table tableScoreLimitStars2 = new Table();
        tableScoreLimitStars2.add(new Image(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_STAR_UNLOCKED)))))
                .padRight(2).width(starWidth).height(starHeight);
        tableScoreLimitStars2.add(new Image(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_STAR_UNLOCKED)))))
                .width(starWidth).height(starHeight);
        labelStarLimit2 = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        Table tableScoreLimitStars3 = new Table();
        tableScoreLimitStars3.add(new Image(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_STAR_UNLOCKED)))))
                .padRight(2).width(starWidth).height(starHeight);
        tableScoreLimitStars3.add(new Image(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_STAR_UNLOCKED)))))
                .padRight(2).width(starWidth).height(starHeight);
        tableScoreLimitStars3.add(new Image(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_STAR_UNLOCKED)))))
                .width(starWidth).height(starHeight);
        labelStarLimit3 = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        Table tableScoreLimits = new Table();
        tableScoreLimits.add(tableScoreLimitStars1).padRight(10).expandX().align(Align.right);
        tableScoreLimits.add(labelStarLimit1).expandX().align(Align.left);
        tableScoreLimits.row();
        tableScoreLimits.add(tableScoreLimitStars2).padRight(10).expandX().align(Align.right);
        tableScoreLimits.add(labelStarLimit2).expandX().align(Align.left);
        tableScoreLimits.row();
        tableScoreLimits.add(tableScoreLimitStars3).padRight(10).expandX().align(Align.right);
        tableScoreLimits.add(labelStarLimit3).expandX().align(Align.left);
        Table tableStars = new Table();
        tableStars.add(imageStar1).align(Align.topLeft)
                .width(labelBestScore.getHeight())
                .height(labelBestScore.getHeight());
        tableStars.add(imageStar2).align(Align.topLeft).padLeft(2)
                .width(labelBestScore.getHeight())
                .height(labelBestScore.getHeight());
        tableStars.add(imageStar3).align(Align.topLeft).padLeft(2)
                .width(labelBestScore.getHeight())
                .height(labelBestScore.getHeight());
        Table tableInfo = new Table();
        tableInfo.add(labelLevelTitle).colspan(2).align(Align.topLeft).padBottom(10).expandX();
        tableInfo.row();
        tableInfo.add(imageBestScore).align(Align.topLeft)
                .width(labelBestScore.getHeight())
                .height(labelBestScore.getHeight()).padRight(10).padBottom(10);
        tableInfo.add(labelBestScore).align(Align.topLeft).expandX().padBottom(10);
        tableInfo.row();
        tableInfo.add(imageBestDistance).align(Align.topLeft)
                .width(labelBestDistance.getHeight())
                .height(labelBestDistance.getHeight()).padRight(10).padBottom(10);
        tableInfo.add(labelBestDistance).align(Align.topLeft).expandX().padBottom(10);
        tableInfo.row();
        tableInfo.add(tableStars).colspan(2).align(Align.topLeft).expandX().padBottom(10);
        tableInfo.row();
        tableInfo.add(new Label("Limits",
                game.getResources().getStyles().getLabelStyleRedMedium()))
                .colspan(2).expandX().align(Align.topLeft);
        tableInfo.row();
        tableInfo.add(tableScoreLimits).colspan(2).align(Align.topLeft).expandX().padLeft(20);

        Table tablePriWeaSelect = new Table();
        tablePriWeaSelect.add(labelPriWeapon);
        tablePriWeaSelect.row();
        tablePriWeaSelect.add(tablePriWea).padTop(10);

        Table tableSecWeaSelect = new Table();
        tableSecWeaSelect.add(labelSecWeapon);
        tableSecWeaSelect.row();
        tableSecWeaSelect.add(tableSecWea).padTop(10);

        Table tableContent = new Table();
        tableContent.add(new Label("WEAPONS",
                game.getResources().getStyles().getLabelStyleBlueHuge()))
                .expandX().align(Align.top).colspan(4);
        tableContent.row();
        tableContent.add(tableInfo).align(Align.topLeft).expand();
        tableContent.add(tablePriWeaSelect).align(Align.top).expandY().padTop(30).padRight(20);
        tableContent.add(tableSecWeaSelect).align(Align.top).expandY().padTop(30);
        tableContent.add(tableButtons).align(Align.right).expand();

        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_BACKGROUND))));
        table.add(tableContent).align(Align.center).grow().pad(Constants.PAD_SCREEN);

        stage.addActor(table);

        buttonPlay.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonPlay.getWidth()) {
                    if (y > 0 && y < buttonPlay.getHeight()) {
                        game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_POS)
                                .play(game.getSaveManager().getSaveData().getSoundVolume());
                        game.getGameScreen().updateData();
                        game.setScreen(game.getGameScreen());
                    }
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

        buttonMachineGun.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonMainMenu.getWidth()) {
                    if (y > 0 && y < buttonMainMenu.getHeight()) {
                        game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_POS)
                                .play(game.getSaveManager().getSaveData().getSoundVolume());
                        game.getSaveManager().getSaveData()
                                .setSelectedPrimaryWeapon(Constants.MACHINE_GUN_ID);
                    }
                }
            }
        });

        buttonShotgun.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonShotgun.getWidth()) {
                    if (y > 0 && y < buttonShotgun.getHeight()) {
                        game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_POS)
                                .play(game.getSaveManager().getSaveData().getSoundVolume());
                        game.getSaveManager().getSaveData()
                                .setSelectedPrimaryWeapon(Constants.SHOTGUN_ID);
                    }
                }
            }
        });

        buttonFlamethrower.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonFlamethrower.getWidth()) {
                    if (y > 0 && y < buttonFlamethrower.getHeight()) {
                        game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_POS)
                                .play(game.getSaveManager().getSaveData().getSoundVolume());
                        game.getSaveManager().getSaveData()
                                .setSelectedPrimaryWeapon(Constants.FLAMETHROWER_ID);
                    }
                }
            }
        });

        buttonKnifeThrower.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonKnifeThrower.getWidth()) {
                    if (y > 0 && y < buttonKnifeThrower.getHeight()) {
                        game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_POS)
                                .play(game.getSaveManager().getSaveData().getSoundVolume());
                        game.getSaveManager().getSaveData()
                                .setSelectedPrimaryWeapon(Constants.KNIFE_THROWER_ID);
                    }
                }
            }
        });

        buttonGrenadeLauncher.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonGrenadeLauncher.getWidth()) {
                    if (y > 0 && y < buttonGrenadeLauncher.getHeight()) {
                        game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_POS)
                                .play(game.getSaveManager().getSaveData().getSoundVolume());
                        game.getSaveManager().getSaveData()
                                .setSelectedSecondaryWeapon(Constants.GRENADE_LAUNCHER_ID);
                    }
                }
            }
        });

        buttonRocketLauncher.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonRocketLauncher.getWidth()) {
                    if (y > 0 && y < buttonRocketLauncher.getHeight()) {
                        game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_POS)
                                .play(game.getSaveManager().getSaveData().getSoundVolume());
                        game.getSaveManager().getSaveData()
                                .setSelectedSecondaryWeapon(Constants.ROCKET_LAUNCHER_ID);
                    }
                }
            }
        });

        buttonDynamiteLauncher.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonDynamiteLauncher.getWidth()) {
                    if (y > 0 && y < buttonDynamiteLauncher.getHeight()) {
                        game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_POS)
                                .play(game.getSaveManager().getSaveData().getSoundVolume());
                        game.getSaveManager().getSaveData()
                                .setSelectedSecondaryWeapon(Constants.DYNAMITE_LAUNCHER_ID);
                    }
                }
            }
        });

        buttonBladeLauncher.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonBladeLauncher.getWidth()) {
                    if (y > 0 && y < buttonBladeLauncher.getHeight()) {
                        game.getResources().getSounds().getSoundByID(Constants.SOUND_BUTTON_POS)
                                .play(game.getSaveManager().getSaveData().getSoundVolume());
                        game.getSaveManager().getSaveData()
                                .setSelectedSecondaryWeapon(Constants.BLADE_LAUNCHER_ID);
                    }
                }
            }
        });
    }
}