package com.aleksiprograms.battleagainstshapes.resources;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;

/**
 * Creates and holds the particle effect pools so the particle effects are reusable.
 * When you need a effect, you obtain the effect from its pool and initialize it
 * and after usage you free it back to the pool.
 */
public class ParticleEffectPools {

    public ParticleEffectPool fighterExplosionPool;
    public ParticleEffectPool rocketExplosionPool;
    public ParticleEffectPool grenadeExplosionPool;
    public ParticleEffectPool dynamiteExplosionPool;
    public ParticleEffectPool grenadeSmokePool;
    public ParticleEffectPool rocketSmokePool;
    public ParticleEffectPool dynamiteFlamePool;
    public ParticleEffectPool enemyCircleExplosionPool;
    public ParticleEffectPool enemyEllipseExplosionPool;
    public ParticleEffectPool enemyPentagonExplosionPool;
    public ParticleEffectPool enemyRectangleExplosionPool;
    public ParticleEffectPool enemySemicircleExplosionPool;
    public ParticleEffectPool enemySquareExplosionPool;
    public ParticleEffectPool enemyStarExplosionPool;
    public ParticleEffectPool enemyTriangleExplosionPool;
    public ParticleEffectPool enemyCircleHitPool;
    public ParticleEffectPool enemyEllipseHitPool;
    public ParticleEffectPool enemyPentagonHitPool;
    public ParticleEffectPool enemyRectangleHitPool;
    public ParticleEffectPool enemySemicircleHitPool;
    public ParticleEffectPool enemySquareHitPool;
    public ParticleEffectPool enemyStarHitPool;
    public ParticleEffectPool enemyTriangleHitPool;

    public ParticleEffectPools(TheGame game) {
        ParticleEffect particleEffect = game.getParticleEffectByID(Constants.PE_SRC_FIGHTER_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        fighterExplosionPool = new ParticleEffectPool(particleEffect, 0, 1);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_GRENADE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        grenadeExplosionPool = new ParticleEffectPool(particleEffect, 0, 5);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ROCKET_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        rocketExplosionPool = new ParticleEffectPool(particleEffect, 0, 5);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_DYNAMITE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        dynamiteExplosionPool = new ParticleEffectPool(particleEffect, 0, 5);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_GRENADE_SMOKE);
        particleEffect.scaleEffect((1 / Constants.PPM));
        grenadeSmokePool = new ParticleEffectPool(particleEffect, 0, 50);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ROCKET_SMOKE);
        particleEffect.scaleEffect((1 / Constants.PPM));
        rocketSmokePool = new ParticleEffectPool(particleEffect, 0, 50);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_DYNAMITE_FLAME);
        particleEffect.scaleEffect((1 / Constants.PPM));
        dynamiteFlamePool = new ParticleEffectPool(particleEffect, 0, 50);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_CIRCLE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyCircleExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_ELLIPSE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyEllipseExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_PENTAGON_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyPentagonExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_RECTANGLE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyRectangleExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_SEMICIRCLE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemySemicircleExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_SQUARE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemySquareExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_STAR_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyStarExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_TRIANGLE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyTriangleExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_CIRCLE_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyCircleHitPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_ELLIPSE_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyEllipseHitPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_PENTAGON_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyPentagonHitPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_RECTANGLE_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyRectangleHitPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_SEMICIRCLE_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemySemicircleHitPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_SQUARE_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemySquareHitPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_STAR_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyStarHitPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getParticleEffectByID(Constants.PE_SRC_ENEMY_TRIANGLE_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyTriangleHitPool = new ParticleEffectPool(particleEffect, 0, 10);
    }

    public void dispose() {
        fighterExplosionPool.clear();
        rocketExplosionPool.clear();
        grenadeExplosionPool.clear();
        dynamiteExplosionPool.clear();
        grenadeSmokePool.clear();
        rocketSmokePool.clear();
        dynamiteFlamePool.clear();
        enemyCircleExplosionPool.clear();
        enemyEllipseExplosionPool.clear();
        enemyPentagonExplosionPool.clear();
        enemyRectangleExplosionPool.clear();
        enemySemicircleExplosionPool.clear();
        enemySquareExplosionPool.clear();
        enemyStarExplosionPool.clear();
        enemyTriangleExplosionPool.clear();
        enemyCircleHitPool.clear();
        enemyEllipseHitPool.clear();
        enemyPentagonHitPool.clear();
        enemyRectangleHitPool.clear();
        enemySemicircleHitPool.clear();
        enemySquareHitPool.clear();
        enemyStarHitPool.clear();
        enemyTriangleHitPool.clear();
    }
}