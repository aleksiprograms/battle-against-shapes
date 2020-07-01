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

public class PausedHud extends AbstractHud {

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

    public PausedHud(final TheGame game) {
        super(game);
        initialize();
    }

    @Override
    public void updateData() {
        long oldScore = game.getSaveManager().getSaveData().getGameModeScore(
                game.getGameScreen().getGameMode().getGameModeID());
        long oldDistance = game.getSaveManager().getSaveData().getGameModeDistance(
                game.getGameScreen().getGameMode().getGameModeID());
        labelLevelTitle.setText(
                game.getGameScreen().getGameMode().getName());
        labelBestScore.setText(
                game.getResources().getStyles().getFormattedScore(oldScore));
        labelBestDistance.setText(
                game.getResources().getStyles().getFormattedScore(oldDistance) + " m");
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

        final ImageButton buttonPlay = new ImageButton(
                game.getResources().getStyles().getImageButtonStylePlay());
        final ImageButton buttonReplay = new ImageButton(
                game.getResources().getStyles().getImageButtonStyleReplay());
        final ImageButton buttonMainMenu = new ImageButton(
                game.getResources().getStyles().getImageButtonStyleHome());
        Table tableButtons = new Table();
        tableButtons.add(buttonPlay).padBottom(10)
                .width(Constants.BUTTON_WIDTH_IMAGE)
                .height(Constants.BUTTON_HEIGHT_IMAGE).align(Align.right);
        tableButtons.row();
        tableButtons.add(buttonReplay).padBottom(10)
                .width(Constants.BUTTON_WIDTH_IMAGE)
                .height(Constants.BUTTON_HEIGHT_IMAGE).align(Align.right);
        tableButtons.row();
        tableButtons.add(buttonMainMenu)
                .width(Constants.BUTTON_WIDTH_IMAGE)
                .height(Constants.BUTTON_HEIGHT_IMAGE).align(Align.right);

        super.add(new Label("PAUSED",
                game.getResources().getStyles().getLabelStyleBlueHuge()))
                .expandX().align(Align.top).colspan(2);
        super.row();
        super.add(tableInfo).align(Align.topLeft).expandY();
        super.add(tableButtons).expandY().align(Align.right);

        buttonPlay.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonPlay.getWidth()) {
                    if (y > 0 && y < buttonPlay.getHeight()) {
                        game.getGameScreen().changeGameState(GameState.IN_GAME);
                    }
                }
            }
        });

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