package com.aleksiprograms.battleagainstshapes.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.aleksiprograms.battleagainstshapes.TheGame;

/**
 * Holds UI styles of the game.
 */
public class Styles {

    private final Color colorBlue = new Color(0x5050c8ff);
    private final Color colorGreen = new Color(0x50c850ff);
    private final Color colorRed = new Color(0xc85050ff);
    private final Color colorWhite = new Color(0xffffffff);
    private final Color colorDis = new Color(0x3c3c3cff);
    private final Color colorGold = new Color(0xffd700ff);
    private final Color colorSilver = new Color(0xc0c0c0ff);
    private final Color colorBronze = new Color(0xcd7f32ff);

    private final String FONT = "roboto_ttf/RobotoCondensed-Regular.ttf";

    public Skin skinLabelTitle1;
    public Skin skinLabelTitle2;
    public Skin skinLabelTitle3;
    public Skin skinLabelNumberText;
    public Skin skinLabelLimitsText;
    public Skin skinLabelInGameTitle;
    public Skin skinLabelInGameText;
    public Skin skinLabelSettingsTitle;
    public Skin skinLabelSettingsText;
    public Skin skinLabelCreditTitle;
    public Skin skinLabelCreditText;
    public Skin skinLabelStatsTitle;
    public Skin skinLabelStatsSubTitle;
    public Skin skinLabelStatsText;

    public Label.LabelStyle labelStyleLoggedInGPGS;
    public Label.LabelStyle labelStyleLoggedOutGPGS;
    public Skin skinLabelDialogText;

    public Skin skinScrollPane;
    public Skin skinDialogBox;
    public Skin skinSlider;
    public Skin skinSelectBox;

    public Skin skinButton;
    public Skin skinButtonDialog;
    public Skin skinButtonWeaponSelect;
    public Skin skinButtonLanguage;

    public Skin skinImageButtonLeaderboard;
    public Skin skinImageButtonAchievements;
    public Skin skinImageButtonStats;
    public Skin skinImageButtonSettings;
    public Skin skinImageButtonCredits;
    public Skin skinImageButtonPlay;
    public Skin skinImageButtonHome;
    public Skin skinImageButtonPause;
    public Skin skinImageButtonReplay;
    public Skin skinImageButtonMoveUp;
    public Skin skinImageButtonMoveDown;
    public Skin skinImageButtonUsePriWea;
    public Skin skinImageButtonUseSecWea;

    public Styles() {
        skinLabelTitle1 = getSkinLabel(FONT, 80, colorBlue);
        skinLabelTitle2 = getSkinLabel(FONT, 50, colorGreen);
        skinLabelTitle3 = getSkinLabel(FONT, 40, colorRed);
        skinLabelNumberText = getSkinLabel(FONT, 40, colorWhite);
        skinLabelLimitsText = getSkinLabel(FONT, 35, colorWhite);
        skinLabelInGameTitle = getSkinLabel(FONT, 35, colorBlue);
        skinLabelInGameText = getSkinLabel(FONT, 35, colorWhite);
        skinLabelSettingsTitle = getSkinLabel(FONT, 35, colorGreen);
        skinLabelSettingsText = getSkinLabel(FONT, 28, colorWhite);
        skinLabelCreditTitle = getSkinLabel(FONT, 35, colorGreen);
        skinLabelCreditText = getSkinLabel(FONT, 35, colorWhite);
        skinLabelStatsTitle = getSkinLabel(FONT, 35, colorGreen);
        skinLabelStatsSubTitle = getSkinLabel(FONT, 35, colorRed);
        skinLabelStatsText = getSkinLabel(FONT, 35, colorWhite);

        labelStyleLoggedInGPGS = getLabelStyle(FONT, 35, colorGreen);
        labelStyleLoggedOutGPGS = getLabelStyle(FONT, 35, colorRed);
    }

