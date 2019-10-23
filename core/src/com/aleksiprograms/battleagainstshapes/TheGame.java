package com.aleksiprograms.battleagainstshapes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.aleksiprograms.battleagainstshapes.game_world.*;
import com.aleksiprograms.battleagainstshapes.managers.GameStateManager;
import com.aleksiprograms.battleagainstshapes.managers.HudManager;
import com.aleksiprograms.battleagainstshapes.managers.SaveManager;
import com.aleksiprograms.battleagainstshapes.resources.*;
import com.aleksiprograms.battleagainstshapes.screens.CreditsScreen;
import com.aleksiprograms.battleagainstshapes.screens.GameScreen;
import com.aleksiprograms.battleagainstshapes.screens.HomeScreen;
import com.aleksiprograms.battleagainstshapes.screens.SettingsScreen;
import com.aleksiprograms.battleagainstshapes.screens.StatsScreen;
import com.aleksiprograms.battleagainstshapes.screens.WeaponsScreen;
import com.aleksiprograms.battleagainstshapes.toolbox.GameMode;
import com.aleksiprograms.battleagainstshapes.managers.GameModeManager;
import com.aleksiprograms.battleagainstshapes.screens.LoadingScreen;
import com.aleksiprograms.battleagainstshapes.toolbox.Player;

/**
 * Class, which holds all resources and the screens, which
 * are also reused through the live time of the game/application.
 */
public class TheGame extends Game {

    public GameStateManager gameStateManager;
    public float appTime;
    public float gameTime;
    public Player player;
    public GameMode gameMode;
    public SpriteBatch spriteBatch;
    public AssetManager assetManager;
    public GameWorld gameWorld;
    public GameObjectPools gameObjectPools;
    public ParticleEffectPools particleEffectPools;
    public Sounds sounds;
    public Styles styles;
    public Shapes shapes;
    public PhysicalDefinitions physicalDefinitions;
    public SaveManager saveManager;
    public GameModeManager gameModeManager;
    public HudManager hudManager;

    public CreditsScreen creditsScreen;
    public GameScreen gameScreen;
    public HomeScreen homeScreen;
    public SettingsScreen settingsScreen;
    public StatsScreen statsScreen;
    public WeaponsScreen weaponsScreen;

    public TheGame() {}

    @Override
    public void create() {
        Gdx.input.setCatchBackKey(true);
        saveManager = new SaveManager(this);
        assetManager = new AssetManager();
        TextureLoader.TextureParameter param = new TextureLoader.TextureParameter();
        param.genMipMaps = true;
        assetManager.load("textures/ui_background.png", Texture.class, param);
        assetManager.load("textures/logo_bas_text.png", Texture.class, param);
        assetManager.finishLoading();
        appTime = 0f;
        gameTime = 0f;
        spriteBatch = new SpriteBatch();
        styles = new Styles();

        setScreen(new LoadingScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        assetManager.dispose();
        gameWorld.box2DWorld.dispose();
        shapes.dispose();
        gameObjectPools.dispose();
        particleEffectPools.dispose();
        creditsScreen.dispose();
        gameScreen.dispose();
        homeScreen.dispose();
        settingsScreen.dispose();
        statsScreen.dispose();
        weaponsScreen.dispose();
    }

    @Override
    public void pause() {
        super.pause();
        saveManager.save();
    }

    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
        // When setting new screen, the app and game time stats are saved.
        if (saveManager != null) {
            if (saveManager.saveData != null) {
                saveManager.saveData.addToAppTime((int) appTime);
                appTime = 0f;
                saveManager.saveData.addToGameTime((int) gameTime);
                gameTime = 0f;
            }
            saveManager.save();
        }
    }

    public Texture getStartTexture(String textureID) {
        Texture texture;
        if (textureID.equals("game_logo"))
            texture = assetManager.get("textures/logo_bas_text.png", Texture.class);
        else
            texture = assetManager.get("textures/ui_background.png", Texture.class);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return texture;
    }

