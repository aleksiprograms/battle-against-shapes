package com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.toolbox.PhysicalDef;

public abstract class Particle extends PhysicalGameObject {

    public Particle(
            TheGame game,
            PhysicalDef physicalDef,
            float width,
            float height) {
        super(game, physicalDef, width, height, false);
    }
}