package com.aleksiprograms.battleagainstshapes.resources;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Holds UI styles of the game.
 */
public class Styles {

    private final Color colorBlue = new Color(0x5050c8ff);
    private final Color colorGreen = new Color(0x50c850ff);
    private final Color colorRed = new Color(0xc85050ff);
    private final Color colorWhite = new Color(0xffffffff);
    private final Color colorDisabled = new Color(0x3c3c3cff);

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    private Label.LabelStyle labelStyleBlueHuge;
    private Label.LabelStyle labelStyleGreenBig;
    private Label.LabelStyle labelStyleRedMedium;
    private Label.LabelStyle labelStyleWhiteMedium;
    private Label.LabelStyle labelStyleWhiteSmall;
    private Label.LabelStyle labelStyleBlueSmall;
    private Label.LabelStyle labelStyleGreenSmall;
    private Label.LabelStyle labelStyleRedSmall;

    private Label.LabelStyle labelStyleDialogText;

    private ScrollPane.ScrollPaneStyle scrollPaneStyle;
    private Dialog.WindowStyle dialogBoxStyle;
    private Slider.SliderStyle sliderStyle;

    private TextButton.TextButtonStyle textButtonStyleDialog;
    private TextButton.TextButtonStyle textButtonStyleWeaponSelect;

    private ImageButton.ImageButtonStyle imageButtonStyleStats;
    private ImageButton.ImageButtonStyle imageButtonStyleSettings;
    private ImageButton.ImageButtonStyle imageButtonStyleCredits;
    private ImageButton.ImageButtonStyle imageButtonStylePlay;
    private ImageButton.ImageButtonStyle imageButtonStyleHome;
    private ImageButton.ImageButtonStyle imageButtonStylePause;
    private ImageButton.ImageButtonStyle imageButtonStyleReplay;
    private ImageButton.ImageButtonStyle imageButtonStyleMoveUp;
    private ImageButton.ImageButtonStyle imageButtonStyleMoveDown;
    private ImageButton.ImageButtonStyle imageButtonStyleUsePriWea;
    private ImageButton.ImageButtonStyle imageButtonStyleUseSecWea;

    public Styles() {
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        labelStyleBlueHuge = getLabelStyle(80, colorBlue);
        labelStyleGreenBig = getLabelStyle(50, colorGreen);
        labelStyleRedMedium = getLabelStyle(40, colorRed);
        labelStyleWhiteMedium = getLabelStyle(40, colorWhite);
        labelStyleWhiteSmall = getLabelStyle(35, colorWhite);
        labelStyleBlueSmall = getLabelStyle(35, colorBlue);
        labelStyleGreenSmall = getLabelStyle(35, colorGreen);
        labelStyleRedSmall = getLabelStyle(35, colorRed);
    }

