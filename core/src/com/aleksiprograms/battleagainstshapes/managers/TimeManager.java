package com.aleksiprograms.battleagainstshapes.managers;

import com.aleksiprograms.battleagainstshapes.TheGame;

/**
 * Manages the time used in app or in playing.
 */
public class TimeManager {

    private TheGame game;
    private float appTime;
    private float gameTime;

    public TimeManager(TheGame game) {
        this.game = game;
        this.appTime = 0f;
        this.gameTime = 0f;
    }

    public void saveTime() {
        game.getSaveManager().getSaveData().addToAppTime((int) appTime);
        appTime = 0f;
        game.getSaveManager().getSaveData().addToGameTime((int) gameTime);
        gameTime = 0f;
    }

    public void addToAppTime(float deltaTime) {
        appTime += deltaTime;
    }

    public void addToGameTime(float deltaTime) {
        gameTime += deltaTime;
    }
}