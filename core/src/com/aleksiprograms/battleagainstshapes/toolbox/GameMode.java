package com.aleksiprograms.battleagainstshapes.toolbox;

public class GameMode {

    public int id = 0;
    public String name = "";
    public String imageName;
    public int scoreToOneStar;
    public int scoreToTwoStar;
    public int scoreToThreeStar;

    public GameMode(
            int id,
            String name,
            String imageName,
            int scoreToOneStar,
            int scoreToTwoStar,
            int scoreToThreeStar) {

        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.scoreToOneStar = scoreToOneStar;
        this.scoreToTwoStar = scoreToTwoStar;
        this.scoreToThreeStar = scoreToThreeStar;
    }
}