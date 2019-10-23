package com.aleksiprograms.battleagainstshapes.game_world.game_objects.general_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.DrawableGameObject;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.aleksiprograms.battleagainstshapes.game_world.effects.FighterFlame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.toolbox.LevelState;
import com.aleksiprograms.battleagainstshapes.game_world.effects.PhysicalEffects;

public class Fighter extends DrawableGameObject {

    private FighterFlame fighterFlame;
    private boolean exploded;
    private boolean blastWaveMade;
    private float fighterFlameTimer;

    public Fighter(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_FIGHTER),
                game.physicalDefinitions.pdFighter,
                Constants.FIGHTER_WIDTH,
                Constants.FIGHTER_HEIGHT,
                true);

        box2DBody.createFixture(physicalDef.fixtureDef).setUserData(this);

        FixtureDef fd = new FixtureDef();
        fd.density = 0.1f;
        fd.friction = 0.0f;
        fd.restitution = 0.0f;
        fd.filter.categoryBits = Constants.CATEGORY_PLAYER;
        fd.filter.maskBits = Constants.MASK_PLAYER;
        fd.shape = game.shapes.tailWingShape;
        box2DBody.createFixture(fd).setUserData(this);

        fighterFlame = new FighterFlame(game);
    }

    public boolean isExploded() {
        return exploded;
    }

    @Override
    public void init(float x, float y, float angle, Vector2 velocity, float health, float damage) {
        super.init(x, y, angle, velocity, health, damage);
        exploded = false;
        blastWaveMade = false;
        fighterFlameTimer = 0;
        fighterFlame.setDrawFlame(true);
        box2DBody.setGravityScale(0);
        box2DBody.setFixedRotation(true);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        fighterFlameTimer += deltaTime;
        fighterFlame.update(deltaTime);
        if (!exploded) {
            box2DBody.applyForceToCenter(60, 0, true);
            if (fighterFlameTimer > 0.08f) {
                fighterFlameTimer = 0;
                game.gameWorld.addParticleToWorld(
                        game.gameObjectPools.fighterFlameParticlePool.obtain(),
                        game.player.fighter.box2DBody.getPosition().x + Constants.FIGHTER_FLAME_X_OFFSET,
                        game.player.fighter.box2DBody.getPosition().y + Constants.FIGHTER_FLAME_Y_OFFSET,
                        0,
                        new Vector2(-8, 0),
                        Constants.MAX_HEALTH_FIGHTER_FLAME_PARTICLE,
                        Constants.DAMAGE_FIGHTER_FLAME_PARTICLE);
            }
        } else {
            game.gameStateManager.setLevelCompleted();

            fighterFlame.setDrawFlame(false);
            box2DBody.setGravityScale(2);
            box2DBody.setFixedRotation(false);
        }
        if (exploded && !blastWaveMade) {
            PhysicalEffects.makeFighterExplosion(
                    game,
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
            blastWaveMade = true;
        }
        if (game.gameStateManager.getLevelState().equals(LevelState.COMPLETED)) {
            if (game.player.controlManager.btMoveUpPress)
                game.player.controlManager.btMoveUpPress = false;
            if (game.player.controlManager.btMoveDownPress)
                game.player.controlManager.btMoveDownPress = false;
            if (game.player.controlManager.btUsePriWeaPress)
                game.player.controlManager.btUsePriWeaPress = false;
            if (game.player.controlManager.btUseSecWeaPress)
                game.player.controlManager.btUseSecWeaPress = false;
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        fighterFlame.draw(spriteBatch);
    }

    @Override
    public void onContact(float damage, GameObject gameObjectA, GameObject gameObjectB) {
        health += damage;
        if (damage < 0) {
            game.player.gameModeStatsManager.breakCombo();
        }
        if (health <= 0 && !exploded) {
            exploded = true;
            game.sounds.getSoundByID(Constants.SOUND_SRC_EXPLOSION_BIG_A).play(game.saveManager.saveData.getSoundVolume());
            game.gameWorld.addEffect(
                    game.particleEffectPools.fighterExplosionPool.obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
        game.gameStateManager.setUpdateInGameHud(true);
    }

    public void repair() {
        health = Constants.MAX_HEALTH_PLAYER;
    }
}