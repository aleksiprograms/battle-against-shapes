package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.DrawablePhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.Fighter;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.PhysicalGameObject;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Ammunition;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.ExplosionParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.FighterFlameParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.FlamethrowerParticle;
import com.aleksiprograms.battleagainstshapes.resources.Constants;
import com.aleksiprograms.battleagainstshapes.toolbox.PhysicalDef;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends DrawablePhysicalGameObject {

    protected boolean dead;

    public Enemy(
            TheGame game,
            TextureRegion textureRegion,
            PhysicalDef physicalDef,
            float width,
            float height) {
        super(
                game,
                textureRegion,
                physicalDef,
                width,
                height,
                true);
    }

    @Override
    public void initialize(
            float x,
            float y,
            float angle,
            Vector2 velocity,
            float health,
            float damage) {
        super.initialize(x, y, angle, velocity, health, damage);
        box2DBody.setTransform(x, y, 0);
        dead = false;
    }

    @Override
    public void onContact(
            float damage,
            PhysicalGameObject physicalGameObjectA,
            PhysicalGameObject physicalGameObjectB) {
        super.onContact(damage, physicalGameObjectA, physicalGameObjectB);
        if (health <= 0 && !dead) {
            game.getGameScreen().getInGameStatsManager().addToScore();
            game.getGameScreen().getInGameHud().updateData();

            if (physicalGameObjectA instanceof Fighter ||
                    physicalGameObjectB instanceof Fighter) {
                game.getGameScreen().getInGameStatsManager().addToFighterKills();
            }
            if (physicalGameObjectA instanceof Ammunition ||
                    physicalGameObjectB instanceof Ammunition) {
                if (physicalGameObjectA instanceof Ammunition) {
                    game.getGameScreen().getInGameStatsManager().addToWeaponKills(
                            ((Ammunition) physicalGameObjectA).getWeaponID());
                } else {
                    game.getGameScreen().getInGameStatsManager().addToWeaponKills(
                            ((Ammunition) physicalGameObjectB).getWeaponID());
                }
            }
            if (physicalGameObjectA instanceof FighterFlameParticle ||
                    physicalGameObjectB instanceof FighterFlameParticle) {
                game.getGameScreen().getInGameStatsManager().addToFighterKills();
            }
            if (physicalGameObjectA instanceof FlamethrowerParticle ||
                    physicalGameObjectB instanceof FlamethrowerParticle) {
                game.getGameScreen().getInGameStatsManager().addToWeaponKills(
                        Constants.FLAMETHROWER_ID);
            }
            if (physicalGameObjectA instanceof ExplosionParticle ||
                    physicalGameObjectB instanceof ExplosionParticle) {
                if (physicalGameObjectA instanceof ExplosionParticle) {
                    game.getGameScreen().getInGameStatsManager().addToWeaponKills(
                            ((ExplosionParticle) physicalGameObjectA).getWeaponID());
                } else {
                    game.getGameScreen().getInGameStatsManager().addToWeaponKills(
                            ((ExplosionParticle) physicalGameObjectB).getWeaponID());
                }
            }
        }
    }
}