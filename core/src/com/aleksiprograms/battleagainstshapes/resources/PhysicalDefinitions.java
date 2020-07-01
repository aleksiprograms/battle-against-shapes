package com.aleksiprograms.battleagainstshapes.resources;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.toolbox.PhysicalDef;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;

/**
 * Holds all physical definitions of the game objects
 * for example linear damping, density and friction.
 */
public class PhysicalDefinitions {

    private PhysicalDef fighterPhyDef;
    private PhysicalDef invisibleWallPhyDef;
    private PhysicalDef bladePhyDef;
    private PhysicalDef dynamitePhyDef;
    private PhysicalDef bulletPhyDef;
    private PhysicalDef grenadePhyDef;
    private PhysicalDef knifePhyDef;
    private PhysicalDef rocketPhyDef;
    private PhysicalDef shotPhyDef;
    private PhysicalDef flamethrowerParticlePhyDef;
    private PhysicalDef explosionParticlePhyDef;
    private PhysicalDef fighterExplosionParticlePhyDef;
    private PhysicalDef fighterFlameParticlePhyDef;
    private PhysicalDef enemyCirclePhyDef;
    private PhysicalDef enemyEllipsePhyDef;
    private PhysicalDef enemyPentagonPhyDef;
    private PhysicalDef enemyRectanglePhyDef;
    private PhysicalDef enemySemicirclePhyDef;
    private PhysicalDef enemySquarePhyDef;
    private PhysicalDef enemyStarPhyDef;
    private PhysicalDef enemyTrianglePhyDef;
    private PhysicalDef enemyCircleAmmunitionPhyDef;
    private PhysicalDef enemyPentagonAmmunitionPhyDef;
    private PhysicalDef enemyRectangleAmmunitionPhyDef;
    private PhysicalDef enemySemicircleAmmunitionPhyDef;
    private PhysicalDef enemyStarAmmunitionPhyDef;
    private PhysicalDef enemyTriangleAmmunitionPhyDef;

    public PhysicalDefinitions(TheGame game) {
        initialize(game);
    }

