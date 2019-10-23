package com.aleksiprograms.battleagainstshapes.resources;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.utils.Pool;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Blade;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Bullet;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Dynamite;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Grenade;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Knife;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Rocket;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Shot;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyCircle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyCircleAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyPentagonAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyEllipse;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyPentagon;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyRectangle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyRectangleAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemySemicircle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemySemicircleAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemySquare;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyStar;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyStarAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyTriangle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyTriangleAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.general_objects.Fighter;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.general_objects.Wall;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.DynamiteExplosionParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.FighterExplosionParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.FighterFlameParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.FlamethrowerParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.GrenadeExplosionParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.RocketExplosionParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.DynamiteLauncher;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.BladeLauncher;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.Flamethrower;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.GrenadeLauncher;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.MachineGun;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.RocketLauncher;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.Shotgun;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.KnifeThrower;

/**
 * Creates and holds all the game object pools, hence the game objects are reusable.
 * You can obtain a game object from the pool (you may want to initialize it).
 * After using the object, it must be freed back to its pool.
 */
public class GameObjectPools {

    public Pool<Fighter> fighterPool;
    public Pool<Wall> wallPool;

    public Pool<Bullet> bulletPool;
    public Pool<Shot> shotPool;
    public Pool<Knife> knifePool;
    public Pool<Grenade> grenadePool;
    public Pool<Rocket> rocketPool;
    public Pool<Dynamite> dynamitePool;
    public Pool<Blade> bladePool;

    public Pool<MachineGun> machineGunPool;
    public Pool<Shotgun> shotgunPool;
    public Pool<Flamethrower> flamethrowerPool;
    public Pool<KnifeThrower> swordHolderPool;
    public Pool<GrenadeLauncher> grenadeLauncherPool;
    public Pool<RocketLauncher> rocketLauncherPool;
    public Pool<BladeLauncher> bladeLauncherPool;
    public Pool<DynamiteLauncher> airCannonPool;

    public Pool<FighterFlameParticle> fighterFlameParticlePool;
    public Pool<FlamethrowerParticle> flamethrowerParticlePool;
    public Pool<FighterExplosionParticle> fighterExplosionParticlePool;
    public Pool<GrenadeExplosionParticle> grenadeExplosionParticlePool;
    public Pool<RocketExplosionParticle> rocketExplosionParticlePool;
    public Pool<DynamiteExplosionParticle> dynamiteExplosionParticlePool;

    public Pool<EnemyCircle> enemyCirclePool;
    public Pool<EnemyEllipse> enemyEllipsePool;
    public Pool<EnemyPentagon> enemyPentagonPool;
    public Pool<EnemyRectangle> enemyRectanglePool;
    public Pool<EnemySemicircle> enemySemicirclePool;
    public Pool<EnemySquare> enemySquarePool;
    public Pool<EnemyStar> enemyStarPool;
    public Pool<EnemyTriangle> enemyTrianglePool;
    public Pool<EnemyCircleAmmunition> enemyCircleAmmunitionPool;
    public Pool<EnemyPentagonAmmunition> enemyPentagonAmmunitionPool;
    public Pool<EnemyRectangleAmmunition> enemyRectangleAmmunitionPool;
    public Pool<EnemySemicircleAmmunition> enemySemicircleAmmunitionPool;
    public Pool<EnemyStarAmmunition> enemyStarAmmunitionPool;
    public Pool<EnemyTriangleAmmunition> enemyTriangleAmmunitionPool;

    public GameObjectPools(final TheGame game) {
        initializePools(game);
    }

