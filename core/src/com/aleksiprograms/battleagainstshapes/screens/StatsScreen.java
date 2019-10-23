package com.aleksiprograms.battleagainstshapes.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

import java.util.Locale;

public class StatsScreen extends AbstractScreen {

    private Label labelStars;
    private Label labelTotalTime;
    private Label labelBattlingTime;
    private Label labelKills;
    private Label labelFighterKills;
    private Label labelMachineGunKills;
    private Label labelShotgunKills;
    private Label labelFlamethrowerKills;
    private Label labelKnifeThrowerKills;
    private Label labelGrenadeLauncherKills;
    private Label labelRocketLauncherKills;
    private Label labelDynamiteLauncherKills;
    private Label labelBladeLauncherKills;
    private Label labelAccuracy;
    private Label labelMachineGunAccuracy;
    private Label labelShotgunAccuracy;
    private Label labelFlamethrowerAccuracy;
    private Label labelKnifeThrowerAccuracy;
    private Label labelGrenadeLauncherAccuracy;
    private Label labelRocketLauncherAccuracy;
    private Label labelDynamiteLauncherAccuracy;
    private Label labelBladeLauncherAccuracy;

    private long appTime;
    private long daysTotal;
    private long hoursTotal;
    private long minutesTotal;
    private long gameTime;
    private long daysGaming;
    private long hoursGaming;
    private long minutesGaming;