    public void loadTextureStyles(TheGame game) {
        skinLabelDialogText = getSkinLabel(FONT, 45, colorWhite,
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_BLACK)));

        skinScrollPane = getSkinScrollPane(
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_GREY)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_BLUE)));
        skinDialogBox = getSkinDialogBox(FONT, 50, colorWhite,
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_BLUE)));
        skinSlider = getSkinSlider(
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_GREY)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_BLUE)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_KNOB)));
        skinSelectBox = getSkinSelectBox(FONT, 45, colorBlue, colorWhite,
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_BLACK)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_GREY)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_BLUE)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_GREY)));

        skinButton = getSkinTextButton(
                FONT, 45, colorWhite, colorBlue, colorWhite, colorDis);
        skinButtonDialog = getSkinTextImageButton(
                FONT, 45, colorWhite, colorBlue, colorWhite, colorDis,
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_BLACK)));
        skinButtonWeaponSelect = getSkinTextImageButton(
                FONT, 30, colorWhite, colorBlue, colorBlue, colorDis,
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_BLACK)));
        skinButtonLanguage = getSkinTextButton(
                FONT, 45, colorWhite, colorBlue, colorBlue, colorDis);

        skinImageButtonStats = getSkinImageButton(
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_STATS_U)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_STATS_D)));
        skinImageButtonSettings = getSkinImageButton(
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_SETTINGS_U)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_SETTINGS_D)));
        skinImageButtonCredits = getSkinImageButton(
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_CREDITS_U)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_CREDITS_D)));
        skinImageButtonPlay = getSkinImageButton(
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_PLAY_U)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_PLAY_D)));
        skinImageButtonHome = getSkinImageButton(
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_HOME_U)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_HOME_D)));
        skinImageButtonPause = getSkinImageButton(
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_PAUSE_U)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_PAUSE_D)));
        skinImageButtonReplay = getSkinImageButton(
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_REPLAY_U)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_REPLAY_D)));

        skinImageButtonMoveUp = getSkinImageButton(
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_MOVE_UP_U)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_MOVE_UP_D)));
        skinImageButtonMoveDown = getSkinImageButton(
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_MOVE_DOWN_U)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_MOVE_DOWN_D)));
        skinImageButtonUsePriWea = getSkinImageButton(
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_USE_PRI_WEA_U)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_USE_PRI_WEA_D)));
        skinImageButtonUseSecWea = getSkinImageButton(
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_USE_SEC_WEA_U)),
                new TextureRegionDrawable(game.getTextureRegionByID(Constants.TEX_SRC_BUTTON_USE_SEC_WEA_D)));
    }

    public static void dispose() {}

    private BitmapFont getFont(
            String fontFilePath,
            int fontSize) {
        BitmapFont font;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontFilePath));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter;
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    private Skin getSkinLabel(
            String fontFilePath,
            int fontSize,
            Color fontColor) {
        Skin skin = new Skin();
        skin.add("default", getFont(fontFilePath, fontSize));
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default");
        labelStyle.fontColor = fontColor;
        skin.add("default", labelStyle);
        return skin;
    }

    private Skin getSkinLabel(
            String fontFilePath,
            int fontSize,
            Color fontColor,
            TextureRegionDrawable background) {
        Skin skin = new Skin();
        skin.add("default", getFont(fontFilePath, fontSize));
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default");
        labelStyle.fontColor = fontColor;
        labelStyle.background = background;
        skin.add("default", labelStyle);
        return skin;
    }

    private Label.LabelStyle getLabelStyle(
            String fontFilePath,
            int fontSize,
            Color fontColor) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = getFont(fontFilePath, fontSize);
        labelStyle.fontColor = fontColor;
        return labelStyle;
    }

    private Skin getSkinTextButton(
            String fontFilePath,
            int fontSize,
            Color fontColorUp,
            Color fontColorDown,
            Color fontColorChecked,
            Color fontColorDisabled) {
        Skin skin = new Skin();
        skin.add("default", getFont(fontFilePath, fontSize));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.fontColor = fontColorUp;
        textButtonStyle.downFontColor = fontColorDown;
        textButtonStyle.checkedFontColor = fontColorChecked;
        textButtonStyle.overFontColor = fontColorChecked;
        textButtonStyle.checkedOverFontColor = fontColorChecked;
        textButtonStyle.disabledFontColor = fontColorDisabled;
        skin.add("default", textButtonStyle);
        return skin;
    }

    private Skin getSkinTextImageButton(
            String fontFilePath,
            int fontSize,
            Color fontColorUp,
            Color fontColorDown,
            Color fontColorChecked,
            Color fontColorDisabled,
            TextureRegionDrawable background) {
        Skin skin = new Skin();
        skin.add("default", getFont(fontFilePath, fontSize));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.fontColor = fontColorUp;
        textButtonStyle.downFontColor = fontColorDown;
        textButtonStyle.checkedFontColor = fontColorChecked;
        textButtonStyle.overFontColor = fontColorChecked;
        textButtonStyle.checkedOverFontColor = fontColorChecked;
        textButtonStyle.disabledFontColor = fontColorDisabled;
        textButtonStyle.up = background;
        textButtonStyle.down = background;
        skin.add("default", textButtonStyle);
        return skin;
    }

    private Skin getSkinImageButton(
            TextureRegionDrawable textureUp,
            TextureRegionDrawable textureDown) {
        Skin skin = new Skin();
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = textureUp;
        imageButtonStyle.down = textureDown;
        skin.add("default", imageButtonStyle);
        return skin;
    }

    private Skin getSkinImageButton(
            TextureRegionDrawable textureUp,
            TextureRegionDrawable textureDown,
            TextureRegionDrawable textureDisabled) {
        Skin skin = new Skin();
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = textureUp;
        imageButtonStyle.down = textureDown;
        imageButtonStyle.disabled = textureDisabled;
        skin.add("default", imageButtonStyle);
        return skin;
    }

    private Skin getSkinScrollPane(
            TextureRegionDrawable scroll,
            TextureRegionDrawable scrollKnob) {
        Skin skin = new Skin();
        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle(null, scroll, scrollKnob, scroll, scrollKnob);
        skin.add("default", scrollPaneStyle);
        return skin;
    }

    private Skin getSkinDialogBox(
            String fontFilePath,
            int fontSize,
            Color fontColorLabel,
            TextureRegionDrawable background) {
        Skin skin = new Skin();
        skin.add("default", getFont(fontFilePath, fontSize));
        Dialog.WindowStyle windowStyle = new Dialog.WindowStyle();
        windowStyle.background = new TextureRegionDrawable(background);
        windowStyle.titleFont = skin.getFont("default");
        windowStyle.titleFontColor = fontColorLabel;
        skin.add("default", windowStyle);
        return skin;
    }

    private Skin getSkinSlider(
            TextureRegionDrawable background,
            TextureRegionDrawable knobBefore,
            TextureRegionDrawable knob) {
        Skin skin = new Skin();
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle(background, knob);
        sliderStyle.knobBefore = knobBefore;
        skin.add("default-horizontal", sliderStyle);
        return skin;
    }

    private Skin getSkinSelectBox(
            String fontFilePath,
            int fontSize,
            Color fontColorSelected,
            Color fontColorUnselected,
            TextureRegionDrawable background,
            TextureRegionDrawable scroll,
            TextureRegionDrawable scrollKnob,
            TextureRegionDrawable selection) {
        Skin skin = new Skin();
        skin.add("default", getFont(fontFilePath, fontSize));
        SelectBox.SelectBoxStyle selectBoxStyle = new SelectBox.SelectBoxStyle();
        selectBoxStyle.background = background;
        selectBoxStyle.font = skin.getFont("default");
        selectBoxStyle.fontColor = fontColorUnselected;
        selectBoxStyle.scrollStyle = new ScrollPane.ScrollPaneStyle(background, scroll, scrollKnob, scroll, scrollKnob);
        selectBoxStyle.listStyle = new List.ListStyle(
                getFont(fontFilePath, fontSize),
                fontColorSelected,
                fontColorUnselected,
                selection);
        skin.add("default", selectBoxStyle);
        return skin;
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
            formattedScore = formattedScore.substring(1, formattedScore.length());
        return formattedScore;
    }
}