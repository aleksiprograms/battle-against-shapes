package com.aleksiprograms.battleagainstshapes.game_world.effects;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.resources.Constants;

/**
 * Here are static methods for making physical effects
 * of the different kind of explosions made with Particle-classes.
 */
public class PhysicalEffects {

    private PhysicalEffects() {}

    public static void makeGrenadeExplosion(
            TheGame game,
            float x,
            float y) {

        float angle;
        Vector2 velocity;
        for (int i = 0; i < 10; i++) {
            angle = (i / (float)10) * 360 * MathUtils.degreesToRadians;
            velocity = new Vector2(MathUtils.sin(angle), MathUtils.cos(angle)).scl(50);
            game.gameWorld.addParticleToWorld(
                    game.gameObjectPools.grenadeExplosionParticlePool.obtain(),
                    x,
                    y,
                    0,
                    velocity,
                    Constants.MAX_HEALTH_EXPLOSION_PARTICLE,
                    Constants.DAMAGE_EXPLOSION_PARTICLE);
        }
    }

    public static void makeRocketExplosion(
            TheGame game,
            float x,
            float y) {

        float angle;
        Vector2 velocity;
        for (int i = 0; i < 25; i++) {
            angle = (i / (float)25) * 360 * MathUtils.degreesToRadians;
            velocity = new Vector2(MathUtils.sin(angle), MathUtils.cos(angle)).scl(100);
            game.gameWorld.addParticleToWorld(
                    game.gameObjectPools.rocketExplosionParticlePool.obtain(),
                    x,
                    y,
                    0,
                    velocity,
                    Constants.MAX_HEALTH_EXPLOSION_PARTICLE,
                    Constants.DAMAGE_EXPLOSION_PARTICLE);
        }
    }

    public static void makeDynamiteExplosion(
            TheGame game,
            float x,
            float y) {

        float angle;
        Vector2 velocity;
        for (int i = 0; i < 15; i++) {
            angle = (i / (float)15) * 360 * MathUtils.degreesToRadians;
            velocity = new Vector2(MathUtils.sin(angle), MathUtils.cos(angle)).scl(70);
            game.gameWorld.addParticleToWorld(
                    game.gameObjectPools.dynamiteExplosionParticlePool.obtain(),
                    x,
                    y,
                    0,
                    velocity,
                    Constants.MAX_HEALTH_EXPLOSION_PARTICLE,
                    Constants.DAMAGE_EXPLOSION_PARTICLE);
        }
    }

    public static void makeFighterExplosion(
            TheGame game,
            float x,
            float y) {

        float angle;
        Vector2 velocity;
        for (int i = 0; i < 25; i++) {
            angle = (i / (float)25) * 360 * MathUtils.degreesToRadians;
            velocity = new Vector2(MathUtils.sin(angle), MathUtils.cos(angle)).scl(100);
            game.gameWorld.addParticleToWorld(
                    game.gameObjectPools.fighterExplosionParticlePool.obtain(),
                    x,
                    y,
                    0,
                    velocity,
                    Constants.MAX_HEALTH_EXPLOSION_PARTICLE,
                    Constants.DAMAGE_EXPLOSION_PARTICLE);
        }
    }
}