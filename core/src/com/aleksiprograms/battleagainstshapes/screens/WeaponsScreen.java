package com.aleksiprograms.battleagainstshapes.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class WeaponsScreen extends AbstractScreen {

    private TextButton btMachineGun;
    private TextButton btShotgun;
    private TextButton btFlamethrower;
    private TextButton btKnifeThrower;
    private TextButton btGrenadeLauncher;
    private TextButton btRocketLauncher;
    private TextButton btDynamiteLauncher;
    private TextButton btBladeLauncher;
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
        initScreen();
    }

    @Override
    public void show() {
        updateData();
        super.show();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        game.appTime += deltaTime;
        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    game.setScreen(game.homeScreen);
                }
            })));
        }
    }

    private void initScreen() {
        final ImageButton btPlay = new ImageButton(game.styles.skinImageButtonPlay);
        final ImageButton btHome = new ImageButton(game.styles.skinImageButtonHome);
        Table tableButtons = new Table();
        tableButtons.add(btPlay).padBottom(10).width(Constants.BUTTON_WIDTH_IMAGE).height(Constants.BUTTON_HEIGHT_IMAGE).align(Align.right);
        tableButtons.row();
        tableButtons.add(btHome).padBottom(10).width(Constants.BUTTON_WIDTH_IMAGE).height(Constants.BUTTON_HEIGHT_IMAGE).align(Align.right);

        Table tablePriWea = new Table();
        tablePriWea.pad(5);
        tablePriWea.background(new TextureRegionDrawable(
                game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_GREY)));

        Table tableSecWea = new Table();
        tableSecWea.pad(5);
        tableSecWea.background(new TextureRegionDrawable(
                game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_GREY)));

        Label labelPriWeapon   = new Label("Primary weapon", game.styles.skinLabelTitle3);
        Label labelSecWeapon   = new Label("Secondary weapon", game.styles.skinLabelTitle3);

        btMachineGun       = new TextButton("MACHINE GUN", game.styles.skinButtonWeaponSelect);
        btShotgun          = new TextButton("SHOTGUN", game.styles.skinButtonWeaponSelect);
        btFlamethrower     = new TextButton("FLAMETHROWER", game.styles.skinButtonWeaponSelect);
        btKnifeThrower     = new TextButton("KNIFE THROWER", game.styles.skinButtonWeaponSelect);
        btGrenadeLauncher  = new TextButton("GRENADE LAUNCHER", game.styles.skinButtonWeaponSelect);
        btRocketLauncher   = new TextButton("ROCKET LAUNCHER", game.styles.skinButtonWeaponSelect);
        btDynamiteLauncher = new TextButton("DYNAMITE LAUNCHER", game.styles.skinButtonWeaponSelect);
        btBladeLauncher    = new TextButton("BLADE LAUNCHER", game.styles.skinButtonWeaponSelect);

        ButtonGroup<TextButton> btgPriWea = new ButtonGroup<TextButton>(btMachineGun, btShotgun, btFlamethrower, btKnifeThrower);
        btgPriWea.setMaxCheckCount(1);
        btgPriWea.setMinCheckCount(1);
        btgPriWea.setUncheckLast(true);
        ButtonGroup<TextButton> btgSecWea = new ButtonGroup<TextButton>(btGrenadeLauncher, btRocketLauncher, btDynamiteLauncher, btBladeLauncher);
        btgSecWea.setMaxCheckCount(1);
        btgSecWea.setMinCheckCount(1);
        btgSecWea.setUncheckLast(true);

        tablePriWea.add(btMachineGun).pad(5).width(Constants.BUTTON_WIDTH_WEA_SEL).height(Constants.BUTTON_HEIGHT_WEA_SEL);
        tablePriWea.row();
        tablePriWea.add(btShotgun).pad(5).width(Constants.BUTTON_WIDTH_WEA_SEL).height(Constants.BUTTON_HEIGHT_WEA_SEL);
        tablePriWea.row();
        tablePriWea.add(btFlamethrower).pad(5).width(Constants.BUTTON_WIDTH_WEA_SEL).height(Constants.BUTTON_HEIGHT_WEA_SEL);
        tablePriWea.row();
        tablePriWea.add(btKnifeThrower).pad(5).width(Constants.BUTTON_WIDTH_WEA_SEL).height(Constants.BUTTON_HEIGHT_WEA_SEL);

        tableSecWea.add(btGrenadeLauncher).pad(5).width(Constants.BUTTON_WIDTH_WEA_SEL).height(Constants.BUTTON_HEIGHT_WEA_SEL);
        tableSecWea.row();
        tableSecWea.add(btRocketLauncher).pad(5).width(Constants.BUTTON_WIDTH_WEA_SEL).height(Constants.BUTTON_HEIGHT_WEA_SEL);
        tableSecWea.row();
        tableSecWea.add(btDynamiteLauncher).pad(5).width(Constants.BUTTON_WIDTH_WEA_SEL).height(Constants.BUTTON_HEIGHT_WEA_SEL);
        tableSecWea.row();
        tableSecWea.add(btBladeLauncher).pad(5).width(Constants.BUTTON_WIDTH_WEA_SEL).height(Constants.BUTTON_HEIGHT_WEA_SEL);

        imageStarLocked1 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED)));
        imageStarUnlocked1 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED)));
        imageStarLocked2 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED)));
        imageStarUnlocked2 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED)));
        imageStarLocked3 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED)));
        imageStarUnlocked3 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED)));

        labelLevelTitle = new Label("", game.styles.skinLabelTitle2);
        Image imageBestScore = new Image();
        imageBestScore.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_SCORE))));
        labelBestScore = new Label(" ", game.styles.skinLabelNumberText);
        Image imageBestDistance = new Image();
        imageBestDistance.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_DISTANCE))));
        labelBestDistance = new Label(" ", game.styles.skinLabelNumberText);
        imageStar1 = new Image();
        imageStar2 = new Image();
        imageStar3 = new Image();
        Table tableScoreLimitStars1 = new Table();
        tableScoreLimitStars1.add(new Image(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))))).width(labelBestScore.getHeight()*0.8f).height(labelBestScore.getHeight()*0.8f);
        labelStarLimit1 = new Label("", game.styles.skinLabelLimitsText);
        Table tableScoreLimitStars2 = new Table();
        tableScoreLimitStars2.add(new Image(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))))).padRight(2).width(labelBestScore.getHeight()*0.8f).height(labelBestScore.getHeight()*0.8f);
        tableScoreLimitStars2.add(new Image(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))))).width(labelBestScore.getHeight()*0.8f).height(labelBestScore.getHeight()*0.8f);
        labelStarLimit2 = new Label("", game.styles.skinLabelLimitsText);
        Table tableScoreLimitStars3 = new Table();
        tableScoreLimitStars3.add(new Image(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))))).padRight(2).width(labelBestScore.getHeight()*0.8f).height(labelBestScore.getHeight()*0.8f);
        tableScoreLimitStars3.add(new Image(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))))).padRight(2).width(labelBestScore.getHeight()*0.8f).height(labelBestScore.getHeight()*0.8f);
        tableScoreLimitStars3.add(new Image(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))))).width(labelBestScore.getHeight()*0.8f).height(labelBestScore.getHeight()*0.8f);
        labelStarLimit3 = new Label("", game.styles.skinLabelLimitsText);
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
        tableStars.add(imageStar1).align(Align.topLeft).width(labelBestScore.getHeight()).height(labelBestScore.getHeight());
        tableStars.add(imageStar2).align(Align.topLeft).padLeft(2).width(labelBestScore.getHeight()).height(labelBestScore.getHeight());
        tableStars.add(imageStar3).align(Align.topLeft).padLeft(2).width(labelBestScore.getHeight()).height(labelBestScore.getHeight());
        Table tableInfo = new Table();
        tableInfo.add(labelLevelTitle).colspan(2).align(Align.topLeft).padBottom(10).expandX();
        tableInfo.row();
        tableInfo.add(imageBestScore).align(Align.topLeft).width(labelBestScore.getHeight()).height(labelBestScore.getHeight()).padRight(10).padBottom(10);
        tableInfo.add(labelBestScore).align(Align.topLeft).expandX().padBottom(10);
        tableInfo.row();
        tableInfo.add(imageBestDistance).align(Align.topLeft).width(labelBestDistance.getHeight()).height(labelBestDistance.getHeight()).padRight(10).padBottom(10);
        tableInfo.add(labelBestDistance).align(Align.topLeft).expandX().padBottom(10);
        tableInfo.row();
        tableInfo.add(tableStars).colspan(2).align(Align.topLeft).expandX().padBottom(10);
        tableInfo.row();
        tableInfo.add(new Label("Limits", game.styles.skinLabelTitle3)).colspan(2).expandX().align(Align.topLeft);
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
        tableContent.add(new Label("WEAPONS", game.styles.skinLabelTitle1)).expandX().align(Align.top).colspan(4);
        tableContent.row();
        tableContent.add(tableInfo).align(Align.topLeft).expand();
        tableContent.add(tablePriWeaSelect).align(Align.top).expandY().padTop(30).padRight(20);
        tableContent.add(tableSecWeaSelect).align(Align.top).expandY().padTop(30);
        tableContent.add(tableButtons).align(Align.right).expand();

        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_BACKGROUND))));
        table.add(tableContent).align(Align.center).grow().pad(Constants.PAD_SCREEN);

        stage.addActor(table);

        btPlay.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(y > 0 && y <  + btPlay.getHeight()) {
                    if(x > 0 && x < btPlay.getWidth()) {
                        if(game.saveManager.saveData.getPrimaryWeapon() == Constants.MACHINE_GUN_ID) {
                            game.player.primaryWeapon = game.gameObjectPools.machineGunPool.obtain();
                        } else if(game.saveManager.saveData.getPrimaryWeapon() == Constants.SHOTGUN_ID) {
                            game.player.primaryWeapon = game.gameObjectPools.shotgunPool.obtain();
                        } else if(game.saveManager.saveData.getPrimaryWeapon() == Constants.FLAMETHROWER_ID) {
                            game.player.primaryWeapon = game.gameObjectPools.flamethrowerPool.obtain();
                        } else if(game.saveManager.saveData.getPrimaryWeapon() == Constants.KNIFE_THROWER_ID) {
                            game.player.primaryWeapon = game.gameObjectPools.swordHolderPool.obtain();
                        }

                        if(game.saveManager.saveData.getSecondaryWeapon() == Constants.GRENADE_LAUNCHER_ID) {
                            game.player.secondaryWeapon = game.gameObjectPools.grenadeLauncherPool.obtain();
                        } else if(game.saveManager.saveData.getSecondaryWeapon() == Constants.ROCKET_LAUNCHER_ID) {
                            game.player.secondaryWeapon = game.gameObjectPools.rocketLauncherPool.obtain();
                        } else if(game.saveManager.saveData.getSecondaryWeapon() == Constants.DYNAMITE_LAUNCHER_ID) {
                            game.player.secondaryWeapon = game.gameObjectPools.airCannonPool.obtain();
                        } else if(game.saveManager.saveData.getSecondaryWeapon() == Constants.BLADE_LAUNCHER_ID) {
                            game.player.secondaryWeapon = game.gameObjectPools.bladeLauncherPool.obtain();
                        }

                        game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                        stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(game.gameScreen);
                            }
                        })));
                    }
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
                if(y > 0 && y <  + btHome.getHeight()) {
                    if(x > 0 && x < btHome.getWidth()) {
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

        btMachineGun.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < + btMachineGun.getHeight()) {
                    if (x > 0 && x < btMachineGun.getWidth()) {
                        if (btMachineGun.isDisabled()) {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(game.saveManager.saveData.getSoundVolume());
                        } else {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                            game.saveManager.saveData.setPrimaryWeapon(Constants.MACHINE_GUN_ID);
                        }
                    }
                }
            }
        });

        btShotgun.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < + btShotgun.getHeight()) {
                    if (x > 0 && x < btShotgun.getWidth()) {
                        if (btShotgun.isDisabled()) {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(game.saveManager.saveData.getSoundVolume());
                        } else {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                            game.saveManager.saveData.setPrimaryWeapon(Constants.SHOTGUN_ID);
                        }
                    }
                }
            }
        });

        btFlamethrower.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < + btFlamethrower.getHeight()) {
                    if (x > 0 && x < btFlamethrower.getWidth()) {
                        if (btFlamethrower.isDisabled()) {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(game.saveManager.saveData.getSoundVolume());
                        } else {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                            game.saveManager.saveData.setPrimaryWeapon(Constants.FLAMETHROWER_ID);
                        }
                    }
                }
            }
        });

        btKnifeThrower.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < + btKnifeThrower.getHeight()) {
                    if (x > 0 && x < btKnifeThrower.getWidth()) {
                        if (btKnifeThrower.isDisabled()) {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(game.saveManager.saveData.getSoundVolume());
                        } else {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                            game.saveManager.saveData.setPrimaryWeapon(Constants.KNIFE_THROWER_ID);
                        }
                    }
                }
            }
        });

        btGrenadeLauncher.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < + btGrenadeLauncher.getHeight()) {
                    if (x > 0 && x < btGrenadeLauncher.getWidth()) {
                        if (btGrenadeLauncher.isDisabled()) {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(game.saveManager.saveData.getSoundVolume());
                        } else {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                            game.saveManager.saveData.setSecondaryWeapon(Constants.GRENADE_LAUNCHER_ID);
                        }
                    }
                }
            }
        });

        btRocketLauncher.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < + btRocketLauncher.getHeight()) {
                    if (x > 0 && x < btRocketLauncher.getWidth()) {
                        if (btRocketLauncher.isDisabled()) {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(game.saveManager.saveData.getSoundVolume());
                        } else {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                            game.saveManager.saveData.setSecondaryWeapon(Constants.ROCKET_LAUNCHER_ID);
                        }
                    }
                }
            }
        });

        btDynamiteLauncher.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < + btDynamiteLauncher.getHeight()) {
                    if (x > 0 && x < btDynamiteLauncher.getWidth()) {
                        if (btDynamiteLauncher.isDisabled()) {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(game.saveManager.saveData.getSoundVolume());
                        } else {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                            game.saveManager.saveData.setSecondaryWeapon(Constants.DYNAMITE_LAUNCHER_ID);
                        }
                    }
                }
            }
        });

        btBladeLauncher.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < + btBladeLauncher.getHeight()) {
                    if (x > 0 && x < btBladeLauncher.getWidth()) {
                        if (btBladeLauncher.isDisabled()) {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(game.saveManager.saveData.getSoundVolume());
                        } else {
                            game.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(game.saveManager.saveData.getSoundVolume());
                            game.saveManager.saveData.setSecondaryWeapon(Constants.BLADE_LAUNCHER_ID);
                        }
                    }
                }
            }
        });
    }

    private void updateData() {
        if (game.saveManager.saveData.getPrimaryWeapon() == Constants.MACHINE_GUN_ID)
            btMachineGun.setChecked(true);
        if (game.saveManager.saveData.getPrimaryWeapon() == Constants.SHOTGUN_ID)
            btShotgun.setChecked(true);
        if (game.saveManager.saveData.getPrimaryWeapon() == Constants.FLAMETHROWER_ID)
            btFlamethrower.setChecked(true);
        if (game.saveManager.saveData.getPrimaryWeapon() == Constants.KNIFE_THROWER_ID)
            btKnifeThrower.setChecked(true);
        if (game.saveManager.saveData.getSecondaryWeapon() == Constants.GRENADE_LAUNCHER_ID)
            btGrenadeLauncher.setChecked(true);
        if (game.saveManager.saveData.getSecondaryWeapon() == Constants.ROCKET_LAUNCHER_ID)
            btRocketLauncher.setChecked(true);
        if (game.saveManager.saveData.getSecondaryWeapon() == Constants.DYNAMITE_LAUNCHER_ID)
            btDynamiteLauncher.setChecked(true);
        if (game.saveManager.saveData.getSecondaryWeapon() == Constants.BLADE_LAUNCHER_ID)
            btBladeLauncher.setChecked(true);

        labelLevelTitle.setText(game.gameMode.name);
        labelBestScore.setText(game.styles.getFormattedScore(game.saveManager.saveData.getLevelScore(game.gameMode.id)));
        labelBestDistance.setText(game.styles.getFormattedScore(game.saveManager.saveData.getLevelDistance(game.gameMode.id)) + " m");
        if (game.saveManager.saveData.getLevelStars(game.gameMode.id) >= 1)
            imageStar1.setDrawable(imageStarUnlocked1);
        else
            imageStar1.setDrawable(imageStarLocked1);
        if (game.saveManager.saveData.getLevelStars(game.gameMode.id) >= 2)
            imageStar2.setDrawable(imageStarUnlocked2);
        else
            imageStar2.setDrawable(imageStarLocked2);
        if (game.saveManager.saveData.getLevelStars(game.gameMode.id) >= 3)
            imageStar3.setDrawable(imageStarUnlocked3);
        else
            imageStar3.setDrawable(imageStarLocked3);
        labelStarLimit1.setText(game.styles.getFormattedScore(game.gameMode.scoreToOneStar));
        labelStarLimit2.setText(game.styles.getFormattedScore(game.gameMode.scoreToTwoStar));
        labelStarLimit3.setText(game.styles.getFormattedScore(game.gameMode.scoreToThreeStar));
    }
}