    private void initialize(TheGame game) {
        Array<BodyDef> bodyDefs = new Array<BodyDef>();
        Array<FixtureDef> fixtureDefs = new Array<FixtureDef>();

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 3.5f;
        bodyDefs.peek().angularDamping = 2.5f;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getFighterShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_PLAYER;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_PLAYER;
        fixtureDefs.peek().density = 1.5f;
        fixtureDefs.peek().friction = 0.1f;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        fighterPhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.StaticBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 0;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getInvisibleWallShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_LEVEL_WALL;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_LEVEL_WALL;
        fixtureDefs.peek().density = 0;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        invisibleWallPhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 0;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = false;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getBladeShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_PLAYER_AMMUNITION;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_PLAYER_AMMUNITION;
        fixtureDefs.peek().density = 30;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = true;
        bladePhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 0;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = false;
        bodyDefs.peek().gravityScale = 1;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getDynamiteShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_PLAYER_AMMUNITION;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_PLAYER_AMMUNITION;
        fixtureDefs.peek().density = 3;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        dynamitePhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 0;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getBulletShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_PLAYER_AMMUNITION;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_PLAYER_AMMUNITION;
        fixtureDefs.peek().density = 3;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        bulletPhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 0;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getGrenadeShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_PLAYER_AMMUNITION;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_PLAYER_AMMUNITION;
        fixtureDefs.peek().density = 3;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        grenadePhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 0;
        bodyDefs.peek().angularDamping = 0.5f;
        bodyDefs.peek().fixedRotation = false;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getKnifeShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_PLAYER_AMMUNITION;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_PLAYER_AMMUNITION;
        fixtureDefs.peek().density = 1;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = true;
        knifePhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 0;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getRocketShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_PLAYER_AMMUNITION;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_PLAYER_AMMUNITION;
        fixtureDefs.peek().density = 3;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        rocketPhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 0;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getShotShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_PLAYER_AMMUNITION;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_PLAYER_AMMUNITION;
        fixtureDefs.peek().density = 3;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        shotPhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 0;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getParticleShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_PLAYER_IGNORING_PARTICLE;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_PLAYER_IGNORING_PARTICLE;
        fixtureDefs.peek().density = 1;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        flamethrowerParticlePhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 30;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getParticleShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_GENERAL_PARTICLE;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_GENERAL_PARTICLE;
        fixtureDefs.peek().density = 150;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        explosionParticlePhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 30;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getParticleShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_PLAYER_IGNORING_PARTICLE;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_PLAYER_IGNORING_PARTICLE;
        fixtureDefs.peek().density = 150;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        fighterExplosionParticlePhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 0;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getParticleShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_PLAYER_IGNORING_PARTICLE;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_PLAYER_IGNORING_PARTICLE;
        fixtureDefs.peek().density = 1;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        fighterFlameParticlePhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 1;
        bodyDefs.peek().angularDamping = 1;
        bodyDefs.peek().fixedRotation = false;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getEnemyCircleShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_ENEMY;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_ENEMY;
        fixtureDefs.peek().density = 1;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        enemyCirclePhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 1;
        bodyDefs.peek().angularDamping = 1;
        bodyDefs.peek().fixedRotation = false;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getEnemyEllipseShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_ENEMY;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_ENEMY;
        fixtureDefs.peek().density = 1;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        enemyEllipsePhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 1;
        bodyDefs.peek().angularDamping = 1;
        bodyDefs.peek().fixedRotation = false;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getEnemyPentagonShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_ENEMY;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_ENEMY;
        fixtureDefs.peek().density = 1;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        enemyPentagonPhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 1;
        bodyDefs.peek().angularDamping = 1;
        bodyDefs.peek().fixedRotation = false;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getEnemyRectangleShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_ENEMY;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_ENEMY;
        fixtureDefs.peek().density = 1;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        enemyRectanglePhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 1;
        bodyDefs.peek().angularDamping = 1;
        bodyDefs.peek().fixedRotation = false;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getEnemySemicircleShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_ENEMY;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_ENEMY;
        fixtureDefs.peek().density = 1;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        enemySemicirclePhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 1;
        bodyDefs.peek().angularDamping = 1;
        bodyDefs.peek().fixedRotation = false;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getEnemySquareShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_ENEMY;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_ENEMY;
        fixtureDefs.peek().density = 1;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        enemySquarePhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 1;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = false;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getEnemyStarShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_ENEMY;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_ENEMY;
        fixtureDefs.peek().density = 1;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        enemyStarPhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 1;
        bodyDefs.peek().angularDamping = 1;
        bodyDefs.peek().fixedRotation = false;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getEnemyTriangleShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_ENEMY;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_ENEMY;
        fixtureDefs.peek().density = 1;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        enemyTrianglePhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 5;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getEnemyCircleAmmunitionShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_ENEMY_AMMUNITION;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_ENEMY_AMMUNITION;
        fixtureDefs.peek().density = 0.1f;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        enemyCircleAmmunitionPhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 5;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getEnemyPentagonAmmunitionShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_ENEMY_AMMUNITION;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_ENEMY_AMMUNITION;
        fixtureDefs.peek().density = 0.1f;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        enemyPentagonAmmunitionPhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 0;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getEnemyRectangleAmmunitionShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_ENEMY_AMMUNITION;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_ENEMY_AMMUNITION;
        fixtureDefs.peek().density = 0.1f;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        enemyRectangleAmmunitionPhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 0;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getEnemySemicircleAmmunitionShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_ENEMY_AMMUNITION;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_ENEMY_AMMUNITION;
        fixtureDefs.peek().density = 0.1f;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        enemySemicircleAmmunitionPhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 0;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getEnemyStarAmmunitionShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_ENEMY_AMMUNITION;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_ENEMY_AMMUNITION;
        fixtureDefs.peek().density = 0.1f;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        enemyStarAmmunitionPhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());

