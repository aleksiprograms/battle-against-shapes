package com.aleksiprograms.battleagainstshapes.game_world;

import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Listens collisions between game objects and fires onContact() for both game object.
 */
class GameWorldContactListener implements ContactListener {

    GameWorldContactListener() {
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        int collisionDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        PhysicalGameObject physicalGameObjectA = ((PhysicalGameObject) fixA.getUserData());
        PhysicalGameObject physicalGameObjectB = ((PhysicalGameObject) fixB.getUserData());

        switch (collisionDef) {
            case Constants.CATEGORY_PLAYER | Constants.CATEGORY_LEVEL_END_SENSOR:
            case Constants.CATEGORY_PLAYER | Constants.CATEGORY_COLLECTIBLE:
            case Constants.CATEGORY_PLAYER | Constants.CATEGORY_ENEMY:
            case Constants.CATEGORY_PLAYER | Constants.CATEGORY_ENEMY_AMMUNITION:
            case Constants.CATEGORY_PLAYER | Constants.CATEGORY_GENERAL_PARTICLE:
            case Constants.CATEGORY_PLAYER_AMMUNITION | Constants.CATEGORY_ENEMY:
            case Constants.CATEGORY_PLAYER_AMMUNITION | Constants.CATEGORY_ENEMY_AMMUNITION:
            case Constants.CATEGORY_PLAYER_AMMUNITION | Constants.CATEGORY_LEVEL_WALL:
            case Constants.CATEGORY_ENEMY | Constants.CATEGORY_GENERAL_PARTICLE:
            case Constants.CATEGORY_ENEMY | Constants.CATEGORY_PLAYER_IGNORING_PARTICLE:
            case Constants.CATEGORY_ENEMY_AMMUNITION | Constants.CATEGORY_LEVEL_WALL:
            case Constants.CATEGORY_ENEMY_AMMUNITION | Constants.CATEGORY_GENERAL_PARTICLE:
            case Constants.CATEGORY_ENEMY_AMMUNITION | Constants.CATEGORY_PLAYER_IGNORING_PARTICLE:
            case Constants.CATEGORY_GENERAL_PARTICLE | Constants.CATEGORY_LEVEL_WALL:
            case Constants.CATEGORY_PLAYER_IGNORING_PARTICLE | Constants.CATEGORY_LEVEL_WALL:
                physicalGameObjectA.onContact(
                        physicalGameObjectB.getDamage(),
                        physicalGameObjectA,
                        physicalGameObjectB);
                physicalGameObjectB.onContact(
                        physicalGameObjectA.getDamage(),
                        physicalGameObjectA,
                        physicalGameObjectB);
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }
}