package com.aleksiprograms.battleagainstshapes.screens.huds;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.toolbox.GameState;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class GameOverHud extends AbstractHud {

    private Label labelGameModeTitle;
    private Label labelScore;
    private Label labelDistance;
    private Label labelScoreText;
    private Label labelDistanceText;
    private Image imageStar1;
    private Image imageStar2;
    private Image imageStar3;

    private TextureRegionDrawable imageStarLocked1;
    private TextureRegionDrawable imageStarUnlocked1;
    private TextureRegionDrawable imageStarLocked2;
    private TextureRegionDrawable imageStarUnlocked2;
    private TextureRegionDrawable imageStarLocked3;
    private TextureRegionDrawable imageStarUnlocked3;

    public GameOverHud(final TheGame game) {
        super(game);
        initialize();
    }

    @Override
    public void updateData() {
        long newScore = game.getGameScreen().getInGameStatsManager().getScore();
        long newDistance = game.getGameScreen().getInGameStatsManager().getDistance();
        labelGameModeTitle.setText(game.getGameScreen().getGameMode().getName());

        if (game.getGameScreen().getInGameStatsManager().isNewBestScore()) {
            labelScoreText.setText("New best!");
        } else {
            labelScoreText.setText(" ");
        }
        labelScore.setText(game.getResources().getStyles().getFormattedScore(newScore));

        if (game.getGameScreen().getInGameStatsManager().isNewBestDistance()) {
            labelDistanceText.setText("New best!");
        } else {
            labelDistanceText.setText(" ");
        }
        labelDistance.setText(game.getResources().getStyles()
                .getFormattedScore(newDistance) + " m");

        if (newScore >= game.getGameScreen().getGameMode().getScoreToOneStar()) {
            imageStar1.setDrawable(imageStarUnlocked1);
        } else {
            imageStar1.setDrawable(imageStarLocked1);
        }
        if (newScore >= game.getGameScreen().getGameMode().getScoreToTwoStar()) {
            imageStar2.setDrawable(imageStarUnlocked2);
        } else {
            imageStar2.setDrawable(imageStarLocked2);
        }
        if (newScore >= game.getGameScreen().getGameMode().getScoreToThreeStar()) {
            imageStar3.setDrawable(imageStarUnlocked3);
        } else {
            imageStar3.setDrawable(imageStarLocked3);
        }
    }

    @Override
    protected void initialize() {
        super.initialize();

        super.pad(Constants.PAD_SCREEN);
        super.center();
        super.setFillParent(true);

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

        final ImageButton buttonReplay = new ImageButton(
                game.getResources().getStyles().getImageButtonStyleReplay());
        final ImageButton buttonMainMenu = new ImageButton(
                game.getResources().getStyles().getImageButtonStyleHome());
        Table tableButtons = new Table();
        tableButtons.add(buttonReplay).padBottom(10).align(Align.right)
                .width(Constants.BUTTON_WIDTH_IMAGE)
                .height(Constants.BUTTON_HEIGHT_IMAGE);
        tableButtons.row();
        tableButtons.add(buttonMainMenu).align(Align.right)
                .width(Constants.BUTTON_WIDTH_IMAGE)
                .height(Constants.BUTTON_HEIGHT_IMAGE);

        labelGameModeTitle = new Label("",
                game.getResources().getStyles().getLabelStyleGreenBig());
        Image imageScore = new Image();
        imageScore.setDrawable(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_SCORE))));
        Image imageDistance = new Image();
        imageDistance.setDrawable(new TextureRegionDrawable(new TextureRegion(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_UI_DISTANCE))));
        labelScore = new Label(" ",
                game.getResources().getStyles().getLabelStyleWhiteMedium());
        labelDistance = new Label(" ",
                game.getResources().getStyles().getLabelStyleWhiteMedium());
        imageStar1 = new Image();
        imageStar2 = new Image();
        imageStar3 = new Image();
        Table tableStars = new Table();
        tableStars.add(imageStar1).align(Align.topLeft)
                .width(labelScore.getHeight())
                .height(labelScore.getHeight());
        tableStars.add(imageStar2).align(Align.topLeft).padLeft(2)
                .width(labelScore.getHeight())
                .height(labelScore.getHeight());
        tableStars.add(imageStar3).align(Align.topLeft).padLeft(2)
                .width(labelScore.getHeight())
                .height(labelScore.getHeight());
        labelScoreText = new Label("",
                game.getResources().getStyles().getLabelStyleRedMedium());
        labelDistanceText = new Label("",
                game.getResources().getStyles().getLabelStyleRedMedium());

        Table tableScore = new Table();
        tableScore.add(imageScore).align(Align.right).padBottom(20)
                .width(labelScore.getHeight())
                .height(labelScore.getHeight()).expandX();
        tableScore.add(labelScore).align(Align.center).padBottom(20).padLeft(20);
        tableScore.add(labelScoreText).align(Align.left).padBottom(20).padLeft(20).expandX();

        Table tableDistance = new Table();
        tableDistance.add(imageDistance).align(Align.right).padBottom(20)
                .width(labelDistance.getHeight())
                .height(labelDistance.getHeight()).expandX();
        tableDistance.add(labelDistance).align(Align.center).padBottom(20).padLeft(20);
        tableDistance.add(labelDistanceText).align(Align.left).padBottom(20).padLeft(20).expandX();

        Table tableStats = new Table();
        tableStats.pad(20);

        tableStats.add(labelGameModeTitle).colspan(3).align(Align.center).padBottom(20).expandX();
        tableStats.row();
        tableStats.add(tableScore);
        tableStats.row();
        tableStats.add(tableDistance);
        tableStats.row();
        tableStats.add(tableStars).colspan(3).align(Align.center).padBottom(20).expandX();

        super.add(new Label("GAME OVER",
                game.getResources().getStyles().getLabelStyleBlueHuge()))
                .expandX().align(Align.top).colspan(2);
        super.row();
        super.add(tableStats).expand().align(Align.center);
        super.add(tableButtons).expandY().align(Align.right);

        buttonReplay.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonReplay.getWidth()) {
                    if (y > 0 && y < buttonReplay.getHeight()) {
                        game.getGameScreen().changeGameState(GameState.TO_REPLAY);
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
                        game.getGameScreen().changeGameState(GameState.TO_MAIN_MENU);
                    }
                }
            }
        });
    }
}