        bodyDefs.add(new BodyDef());
        fixtureDefs.add(new FixtureDef());
        bodyDefs.peek().type = BodyDef.BodyType.DynamicBody;
        bodyDefs.peek().position.set(0, 0);
        bodyDefs.peek().linearDamping = 0;
        bodyDefs.peek().angularDamping = 0;
        bodyDefs.peek().fixedRotation = true;
        bodyDefs.peek().gravityScale = 0;
        fixtureDefs.peek().shape = game.getResources().getPhysicalShapes().getEnemyTriangleAmmunitionShape();
        fixtureDefs.peek().filter.categoryBits = Constants.CATEGORY_ENEMY_AMMUNITION;
        fixtureDefs.peek().filter.maskBits = Constants.MASK_ENEMY_AMMUNITION;
        fixtureDefs.peek().density = 0.1f;
        fixtureDefs.peek().friction = 0;
        fixtureDefs.peek().restitution = 0;
        fixtureDefs.peek().isSensor = false;
        enemyTriangleAmmunitionPhyDef = new PhysicalDef(bodyDefs.peek(), fixtureDefs.peek());
    }

    public PhysicalDef getFighterPhyDef() {
        return fighterPhyDef;
    }

    public PhysicalDef getInvisibleWallPhyDef() {
        return invisibleWallPhyDef;
    }

    public PhysicalDef getBladePhyDef() {
        return bladePhyDef;
    }

    public PhysicalDef getDynamitePhyDef() {
        return dynamitePhyDef;
    }

    public PhysicalDef getBulletPhyDef() {
        return bulletPhyDef;
    }

    public PhysicalDef getGrenadePhyDef() {
        return grenadePhyDef;
    }

    public PhysicalDef getKnifePhyDef() {
        return knifePhyDef;
    }

    public PhysicalDef getRocketPhyDef() {
        return rocketPhyDef;
    }

    public PhysicalDef getShotPhyDef() {
        return shotPhyDef;
    }

    public PhysicalDef getFlamethrowerParticlePhyDef() {
        return flamethrowerParticlePhyDef;
    }

    public PhysicalDef getExplosionParticlePhyDef() {
        return explosionParticlePhyDef;
    }

    public PhysicalDef getFighterExplosionParticlePhyDef() {
        return fighterExplosionParticlePhyDef;
    }

    public PhysicalDef getFighterFlameParticlePhyDef() {
        return fighterFlameParticlePhyDef;
    }

    public PhysicalDef getEnemyCirclePhyDef() {
        return enemyCirclePhyDef;
    }

    public PhysicalDef getEnemyEllipsePhyDef() {
        return enemyEllipsePhyDef;
    }

    public PhysicalDef getEnemyPentagonPhyDef() {
        return enemyPentagonPhyDef;
    }

    public PhysicalDef getEnemyRectanglePhyDef() {
        return enemyRectanglePhyDef;
    }

    public PhysicalDef getEnemySemicirclePhyDef() {
        return enemySemicirclePhyDef;
    }

    public PhysicalDef getEnemySquarePhyDef() {
        return enemySquarePhyDef;
    }

    public PhysicalDef getEnemyStarPhyDef() {
        return enemyStarPhyDef;
    }

    public PhysicalDef getEnemyTrianglePhyDef() {
        return enemyTrianglePhyDef;
    }

    public PhysicalDef getEnemyCircleAmmunitionPhyDef() {
        return enemyCircleAmmunitionPhyDef;
    }

    public PhysicalDef getEnemyPentagonAmmunitionPhyDef() {
        return enemyPentagonAmmunitionPhyDef;
    }

    public PhysicalDef getEnemyRectangleAmmunitionPhyDef() {
        return enemyRectangleAmmunitionPhyDef;
    }

    public PhysicalDef getEnemySemicircleAmmunitionPhyDef() {
        return enemySemicircleAmmunitionPhyDef;
    }

    public PhysicalDef getEnemyStarAmmunitionPhyDef() {
        return enemyStarAmmunitionPhyDef;
    }

    public PhysicalDef getEnemyTriangleAmmunitionPhyDef() {
        return enemyTriangleAmmunitionPhyDef;
    }
}