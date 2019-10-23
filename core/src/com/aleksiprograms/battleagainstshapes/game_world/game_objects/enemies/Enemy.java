package com.aleksiprograms.battleagainstshapes.game_world.game_objects.enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.aleksiprograms.battleagainstshapes.TheGame;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.DrawableGameObject;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.GameObject;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Blade;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Bullet;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Dynamite;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Grenade;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Knife;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Rocket;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.ammunitions.Shot;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.general_objects.Fighter;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.DynamiteExplosionParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.FighterFlameParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.FlamethrowerParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.GrenadeExplosionParticle;
import com.aleksiprograms.battleagainstshapes.game_world.game_objects.particles.RocketExplosionParticle;
import com.aleksiprograms.battleagainstshapes.toolbox.PhysicalDef;

public abstract class Enemy extends DrawableGameObject {

    public boolean dead;

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
    public void init(float x, float y, float angle, Vector2 velocity, float health, float damage) {
        super.init(x, y, angle, velocity, health, damage);
        box2DBody.setTransform(x, y, 0);
        dead = false;
    }

    @Override
    public void onContact(float damage, GameObject gameObjectA, GameObject gameObjectB) {
        super.onContact(damage, gameObjectA, gameObjectB);
        if (health <= 0 && !dead) {
            game.player.gameModeStatsManager.addToScore();
            game.gameStateManager.setUpdateInGameHud(true);

            if (gameObjectA instanceof Fighter || gameObjectB instanceof Fighter) {
                game.player.gameModeStatsManager.addToFighterKills();
            }
            if (gameObjectA instanceof FighterFlameParticle || gameObjectB instanceof FighterFlameParticle) {
                game.player.gameModeStatsManager.addToFighterKills();
            }
            if (gameObjectA instanceof Bullet || gameObjectB instanceof Bullet) {
                game.player.gameModeStatsManager.addToPriWeaKills();
            }
            if (gameObjectA instanceof Shot || gameObjectB instanceof Shot) {
                game.player.gameModeStatsManager.addToPriWeaKills();
            }
            if (gameObjectA instanceof FlamethrowerParticle || gameObjectB instanceof FlamethrowerParticle) {
                game.player.gameModeStatsManager.addToPriWeaKills();
            }
            if (gameObjectA instanceof Knife || gameObjectB instanceof Knife) {
                game.player.gameModeStatsManager.addToPriWeaKills();
            }
            if (gameObjectA instanceof Grenade || gameObjectB instanceof Grenade) {
                game.player.gameModeStatsManager.addToSecWeaKills();
            }
            if (gameObjectA instanceof GrenadeExplosionParticle || gameObjectB instanceof GrenadeExplosionParticle) {
                game.player.gameModeStatsManager.addToSecWeaKills();
            }
            if (gameObjectA instanceof Rocket || gameObjectB instanceof Rocket) {
                game.player.gameModeStatsManager.addToSecWeaKills();
            }
            if (gameObjectA instanceof RocketExplosionParticle || gameObjectB instanceof RocketExplosionParticle) {
                game.player.gameModeStatsManager.addToSecWeaKills();
            }
            if (gameObjectA instanceof Dynamite || gameObjectB instanceof Dynamite) {
                game.player.gameModeStatsManager.addToSecWeaKills();
            }
            if (gameObjectA instanceof DynamiteExplosionParticle || gameObjectB instanceof DynamiteExplosionParticle) {
                game.player.gameModeStatsManager.addToSecWeaKills();
            }
            if (gameObjectA instanceof Blade || gameObjectB instanceof Blade) {
                game.player.gameModeStatsManager.addToSecWeaKills();
            }
        }
    }
}