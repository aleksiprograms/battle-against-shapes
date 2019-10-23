package com.aleksiprograms.battleagainstshapes.game_world;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Blade;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Bullet;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Dynamite;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Grenade;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Knife;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Rocket;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Shot;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyCircleAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyEllipse;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyPentagon;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyRectangle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyRectangleAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemySemicircle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemySemicircleAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemySquare;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyCircle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyPentagonAmmunition;
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
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.Particle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.RocketExplosionParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.DynamiteLauncher;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.BladeLauncher;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.Flamethrower;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.GrenadeLauncher;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.MachineGun;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.KnifeThrower;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.RocketLauncher;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.Shotgun;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.Weapon;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.toolbox.GameMode;
import com.aleksiprograms.battleagainstshapes.toolbox.LevelState;

/**
 * Manages the game world. All game world objects are in their own object specific arrays.
 * Game objects can be added to the game world, they are updated and drawn and removed from the world or freed to the pool, if needed.
 * Contains also particle effects and same actions are done to them as to game objects (update, draw, delete/free, add).
 */
public class GameWorld {

    private TheGame game;
    public World box2DWorld;
    public Box2DDebugRenderer box2DDebugRenderer;
    private GameWorldCreator gameWorldCreator;
    private Sprite spriteSky;
    private Wall aboveWall;
    private Wall belowWall;

    private Array<Particle> particles = new Array<Particle>();
    private Array<Bullet> bullets = new Array<Bullet>();
    private Array<Shot> shots = new Array<Shot>();
    private Array<Knife> knifes = new Array<Knife>();
    private Array<Grenade> grenades = new Array<Grenade>();
    private Array<Rocket> rockets = new Array<Rocket>();
    private Array<Dynamite> dynamites = new Array<Dynamite>();
    private Array<Blade> blades = new Array<Blade>();
    private Array<EnemyCircle> enemyCircles = new Array<EnemyCircle>();
    private Array<EnemyEllipse> enemyEllipses = new Array<EnemyEllipse>();
    private Array<EnemyPentagon> enemyPentagons = new Array<EnemyPentagon>();
    private Array<EnemyRectangle> enemyRectangles = new Array<EnemyRectangle>();
    private Array<EnemySemicircle> enemySemicircles = new Array<EnemySemicircle>();
    private Array<EnemySquare> enemySquares = new Array<EnemySquare>();
    private Array<EnemyStar> enemyStars = new Array<EnemyStar>();
    private Array<EnemyTriangle> enemyTriangles = new Array<EnemyTriangle>();
    private Array<EnemyCircleAmmunition> enemyCircleAmmunitions = new Array<EnemyCircleAmmunition>();
    private Array<EnemyPentagonAmmunition> enemyPentagonAmmunitions = new Array<EnemyPentagonAmmunition>();
    private Array<EnemyRectangleAmmunition> enemyRectangleAmmunitions = new Array<EnemyRectangleAmmunition>();
    private Array<EnemySemicircleAmmunition> enemySemicircleAmmunitions = new Array<EnemySemicircleAmmunition>();
    private Array<EnemyStarAmmunition> enemyStarAmmunitions = new Array<EnemyStarAmmunition>();
    private Array<EnemyTriangleAmmunition> enemyTriangleAmmunitions = new Array<EnemyTriangleAmmunition>();

    private Array<ParticleEffectPool.PooledEffect> activeEffects = new Array<ParticleEffectPool.PooledEffect>();