    public StatsScreen(TheGame game) {
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    game.setScreen(game.homeScreen);
                }
            })));
        }
    }

    private void initScreen() {
        labelStars = new Label("", game.styles.skinLabelStatsText);
        labelTotalTime = new Label("", game.styles.skinLabelStatsText);
        labelBattlingTime = new Label("", game.styles.skinLabelStatsText);
        labelKills = new Label("", game.styles.skinLabelStatsText);
        labelFighterKills = new Label("", game.styles.skinLabelStatsText);
        labelMachineGunKills = new Label("", game.styles.skinLabelStatsText);
        labelShotgunKills = new Label("", game.styles.skinLabelStatsText);
        labelFlamethrowerKills = new Label("", game.styles.skinLabelStatsText);
        labelKnifeThrowerKills = new Label("", game.styles.skinLabelStatsText);
        labelGrenadeLauncherKills = new Label("", game.styles.skinLabelStatsText);
        labelRocketLauncherKills = new Label("", game.styles.skinLabelStatsText);
        labelDynamiteLauncherKills = new Label("", game.styles.skinLabelStatsText);
        labelBladeLauncherKills = new Label("", game.styles.skinLabelStatsText);
        labelAccuracy = new Label("", game.styles.skinLabelStatsText);
        labelMachineGunAccuracy = new Label("", game.styles.skinLabelStatsText);
        labelShotgunAccuracy = new Label("", game.styles.skinLabelStatsText);
        labelFlamethrowerAccuracy = new Label("", game.styles.skinLabelStatsText);
        labelKnifeThrowerAccuracy = new Label("", game.styles.skinLabelStatsText);
        labelGrenadeLauncherAccuracy = new Label("", game.styles.skinLabelStatsText);
        labelRocketLauncherAccuracy = new Label("", game.styles.skinLabelStatsText);
        labelDynamiteLauncherAccuracy = new Label("", game.styles.skinLabelStatsText);
        labelBladeLauncherAccuracy = new Label("", game.styles.skinLabelStatsText);

        Table tableStats = new Table();
        tableStats.add(new Label("Stars", game.styles.skinLabelStatsTitle)).align(Align.left).padRight(100);
        tableStats.add(labelStars).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Total time", game.styles.skinLabelStatsTitle)).align(Align.left).padRight(100);
        tableStats.add(labelTotalTime).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Battling time", game.styles.skinLabelStatsTitle)).align(Align.left).padRight(100);
        tableStats.add(labelBattlingTime).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("", game.styles.skinLabelStatsTitle)).align(Align.left).padRight(100);
        tableStats.add(new Label("", game.styles.skinLabelStatsText)).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Kills", game.styles.skinLabelStatsTitle)).align(Align.left).padRight(100);
        tableStats.add(labelMachineGunKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Fighter", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelFighterKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Machine gun", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelMachineGunKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Shotgun", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelShotgunKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Flamethrower", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelFlamethrowerKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Knife thrower", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelKnifeThrowerKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Grenade launcher", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelGrenadeLauncherKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Rocket launcher", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelRocketLauncherKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Dynamite launcher", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelDynamiteLauncherKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Blade launcher", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelBladeLauncherKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("", game.styles.skinLabelStatsTitle)).align(Align.left).padRight(100);
        tableStats.add(new Label("", game.styles.skinLabelStatsText)).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Accuracy", game.styles.skinLabelStatsTitle)).align(Align.left).padRight(100);
        tableStats.add(labelAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Machine gun", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelMachineGunAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Shotgun", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelShotgunAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Flamethrower", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelFlamethrowerAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Knife thrower", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelKnifeThrowerAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Grenade launcher", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelGrenadeLauncherAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Rocket launcher", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelRocketLauncherAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Dynamite launcher", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelDynamiteLauncherAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Blade launcher", game.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelBladeLauncherAccuracy).align(Align.right);

        ScrollPane scrollPane = new ScrollPane(tableStats, game.styles.skinScrollPane);
        scrollPane.getStyle().vScroll.setMinHeight(15);
        scrollPane.getStyle().hScroll.setMinWidth(15);
        scrollPane.getStyle().vScrollKnob.setMinHeight(15);
        scrollPane.getStyle().hScrollKnob.setMinWidth(15);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFadeScrollBars(false);
        scrollPane.layout();
        scrollPane.setScrollY(game.saveManager.saveData.getPriWeaScrollY());
        scrollPane.updateVisualScroll();

        final ImageButton btHome = new ImageButton(game.styles.skinImageButtonHome);
        Table tableButtons = new Table();
        tableButtons.add(btHome).width(Constants.BUTTON_WIDTH_IMAGE).height(Constants.BUTTON_HEIGHT_IMAGE).align(Align.right);

        Table tableContent = new Table();
        tableContent.add(new Label("STATS", game.styles.skinLabelTitle1)).expandX().align(Align.top).colspan(2);
        tableContent.row();
        tableContent.add(scrollPane).fill().expand().align(Align.center).padTop(50);
        tableContent.add(tableButtons).expandY().align(Align.right).padLeft(30);

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

    private void updateData() {
        labelStars.setText(game.styles.getFormattedScore(game.saveManager.saveData.getTotalStars()) + " / " + game.styles.getFormattedScore(3 * Constants.GAME_MODES));
        labelKills.setText(game.styles.getFormattedScore(game.saveManager.saveData.getTotalKills()));
        labelFighterKills.setText(game.styles.getFormattedScore(game.saveManager.saveData.getFighterKills()));
        labelMachineGunKills.setText(game.styles.getFormattedScore(game.saveManager.saveData.getWeaponKills(Constants.MACHINE_GUN_ID)));
        labelShotgunKills.setText(game.styles.getFormattedScore(game.saveManager.saveData.getWeaponKills(Constants.SHOTGUN_ID)));
        labelFlamethrowerKills.setText(game.styles.getFormattedScore(game.saveManager.saveData.getWeaponKills(Constants.FLAMETHROWER_ID)));
        labelKnifeThrowerKills.setText(game.styles.getFormattedScore(game.saveManager.saveData.getWeaponKills(Constants.KNIFE_THROWER_ID)));
        labelGrenadeLauncherKills.setText(game.styles.getFormattedScore(game.saveManager.saveData.getWeaponKills(Constants.GRENADE_LAUNCHER_ID)));
        labelRocketLauncherKills.setText(game.styles.getFormattedScore(game.saveManager.saveData.getWeaponKills(Constants.ROCKET_LAUNCHER_ID)));
        labelDynamiteLauncherKills.setText(game.styles.getFormattedScore(game.saveManager.saveData.getWeaponKills(Constants.DYNAMITE_LAUNCHER_ID)));
        labelBladeLauncherKills.setText(game.styles.getFormattedScore(game.saveManager.saveData.getWeaponKills(Constants.BLADE_LAUNCHER_ID)));
        labelAccuracy.setText(String.format(Locale.US, "%.2f", game.saveManager.saveData.getTotalAccuracy()) + " %");
        labelMachineGunAccuracy.setText(String.format(Locale.US, "%.2f", game.saveManager.saveData.getWeaponAccuracy(Constants.MACHINE_GUN_ID)) + " %");
        labelShotgunAccuracy.setText(String.format(Locale.US, "%.2f", game.saveManager.saveData.getWeaponAccuracy(Constants.SHOTGUN_ID)) + " %");
        labelFlamethrowerAccuracy.setText(String.format(Locale.US, "%.2f", game.saveManager.saveData.getWeaponAccuracy(Constants.FLAMETHROWER_ID)) + " %");
        labelKnifeThrowerAccuracy.setText(String.format(Locale.US, "%.2f", game.saveManager.saveData.getWeaponAccuracy(Constants.KNIFE_THROWER_ID)) + " %");
        labelGrenadeLauncherAccuracy.setText(String.format(Locale.US, "%.2f", game.saveManager.saveData.getWeaponAccuracy(Constants.GRENADE_LAUNCHER_ID)) + " %");
        labelRocketLauncherAccuracy.setText(String.format(Locale.US, "%.2f", game.saveManager.saveData.getWeaponAccuracy(Constants.ROCKET_LAUNCHER_ID)) + " %");
        labelDynamiteLauncherAccuracy.setText(String.format(Locale.US, "%.2f", game.saveManager.saveData.getWeaponAccuracy(Constants.DYNAMITE_LAUNCHER_ID)) + " %");
        labelBladeLauncherAccuracy.setText(String.format(Locale.US, "%.2f", game.saveManager.saveData.getWeaponAccuracy(Constants.BLADE_LAUNCHER_ID)) + " %");

        appTime = game.saveManager.saveData.getAppTime();
        daysTotal = (long)(appTime / (24 * 60 * 60));
        appTime -= daysTotal * 24 * 60 * 60;
        hoursTotal = (long)(appTime / (60 * 60));
        appTime -= hoursTotal * 60 * 60;
        minutesTotal = (long)(appTime / 60);
        if (daysTotal == 0 && hoursTotal == 0)
            labelTotalTime.setText(minutesTotal + " min");
        else if (daysTotal == 0)
            labelTotalTime.setText(hoursTotal + " h  " + minutesTotal + " min");
        else
            labelTotalTime.setText(daysTotal + " d " + hoursTotal + " h  " + minutesTotal + " min");

        gameTime = game.saveManager.saveData.getGameTime();
        daysGaming = (long)(gameTime / (24 * 60 * 60));
        gameTime -= daysGaming * 24 * 60 * 60;
        hoursGaming = (long)(gameTime / (60 * 60));
        gameTime -= hoursGaming * 60 * 60;
        minutesGaming = (long)(gameTime / 60);
        if (daysGaming == 0 && hoursGaming == 0)
            labelBattlingTime.setText(minutesGaming + " min");
        else if (daysGaming == 0)
            labelBattlingTime.setText(hoursGaming + " h  " + minutesGaming + " min");
        else
            labelBattlingTime.setText(daysGaming + " d " + hoursGaming + " h  " + minutesGaming + " min");
    }
}