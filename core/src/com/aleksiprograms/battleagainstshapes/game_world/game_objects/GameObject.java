package com.aleksiprograms.battleagainstshapes.game_world.game_objects;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.toolbox.PhysicalDef;

/**
 * Updates, initializes and holds basic properties of a game object.
 * It also handles what happens to the game object, when it collide with another game object.
 * Game objects are also poolable or reusable for better memory management.
 */
public abstract class GameObject implements Pool.Poolable {

    public TheGame game;
    public Body box2DBody;
    public PhysicalDef physicalDef;
    public float width;
    public float height;
    public boolean breakable;
    public boolean freeObject;
    public boolean objectFreed;
    public float health;
    public float damage;

    public GameObject(TheGame game, PhysicalDef physicalDef, float width, float height, boolean breakable) {
        this.game = game;
        this.physicalDef = physicalDef;
        this.width = width;
        this.height = height;
        this.breakable = breakable;
        freeObject = false;
        objectFreed = false;
        box2DBody = game.gameWorld.box2DWorld.createBody(physicalDef.bodyDef);
        box2DBody.setActive(false);
    }

    /**
     * Called when game is set to paused.
     */
    public void onPause() {}

    /**
     * Called when game resumes.
     */
    public void onResume() {}

    @Override
    public void reset() {
        box2DBody.setActive(false);
    }

    /**
     * Initializes the game object (for example after it is obtained from pool).
     * @param x x-position
     * @param y y-position
     * @param angle angle
     * @param velocity velocity
     * @param health max health
     * @param damage damage it makes to other objects
     */
    public void init(float x, float y, float angle, Vector2 velocity, float health, float damage) {
        box2DBody.setActive(true);
        box2DBody.setTransform(x, y, angle);
        box2DBody.setLinearVelocity(velocity);
        freeObject = false;
        objectFreed = false;
        this.health = health;
        this.damage = damage;
    }

    /**
     * This is called, when the game object collide with another game object.
     * The health of the object is calculated and subclasses of this game object can
     * decide what to do, when the object dies or survives (for example play a sound or
     * display a particle effect).
     * @param damage damage for game object
     * @param gameObjectA one of the colliding game object
     * @param gameObjectB another of the colliding game object
     */
    public void onContact(float damage, GameObject gameObjectA, GameObject gameObjectB) {
        if (breakable) {
            health += damage;
            if (health <= 0) {
                freeObject = true;
            }
        }
    }

    /**
     * Updates the game object. If object is not visible by player, the object is freed to its pool.
     * Every functionally of the game object goes here (for example the shooting of an enemy)
     * @param deltaTime time between frames
     */
    public void update(float deltaTime) {
        if (freeObject && !objectFreed) {
            objectFreed = true;
        }
        if (box2DBody.getPosition().x + (width/ 2) <
                game.player.fighter.box2DBody.getPosition().x - Constants.PLAYER_X_POS_FROM_LEFT - Constants.OFF_SCREEN_MARGIN_LEFT) {
            freeObject = true;
        }
        if (box2DBody.getPosition().x - (width / 2) >
                game.player.fighter.box2DBody.getPosition().x + Constants.SCREEN_WIDTH - Constants.PLAYER_X_POS_FROM_LEFT + Constants.OFF_SCREEN_MARGIN_RIGHT) {
            freeObject = true;
        }
    }
}