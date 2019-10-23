package com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.aleksiprograms.battleagainstshapes.toolbox.PhysicalDef;

public abstract class Particle extends GameObject {

    public Particle(
            TheGame game,
            PhysicalDef physicalDef,
            float width,
            float height) {

        super(game, physicalDef, width, height, false);
    }
}