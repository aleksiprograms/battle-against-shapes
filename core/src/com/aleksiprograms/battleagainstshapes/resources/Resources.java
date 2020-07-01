package com.aleksiprograms.battleagainstshapes.resources;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Holds different kind of resources for other object to use.
 */
public class Resources {

    private TheGame game;
    private AssetManager assetManager;
    private GameObjectPools gameObjectPools;
    private ParticleEffectPools particleEffectPools;
    private Sounds sounds;
    private Styles styles;
    private PhysicalShapes physicalShapes;
    private PhysicalDefinitions physicalDefinitions;

    public Resources(TheGame game) {
        this.game = game;
        assetManager = new AssetManager();
        TextureLoader.TextureParameter param = new TextureLoader.TextureParameter();
        param.genMipMaps = true;
        assetManager.load("textures/ui_background.png", Texture.class, param);
        assetManager.load("textures/logo_bas_text.png", Texture.class, param);
        assetManager.finishLoading();
        styles = new Styles();
    }

    public void loadAssets() {
        assetManager.load(Constants.TEXTURE_TEXTURE_ATLAS_BAS, TextureAtlas.class);
        ParticleEffectLoader.ParticleEffectParameter pep
                = new ParticleEffectLoader.ParticleEffectParameter();
        pep.imagesDir = Gdx.files.internal("textures");
        assetManager.load(Constants.PE_FIGHTER_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_GRENADE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ROCKET_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_DYNAMITE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_GRENADE_SMOKE, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ROCKET_SMOKE, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_DYNAMITE_FLAME, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_CIRCLE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_ELLIPSE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_PENTAGON_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_RECTANGLE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_SEMICIRCLE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_SQUARE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_STAR_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_TRIANGLE_EXPLOSION, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_CIRCLE_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_ELLIPSE_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_PENTAGON_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_RECTANGLE_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_SEMICIRCLE_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_SQUARE_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_STAR_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.PE_ENEMY_TRIANGLE_HIT, ParticleEffect.class, pep);
        assetManager.load(Constants.SOUND_BUTTON_POS, Sound.class);
        assetManager.load(Constants.SOUND_BUTTON_NEG, Sound.class);
        assetManager.load(Constants.SOUND_ENEMY_HIT, Sound.class);
        assetManager.load(Constants.SOUND_ENEMY_EXPLOSION, Sound.class);
        assetManager.load(Constants.SOUND_ENEMY_AMMUNITION, Sound.class);
        assetManager.load(Constants.SOUND_BULLET, Sound.class);
        assetManager.load(Constants.SOUND_SHOT, Sound.class);
        assetManager.load(Constants.SOUND_KNIFE, Sound.class);
        assetManager.load(Constants.SOUND_GRENADE, Sound.class);
        assetManager.load(Constants.SOUND_ROCKET, Sound.class);
        assetManager.load(Constants.SOUND_DYNAMITE_A, Sound.class);
        assetManager.load(Constants.SOUND_DYNAMITE_B, Sound.class);
        assetManager.load(Constants.SOUND_DYNAMITE_C, Sound.class);
        assetManager.load(Constants.SOUND_BLADE, Sound.class);
        assetManager.load(Constants.SOUND_EXPLOSION_SMALL_A, Sound.class);
        assetManager.load(Constants.SOUND_EXPLOSION_SMALL_B, Sound.class);
        assetManager.load(Constants.SOUND_EXPLOSION_SMALL_C, Sound.class);
        assetManager.load(Constants.SOUND_EXPLOSION_BIG_A, Sound.class);
        assetManager.load(Constants.SOUND_EXPLOSION_BIG_B, Sound.class);
        assetManager.load(Constants.SOUND_EXPLOSION_BIG_C, Sound.class);
    }

    public void loadRest() {
        styles.loadRest(game);
        sounds = new Sounds(game);
        physicalShapes = new PhysicalShapes();
        physicalDefinitions = new PhysicalDefinitions(game);
        gameObjectPools = new GameObjectPools(game);
        particleEffectPools = new ParticleEffectPools(game);
    }

    public void dispose() {
        assetManager.dispose();
        physicalShapes.dispose();
        gameObjectPools.dispose();
        particleEffectPools.dispose();
    }

    public TextureRegion getTextureRegionByID(String textureID) {
        return new TextureRegion(assetManager.get(
                Constants.TEXTURE_TEXTURE_ATLAS_BAS, TextureAtlas.class).findRegion(textureID));
    }

    public ParticleEffect getParticleEffectByID(String particleEffectID) {
        return new ParticleEffect(assetManager.get(particleEffectID, ParticleEffect.class));
    }

    public Texture getTextureBeforeAllLoaded(String textureID) {
        Texture texture;
        if (textureID.equals("game_logo")) {
            texture = assetManager.get("textures/logo_bas_text.png", Texture.class);
        } else {
            texture = assetManager.get("textures/ui_background.png", Texture.class);
        }
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return texture;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public GameObjectPools getGameObjectPools() {
        return gameObjectPools;
    }

    public ParticleEffectPools getParticleEffectPools() {
        return particleEffectPools;
    }

    public Sounds getSounds() {
        return sounds;
    }

    public Styles getStyles() {
        return styles;
    }

    public PhysicalShapes getPhysicalShapes() {
        return physicalShapes;
    }

    public PhysicalDefinitions getPhysicalDefinitions() {
        return physicalDefinitions;
    }
}