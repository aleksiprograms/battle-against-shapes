package com.aleksiprograms.battleagainstshapes.toolbox;

public class GameMode {

    private int gameModeID;
    private String name;
    private String imageName;
    private int scoreToOneStar;
    private int scoreToTwoStar;
    private int scoreToThreeStar;

    public GameMode(
            int gameModeID,
            String name,
            String imageName,
            int scoreToOneStar,
            int scoreToTwoStar,
            int scoreToThreeStar) {

        this.gameModeID = gameModeID;
        this.name = name;
        this.imageName = imageName;
        this.scoreToOneStar = scoreToOneStar;
        this.scoreToTwoStar = scoreToTwoStar;
        this.scoreToThreeStar = scoreToThreeStar;
    }

    public int getGameModeID() {
        return gameModeID;
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }

    public int getScoreToOneStar() {
        return scoreToOneStar;
    }

    public int getScoreToTwoStar() {
        return scoreToTwoStar;
    }

    public int getScoreToThreeStar() {
        return scoreToThreeStar;
    }
}