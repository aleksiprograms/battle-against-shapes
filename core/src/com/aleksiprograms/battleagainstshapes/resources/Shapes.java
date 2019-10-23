package com.aleksiprograms.battleagainstshapes.resources;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Disposable;

/**
 * Holds physical shapes of game object for box2D.
 */
public class Shapes implements Disposable {

    public PolygonShape fighterShape;
    public PolygonShape tailWingShape;
    public PolygonShape wallShape;
    public PolygonShape levelEndSensorShape;
    public CircleShape bladeShape;
    public PolygonShape dynamiteShape;
    public PolygonShape bulletShape;
    public PolygonShape grenadeShape;
    public PolygonShape knifeShape;
    public PolygonShape rocketShape;
    public CircleShape shotShape;
    public CircleShape particleShape;
    public CircleShape enemyCircleShape;
    public PolygonShape enemyEllipseShape;
    public CircleShape enemyPentagonShape;
    public PolygonShape enemyRectangleShape;
    public PolygonShape enemySemicircleShape;
    public PolygonShape enemySquareShape;
    public CircleShape enemyStarShape;
    public PolygonShape enemyTriangleShape;
    public CircleShape enemyCircleAmmunitionShape;
    public CircleShape enemyPentagonAmmunitionShape;
    public PolygonShape enemyRectangleAmmunitionShape;
    public CircleShape enemySemicircleAmmunitionShape;
    public CircleShape enemyStarAmmunitionShape;
    public PolygonShape enemyTriangleAmmunitionShape;