    public GameWorld(TheGame game) {
        this.game = game;
        box2DWorld = new World(new Vector2(0, -9.81f), true);
        box2DWorld.setContactListener(new GameWorldContactListener());
        box2DDebugRenderer = new Box2DDebugRenderer();
        spriteSky = new Sprite(
                game.getTextureRegionByID(Constants.TEX_SRC_BACKGROUND));
        spriteSky.setBounds(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

    /**
     * Called when the game is paused and mainly pauses the sounds of some weapons.
     */
    public void onPause() {
        for (int i = 0; i < knifes.size; i++) {
            knifes.get(i).onPause();
        }
        for (int i = 0; i < grenades.size; i++) {
            grenades.get(i).onPause();
        }
        for (int i = 0; i < rockets.size; i++) {
            rockets.get(i).onPause();
        }
        for (int i = 0; i < dynamites.size; i++) {
            dynamites.get(i).onPause();
        }
    }

    /**
     * Called when the game is resumed from pause and mainly resumes the sounds of some weapons.
     */
    public void onResume() {
        for (int i = 0; i < knifes.size; i++) {
            knifes.get(i).onResume();
        }
        for (int i = 0; i < grenades.size; i++) {
            grenades.get(i).onResume();
        }
        for (int i = 0; i < rockets.size; i++) {
            rockets.get(i).onResume();
        }
        for (int i = 0; i < dynamites.size; i++) {
            dynamites.get(i).onResume();
        }
    }

    /**
     * Initializes the game world.
     * @param gameMode game mode (easy, medium or hard)
     */
    public void createWorld(GameMode gameMode) {
        Fighter fighter = game.gameObjectPools.fighterPool.obtain();
        fighter.init(
                Constants.PLAYER_X_POSITION,
                Constants.PLAYER_Y_POSITION,
                0, new Vector2(0, 0),
                Constants.MAX_HEALTH_PLAYER,
                Constants.DAMAGE_PLAYER);
        aboveWall = game.gameObjectPools.wallPool.obtain();
        aboveWall.init(
                0, Constants.SCREEN_HEIGHT + Constants.WALL_HEIGHT / 2,
                0, new Vector2(0, 0),
                Constants.MAX_HEALTH_WALL,
                Constants.DAMAGE_WALL);
        belowWall = game.gameObjectPools.wallPool.obtain();
        belowWall.init(
                0, 0 - Constants.WALL_HEIGHT / 2,
                0, new Vector2(0, 0),
                Constants.MAX_HEALTH_WALL,
                Constants.DAMAGE_WALL);
        game.player.fighter = fighter;
        game.player.gameModeStatsManager.reset();
        game.player.gameModeStatsManager.setPriWeaID(game.player.primaryWeapon.ID);
        game.player.gameModeStatsManager.setSecWeaID(game.player.secondaryWeapon.ID);
        gameWorldCreator = new GameWorldCreator(game, gameMode.id);
    }

    /**
     * Updates the game world.
     * @param deltaTime time between frames
     */
    public void update(float deltaTime) {
        if (!game.gameStateManager.getLevelState().equals(LevelState.PAUSED)) {
            box2DWorld.step(deltaTime, 4, 2);
            gameWorldCreator.update();
            game.player.update(deltaTime);
            updateObjects(deltaTime);
            updateEffects(deltaTime);
            spriteSky.setBounds(
                    game.player.fighter.box2DBody.getPosition().x - Constants.PLAYER_X_POS_FROM_LEFT, 0,
                    Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        }
    }

    /**
     * Updates the game objects of the game world. Frees objects back to the pools and
     * removes them from the game object arrays, if needed.
     * @param deltaTime time between frames
     */
    private void updateObjects(float deltaTime) {
        aboveWall.update(deltaTime);
        belowWall.update(deltaTime);
        for (int i = 0; i < particles.size; i++) {
            if (!(particles.get(i)).objectFreed) {
                particles.get(i).update(deltaTime);
            } else {
                if (particles.get(i) instanceof FlamethrowerParticle) {
                    if (((FlamethrowerParticle)particles.get(i)).hit) {
                        game.player.gameModeStatsManager.addToPriWeaAmmunitionsHit();
                    }
                }
                freeParticle(particles.get(i));
                particles.removeIndex(i);
            }
        }
        for (int i = 0; i < blades.size; i++) {
            if (!(blades.get(i)).objectFreed) {
                blades.get(i).update(deltaTime);
            } else {
                if (blades.get(i).hit) {
                    game.player.gameModeStatsManager.addToSecWeaAmmunitionsHit();
                }
                game.gameObjectPools.bladePool.free(blades.get(i));
                blades.removeIndex(i);
            }
        }
        for (int i = 0; i < bullets.size; i++) {
            if (!(bullets.get(i)).objectFreed) {
                bullets.get(i).update(deltaTime);
            } else {
                if (bullets.get(i).hit) {
                    game.player.gameModeStatsManager.addToPriWeaAmmunitionsHit();
                }
                game.gameObjectPools.bulletPool.free(bullets.get(i));
                bullets.removeIndex(i);
            }
        }
        for (int i = 0; i < grenades.size; i++) {
            if (!(grenades.get(i)).objectFreed) {
                grenades.get(i).update(deltaTime);
            } else {
                if (grenades.get(i).hit) {
                    game.player.gameModeStatsManager.addToSecWeaAmmunitionsHit();
                }
                game.gameObjectPools.grenadePool.free(grenades.get(i));
                grenades.removeIndex(i);
            }
        }
        for (int i = 0; i < rockets.size; i++) {
            if (!(rockets.get(i)).objectFreed) {
                rockets.get(i).update(deltaTime);
            } else {
                if (rockets.get(i).hit) {
                    game.player.gameModeStatsManager.addToSecWeaAmmunitionsHit();
                }
                game.gameObjectPools.rocketPool.free(rockets.get(i));
                rockets.removeIndex(i);
            }
        }
        for (int i = 0; i < shots.size; i++) {
            if (!(shots.get(i)).objectFreed) {
                shots.get(i).update(deltaTime);
            } else {
                if (shots.get(i).hit) {
                    game.player.gameModeStatsManager.addToPriWeaAmmunitionsHit();
                }
                game.gameObjectPools.shotPool.free(shots.get(i));
                shots.removeIndex(i);
            }
        }
        for (int i = 0; i < knifes.size; i++) {
            if (!(knifes.get(i)).objectFreed) {
                knifes.get(i).update(deltaTime);
            } else {
                if (knifes.get(i).hit) {
                    game.player.gameModeStatsManager.addToPriWeaAmmunitionsHit();
                }
                game.gameObjectPools.knifePool.free(knifes.get(i));
                knifes.removeIndex(i);
            }
        }
        for (int i = 0; i < dynamites.size; i++) {
            if (!(dynamites.get(i)).objectFreed) {
                dynamites.get(i).update(deltaTime);
            } else {
                if (dynamites.get(i).hit) {
                    game.player.gameModeStatsManager.addToSecWeaAmmunitionsHit();
                }
                game.gameObjectPools.dynamitePool.free(dynamites.get(i));
                dynamites.removeIndex(i);
            }
        }
        for (int i = 0; i < enemyCircles.size; i++) {
            if (!(enemyCircles.get(i)).objectFreed) {
                enemyCircles.get(i).update(deltaTime);
            } else {
                game.gameObjectPools.enemyCirclePool.free(enemyCircles.get(i));
                enemyCircles.removeIndex(i);
            }
        }
        for (int i = 0; i < enemyEllipses.size; i++) {
            if (!(enemyEllipses.get(i)).objectFreed) {
                enemyEllipses.get(i).update(deltaTime);
            } else {
                game.gameObjectPools.enemyEllipsePool.free(enemyEllipses.get(i));
                enemyEllipses.removeIndex(i);
            }
        }
        for (int i = 0; i < enemyPentagons.size; i++) {
            if (!(enemyPentagons.get(i)).objectFreed) {
                enemyPentagons.get(i).update(deltaTime);
            } else {
                game.gameObjectPools.enemyPentagonPool.free(enemyPentagons.get(i));
                enemyPentagons.removeIndex(i);
            }
        }
        for (int i = 0; i < enemyRectangles.size; i++) {
            if (!(enemyRectangles.get(i)).objectFreed) {
                enemyRectangles.get(i).update(deltaTime);
            } else {
                game.gameObjectPools.enemyRectanglePool.free(enemyRectangles.get(i));
                enemyRectangles.removeIndex(i);
            }
        }
        for (int i = 0; i < enemySemicircles.size; i++) {
            if (!(enemySemicircles.get(i)).objectFreed) {
                enemySemicircles.get(i).update(deltaTime);
            } else {
                game.gameObjectPools.enemySemicirclePool.free(enemySemicircles.get(i));
                enemySemicircles.removeIndex(i);
            }
        }
        for (int i = 0; i < enemySquares.size; i++) {
            if (!(enemySquares.get(i)).objectFreed) {
                enemySquares.get(i).update(deltaTime);
            } else {
                game.gameObjectPools.enemySquarePool.free(enemySquares.get(i));
                enemySquares.removeIndex(i);
            }
        }
        for (int i = 0; i < enemyStars.size; i++) {
            if (!(enemyStars.get(i)).objectFreed) {
                enemyStars.get(i).update(deltaTime);
            } else {
                game.gameObjectPools.enemyStarPool.free(enemyStars.get(i));
                enemyStars.removeIndex(i);
            }
        }
        for (int i = 0; i < enemyTriangles.size; i++) {
            if (!(enemyTriangles.get(i)).objectFreed) {
                enemyTriangles.get(i).update(deltaTime);
            } else {
                game.gameObjectPools.enemyTrianglePool.free(enemyTriangles.get(i));
                enemyTriangles.removeIndex(i);
            }
        }
        for (int i = 0; i < enemyCircleAmmunitions.size; i++) {
            if (!(enemyCircleAmmunitions.get(i)).objectFreed) {
                enemyCircleAmmunitions.get(i).update(deltaTime);
            } else {
                game.gameObjectPools.enemyCircleAmmunitionPool.free(enemyCircleAmmunitions.get(i));
                enemyCircleAmmunitions.removeIndex(i);
            }
        }
        for (int i = 0; i < enemyPentagonAmmunitions.size; i++) {
            if (!(enemyPentagonAmmunitions.get(i)).objectFreed) {
                enemyPentagonAmmunitions.get(i).update(deltaTime);
            } else {
                game.gameObjectPools.enemyPentagonAmmunitionPool.free(enemyPentagonAmmunitions.get(i));
                enemyPentagonAmmunitions.removeIndex(i);
            }
        }
        for (int i = 0; i < enemyRectangleAmmunitions.size; i++) {
            if (!(enemyRectangleAmmunitions.get(i)).objectFreed) {
                enemyRectangleAmmunitions.get(i).update(deltaTime);
            } else {
                game.gameObjectPools.enemyRectangleAmmunitionPool.free(enemyRectangleAmmunitions.get(i));
                enemyRectangleAmmunitions.removeIndex(i);
            }
        }
        for (int i = 0; i < enemySemicircleAmmunitions.size; i++) {
            if (!(enemySemicircleAmmunitions.get(i)).objectFreed) {
                enemySemicircleAmmunitions.get(i).update(deltaTime);
            } else {
                game.gameObjectPools.enemySemicircleAmmunitionPool.free(enemySemicircleAmmunitions.get(i));
                enemySemicircleAmmunitions.removeIndex(i);
            }
        }
        for (int i = 0; i < enemyStarAmmunitions.size; i++) {
            if (!(enemyStarAmmunitions.get(i)).objectFreed) {
                enemyStarAmmunitions.get(i).update(deltaTime);
            } else {
                game.gameObjectPools.enemyStarAmmunitionPool.free(enemyStarAmmunitions.get(i));
                enemyStarAmmunitions.removeIndex(i);
            }
        }
        for (int i = 0; i < enemyTriangleAmmunitions.size; i++) {
            if (!(enemyTriangleAmmunitions.get(i)).objectFreed) {
                enemyTriangleAmmunitions.get(i).update(deltaTime);
            } else {
                game.gameObjectPools.enemyTriangleAmmunitionPool.free(enemyTriangleAmmunitions.get(i));
                enemyTriangleAmmunitions.removeIndex(i);
            }
        }
    }

    /**
     * Updates the game effects of the game world. Frees effects back to the pools and
     * removes them from the effect array, if they are completed.
     * @param deltaTime time between frames
     */
    private void updateEffects(float deltaTime) {
        for (int i = 0; i < activeEffects.size; i++) {
            activeEffects.get(i).update(deltaTime);
            if (activeEffects.get(i).isComplete()) {
                activeEffects.get(i).reset();
                activeEffects.get(i).free();
                activeEffects.removeIndex(i);
            }
        }
    }

    /**
     * Draws the background sky.
     * @param spriteBatch sprite batch
     */
    public void drawBackground(SpriteBatch spriteBatch) {
        spriteSky.draw(spriteBatch);
    }

    /**
     * Draws the game objects.
     * @param spriteBatch sprite batch
     */
    public void drawObjects(SpriteBatch spriteBatch) {
        game.player.draw(spriteBatch);
        for (int i = 0; i < blades.size; i++) {
            blades.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < bullets.size; i++) {
            bullets.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < grenades.size; i++) {
            grenades.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < rockets.size; i++) {
            rockets.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < shots.size; i++) {
            shots.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < knifes.size; i++) {
            knifes.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < dynamites.size; i++) {
            dynamites.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < enemyCircles.size; i++) {
            enemyCircles.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < enemyEllipses.size; i++) {
            enemyEllipses.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < enemyPentagons.size; i++) {
            enemyPentagons.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < enemyRectangles.size; i++) {
            enemyRectangles.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < enemySemicircles.size; i++) {
            enemySemicircles.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < enemySquares.size; i++) {
            enemySquares.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < enemyStars.size; i++) {
            enemyStars.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < enemyTriangles.size; i++) {
            enemyTriangles.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < enemyCircleAmmunitions.size; i++) {
            enemyCircleAmmunitions.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < enemyPentagonAmmunitions.size; i++) {
            enemyPentagonAmmunitions.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < enemyRectangleAmmunitions.size; i++) {
            enemyRectangleAmmunitions.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < enemySemicircleAmmunitions.size; i++) {
            enemySemicircleAmmunitions.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < enemyStarAmmunitions.size; i++) {
            enemyStarAmmunitions.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < enemyTriangleAmmunitions.size; i++) {
            enemyTriangleAmmunitions.get(i).draw(spriteBatch);
        }
    }

    /**
     * Draws the game effects.
     * @param spriteBatch sprite batch
     */
    public void drawEffects(SpriteBatch spriteBatch) {
        for (int i = 0; i < activeEffects.size; i++) {
            if (!activeEffects.get(i).isComplete()) {
                activeEffects.get(i).draw(spriteBatch);
            }
        }
    }

    /**
     * When the game is ended and the game world is no longer needed,
     * all game objects and effects are freed back to their pools and
     * the game object and effect arrays are cleared.
     */
    public void clearWorld() {
        game.gameObjectPools.fighterPool.free(game.player.fighter);
        game.gameObjectPools.wallPool.free(aboveWall);
        game.gameObjectPools.wallPool.free(belowWall);
        for (int i = 0; i < particles.size; i++) {
            if (particles.get(i) instanceof FlamethrowerParticle) {
                if (((FlamethrowerParticle)particles.get(i)).hit) {
                    game.player.gameModeStatsManager.addToPriWeaAmmunitionsHit();
                }
            }
            freeParticle(particles.get(i));
            particles.removeIndex(i);
        }
        for (int i = 0; i < blades.size; i++) {
            if (blades.get(i).hit) {
                game.player.gameModeStatsManager.addToSecWeaAmmunitionsHit();
            }
            game.gameObjectPools.bladePool.free(blades.get(i));
            blades.removeIndex(i);
        }
        for (int i = 0; i < bullets.size; i++) {
            if (bullets.get(i).hit) {
                game.player.gameModeStatsManager.addToPriWeaAmmunitionsHit();
            }
            game.gameObjectPools.bulletPool.free(bullets.get(i));
            bullets.removeIndex(i);
        }
        for (int i = 0; i < grenades.size; i++) {
            if (grenades.get(i).hit) {
                game.player.gameModeStatsManager.addToSecWeaAmmunitionsHit();
            }
            game.gameObjectPools.grenadePool.free(grenades.get(i));
            grenades.removeIndex(i);
        }
        for (int i = 0; i < rockets.size; i++) {
            if (rockets.get(i).hit) {
                game.player.gameModeStatsManager.addToSecWeaAmmunitionsHit();
            }
            game.gameObjectPools.rocketPool.free(rockets.get(i));
            rockets.removeIndex(i);
        }
        for (int i = 0; i < shots.size; i++) {
            if (shots.get(i).hit) {
                game.player.gameModeStatsManager.addToPriWeaAmmunitionsHit();
            }
            game.gameObjectPools.shotPool.free(shots.get(i));
            shots.removeIndex(i);
        }
        for (int i = 0; i < knifes.size; i++) {
            if (knifes.get(i).hit) {
                game.player.gameModeStatsManager.addToPriWeaAmmunitionsHit();
            }
            game.gameObjectPools.knifePool.free(knifes.get(i));
            knifes.removeIndex(i);
        }
        for (int i = 0; i < dynamites.size; i++) {
            if (dynamites.get(i).hit) {
                game.player.gameModeStatsManager.addToSecWeaAmmunitionsHit();
            }
            game.gameObjectPools.dynamitePool.free(dynamites.get(i));
            dynamites.removeIndex(i);
        }
        for (int i = 0; i < enemyCircles.size; i++) {
            game.gameObjectPools.enemyCirclePool.free(enemyCircles.get(i));
            enemyCircles.removeIndex(i);
        }
        for (int i = 0; i < enemyEllipses.size; i++) {
            game.gameObjectPools.enemyEllipsePool.free(enemyEllipses.get(i));
            enemyEllipses.removeIndex(i);
        }
        for (int i = 0; i < enemyPentagons.size; i++) {
            game.gameObjectPools.enemyPentagonPool.free(enemyPentagons.get(i));
            enemyPentagons.removeIndex(i);
        }
        for (int i = 0; i < enemyRectangles.size; i++) {
            game.gameObjectPools.enemyRectanglePool.free(enemyRectangles.get(i));
            enemyRectangles.removeIndex(i);
        }
        for (int i = 0; i < enemySemicircles.size; i++) {
            game.gameObjectPools.enemySemicirclePool.free(enemySemicircles.get(i));
            enemySemicircles.removeIndex(i);
        }
        for (int i = 0; i < enemySquares.size; i++) {
            game.gameObjectPools.enemySquarePool.free(enemySquares.get(i));
            enemySquares.removeIndex(i);
        }
        for (int i = 0; i < enemyStars.size; i++) {
            game.gameObjectPools.enemyStarPool.free(enemyStars.get(i));
            enemyStars.removeIndex(i);
        }
        for (int i = 0; i < enemyTriangles.size; i++) {
            game.gameObjectPools.enemyTrianglePool.free(enemyTriangles.get(i));
            enemyTriangles.removeIndex(i);
        }
        for (int i = 0; i < enemyCircleAmmunitions.size; i++) {
            game.gameObjectPools.enemyCircleAmmunitionPool.free(enemyCircleAmmunitions.get(i));
            enemyCircleAmmunitions.removeIndex(i);
        }
        for (int i = 0; i < enemyPentagonAmmunitions.size; i++) {
            game.gameObjectPools.enemyPentagonAmmunitionPool.free(enemyPentagonAmmunitions.get(i));
            enemyPentagonAmmunitions.removeIndex(i);
        }
        for (int i = 0; i < enemyRectangleAmmunitions.size; i++) {
            game.gameObjectPools.enemyRectangleAmmunitionPool.free(enemyRectangleAmmunitions.get(i));
            enemyRectangleAmmunitions.removeIndex(i);
        }
        for (int i = 0; i < enemySemicircleAmmunitions.size; i++) {
            game.gameObjectPools.enemySemicircleAmmunitionPool.free(enemySemicircleAmmunitions.get(i));
            enemySemicircleAmmunitions.removeIndex(i);
        }
        for (int i = 0; i < enemyStarAmmunitions.size; i++) {
            game.gameObjectPools.enemyStarAmmunitionPool.free(enemyStarAmmunitions.get(i));
            enemyStarAmmunitions.removeIndex(i);
        }
        for (int i = 0; i < enemyTriangleAmmunitions.size; i++) {
            game.gameObjectPools.enemyTriangleAmmunitionPool.free(enemyTriangleAmmunitions.get(i));
            enemyTriangleAmmunitions.removeIndex(i);
        }
        freeWeapon(game.player.primaryWeapon);
        freeWeapon(game.player.secondaryWeapon);
        for (int i = 0; i < activeEffects.size; i++) {
            activeEffects.get(i).reset();
            activeEffects.get(i).free();
        }
        activeEffects.clear();
    }

    /**
     * Frees a specific particle to its own pool.
     * @param particle particle
     */
    private void freeParticle(Particle particle) {
        if (particle instanceof FighterFlameParticle)
            game.gameObjectPools.fighterFlameParticlePool.free(((FighterFlameParticle) particle));
        if (particle instanceof FighterExplosionParticle)
            game.gameObjectPools.fighterExplosionParticlePool.free(((FighterExplosionParticle) particle));
        if (particle instanceof GrenadeExplosionParticle)
            game.gameObjectPools.grenadeExplosionParticlePool.free(((GrenadeExplosionParticle) particle));
        if (particle instanceof RocketExplosionParticle)
            game.gameObjectPools.rocketExplosionParticlePool.free(((RocketExplosionParticle) particle));
        if (particle instanceof DynamiteExplosionParticle)
            game.gameObjectPools.dynamiteExplosionParticlePool.free(((DynamiteExplosionParticle) particle));
        if (particle instanceof FlamethrowerParticle)
            game.gameObjectPools.flamethrowerParticlePool.free(((FlamethrowerParticle) particle));
    }

    /**
     * Frees a specific weapon to its own pool.
     * @param weapon weapon
     */
    private void freeWeapon(Weapon weapon) {
        if (weapon instanceof BladeLauncher)
            game.gameObjectPools.bladeLauncherPool.free(((BladeLauncher) weapon));
        if (weapon instanceof DynamiteLauncher)
            game.gameObjectPools.airCannonPool.free(((DynamiteLauncher) weapon));
        if (weapon instanceof Flamethrower)
            game.gameObjectPools.flamethrowerPool.free(((Flamethrower) weapon));
        if (weapon instanceof GrenadeLauncher)
            game.gameObjectPools.grenadeLauncherPool.free(((GrenadeLauncher) weapon));
        if (weapon instanceof MachineGun)
            game.gameObjectPools.machineGunPool.free(((MachineGun) weapon));
        if (weapon instanceof KnifeThrower)
            game.gameObjectPools.swordHolderPool.free(((KnifeThrower) weapon));
        if (weapon instanceof RocketLauncher)
            game.gameObjectPools.rocketLauncherPool.free(((RocketLauncher) weapon));
        if (weapon instanceof Shotgun)
            game.gameObjectPools.shotgunPool.free(((Shotgun) weapon));
    }

    public void addParticleToWorld(Particle particle, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        particles.add(particle);
        particle.init(x, y, angle, velocity, health, damage);
    }

    public void addBladeToWorld(Blade blade, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        blades.add(blade);
        blade.init(x, y, angle, velocity, health, damage);
    }

    public void addBulletToWorld(Bullet bullet, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        bullets.add(bullet);
        bullet.init(x, y, angle, velocity, health, damage);
    }

    public void addGrenadeToWorld(Grenade grenade, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        grenades.add(grenade);
        grenade.init(x, y, angle, velocity, health, damage);
    }

    public void addRocketToWorld(Rocket rocket, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        rockets.add(rocket);
        rocket.init(x, y, angle, velocity, health, damage);
    }

    public void addShotToWorld(Shot shot, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        shots.add(shot);
        shot.init(x, y, angle, velocity, health, damage);
    }

    public void addKnifeToWorld(Knife knife, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        knifes.add(knife);
        knife.init(x, y, angle, velocity, health, damage);
    }

    public void addDynamiteToWorld(Dynamite dynamite, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        dynamites.add(dynamite);
        dynamite.init(x, y, angle, velocity, health, damage);
    }

    public void addEnemyCircleToWorld(EnemyCircle enemyCircle, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        enemyCircles.add(enemyCircle);
        enemyCircle.init(x, y, angle, velocity, health, damage);
    }

    public void addEnemyEllipseToWorld(EnemyEllipse enemyEllipse, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        enemyEllipses.add(enemyEllipse);
        enemyEllipse.init(x, y, angle, velocity, health, damage);
    }

    public void addEnemyPentagonToWorld(EnemyPentagon enemyPentagon, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        enemyPentagons.add(enemyPentagon);
        enemyPentagon.init(x, y, angle, velocity, health, damage);
    }

    public void addEnemyRectangleToWorld(EnemyRectangle enemyRectangle, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        enemyRectangles.add(enemyRectangle);
        enemyRectangle.init(x, y, angle, velocity, health, damage);
    }

    public void addEnemySemicircleToWorld(EnemySemicircle enemySemicircle, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        enemySemicircles.add(enemySemicircle);
        enemySemicircle.init(x, y, angle, velocity, health, damage);
    }

    public void addEnemySquareToWorld(EnemySquare enemySquare, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        enemySquares.add(enemySquare);
        enemySquare.init(x, y, angle, velocity, health, damage);
    }

    public void addEnemyStarToWorld(EnemyStar enemyStar, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        enemyStars.add(enemyStar);
        enemyStar.init(x, y, angle, velocity, health, damage);
    }

    public void addEnemyTriangleToWorld(EnemyTriangle enemyTriangle, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        enemyTriangles.add(enemyTriangle);
        enemyTriangle.init(x, y, angle, velocity, health, damage);
    }

    public void addEnemyCircleAmmunitionToWorld(EnemyCircleAmmunition enemyCircleAmmunition, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        enemyCircleAmmunitions.add(enemyCircleAmmunition);
        enemyCircleAmmunition.init(x, y, angle, velocity, health, damage);
    }

    public void addEnemyPentagonAmmunitionToWorld(EnemyPentagonAmmunition enemyPentagonAmmunition, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        enemyPentagonAmmunitions.add(enemyPentagonAmmunition);
        enemyPentagonAmmunition.init(x, y, angle, velocity, health, damage);
    }

    public void addEnemyRectangleAmmunitionToWorld(EnemyRectangleAmmunition enemyRectangleAmmunition, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        enemyRectangleAmmunitions.add(enemyRectangleAmmunition);
        enemyRectangleAmmunition.init(x, y, angle, velocity, health, damage);
    }

    public void addEnemySemicircleAmmunitionToWorld(EnemySemicircleAmmunition enemySemicircleAmmunition, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        enemySemicircleAmmunitions.add(enemySemicircleAmmunition);
        enemySemicircleAmmunition.init(x, y, angle, velocity, health, damage);
    }

    public void addEnemyStarAmmunitionToWorld(EnemyStarAmmunition enemyStarAmmunition, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        enemyStarAmmunitions.add(enemyStarAmmunition);
        enemyStarAmmunition.init(x, y, angle, velocity, health, damage);
    }

    public void addEnemyTriangleAmmunitionToWorld(EnemyTriangleAmmunition enemyTriangleAmmunition, float x, float y, float angle, Vector2 velocity, float health, float damage) {
        enemyTriangleAmmunitions.add(enemyTriangleAmmunition);
        enemyTriangleAmmunition.init(x, y, angle, velocity, health, damage);
    }

    public void addEffect(ParticleEffectPool.PooledEffect pooledEffect, float x, float y) {
        activeEffects.add(pooledEffect);
        pooledEffect.setPosition(x, y);
        pooledEffect.start();
    }
}