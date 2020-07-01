package com.aleksiprograms.battleagainstshapes.game_world;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.DrawablePhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.Fighter;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.InvisibleWall;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Ammunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Blade;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Bullet;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Dynamite;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Grenade;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Knife;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Rocket;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Shot;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyCircle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyCircleAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyEllipse;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyPentagon;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyPentagonAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyRectangle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyRectangleAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemySemicircle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemySemicircleAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemySquare;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyStar;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyStarAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyTriangle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies.EnemyTriangleAmmunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.DynamiteExplosionParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.FighterExplosionParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.FighterFlameParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.FlamethrowerParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.GrenadeExplosionParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.Particle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.RocketExplosionParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.BladeLauncher;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.DynamiteLauncher;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.Flamethrower;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.GrenadeLauncher;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.KnifeThrower;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.MachineGun;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.RocketLauncher;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.Shotgun;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.weapons.Weapon;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.toolbox.GameMode;
import com.aleksiprograms.battleagainstshapes.toolbox.GameState;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Manages the game world.
 * Game objects can be added to the game world, they are updated and drawn and
 * removed from the world or freed to the pool, if needed.
 * Contains also particle effects and same actions are done to them as to
 * game objects (update, draw, delete/free, add).
 */
public class GameWorld {

    private TheGame game;
    private Player player;
    private World box2DWorld;
    private GameWorldCreator gameWorldCreator;
    private Box2DDebugRenderer box2DDebugRenderer;
    private Sprite spriteSky;
    private Array<PhysicalGameObject> physicalGameObjects;
    private Array<ParticleEffectPool.PooledEffect> gameEffects;

