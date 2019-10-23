package com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

public class Flamethrower extends Weapon {

    private ParticleEffect particleEffect;
    private Sound sound;
    private boolean shoot = false;
    private long lastShoot = 0;
    private boolean drawFlame = false;
    private boolean destroy   = false;
    private boolean destroyed = false;

    public Flamethrower(TheGame game) {

        super(
                game,
                game.getTextureRegionByID(Constants.TEX_SRC_FLAMETHROWER),
                Constants.FLAMETHROWER_ID,
                0.1f,
                Constants.FLAMETHROWER_WIDTH,
                Constants.FLAMETHROWER_HEIGHT,
                Constants.FLAMETHROWER_X_OFFSET,
                Constants.FLAMETHROWER_Y_OFFSET);

        particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("particle_effects/weapon_flamethrower.p"), Gdx.files.internal("textures"));
        particleEffect.setPosition(0, -10);
        particleEffect.scaleEffect(0.85f * 1 / Constants.PPM);
        particleEffect.start();
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/flamethrower.wav"));
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        if(drawFlame && !destroy)
            particleEffect.draw(spriteBatch);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if (destroy && !destroyed) {
            destroyed = true;
            particleEffect.dispose();
        } else if (!destroy && drawFlame) {
            particleEffect.update(deltaTime);
            particleEffect.setPosition(
                    game.player.fighter.box2DBody.getPosition().x + Constants.FLAME_X_OFFSET,
                    game.player.fighter.box2DBody.getPosition().y + Constants.FLAME_Y_OFFSET);
        }
        if(lastShoot > System.currentTimeMillis() - 100) {
            if (!shoot) {
                shoot = true;
                drawFlame = true;
                particleEffect.getEmitters().get(0).setContinuous(true);
                sound.loop(game.saveManager.saveData.getSoundVolume());
            }
        } else {
            shoot = false;
            particleEffect.getEmitters().get(0).setContinuous(false);
            sound.stop();
        }
        if(!shoot && particleEffect.isComplete()) {
            drawFlame = false;
        }
    }

    @Override
    public void shoot() {
        lastShoot = System.currentTimeMillis();
        if (timeFromLastShot >= shotDelay) {
            timeFromLastShot = 0;
            float[] angles = {
                    -5 * MathUtils.degreesToRadians,
                    0 * MathUtils.degreesToRadians,
                    5 * MathUtils.degreesToRadians};
            Vector2 velocity;
            for (int i = 0; i < angles.length; i++) {
                velocity = new Vector2(MathUtils.cos(angles[i]), MathUtils.sin(angles[i])).scl(Constants.VELOCITY_FLAMETHROWER_PARTICLE.len());
                game.gameWorld.addParticleToWorld(
                        game.gameObjectPools.flamethrowerParticlePool.obtain(),
                        game.player.fighter.box2DBody.getPosition().x + Constants.FLAME_X_OFFSET,
                        game.player.fighter.box2DBody.getPosition().y + Constants.FLAME_Y_OFFSET,
                        0,
                        velocity,
                        Constants.MAX_HEALTH_FLAMETHROWER_PARTICLE,
                        Constants.DAMAGE_FLAMETHROWER_PARTICLE);
                game.player.gameModeStatsManager.addToPriWeaAmmunitionsFired();
            }
        }
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }
}