package com.aleksiprograms.battleagainstshapes.toolbox;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class PhysicalDef {

    public BodyDef bodyDef;
    public FixtureDef fixtureDef;

    public PhysicalDef(
            BodyDef bodyDef,
            FixtureDef fixtureDef) {

        this.bodyDef = bodyDef;
        this.fixtureDef = fixtureDef;
    }
}