    public GameWorld(TheGame game) {
        this.game = game;
        player = new Player(game);
        box2DWorld = new World(new Vector2(0, -9.81f), true);
        box2DWorld.setContactListener(new GameWorldContactListener());
        box2DDebugRenderer = new Box2DDebugRenderer();
        spriteSky = new Sprite(
                game.getResources().getTextureRegionByID(Constants.TEXTURE_BACKGROUND));
        spriteSky.setBounds(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        physicalGameObjects = new Array<PhysicalGameObject>();
        gameEffects = new Array<ParticleEffectPool.PooledEffect>();
    }

    /**
     * Called when the game is paused and mainly pauses the sounds of some weapons.
     */
    public void onPause() {
        for (int i = 0; i < physicalGameObjects.size; i++) {
            physicalGameObjects.get(i).onPause();
        }
    }

    /**
     * Called when the game is resumed from pause and mainly resumes the sounds of some weapons.
     */
    public void onResume() {
        for (int i = 0; i < physicalGameObjects.size; i++) {
            physicalGameObjects.get(i).onResume();
        }
    }

    /**
     * Initializes the game world.
     *
     * @param gameMode game mode (easy, medium or hard)
     */
    public void createWorld(GameMode gameMode) {
        Fighter fighter = game.getResources().getGameObjectPools().getFighterPool().obtain();
        fighter.initialize(
                Constants.PLAYER_X_POSITION,
                Constants.PLAYER_Y_POSITION,
                0, new Vector2(0, 0),
                Constants.MAX_HEALTH_PLAYER,
                Constants.DAMAGE_PLAYER);
        addGameObjectToWorld(
                game.getResources().getGameObjectPools().getInvisibleWallPool().obtain(),
                0, Constants.SCREEN_HEIGHT + Constants.WALL_HEIGHT / 2,
                0, new Vector2(0, 0),
                Constants.MAX_HEALTH_WALL,
                Constants.DAMAGE_WALL);
        addGameObjectToWorld(
                game.getResources().getGameObjectPools().getInvisibleWallPool().obtain(),
                0, 0 - Constants.WALL_HEIGHT / 2,
                0, new Vector2(0, 0),
                Constants.MAX_HEALTH_WALL,
                Constants.DAMAGE_WALL);
        player.setFighter(fighter);
        gameWorldCreator = new GameWorldCreator(game, gameMode.getGameModeID());
    }

    /**
     * Updates the game world.
     *
     * @param deltaTime time between frames
     */
    public void update(float deltaTime) {
        if (!game.getGameScreen().getGameState().equals(GameState.PAUSED)) {
            box2DWorld.step(deltaTime, 4, 2);
            gameWorldCreator.update();
            player.update(deltaTime);
            updateObjects(deltaTime);
            updateEffects(deltaTime);
            spriteSky.setBounds(
                    player.getFighter().getBox2DBody().getPosition().x
                            - Constants.PLAYER_X_POS_FROM_LEFT,
                    0,
                    Constants.SCREEN_WIDTH,
                    Constants.SCREEN_HEIGHT);
        }
    }

    /**
     * Updates the game objects of the game world. Frees objects back to the pools and
     * removes them from the game object arrays, if needed.
     *
     * @param deltaTime time between frames
     */
    private void updateObjects(float deltaTime) {
        for (int i = 0; i < physicalGameObjects.size; i++) {
            if (!(physicalGameObjects.get(i)).isObjectFreed()) {
                physicalGameObjects.get(i).update(deltaTime);
            } else {
                if (physicalGameObjects.get(i) instanceof Ammunition ||
                        physicalGameObjects.get(i) instanceof Particle) {
                    updateWeaponStats(physicalGameObjects.get(i));
                }
                freeGameObject(physicalGameObjects.get(i));
                physicalGameObjects.removeIndex(i);
            }
        }
    }

    private void updateWeaponStats(PhysicalGameObject physicalGameObject) {
        if (physicalGameObject instanceof Ammunition) {
            if (((Ammunition) physicalGameObject).isHit()) {
                game.getGameScreen().getInGameStatsManager()
                        .addToWeaponAmmunitionsHit(((Ammunition) physicalGameObject).getWeaponID());
            }
        }
        if (physicalGameObject instanceof FlamethrowerParticle) {
            if (((FlamethrowerParticle) physicalGameObject).hit) {
                game.getGameScreen().getInGameStatsManager()
                        .addToWeaponAmmunitionsHit(Constants.FLAMETHROWER_ID);
            }
        }
    }

    /**
     * Updates the game effects of the game world. Frees effects back to the pools and
     * removes them from the effect array, if they are completed.
     *
     * @param deltaTime time between frames
     */
    private void updateEffects(float deltaTime) {
        for (int i = 0; i < gameEffects.size; i++) {
            gameEffects.get(i).update(deltaTime);
            if (gameEffects.get(i).isComplete()) {
                gameEffects.get(i).reset();
                gameEffects.get(i).free();
                gameEffects.removeIndex(i);
            }
        }
    }

    /**
     * Draws the background sky.
     *
     * @param spriteBatch sprite batch
     */
    public void drawBackground(SpriteBatch spriteBatch) {
        spriteSky.draw(spriteBatch);
    }

    /**
     * Draws the game objects.
     *
     * @param spriteBatch sprite batch
     */
    public void drawObjects(SpriteBatch spriteBatch) {
        player.draw(spriteBatch);
        for (int i = 0; i < physicalGameObjects.size; i++) {
            if (physicalGameObjects.get(i) instanceof DrawablePhysicalGameObject) {
                ((DrawablePhysicalGameObject) physicalGameObjects.get(i)).draw(spriteBatch);
            }
        }
    }

    /**
     * Draws the game effects.
     *
     * @param spriteBatch sprite batch
     */
    public void drawEffects(SpriteBatch spriteBatch) {
        for (int i = 0; i < gameEffects.size; i++) {
            if (!gameEffects.get(i).isComplete()) {
                gameEffects.get(i).draw(spriteBatch);
            }
        }
    }

    /**
     * When the game is ended and the game world is no longer needed,
     * all game objects and effects are freed back to their pools and
     * the game object and effect arrays are cleared.
     */
    public void clearWorld() {
        game.getResources().getGameObjectPools().getFighterPool().free(player.getFighter());
        for (int i = 0; i < physicalGameObjects.size; i++) {
            freeGameObject(physicalGameObjects.get(i));
        }
        physicalGameObjects.clear();
        freeWeapon(player.getPrimaryWeapon());
        freeWeapon(player.getSecondaryWeapon());
        for (int i = 0; i < gameEffects.size; i++) {
            gameEffects.get(i).reset();
            gameEffects.get(i).free();
        }
        gameEffects.clear();
    }

    /**
     * Frees a specific physicalGameObject to its own pool.
     *
     * @param physicalGameObject physicalGameObject
     */
    private void freeGameObject(PhysicalGameObject physicalGameObject) {
        if (physicalGameObject instanceof InvisibleWall) {
            game.getResources().getGameObjectPools().getInvisibleWallPool()
                    .free(((InvisibleWall) physicalGameObject));
        } else if (physicalGameObject instanceof Blade) {
            game.getResources().getGameObjectPools().getBladePool()
                    .free(((Blade) physicalGameObject));
        } else if (physicalGameObject instanceof Bullet) {
            game.getResources().getGameObjectPools().getBulletPool()
                    .free(((Bullet) physicalGameObject));
        } else if (physicalGameObject instanceof Dynamite) {
            game.getResources().getGameObjectPools().getDynamitePool()
                    .free(((Dynamite) physicalGameObject));
        } else if (physicalGameObject instanceof Grenade) {
            game.getResources().getGameObjectPools().getGrenadePool()
                    .free(((Grenade) physicalGameObject));
        } else if (physicalGameObject instanceof Knife) {
            game.getResources().getGameObjectPools().getKnifePool()
                    .free(((Knife) physicalGameObject));
        } else if (physicalGameObject instanceof Rocket) {
            game.getResources().getGameObjectPools().getRocketPool()
                    .free(((Rocket) physicalGameObject));
        } else if (physicalGameObject instanceof Shot) {
            game.getResources().getGameObjectPools().getShotPool()
                    .free(((Shot) physicalGameObject));
        } else if (physicalGameObject instanceof EnemyCircle) {
            game.getResources().getGameObjectPools().getEnemyCirclePool()
                    .free(((EnemyCircle) physicalGameObject));
        } else if (physicalGameObject instanceof EnemyEllipse) {
            game.getResources().getGameObjectPools().getEnemyEllipsePool()
                    .free(((EnemyEllipse) physicalGameObject));
        } else if (physicalGameObject instanceof EnemyPentagon) {
            game.getResources().getGameObjectPools().getEnemyPentagonPool()
                    .free(((EnemyPentagon) physicalGameObject));
        } else if (physicalGameObject instanceof EnemyRectangle) {
            game.getResources().getGameObjectPools().getEnemyRectanglePool()
                    .free(((EnemyRectangle) physicalGameObject));
        } else if (physicalGameObject instanceof EnemySemicircle) {
            game.getResources().getGameObjectPools().getEnemySemicirclePool()
                    .free(((EnemySemicircle) physicalGameObject));
        } else if (physicalGameObject instanceof EnemySquare) {
            game.getResources().getGameObjectPools().getEnemySquarePool()
                    .free(((EnemySquare) physicalGameObject));
        } else if (physicalGameObject instanceof EnemyStar) {
            game.getResources().getGameObjectPools().getEnemyStarPool()
                    .free(((EnemyStar) physicalGameObject));
        } else if (physicalGameObject instanceof EnemyTriangle) {
            game.getResources().getGameObjectPools().getEnemyTrianglePool()
                    .free(((EnemyTriangle) physicalGameObject));
        } else if (physicalGameObject instanceof EnemyCircleAmmunition) {
            game.getResources().getGameObjectPools().getEnemyCircleAmmunitionPool()
                    .free(((EnemyCircleAmmunition) physicalGameObject));
        } else if (physicalGameObject instanceof EnemyPentagonAmmunition) {
            game.getResources().getGameObjectPools().getEnemyPentagonAmmunitionPool()
                    .free(((EnemyPentagonAmmunition) physicalGameObject));
        } else if (physicalGameObject instanceof EnemyRectangleAmmunition) {
            game.getResources().getGameObjectPools().getEnemyRectangleAmmunitionPool()
                    .free(((EnemyRectangleAmmunition) physicalGameObject));
        } else if (physicalGameObject instanceof EnemySemicircleAmmunition) {
            game.getResources().getGameObjectPools().getEnemySemicircleAmmunitionPool()
                    .free(((EnemySemicircleAmmunition) physicalGameObject));
        } else if (physicalGameObject instanceof EnemyStarAmmunition) {
            game.getResources().getGameObjectPools().getEnemyStarAmmunitionPool()
                    .free(((EnemyStarAmmunition) physicalGameObject));
        } else if (physicalGameObject instanceof EnemyTriangleAmmunition) {
            game.getResources().getGameObjectPools().getEnemyTriangleAmmunitionPool()
                    .free(((EnemyTriangleAmmunition) physicalGameObject));
        } else if (physicalGameObject instanceof FighterFlameParticle) {
            game.getResources().getGameObjectPools().getFighterFlameParticlePool()
                    .free(((FighterFlameParticle) physicalGameObject));
        } else if (physicalGameObject instanceof FighterExplosionParticle) {
            game.getResources().getGameObjectPools().getFighterExplosionParticlePool()
                    .free(((FighterExplosionParticle) physicalGameObject));
        } else if (physicalGameObject instanceof GrenadeExplosionParticle) {
            game.getResources().getGameObjectPools().getGrenadeExplosionParticlePool()
                    .free(((GrenadeExplosionParticle) physicalGameObject));
        } else if (physicalGameObject instanceof RocketExplosionParticle) {
            game.getResources().getGameObjectPools().getRocketExplosionParticlePool()
                    .free(((RocketExplosionParticle) physicalGameObject));
        } else if (physicalGameObject instanceof DynamiteExplosionParticle) {
            game.getResources().getGameObjectPools().getDynamiteExplosionParticlePool()
                    .free(((DynamiteExplosionParticle) physicalGameObject));
        } else if (physicalGameObject instanceof FlamethrowerParticle) {
            game.getResources().getGameObjectPools().getFlamethrowerParticlePool()
                    .free(((FlamethrowerParticle) physicalGameObject));
        }
    }

    /**
     * Frees a specific weapon to its own pool.
     *
     * @param weapon weapon
     */
    private void freeWeapon(Weapon weapon) {
        if (weapon instanceof BladeLauncher) {
            game.getResources().getGameObjectPools().getBladeLauncherPool()
                    .free(((BladeLauncher) weapon));
        } else if (weapon instanceof DynamiteLauncher) {
            game.getResources().getGameObjectPools().getDynamiteLauncherPool()
                    .free(((DynamiteLauncher) weapon));
        } else if (weapon instanceof Flamethrower) {
            game.getResources().getGameObjectPools().getFlamethrowerPool()
                    .free(((Flamethrower) weapon));
        } else if (weapon instanceof GrenadeLauncher) {
            game.getResources().getGameObjectPools().getGrenadeLauncherPool()
                    .free(((GrenadeLauncher) weapon));
        } else if (weapon instanceof MachineGun) {
            game.getResources().getGameObjectPools().getMachineGunPool()
                    .free(((MachineGun) weapon));
        } else if (weapon instanceof KnifeThrower) {
            game.getResources().getGameObjectPools().getKnifeThrowerPool().
                    free(((KnifeThrower) weapon));
        } else if (weapon instanceof RocketLauncher) {
            game.getResources().getGameObjectPools().getRocketLauncherPool()
                    .free(((RocketLauncher) weapon));
        } else if (weapon instanceof Shotgun) {
            game.getResources().getGameObjectPools().getShotgunPool()
                    .free(((Shotgun) weapon));
        }
    }

    public void addGameObjectToWorld(
            PhysicalGameObject physicalGameObject,
            float x,
            float y,
            float angle,
            Vector2 velocity,
            float health,
            float damage) {
        physicalGameObjects.add(physicalGameObject);
        physicalGameObject.initialize(x, y, angle, velocity, health, damage);
    }

    public void addEffectToWorld(
            ParticleEffectPool.PooledEffect pooledEffect,
            float x,
            float y) {
        gameEffects.add(pooledEffect);
        pooledEffect.setPosition(x, y);
        pooledEffect.start();
    }

    public Player getPlayer() {
        return player;
    }

    public World getBox2DWorld() {
        return box2DWorld;
    }

    public Box2DDebugRenderer getBox2DDebugRenderer() {
        return box2DDebugRenderer;
    }
}