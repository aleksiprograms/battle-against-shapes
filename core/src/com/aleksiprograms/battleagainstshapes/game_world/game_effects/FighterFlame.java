package com.aleksiprograms.battleagainstshapes.game_world.game_effects;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

/**
 * This class manages the visual effect of the flame,
 * which comes from the engine of the fighter.
 */
public class FighterFlame {

    private TheGame game;
    private ParticleEffect particleEffect;

    public FighterFlame(TheGame game) {
        this.game = game;
        particleEffect = new ParticleEffect();
        particleEffect.load(
                Gdx.files.internal(Constants.PE_FIGHTER_FLAME),
                Gdx.files.internal("textures"));
        particleEffect.setPosition(0, 0);
        particleEffect.scaleEffect(0.9f * 1 / Constants.PPM);
        particleEffect.start();
    }

    public void draw(Batch batch) {
        particleEffect.draw(batch);
    }

    public void update(float deltaTime) {
        particleEffect.setPosition(
                game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                        .getPosition().x + Constants.FIGHTER_FLAME_X_OFFSET,
                game.getGameWorld().getPlayer().getFighter().getBox2DBody()
                        .getPosition().y + Constants.FIGHTER_FLAME_Y_OFFSET);
        particleEffect.update(deltaTime);
    }

    public void setDrawFlame(boolean draw) {
        if (draw) {
            particleEffect.getEmitters().get(0).setContinuous(true);
        } else {
            particleEffect.getEmitters().get(0).setContinuous(false);
        }
    }
}