    public void dispose() {
        fighterPool.clear();
        wallPool.clear();

        bulletPool.clear();
        shotPool.clear();
        knifePool.clear();
        grenadePool.clear();
        rocketPool.clear();
        dynamitePool.clear();
        bladePool.clear();

        machineGunPool.clear();
        shotgunPool.clear();
        flamethrowerPool.clear();
        swordHolderPool.clear();
        grenadeLauncherPool.clear();
        rocketLauncherPool.clear();
        bladeLauncherPool.clear();
        airCannonPool.clear();

        fighterFlameParticlePool.clear();
        flamethrowerParticlePool.clear();
        fighterExplosionParticlePool.clear();
        grenadeExplosionParticlePool.clear();
        rocketExplosionParticlePool.clear();
        dynamiteExplosionParticlePool.clear();

        enemyCirclePool.clear();
        enemyEllipsePool.clear();
        enemyPentagonPool.clear();
        enemyRectanglePool.clear();
        enemySemicirclePool.clear();
        enemySquarePool.clear();
        enemyStarPool.clear();
        enemyTrianglePool.clear();
        enemyCircleAmmunitionPool.clear();
        enemyPentagonAmmunitionPool.clear();
        enemyRectangleAmmunitionPool.clear();
        enemySemicircleAmmunitionPool.clear();
        enemyStarAmmunitionPool.clear();
        enemyTriangleAmmunitionPool.clear();

    }

