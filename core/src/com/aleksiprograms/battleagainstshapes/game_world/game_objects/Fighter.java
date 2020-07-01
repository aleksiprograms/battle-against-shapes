package com.aleksiprograms.battleagainstshapes.game_world.game_objects;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_effects.FighterFlame;
import com.aleksiprograms.battleagainstshapes.game_world.game_effects.PhysicalEffects;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.toolbox.GameState;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Fighter extends DrawablePhysicalGameObject {

    private FighterFlame fighterFlame;
    private boolean exploded;
    private boolean blastWaveMade;
    private float fighterFlameTimer;

    public Fighter(TheGame game) {
        super(
                game,
                game.getResources().getTextureRegionByID(Constants.TEXTURE_FIGHTER),
                game.getResources().getPhysicalDefinitions().getFighterPhyDef(),
                Constants.FIGHTER_WIDTH,
                Constants.FIGHTER_HEIGHT,
                true);
        box2DBody.createFixture(physicalDef.getFixtureDef()).setUserData(this);
        FixtureDef fd = new FixtureDef();
        fd.density = 0.1f;
        fd.friction = 0.0f;
        fd.restitution = 0.0f;
        fd.filter.categoryBits = Constants.CATEGORY_PLAYER;
        fd.filter.maskBits = Constants.MASK_PLAYER;
        fd.shape = game.getResources().getPhysicalShapes().getTailWingShape();
        box2DBody.createFixture(fd).setUserData(this);

        fighterFlame = new FighterFlame(game);
    }

    @Override
    public void initialize(
            float x,
            float y,
            float angle,
            Vector2 velocity,
            float health,
            float damage) {
        super.initialize(x, y, angle, velocity, health, damage);
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
                game.getGameWorld().addGameObjectToWorld(
                        game.getResources().getGameObjectPools().getFighterFlameParticlePool().obtain(),
                        box2DBody.getPosition().x + Constants.FIGHTER_FLAME_X_OFFSET,
                        box2DBody.getPosition().y + Constants.FIGHTER_FLAME_Y_OFFSET,
                        0,
                        new Vector2(-8, 0),
                        Constants.MAX_HEALTH_FIGHTER_FLAME_PARTICLE,
                        Constants.DAMAGE_FIGHTER_FLAME_PARTICLE);
            }
        } else {
            game.getGameScreen().changeGameState(GameState.GAME_OVER);
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
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        fighterFlame.draw(spriteBatch);
    }

    @Override
    public void onContact(
            float damage,
            PhysicalGameObject physicalGameObjectA,
            PhysicalGameObject physicalGameObjectB) {
        health += damage;
        if (damage < 0) {
            game.getGameScreen().getInGameStatsManager().breakCombo();
        }
        if (health <= 0 && !exploded) {
            exploded = true;
            game.getResources().getSounds().getSoundByID(Constants.SOUND_EXPLOSION_BIG_A)
                    .play(game.getSaveManager().getSaveData().getSoundVolume());
            game.getGameWorld().addEffectToWorld(
                    game.getResources().getParticleEffectPools().getFighterExplosionPool().obtain(),
                    box2DBody.getPosition().x,
                    box2DBody.getPosition().y);
        }
        game.getGameScreen().getInGameHud().updateData();
    }
}