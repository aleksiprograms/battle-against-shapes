package com.aleksiprograms.battleagainstshapes.game_world;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * This class creates the game world in other words creates enemies,
 * when player move forward in the world.
 * The world is created randomly based on difficulty and the world is as long as player can proceed.
 * The world can be understand as a table, where some columns contain enemy/enemies.
 */
class GameWorldCreator {

    private TheGame game;
    private int gameModeId = 0;
    private int lastBuildColumnIndex = 0;
    private String levelColumn = "";
    private int emptyRows = 0;
    private int rowsFromLastEnemyRow = 0;
    private int enemiesInColumn = 1;
    private int enemy = 0;
    private int nextPosition = 0;
    private int[] positions = new int[Constants.TILES_IN_COLUMN];

    GameWorldCreator(TheGame game, int gameModeId) {
        this.game = game;
        this.gameModeId = gameModeId;
        startLevel();
    }

    /**
     * Updates the creation of the world and creates level columns as player move forward.
     */
    void update() {
        int playerXPos = (int) (game.getGameWorld().getPlayer().getFighter()
                .getBox2DBody().getPosition().x * Constants.PPM);
        int currentColumn = (int) ((playerXPos + Constants.SCREEN_WIDTH_PIXEL
                - Constants.PLAYER_X_POS_FROM_LEFT_PIXEL) / Constants.TILE_WIDTH_PIXEL);
        if (currentColumn >= lastBuildColumnIndex) {
            game.getGameScreen().getInGameStatsManager().addToDistance();
            game.getGameScreen().getInGameHud().updateData();
            levelColumn = createColumn();
            for (int y = 0; y < Constants.TILES_IN_COLUMN; y++) {
                createEnemy(levelColumn.charAt(y), currentColumn, y);
            }
            lastBuildColumnIndex++;
        }
    }

    /**
     * Creates empty columns to the start of the world.
     */
    private void startLevel() {
        for (int x = 0; x < Constants.TILES_IN_ROW; x++) {
            for (int y = 0; y < Constants.TILES_IN_COLUMN; y++) {
                createEnemy('-', x, y);
            }
            lastBuildColumnIndex++;
        }
    }

    /**
     * Creates randomly column, which is empty or contains enemies.
     */
    private String createColumn() {
        levelColumn = "";
        if (rowsFromLastEnemyRow < emptyRows) {
            rowsFromLastEnemyRow++;
            for (int y = 0; y < Constants.TILES_IN_COLUMN; y++) {
                levelColumn = levelColumn + "-";
            }
        } else {
            rowsFromLastEnemyRow = 0;
            emptyRows = 15;
            int positionIndex = MathUtils.random(0, 2);
            if (gameModeId == Constants.GAME_MODE_ID_EASY) {
                enemy = MathUtils.random(1, 3);
                enemiesInColumn = 1;
            } else if (gameModeId == Constants.GAME_MODE_ID_MEDIUM) {
                enemy = MathUtils.random(1, 6);
                enemiesInColumn = 2;
            } else if (gameModeId == Constants.GAME_MODE_ID_HARD) {
                enemy = MathUtils.random(1, 8);
                enemiesInColumn = 3;
            }
            int enemiesAddedToColumn = 0;
            if (positionIndex == 0) {
                nextPosition = 7;
            } else if (positionIndex == 1) {
                nextPosition = 13 - ((enemiesInColumn - 1) * 3 + 1) / 2;
            } else if (positionIndex == 2) {
                nextPosition = 19 - 3 * (enemiesInColumn - 1);
            }
            for (int i = 0; i < Constants.TILES_IN_COLUMN; i++) {
                if (i == nextPosition && enemiesAddedToColumn < enemiesInColumn) {
                    positions[i] = 1;
                    enemiesAddedToColumn++;
                    nextPosition += 3;
                } else {
                    positions[i] = 0;
                }
            }
            for (int i = 0; i < Constants.TILES_IN_COLUMN; i++) {
                if (positions[i] == 1) {
                    levelColumn = levelColumn + enemy;
                } else {
                    levelColumn = levelColumn + "-";
                }
            }
        }
        return levelColumn;
    }

    /**
     * Creates a specific enemy to a specific position of the world.
     *
     * @param enemyID enemy
     * @param x       x-position (column)
     * @param y       y-position (row)
     */
    private void createEnemy(char enemyID, float x, float y) {
        PhysicalGameObject enemy = null;
        int maxHealth = 0;
        int damage = 0;
        switch (enemyID) {
            case Constants.LEVEL_CELL_ENEMY_CIRCLE:
                enemy = game.getResources().getGameObjectPools().getEnemyCirclePool().obtain();
                maxHealth = Constants.MAX_HEALTH_ENEMY_CIRCLE;
                damage = Constants.DAMAGE_ENEMY_CIRCLE;
                break;
            case Constants.LEVEL_CELL_ENEMY_ELLIPSE:
                enemy = game.getResources().getGameObjectPools().getEnemyEllipsePool().obtain();
                maxHealth = Constants.MAX_HEALTH_ENEMY_ELLIPSE;
                damage = Constants.DAMAGE_ENEMY_ELLIPSE;
                break;
            case Constants.LEVEL_CELL_ENEMY_PENTAGON:
                enemy = game.getResources().getGameObjectPools().getEnemyPentagonPool().obtain();
                maxHealth = Constants.MAX_HEALTH_ENEMY_PENTAGON;
                damage = Constants.DAMAGE_ENEMY_PENTAGON;
                break;
            case Constants.LEVEL_CELL_ENEMY_RECTANGLE:
                enemy = game.getResources().getGameObjectPools().getEnemyRectanglePool().obtain();
                maxHealth = Constants.MAX_HEALTH_ENEMY_RECTANGLE;
                damage = Constants.DAMAGE_ENEMY_RECTANGLE;
                break;
            case Constants.LEVEL_CELL_ENEMY_SEMICIRCLE:
                enemy = game.getResources().getGameObjectPools().getEnemySemicirclePool().obtain();
                maxHealth = Constants.MAX_HEALTH_ENEMY_SEMICIRCLE;
                damage = Constants.DAMAGE_ENEMY_SEMICIRCLE;
                break;
            case Constants.LEVEL_CELL_ENEMY_SQUARE:
                enemy = game.getResources().getGameObjectPools().getEnemySquarePool().obtain();
                maxHealth = Constants.MAX_HEALTH_ENEMY_SQUARE;
                damage = Constants.DAMAGE_ENEMY_SQUARE;
                break;
            case Constants.LEVEL_CELL_ENEMY_STAR:
                enemy = game.getResources().getGameObjectPools().getEnemyStarPool().obtain();
                maxHealth = Constants.MAX_HEALTH_ENEMY_STAR;
                damage = Constants.DAMAGE_ENEMY_STAR;
                break;
            case Constants.LEVEL_CELL_ENEMY_TRIANGLE:
                enemy = game.getResources().getGameObjectPools().getEnemyTrianglePool().obtain();
                maxHealth = Constants.MAX_HEALTH_ENEMY_TRIANGLE;
                damage = Constants.DAMAGE_ENEMY_TRIANGLE;
                break;
            default:
                break;
        }
        if (enemy != null) {
            game.getGameWorld().addGameObjectToWorld(
                    enemy,
                    x * Constants.TILE_WIDTH + Constants.TILE_WIDTH / 2,
                    y * Constants.TILE_HEIGHT + Constants.TILE_HEIGHT / 2,
                    0,
                    new Vector2(0, 0),
                    maxHealth,
                    damage);
        }
    }
}