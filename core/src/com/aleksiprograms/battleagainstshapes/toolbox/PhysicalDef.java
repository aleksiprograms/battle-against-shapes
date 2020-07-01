package com.aleksiprograms.battleagainstshapes.toolbox;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class PhysicalDef {

    private BodyDef bodyDef;
    private FixtureDef fixtureDef;

    public PhysicalDef(
            BodyDef bodyDef,
            FixtureDef fixtureDef) {
        this.bodyDef = bodyDef;
        this.fixtureDef = fixtureDef;
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }
}