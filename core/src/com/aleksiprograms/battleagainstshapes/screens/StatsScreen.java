package com.aleksiprograms.battleagainstshapes.screens;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

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

    public StatsScreen(TheGame game) {
        super(game);
        initialize();
    }

    @Override
    public void updateData() {
        super.updateData();
        labelStars.setText(game.getResources().getStyles().getFormattedScore(
                game.getSaveManager().getSaveData().getTotalStars()) + " / "
                + game.getResources().getStyles().getFormattedScore(
                        3 * Constants.GAME_MODES));
        labelKills.setText(game.getResources().getStyles().getFormattedScore(
                game.getSaveManager().getSaveData().getTotalKills()));
        labelFighterKills.setText(game.getResources().getStyles().getFormattedScore(
                game.getSaveManager().getSaveData().getFighterKills()));
        labelMachineGunKills.setText(game.getResources().getStyles().getFormattedScore(
                game.getSaveManager().getSaveData().getWeaponKills(
                        Constants.MACHINE_GUN_ID)));
        labelShotgunKills.setText(game.getResources().getStyles().getFormattedScore(
                game.getSaveManager().getSaveData().getWeaponKills(
                        Constants.SHOTGUN_ID)));
        labelFlamethrowerKills.setText(game.getResources().getStyles().getFormattedScore(
                game.getSaveManager().getSaveData().getWeaponKills(
                        Constants.FLAMETHROWER_ID)));
        labelKnifeThrowerKills.setText(game.getResources().getStyles().getFormattedScore(
                game.getSaveManager().getSaveData().getWeaponKills(
                        Constants.KNIFE_THROWER_ID)));
        labelGrenadeLauncherKills.setText(game.getResources().getStyles().getFormattedScore(
                game.getSaveManager().getSaveData().getWeaponKills(
                        Constants.GRENADE_LAUNCHER_ID)));
        labelRocketLauncherKills.setText(game.getResources().getStyles().getFormattedScore(
                game.getSaveManager().getSaveData().getWeaponKills(
                        Constants.ROCKET_LAUNCHER_ID)));
        labelDynamiteLauncherKills.setText(game.getResources().getStyles().getFormattedScore(
                game.getSaveManager().getSaveData().getWeaponKills(
                        Constants.DYNAMITE_LAUNCHER_ID)));
        labelBladeLauncherKills.setText(game.getResources().getStyles().getFormattedScore(
                game.getSaveManager().getSaveData().getWeaponKills(
                        Constants.BLADE_LAUNCHER_ID)));
        labelAccuracy.setText(String.format(Locale.US, "%.2f",
                game.getSaveManager().getSaveData().getTotalAccuracy()) + " %");
        labelMachineGunAccuracy.setText(String.format(Locale.US, "%.2f",
                game.getSaveManager().getSaveData().getWeaponAccuracy(
                        Constants.MACHINE_GUN_ID)) + " %");
        labelShotgunAccuracy.setText(String.format(Locale.US, "%.2f",
                game.getSaveManager().getSaveData().getWeaponAccuracy(
                        Constants.SHOTGUN_ID)) + " %");
        labelFlamethrowerAccuracy.setText(String.format(Locale.US, "%.2f",
                game.getSaveManager().getSaveData().getWeaponAccuracy(
                        Constants.FLAMETHROWER_ID)) + " %");
        labelKnifeThrowerAccuracy.setText(String.format(Locale.US, "%.2f",
                game.getSaveManager().getSaveData().getWeaponAccuracy(
                        Constants.KNIFE_THROWER_ID)) + " %");
        labelGrenadeLauncherAccuracy.setText(String.format(Locale.US, "%.2f",
                game.getSaveManager().getSaveData().getWeaponAccuracy(
                        Constants.GRENADE_LAUNCHER_ID)) + " %");
        labelRocketLauncherAccuracy.setText(String.format(Locale.US, "%.2f",
                game.getSaveManager().getSaveData().getWeaponAccuracy(
                        Constants.ROCKET_LAUNCHER_ID)) + " %");
        labelDynamiteLauncherAccuracy.setText(String.format(Locale.US, "%.2f",
                game.getSaveManager().getSaveData().getWeaponAccuracy(
                        Constants.DYNAMITE_LAUNCHER_ID)) + " %");
        labelBladeLauncherAccuracy.setText(String.format(Locale.US, "%.2f",
                game.getSaveManager().getSaveData().getWeaponAccuracy(
                        Constants.BLADE_LAUNCHER_ID)) + " %");

        long appTime = game.getSaveManager().getSaveData().getAppTime();
        long daysTotal = appTime / (24 * 60 * 60);
        appTime -= daysTotal * 24 * 60 * 60;
        long hoursTotal = appTime / (60 * 60);
        appTime -= hoursTotal * 60 * 60;
        long minutesTotal = appTime / 60;
        if (daysTotal == 0 && hoursTotal == 0) {
            labelTotalTime.setText(
                    minutesTotal + " min");
        } else if (daysTotal == 0) {
            labelTotalTime.setText(
                    hoursTotal + " h  " + minutesTotal + " min");
        } else {
            labelTotalTime.setText(
                    daysTotal + " d " + hoursTotal + " h  " + minutesTotal + " min");
        }

        long gameTime = game.getSaveManager().getSaveData().getGameTime();
        long daysGaming = gameTime / (24 * 60 * 60);
        gameTime -= daysGaming * 24 * 60 * 60;
        long hoursGaming = gameTime / (60 * 60);
        gameTime -= hoursGaming * 60 * 60;
        long minutesGaming = gameTime / 60;
        if (daysGaming == 0 && hoursGaming == 0) {
            labelBattlingTime.setText(
                    minutesGaming + " min");
        } else if (daysGaming == 0) {
            labelBattlingTime.setText(
                    hoursGaming + " h  " + minutesGaming + " min");
        } else {
            labelBattlingTime.setText(
                    daysGaming + " d " + hoursGaming + " h  " + minutesGaming + " min");
        }
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
        labelStars = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelTotalTime = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelBattlingTime = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelKills = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelFighterKills = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelMachineGunKills = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelShotgunKills = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelFlamethrowerKills = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelKnifeThrowerKills = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelGrenadeLauncherKills = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelRocketLauncherKills = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelDynamiteLauncherKills = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelBladeLauncherKills = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelAccuracy = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelMachineGunAccuracy = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelShotgunAccuracy = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelFlamethrowerAccuracy = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelKnifeThrowerAccuracy = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelGrenadeLauncherAccuracy = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelRocketLauncherAccuracy = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelDynamiteLauncherAccuracy = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());
        labelBladeLauncherAccuracy = new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall());

        Table tableStats = new Table();
        tableStats.add(new Label("Stars",
                game.getResources().getStyles().getLabelStyleGreenSmall()))
                .align(Align.left).padRight(100);
        tableStats.add(labelStars).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Total time",
                game.getResources().getStyles().getLabelStyleGreenSmall()))
                .align(Align.left).padRight(100);
        tableStats.add(labelTotalTime).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Battling time",
                game.getResources().getStyles().getLabelStyleGreenSmall()))
                .align(Align.left).padRight(100);
        tableStats.add(labelBattlingTime).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("",
                game.getResources().getStyles().getLabelStyleGreenSmall()))
                .align(Align.left).padRight(100);
        tableStats.add(new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall()))
                .align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Kills",
                game.getResources().getStyles().getLabelStyleGreenSmall()))
                .align(Align.left).padRight(100);
        tableStats.add(labelMachineGunKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Fighter",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelFighterKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Machine gun",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelMachineGunKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Shotgun",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelShotgunKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Flamethrower",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelFlamethrowerKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Knife thrower",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelKnifeThrowerKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Grenade launcher",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelGrenadeLauncherKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Rocket launcher",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelRocketLauncherKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Dynamite launcher",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelDynamiteLauncherKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Blade launcher",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelBladeLauncherKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("",
                game.getResources().getStyles().getLabelStyleGreenSmall()))
                .align(Align.left).padRight(100);
        tableStats.add(new Label("",
                game.getResources().getStyles().getLabelStyleWhiteSmall()))
                .align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Accuracy",
                game.getResources().getStyles().getLabelStyleGreenSmall()))
                .align(Align.left).padRight(100);
        tableStats.add(labelAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Machine gun",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelMachineGunAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Shotgun",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelShotgunAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Flamethrower",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelFlamethrowerAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Knife thrower",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelKnifeThrowerAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Grenade launcher",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelGrenadeLauncherAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Rocket launcher",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelRocketLauncherAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Dynamite launcher",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelDynamiteLauncherAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Blade launcher",
                game.getResources().getStyles().getLabelStyleRedSmall()))
                .align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelBladeLauncherAccuracy).align(Align.right);

        ScrollPane scrollPane = new ScrollPane(tableStats,
                game.getResources().getStyles().getScrollPaneStyle());
        scrollPane.getStyle().vScroll.setMinHeight(15);
        scrollPane.getStyle().hScroll.setMinWidth(15);
        scrollPane.getStyle().vScrollKnob.setMinHeight(15);
        scrollPane.getStyle().hScrollKnob.setMinWidth(15);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFadeScrollBars(false);
        scrollPane.layout();
        scrollPane.updateVisualScroll();

        final ImageButton buttonMainMenu = new ImageButton(
                game.getResources().getStyles().getImageButtonStyleHome());
        Table tableButtons = new Table();
        tableButtons.add(buttonMainMenu)
                .width(Constants.BUTTON_WIDTH_IMAGE)
                .height(Constants.BUTTON_HEIGHT_IMAGE).align(Align.right);

        Table tableContent = new Table();
        tableContent.add(new Label("STATS",
                game.getResources().getStyles().getLabelStyleBlueHuge()))
                .expandX().align(Align.top).colspan(2);
        tableContent.row();
        tableContent.add(scrollPane).fill().expand().align(Align.center).padTop(50);
        tableContent.add(tableButtons).expandY().align(Align.right).padLeft(30);

        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_BACKGROUND))));
        table.add(tableContent).align(Align.center).grow().pad(Constants.PAD_SCREEN);

        stage.addActor(table);

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