    public Shapes() {
        Vector2[] vector2;

        fighterShape = new PolygonShape();
        vector2 = new Vector2[]{
                new Vector2(-83f / Constants.PPM, -27f / Constants.PPM),
                new Vector2(-60f / Constants.PPM, -43f / Constants.PPM),
                new Vector2(16f / Constants.PPM, -43f / Constants.PPM),
                new Vector2(88.5f / Constants.PPM, -19f / Constants.PPM),
                new Vector2(88.5f / Constants.PPM, -16f / Constants.PPM),
                new Vector2(1f / Constants.PPM, 17f / Constants.PPM),
                new Vector2(-45f / Constants.PPM, 17f / Constants.PPM),
                new Vector2(-83f / Constants.PPM, -3f / Constants.PPM)
        };
        fighterShape.set(vector2);

        tailWingShape = new PolygonShape();
        vector2 = new Vector2[]{
                new Vector2(-88f / Constants.PPM, 44f / Constants.PPM),
                new Vector2(-76f / Constants.PPM, 44f / Constants.PPM),
                new Vector2(-40.5f / Constants.PPM, 14f / Constants.PPM),
                new Vector2(-73f / Constants.PPM, -3f / Constants.PPM)
        };
        tailWingShape.set(vector2);

        wallShape = new PolygonShape();
        wallShape.setAsBox(Constants.WALL_WIDTH / 2, Constants.WALL_HEIGHT / 2);

        levelEndSensorShape = new PolygonShape();
        levelEndSensorShape.setAsBox(
                Constants.LEVEL_END_SENSOR_WIDTH / 2,
                Constants.LEVEL_END_SENSOR_HEIGHT / 2);

        bladeShape = new CircleShape();
        bladeShape.setRadius(Constants.BLADE_WIDTH / 2);

        dynamiteShape = new PolygonShape();
        dynamiteShape.setAsBox(
                Constants.DYNAMITE_WIDTH / 2,
                Constants.DYNAMITE_HEIGHT / 2);

        bulletShape = new PolygonShape();
        bulletShape.setAsBox(
                Constants.BULLET_WIDTH / 2,
                Constants.BULLET_HEIGHT / 2);

        grenadeShape = new PolygonShape();
        grenadeShape.setAsBox(
                Constants.GRENADE_WIDTH / 2,
                Constants.GRENADE_HEIGHT / 2);

        knifeShape = new PolygonShape();
        knifeShape.setAsBox(
                Constants.KNIFE_WIDTH / 2,
                Constants.KNIFE_HEIGHT / 2);

        rocketShape = new PolygonShape();
        rocketShape.setAsBox(
                Constants.ROCKET_WIDTH / 2,
                Constants.ROCKET_HEIGHT / 4);

        shotShape = new CircleShape();
        shotShape.setRadius(Constants.SHOT_WIDTH / 2);

        particleShape = new CircleShape();
        particleShape.setRadius(6 / Constants.PPM);

        enemyCircleShape = new CircleShape();
        enemyCircleShape.setRadius(Constants.ENEMY_CIRCLE_WIDTH / 2);

        enemyEllipseShape = new PolygonShape();
        enemyEllipseShape.setAsBox(Constants.ENEMY_ELLIPSE_WIDTH / 2, Constants.ENEMY_ELLIPSE_HEIGHT / 2);

        enemyPentagonShape = new CircleShape();
        enemyPentagonShape.setRadius(Constants.ENEMY_PENTAGON_WIDTH / 2);

        enemyRectangleShape = new PolygonShape();
        enemyRectangleShape.setAsBox(Constants.ENEMY_RECTANGLE_WIDTH / 2, Constants.ENEMY_RECTANGLE_HEIGHT / 2);

        enemySemicircleShape = new PolygonShape();
        enemySemicircleShape.setAsBox(Constants.ENEMY_SEMICIRCLE_WIDTH / 2, Constants.ENEMY_SEMICIRCLE_HEIGHT / 2);

        enemySquareShape = new PolygonShape();
        enemySquareShape.setAsBox(Constants.ENEMY_SQUARE_WIDTH / 2, Constants.ENEMY_SQUARE_HEIGHT / 2);

        enemyStarShape = new CircleShape();
        enemyStarShape.setRadius(Constants.ENEMY_STAR_WIDTH / 2);

        enemyTriangleShape = new PolygonShape();
        vector2 = new Vector2[]{
                new Vector2(Constants.ENEMY_TRIANGLE_WIDTH / 2, Constants.ENEMY_TRIANGLE_WIDTH / 2),
                new Vector2(-Constants.ENEMY_TRIANGLE_WIDTH / 2, 0),
                new Vector2(Constants.ENEMY_TRIANGLE_WIDTH / 2, -Constants.ENEMY_TRIANGLE_WIDTH / 2)
        };
        enemyTriangleShape.set(vector2);

        enemyCircleAmmunitionShape = new CircleShape();
        enemyCircleAmmunitionShape.setRadius(Constants.ENEMY_CIRCLE_AMMUNITION_WIDTH / 2);

        enemyPentagonAmmunitionShape = new CircleShape();
        enemyPentagonAmmunitionShape.setRadius(Constants.ENEMY_PENTAGON_AMMUNITION_WIDTH / 2);

        enemyRectangleAmmunitionShape = new PolygonShape();
        enemyRectangleAmmunitionShape.setAsBox(Constants.ENEMY_RECTANGLE_AMMUNITION_WIDTH / 2, Constants.ENEMY_RECTANGLE_AMMUNITION_HEIGHT / 2);

        enemySemicircleAmmunitionShape = new CircleShape();
        enemySemicircleAmmunitionShape.setRadius(Constants.ENEMY_SEMICIRCLE_AMMUNITION_WIDTH / 2);

        enemyStarAmmunitionShape = new CircleShape();
        enemyStarAmmunitionShape.setRadius(Constants.ENEMY_STAR_AMMUNITION_WIDTH / 2);

        enemyTriangleAmmunitionShape = new PolygonShape();
        vector2 = new Vector2[]{
                new Vector2(Constants.ENEMY_TRIANGLE_AMMUNITION_WIDTH / 2, Constants.ENEMY_TRIANGLE_AMMUNITION_WIDTH / 2),
                new Vector2(-Constants.ENEMY_TRIANGLE_AMMUNITION_WIDTH / 2, 0),
                new Vector2(Constants.ENEMY_TRIANGLE_AMMUNITION_WIDTH / 2, -Constants.ENEMY_TRIANGLE_AMMUNITION_WIDTH / 2)
        };
        enemyTriangleAmmunitionShape.set(vector2);
    }

    @Override
    public void dispose() {
        fighterShape.dispose();
        tailWingShape.dispose();
        wallShape.dispose();
        levelEndSensorShape.dispose();
        bladeShape.dispose();
        dynamiteShape.dispose();
        bulletShape.dispose();
        grenadeShape.dispose();
        knifeShape.dispose();
        rocketShape.dispose();
        shotShape.dispose();
        particleShape.dispose();
        enemyCircleShape.dispose();
        enemyEllipseShape.dispose();
        enemyPentagonShape.dispose();
        enemyRectangleShape.dispose();
        enemySemicircleShape.dispose();
        enemySquareShape.dispose();
        enemyStarShape.dispose();
        enemyTriangleShape.dispose();
        enemyPentagonAmmunitionShape.dispose();
        enemyRectangleAmmunitionShape.dispose();
        enemySemicircleAmmunitionShape.dispose();
        enemyTriangleAmmunitionShape.dispose();
    }
}