    private void initializePools(final TheGame game) {
        fighterPool = new Pool<Fighter>(0, 1) {
            @Override
            protected Fighter newObject() {
                return new Fighter(game);
            }
        };
        wallPool = new Pool<Wall>(100, 200) {
            @Override
            protected Wall newObject() {
                return new Wall(game);
            }
        };

        // AMMUNITION
        bulletPool = new Pool<Bullet>(20, 50) {
            @Override
            protected Bullet newObject() {
                return new Bullet(game);
            }
        };
        shotPool = new Pool<Shot>(20, 50) {
            @Override
            protected Shot newObject() {
                return new Shot(game);
            }
        };
        knifePool = new Pool<Knife>(0, 50) {
            @Override
            protected Knife newObject() {
                return new Knife(game);
            }
        };
        grenadePool = new Pool<Grenade>(0, 50) {
            @Override
            protected Grenade newObject() {
                return new Grenade(game);
            }
        };
        rocketPool = new Pool<Rocket>(0, 50) {
            @Override
            protected Rocket newObject() {
                return new Rocket(game);
            }
        };
        dynamitePool = new Pool<Dynamite>(0, 50) {
            @Override
            protected Dynamite newObject() {
                return new Dynamite(game);
            }
        };
        bladePool = new Pool<Blade>(0, 50) {
            @Override
            protected Blade newObject() {
                return new Blade(game);
            }
        };

        // WEAPONS
        machineGunPool = new Pool<MachineGun>(0, 1) {
            @Override
            protected MachineGun newObject() {
                return new MachineGun(game);
            }
        };
        shotgunPool = new Pool<Shotgun>(0, 1) {
            @Override
            protected Shotgun newObject() {
                return new Shotgun(game);
            }
        };
        flamethrowerPool = new Pool<Flamethrower>(0, 1) {
            @Override
            protected Flamethrower newObject() {
                return new Flamethrower(game);
            }
        };
        swordHolderPool = new Pool<KnifeThrower>(0, 1) {
            @Override
            protected KnifeThrower newObject() {
                return new KnifeThrower(game);
            }
        };
        grenadeLauncherPool = new Pool<GrenadeLauncher>(0, 1) {
            @Override
            protected GrenadeLauncher newObject() {
                return new GrenadeLauncher(game);
            }
        };
        rocketLauncherPool = new Pool<RocketLauncher>(0, 1) {
            @Override
            protected RocketLauncher newObject() {
                return new RocketLauncher(game);
            }
        };
        bladeLauncherPool = new Pool<BladeLauncher>(0, 1) {
            @Override
            protected BladeLauncher newObject() {
                return new BladeLauncher(game);
            }
        };
        airCannonPool = new Pool<DynamiteLauncher>(0, 1) {
            @Override
            protected DynamiteLauncher newObject() {
                return new DynamiteLauncher(game);
            }
        };

        // PARTICLES
        fighterFlameParticlePool = new Pool<FighterFlameParticle>(0, 50) {
            @Override
            protected FighterFlameParticle newObject() {
                return new FighterFlameParticle(game);
            }
        };
        flamethrowerParticlePool = new Pool<FlamethrowerParticle>(0, 50) {
            @Override
            protected FlamethrowerParticle newObject() {
                return new FlamethrowerParticle(game);
            }
        };
        fighterExplosionParticlePool = new Pool<FighterExplosionParticle>(0, 50) {
            @Override
            protected FighterExplosionParticle newObject() {
                return new FighterExplosionParticle(game);
            }
        };
        grenadeExplosionParticlePool = new Pool<GrenadeExplosionParticle>(0, 50) {
            @Override
            protected GrenadeExplosionParticle newObject() {
                return new GrenadeExplosionParticle(game);
            }
        };
        rocketExplosionParticlePool = new Pool<RocketExplosionParticle>(0, 50) {
            @Override
            protected RocketExplosionParticle newObject() {
                return new RocketExplosionParticle(game);
            }
        };
        dynamiteExplosionParticlePool = new Pool<DynamiteExplosionParticle>(0, 50) {
            @Override
            protected DynamiteExplosionParticle newObject() {
                return new DynamiteExplosionParticle(game);
            }
        };

        // ENEMIES
        enemyCirclePool = new Pool<EnemyCircle>(0, 50) {
            @Override
            protected EnemyCircle newObject() {
                return new EnemyCircle(game);
            }
        };
        enemyEllipsePool = new Pool<EnemyEllipse>(0, 50) {
            @Override
            protected EnemyEllipse newObject() {
                return new EnemyEllipse(game);
            }
        };
        enemyPentagonPool = new Pool<EnemyPentagon>(0, 50) {
            @Override
            protected EnemyPentagon newObject() {
                return new EnemyPentagon(game);
            }
        };
        enemyRectanglePool = new Pool<EnemyRectangle>(0, 50) {
            @Override
            protected EnemyRectangle newObject() {
                return new EnemyRectangle(game);
            }
        };
        enemySemicirclePool = new Pool<EnemySemicircle>(0, 50) {
            @Override
            protected EnemySemicircle newObject() {
                return new EnemySemicircle(game);
            }
        };
        enemySquarePool = new Pool<EnemySquare>(0, 50) {
            @Override
            protected EnemySquare newObject() {
                return new EnemySquare(game);
            }
        };
        enemyStarPool = new Pool<EnemyStar>(0, 50) {
            @Override
            protected EnemyStar newObject() {
                return new EnemyStar(game);
            }
        };
        enemyTrianglePool = new Pool<EnemyTriangle>(0, 50) {
            @Override
            protected EnemyTriangle newObject() {
                return new EnemyTriangle(game);
            }
        };
        enemyCircleAmmunitionPool = new Pool<EnemyCircleAmmunition>(0, 50) {
            @Override
            protected EnemyCircleAmmunition newObject() {
                return new EnemyCircleAmmunition(game);
            }
        };
        enemyPentagonAmmunitionPool = new Pool<EnemyPentagonAmmunition>(0, 50) {
            @Override
            protected EnemyPentagonAmmunition newObject() {
                return new EnemyPentagonAmmunition(game);
            }
        };
        enemyRectangleAmmunitionPool = new Pool<EnemyRectangleAmmunition>(0, 50) {
            @Override
            protected EnemyRectangleAmmunition newObject() {
                return new EnemyRectangleAmmunition(game);
            }
        };
        enemySemicircleAmmunitionPool = new Pool<EnemySemicircleAmmunition>(0, 50) {
            @Override
            protected EnemySemicircleAmmunition newObject() {
                return new EnemySemicircleAmmunition(game);
            }
        };
        enemyStarAmmunitionPool = new Pool<EnemyStarAmmunition>(0, 50) {
            @Override
            protected EnemyStarAmmunition newObject() {
                return new EnemyStarAmmunition(game);
            }
        };
        enemyTriangleAmmunitionPool = new Pool<EnemyTriangleAmmunition>(0, 50) {
            @Override
            protected EnemyTriangleAmmunition newObject() {
                return new EnemyTriangleAmmunition(game);
            }
        };
    }
}