    public void loadAssets() {
        assetManager.load(Constants.TEX_SRC_TEXTURE_ATLAS_BAS, TextureAtlas.class);
        ParticleEffectLoader.ParticleEffectParameter pep = new ParticleEffectLoader.ParticleEffectParameter();
        pep.imagesDir = Gdx.files.internal("textures");
        assetManager.load(Constants.PE_SRC_FIGHTER_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_GRENADE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ROCKET_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_DYNAMITE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_GRENADE_SMOKE, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ROCKET_SMOKE, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_DYNAMITE_FLAME, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_CIRCLE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_ELLIPSE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_PENTAGON_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_RECTANGLE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_SEMICIRCLE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_SQUARE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_STAR_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_TRIANGLE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_CIRCLE_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_ELLIPSE_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_PENTAGON_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_RECTANGLE_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_SEMICIRCLE_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_SQUARE_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_STAR_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_SRC_ENEMY_TRIANGLE_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.SOUND_SRC_BUTTON_POS, Sound.class);
        assetManager.load(Constants.SOUND_SRC_BUTTON_NEG, Sound.class);
        assetManager.load(Constants.SOUND_SRC_ENEMY_HIT, Sound.class);
        assetManager.load(Constants.SOUND_SRC_ENEMY_EXPLOSION, Sound.class);
        assetManager.load(Constants.SOUND_SRC_ENEMY_AMMUNITION, Sound.class);
        assetManager.load(Constants.SOUND_SRC_BULLET, Sound.class);
        assetManager.load(Constants.SOUND_SRC_SHOT, Sound.class);
        assetManager.load(Constants.SOUND_SRC_KNIFE, Sound.class);
        assetManager.load(Constants.SOUND_SRC_GRENADE, Sound.class);
        assetManager.load(Constants.SOUND_SRC_ROCKET, Sound.class);
        assetManager.load(Constants.SOUND_SRC_DYNAMITE_A, Sound.class);
        assetManager.load(Constants.SOUND_SRC_DYNAMITE_B, Sound.class);
        assetManager.load(Constants.SOUND_SRC_DYNAMITE_C, Sound.class);
        assetManager.load(Constants.SOUND_SRC_BLADE, Sound.class);
        assetManager.load(Constants.SOUND_SRC_EXPLOSION_SMALL_A, Sound.class);
        assetManager.load(Constants.SOUND_SRC_EXPLOSION_SMALL_B, Sound.class);
        assetManager.load(Constants.SOUND_SRC_EXPLOSION_SMALL_C, Sound.class);
        assetManager.load(Constants.SOUND_SRC_EXPLOSION_BIG_A, Sound.class);
        assetManager.load(Constants.SOUND_SRC_EXPLOSION_BIG_B, Sound.class);
        assetManager.load(Constants.SOUND_SRC_EXPLOSION_BIG_C, Sound.class);
    }

    public void loadClasses() {
        gameStateManager = new GameStateManager(this);
        styles.loadTextureStyles(this);
        player = new Player(this);
        sounds = new Sounds(this);
        gameModeManager = new GameModeManager(this);
        gameWorld = new GameWorld(this);
        shapes = new Shapes();
        physicalDefinitions = new PhysicalDefinitions(this);
        gameObjectPools = new GameObjectPools(this);
        particleEffectPools = new ParticleEffectPools(this);
        creditsScreen = new CreditsScreen(this);
        gameScreen = new GameScreen(this);
        homeScreen = new HomeScreen(this);
        settingsScreen = new SettingsScreen(this);
        statsScreen = new StatsScreen(this);
        weaponsScreen = new WeaponsScreen(this);
        hudManager = new HudManager(this);
    }

    public TextureRegion getTextureRegionByID(String textureID) {
        return new TextureRegion(assetManager.get(Constants.TEX_SRC_TEXTURE_ATLAS_BAS, TextureAtlas.class).findRegion(textureID));
    }

    public ParticleEffect getParticleEffectByID(String particleEffectID) {
        return new ParticleEffect(assetManager.get(particleEffectID, ParticleEffect.class));
    }
}