    public void loadRest(TheGame game) {
        labelStyleDialogText = getLabelStyle(45, colorWhite,
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_RECTANGLE_BLACK)));

        scrollPaneStyle = getScrollPaneStyle(
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_RECTANGLE_GREY)),
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_RECTANGLE_BLUE)));
        dialogBoxStyle = getDialogBoxStyle(50, colorWhite,
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_RECTANGLE_BLUE)));
        sliderStyle = getSliderStyle(
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_RECTANGLE_GREY)),
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_RECTANGLE_BLUE)),
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_KNOB)));

        textButtonStyleDialog = getTextImageButtonStyle(
                45, colorWhite, colorBlue, colorWhite, colorDisabled,
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_RECTANGLE_BLACK)));
        textButtonStyleWeaponSelect = getTextImageButtonStyle(
                30, colorWhite, colorBlue, colorBlue, colorDisabled,
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_RECTANGLE_BLACK)));

        imageButtonStyleStats = getImageButtonStyle(
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_STATS_U)),
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_STATS_D)));
        imageButtonStyleSettings = getImageButtonStyle(
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_SETTINGS_U)),
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_SETTINGS_D)));
        imageButtonStyleCredits = getImageButtonStyle(
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_CREDITS_U)),
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_CREDITS_D)));
        imageButtonStylePlay = getImageButtonStyle(
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_PLAY_U)),
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_PLAY_D)));
        imageButtonStyleHome = getImageButtonStyle(
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_HOME_U)),
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_HOME_D)));
        imageButtonStylePause = getImageButtonStyle(
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_PAUSE_U)),
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_PAUSE_D)));
        imageButtonStyleReplay = getImageButtonStyle(
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_REPLAY_U)),
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_REPLAY_D)));

        imageButtonStyleMoveUp = getImageButtonStyle(
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_MOVE_UP_U)),
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_MOVE_UP_D)));
        imageButtonStyleMoveDown = getImageButtonStyle(
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_MOVE_DOWN_U)),
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_MOVE_DOWN_D)));
        imageButtonStyleUsePriWea = getImageButtonStyle(
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_USE_PRI_WEA_U)),
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_USE_PRI_WEA_D)));
        imageButtonStyleUseSecWea = getImageButtonStyle(
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_USE_SEC_WEA_U)),
                new TextureRegionDrawable(game.getResources().getTextureRegionByID(
                        Constants.TEXTURE_UI_BUTTON_USE_SEC_WEA_D)));
    }

    private BitmapFont getFont(int fontSize) {
        fontParameter.size = fontSize;
        fontParameter.minFilter = Texture.TextureFilter.Linear;
        fontParameter.magFilter = Texture.TextureFilter.Linear;
        return fontGenerator.generateFont(fontParameter);
    }

    private Label.LabelStyle getLabelStyle(
            int fontSize,
            Color fontColor) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = getFont(fontSize);
        labelStyle.fontColor = fontColor;
        return labelStyle;
    }

    private Label.LabelStyle getLabelStyle(
            int fontSize,
            Color fontColor,
            TextureRegionDrawable background) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = getFont(fontSize);
        labelStyle.fontColor = fontColor;
        labelStyle.background = background;
        return labelStyle;
    }

    private TextButton.TextButtonStyle getTextImageButtonStyle(
            int fontSize,
            Color fontColorUp,
            Color fontColorDown,
            Color fontColorChecked,
            Color fontColorDisabled,
            TextureRegionDrawable background) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = getFont(fontSize);
        textButtonStyle.fontColor = fontColorUp;
        textButtonStyle.downFontColor = fontColorDown;
        textButtonStyle.checkedFontColor = fontColorChecked;
        textButtonStyle.overFontColor = fontColorChecked;
        textButtonStyle.checkedOverFontColor = fontColorChecked;
        textButtonStyle.disabledFontColor = fontColorDisabled;
        textButtonStyle.up = background;
        textButtonStyle.down = background;
        return textButtonStyle;
    }

    private ImageButton.ImageButtonStyle getImageButtonStyle(
            TextureRegionDrawable textureUp,
            TextureRegionDrawable textureDown) {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = textureUp;
        imageButtonStyle.down = textureDown;
        return imageButtonStyle;
    }

    private ScrollPane.ScrollPaneStyle getScrollPaneStyle(
            TextureRegionDrawable scroll,
            TextureRegionDrawable scrollKnob) {
        return new ScrollPane.ScrollPaneStyle(null, scroll, scrollKnob, scroll, scrollKnob);
    }

    private Dialog.WindowStyle getDialogBoxStyle(
            int fontSize,
            Color fontColorLabel,
            TextureRegionDrawable background) {
        Dialog.WindowStyle windowStyle = new Dialog.WindowStyle();
        windowStyle.background = new TextureRegionDrawable(background);
        windowStyle.titleFont = getFont(fontSize);
        windowStyle.titleFontColor = fontColorLabel;
        return windowStyle;
    }

    private Slider.SliderStyle getSliderStyle(
            TextureRegionDrawable background,
            TextureRegionDrawable knobBefore,
            TextureRegionDrawable knob) {
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle(background, knob);
        sliderStyle.knobBefore = knobBefore;
        return sliderStyle;
    }

    public String getFormattedScore(long score) {
        String rawScore = Long.toString(score);
        String formattedScore = "";
        int x = 1;
        for (int i = rawScore.length() - 1; i >= 0; i--) {
            formattedScore = rawScore.charAt(i) + formattedScore;
            if (x % 3 == 0)
                formattedScore = " " + formattedScore;
            x++;
        }
        if (formattedScore.charAt(0) == ' ')
            formattedScore = formattedScore.substring(1);
        return formattedScore;
    }

    public Label.LabelStyle getLabelStyleBlueHuge() {
        return labelStyleBlueHuge;
    }

    public Label.LabelStyle getLabelStyleGreenBig() {
        return labelStyleGreenBig;
    }

    public Label.LabelStyle getLabelStyleRedMedium() {
        return labelStyleRedMedium;
    }

    public Label.LabelStyle getLabelStyleWhiteMedium() {
        return labelStyleWhiteMedium;
    }

    public Label.LabelStyle getLabelStyleWhiteSmall() {
        return labelStyleWhiteSmall;
    }

    public Label.LabelStyle getLabelStyleBlueSmall() {
        return labelStyleBlueSmall;
    }

    public Label.LabelStyle getLabelStyleGreenSmall() {
        return labelStyleGreenSmall;
    }

    public Label.LabelStyle getLabelStyleRedSmall() {
        return labelStyleRedSmall;
    }

    public Label.LabelStyle getLabelStyleDialogText() {
        return labelStyleDialogText;
    }

    public ScrollPane.ScrollPaneStyle getScrollPaneStyle() {
        return scrollPaneStyle;
    }

    public Dialog.WindowStyle getDialogBoxStyle() {
        return dialogBoxStyle;
    }

    public Slider.SliderStyle getSliderStyle() {
        return sliderStyle;
    }

    public TextButton.TextButtonStyle getTextButtonStyleDialog() {
        return textButtonStyleDialog;
    }

    public TextButton.TextButtonStyle getTextButtonStyleWeaponSelect() {
        return textButtonStyleWeaponSelect;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleStats() {
        return imageButtonStyleStats;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleSettings() {
        return imageButtonStyleSettings;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleCredits() {
        return imageButtonStyleCredits;
    }

    public ImageButton.ImageButtonStyle getImageButtonStylePlay() {
        return imageButtonStylePlay;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleHome() {
        return imageButtonStyleHome;
    }

    public ImageButton.ImageButtonStyle getImageButtonStylePause() {
        return imageButtonStylePause;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleReplay() {
        return imageButtonStyleReplay;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleMoveUp() {
        return imageButtonStyleMoveUp;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleMoveDown() {
        return imageButtonStyleMoveDown;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleUsePriWea() {
        return imageButtonStyleUsePriWea;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleUseSecWea() {
        return imageButtonStyleUseSecWea;
    }
}