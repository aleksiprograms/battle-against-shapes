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

    private ParticleEffectPool fighterExplosionPool;
    private ParticleEffectPool rocketExplosionPool;
    private ParticleEffectPool grenadeExplosionPool;
    private ParticleEffectPool dynamiteExplosionPool;
    private ParticleEffectPool grenadeSmokePool;
    private ParticleEffectPool rocketSmokePool;
    private ParticleEffectPool dynamiteFlamePool;
    private ParticleEffectPool enemyCircleExplosionPool;
    private ParticleEffectPool enemyEllipseExplosionPool;
    private ParticleEffectPool enemyPentagonExplosionPool;
    private ParticleEffectPool enemyRectangleExplosionPool;
    private ParticleEffectPool enemySemicircleExplosionPool;
    private ParticleEffectPool enemySquareExplosionPool;
    private ParticleEffectPool enemyStarExplosionPool;
    private ParticleEffectPool enemyTriangleExplosionPool;
    private ParticleEffectPool enemyCircleHitPool;
    private ParticleEffectPool enemyEllipseHitPool;
    private ParticleEffectPool enemyPentagonHitPool;
    private ParticleEffectPool enemyRectangleHitPool;
    private ParticleEffectPool enemySemicircleHitPool;
    private ParticleEffectPool enemySquareHitPool;
    private ParticleEffectPool enemyStarHitPool;
    private ParticleEffectPool enemyTriangleHitPool;

    public ParticleEffectPools(TheGame game) {
        initialize(game);
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

    private void initialize(TheGame game) {
        ParticleEffect particleEffect = game.getResources().getParticleEffectByID(Constants.PE_FIGHTER_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        fighterExplosionPool = new ParticleEffectPool(particleEffect, 0, 1);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_GRENADE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        grenadeExplosionPool = new ParticleEffectPool(particleEffect, 0, 5);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ROCKET_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        rocketExplosionPool = new ParticleEffectPool(particleEffect, 0, 5);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_DYNAMITE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        dynamiteExplosionPool = new ParticleEffectPool(particleEffect, 0, 5);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_GRENADE_SMOKE);
        particleEffect.scaleEffect((1 / Constants.PPM));
        grenadeSmokePool = new ParticleEffectPool(particleEffect, 0, 50);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ROCKET_SMOKE);
        particleEffect.scaleEffect((1 / Constants.PPM));
        rocketSmokePool = new ParticleEffectPool(particleEffect, 0, 50);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_DYNAMITE_FLAME);
        particleEffect.scaleEffect((1 / Constants.PPM));
        dynamiteFlamePool = new ParticleEffectPool(particleEffect, 0, 50);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_CIRCLE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyCircleExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_ELLIPSE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyEllipseExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_PENTAGON_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyPentagonExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_RECTANGLE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyRectangleExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_SEMICIRCLE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemySemicircleExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_SQUARE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemySquareExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_STAR_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyStarExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_TRIANGLE_EXPLOSION);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyTriangleExplosionPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_CIRCLE_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyCircleHitPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_ELLIPSE_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyEllipseHitPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_PENTAGON_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyPentagonHitPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_RECTANGLE_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyRectangleHitPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_SEMICIRCLE_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemySemicircleHitPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_SQUARE_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemySquareHitPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_STAR_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyStarHitPool = new ParticleEffectPool(particleEffect, 0, 10);

        particleEffect = game.getResources().getParticleEffectByID(Constants.PE_ENEMY_TRIANGLE_HIT);
        particleEffect.scaleEffect((1 / Constants.PPM));
        enemyTriangleHitPool = new ParticleEffectPool(particleEffect, 0, 10);
    }

    public ParticleEffectPool getFighterExplosionPool() {
        return fighterExplosionPool;
    }

    public ParticleEffectPool getRocketExplosionPool() {
        return rocketExplosionPool;
    }

    public ParticleEffectPool getGrenadeExplosionPool() {
        return grenadeExplosionPool;
    }

    public ParticleEffectPool getDynamiteExplosionPool() {
        return dynamiteExplosionPool;
    }

    public ParticleEffectPool getGrenadeSmokePool() {
        return grenadeSmokePool;
    }

    public ParticleEffectPool getRocketSmokePool() {
        return rocketSmokePool;
    }

    public ParticleEffectPool getDynamiteFlamePool() {
        return dynamiteFlamePool;
    }

    public ParticleEffectPool getEnemyCircleExplosionPool() {
        return enemyCircleExplosionPool;
    }

    public ParticleEffectPool getEnemyEllipseExplosionPool() {
        return enemyEllipseExplosionPool;
    }

    public ParticleEffectPool getEnemyPentagonExplosionPool() {
        return enemyPentagonExplosionPool;
    }

    public ParticleEffectPool getEnemyRectangleExplosionPool() {
        return enemyRectangleExplosionPool;
    }

    public ParticleEffectPool getEnemySemicircleExplosionPool() {
        return enemySemicircleExplosionPool;
    }

    public ParticleEffectPool getEnemySquareExplosionPool() {
        return enemySquareExplosionPool;
    }

    public ParticleEffectPool getEnemyStarExplosionPool() {
        return enemyStarExplosionPool;
    }

    public ParticleEffectPool getEnemyTriangleExplosionPool() {
        return enemyTriangleExplosionPool;
    }

    public ParticleEffectPool getEnemyCircleHitPool() {
        return enemyCircleHitPool;
    }

    public ParticleEffectPool getEnemyEllipseHitPool() {
        return enemyEllipseHitPool;
    }

    public ParticleEffectPool getEnemyPentagonHitPool() {
        return enemyPentagonHitPool;
    }

    public ParticleEffectPool getEnemyRectangleHitPool() {
        return enemyRectangleHitPool;
    }

    public ParticleEffectPool getEnemySemicircleHitPool() {
        return enemySemicircleHitPool;
    }

    public ParticleEffectPool getEnemySquareHitPool() {
        return enemySquareHitPool;
    }

    public ParticleEffectPool getEnemyStarHitPool() {
        return enemyStarHitPool;
    }

    public ParticleEffectPool getEnemyTriangleHitPool() {
        return enemyTriangleHitPool;
    }
}