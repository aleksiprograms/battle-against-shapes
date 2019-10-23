package com.aleksiprograms.battleagainstshapes.resources;

import com.badlogic.gdx.math.Vector2;

/**
 * Holds all constants of the game and
 * paths of the textures, sounds and particle effects.
 */
public class Constants {

    private Constants() {}

    public static final float PPM               = 48;
    public static final int SCREEN_WIDTH_PIXEL  = 1280;
    public static final int SCREEN_HEIGHT_PIXEL = 720;
    public static final int TILE_WIDTH_PIXEL    = 30;
    public static final int TILE_HEIGHT_PIXEL   = 30;
    public static final int TILES_IN_ROW        = SCREEN_WIDTH_PIXEL / TILE_WIDTH_PIXEL;
    public static final int TILES_IN_COLUMN     = SCREEN_HEIGHT_PIXEL / TILE_HEIGHT_PIXEL;
    public static final float SCREEN_WIDTH      = SCREEN_WIDTH_PIXEL / PPM;
    public static final float SCREEN_HEIGHT     = SCREEN_HEIGHT_PIXEL / PPM;
    public static final float TILE_WIDTH        = TILE_WIDTH_PIXEL / PPM;
    public static final float TILE_HEIGHT       = TILE_HEIGHT_PIXEL / PPM;
    public static final float PLAYER_X_POS_FROM_LEFT_PIXEL = (int)(SCREEN_WIDTH_PIXEL / 4f);
    public static final float PLAYER_X_POS_FROM_LEFT       = PLAYER_X_POS_FROM_LEFT_PIXEL / PPM;
    public static final float PLAYER_X_POS_FROM_CENTER     = (SCREEN_WIDTH / 2 - PLAYER_X_POS_FROM_LEFT);
    public static final float OFF_SCREEN_MARGIN_LEFT       = 0 * TILE_WIDTH;
    public static final float OFF_SCREEN_MARGIN_RIGHT      = 0 * TILE_WIDTH;

    public static final int   GAME_MODES              = 3;
    public static final float PLAYER_X_POSITION       = PLAYER_X_POS_FROM_LEFT;
    public static final float PLAYER_Y_POSITION       = SCREEN_HEIGHT / 2;
    public static final float FIGHTER_WIDTH           = 180 / PPM;
    public static final float FIGHTER_HEIGHT          = 90 / PPM;
    public static final float WALL_WIDTH              = SCREEN_WIDTH;
    public static final float WALL_HEIGHT             = TILE_HEIGHT;
    public static final float LEVEL_END_SENSOR_WIDTH  = TILE_WIDTH;
    public static final float LEVEL_END_SENSOR_HEIGHT = SCREEN_HEIGHT;

    public static final int BUTTON_WIDTH_DIALOG       = 200;
    public static final int BUTTON_HEIGHT_DIALOG      =  80;
    public static final int BUTTON_WIDTH_IMAGE        =  90;
    public static final int BUTTON_HEIGHT_IMAGE       =  90;
    public static final int BUTTON_WIDTH_WEA_SEL      = 320;
    public static final int BUTTON_HEIGHT_WEA_SEL     =  80;
    public static final int BUTTON_WIDTH_GAME         = 150;
    public static final int BUTTON_HEIGHT_GAME        = 150;

    public static final int PAD_SCREEN = 60;

    public static final int MAX_HEALTH_PLAYER           = 512;
    public static final int MAX_HEALTH_ENEMY_CIRCLE     =  90;
    public static final int MAX_HEALTH_ENEMY_ELLIPSE    =  20;
    public static final int MAX_HEALTH_ENEMY_PENTAGON   =  60;
    public static final int MAX_HEALTH_ENEMY_RECTANGLE  =  60;
    public static final int MAX_HEALTH_ENEMY_SEMICIRCLE =  30;
    public static final int MAX_HEALTH_ENEMY_SQUARE     =  30;
    public static final int MAX_HEALTH_ENEMY_STAR       =  60;
    public static final int MAX_HEALTH_ENEMY_TRIANGLE   =  30;
    public static final int MAX_HEALTH_ENEMY_CIRCLE_AMMUNITION     = 1;
    public static final int MAX_HEALTH_ENEMY_PENTAGON_AMMUNITION   = 1;
    public static final int MAX_HEALTH_ENEMY_RECTANGLE_AMMUNITION  = 1;
    public static final int MAX_HEALTH_ENEMY_SEMICIRCLE_AMMUNITION = 1;
    public static final int MAX_HEALTH_ENEMY_STAR_AMMUNITION       = 1;
    public static final int MAX_HEALTH_ENEMY_TRIANGLE_AMMUNITION   = 1;
    public static final int MAX_HEALTH_BLADE    = 1;
    public static final int MAX_HEALTH_BULLET   = 1;
    public static final int MAX_HEALTH_DYNAMITE = 1;
    public static final int MAX_HEALTH_GRENADE  = 1;
    public static final int MAX_HEALTH_KNIFE    = 1;
    public static final int MAX_HEALTH_ROCKET   = 1;
    public static final int MAX_HEALTH_SHOT     = 1;
    public static final int MAX_HEALTH_EXPLOSION_PARTICLE     = 1000;
    public static final int MAX_HEALTH_FIGHTER_FLAME_PARTICLE = 1000;
    public static final int MAX_HEALTH_FLAMETHROWER_PARTICLE  = 1000;
    public static final int MAX_HEALTH_WALL   = 1;

    public static final int DAMAGE_PLAYER           = -1000;
    public static final int DAMAGE_ENEMY_CIRCLE     = -40;
    public static final int DAMAGE_ENEMY_ELLIPSE    = -40;
    public static final int DAMAGE_ENEMY_PENTAGON   = -40;
    public static final int DAMAGE_ENEMY_RECTANGLE  = -40;
    public static final int DAMAGE_ENEMY_SEMICIRCLE = -40;
    public static final int DAMAGE_ENEMY_SQUARE     = -40;
    public static final int DAMAGE_ENEMY_STAR       = -40;
    public static final int DAMAGE_ENEMY_TRIANGLE   = -40;
    public static final int DAMAGE_ENEMY_CIRCLE_AMMUNITION     = -20;
    public static final int DAMAGE_ENEMY_PENTAGON_AMMUNITION   = -20;
    public static final int DAMAGE_ENEMY_RECTANGLE_AMMUNITION  = -30;
    public static final int DAMAGE_ENEMY_SEMICIRCLE_AMMUNITION = -20;
    public static final int DAMAGE_ENEMY_STAR_AMMUNITION       = -20;
    public static final int DAMAGE_ENEMY_TRIANGLE_AMMUNITION   = -20;
    public static final int DAMAGE_BLADE    = -100;
    public static final int DAMAGE_BULLET   = -10;
    public static final int DAMAGE_DYNAMITE = -100;
    public static final int DAMAGE_GRENADE  = -100;
    public static final int DAMAGE_KNIFE    = -100;
    public static final int DAMAGE_ROCKET   = -100;
    public static final int DAMAGE_SHOT     = -5;
    public static final int DAMAGE_EXPLOSION_PARTICLE     = -50;
    public static final int DAMAGE_FIGHTER_FLAME_PARTICLE = -10;
    public static final int DAMAGE_FLAMETHROWER_PARTICLE  = -8;
    public static final int DAMAGE_WALL   = -1000;

    public static final Vector2 VELOCITY_ENEMY_ELLIPSE    = new Vector2(-3,0);
    public static final Vector2 VELOCITY_ENEMY_PENTAGON   = new Vector2(3,0);
    public static final Vector2 VELOCITY_ENEMY_SQUARE     = new Vector2(3,0);
    public static final Vector2 VELOCITY_ENEMY_CIRCLE_AMMUNITION   = new Vector2(40,0);
    public static final Vector2 VELOCITY_ENEMY_PENTAGON_AMMUNITION   = new Vector2(40,0);
    public static final Vector2 VELOCITY_ENEMY_RECTANGLE_AMMUNITION  = new Vector2(8,0);
    public static final Vector2 VELOCITY_ENEMY_SEMICIRCLE_AMMUNITION = new Vector2(8,0);
    public static final Vector2 VELOCITY_ENEMY_STAR_AMMUNITION       = new Vector2(8,0);
    public static final Vector2 VELOCITY_ENEMY_TRIANGLE_AMMUNITION   = new Vector2(8,0);
    public static final Vector2 VELOCITY_BLADE    = new Vector2(10,0);
    public static final Vector2 VELOCITY_BULLET   = new Vector2(15,0);
    public static final Vector2 VELOCITY_DYNAMITE = new Vector2(20,0);
    public static final Vector2 VELOCITY_GRENADE  = new Vector2(10,0);
    public static final Vector2 VELOCITY_KNIFE    = new Vector2(15,0);
    public static final Vector2 VELOCITY_ROCKET   = new Vector2(10,0);
    public static final Vector2 VELOCITY_SHOT     = new Vector2(15,0);
    public static final Vector2 VELOCITY_FLAMETHROWER_PARTICLE = new Vector2(20,0);

    public static final float FIGHTER_FLAME_X_OFFSET = -93 / PPM;
    public static final float FIGHTER_FLAME_Y_OFFSET = -17 / PPM;

    public static final float MACHINE_GUN_WIDTH         =  44 / PPM;
    public static final float MACHINE_GUN_HEIGHT        =  22 / PPM;
    public static final float MACHINE_GUN_X_OFFSET      =  50 / PPM;
    public static final float MACHINE_GUN_Y_OFFSET      = -19 / PPM;
    public static final float SHOTGUN_WIDTH             =  44 / PPM;
    public static final float SHOTGUN_HEIGHT            =  22 / PPM;
    public static final float SHOTGUN_X_OFFSET          =  50 / PPM;
    public static final float SHOTGUN_Y_OFFSET          = -19 / PPM;
    public static final float FLAMETHROWER_WIDTH        =  46 / PPM;
    public static final float FLAMETHROWER_HEIGHT       =  23 / PPM;
    public static final float FLAMETHROWER_X_OFFSET     =  56 / PPM;
    public static final float FLAMETHROWER_Y_OFFSET     = -22 / PPM;
    public static final float KNIFE_THROWER_WIDTH       =  18 / PPM;
    public static final float KNIFE_THROWER_HEIGHT      =  18 / PPM;
    public static final float KNIFE_THROWER_X_OFFSET    =  60 / PPM;
    public static final float KNIFE_THROWER_Y_OFFSET    = -18 / PPM;
    public static final float GRENADE_LAUNCHER_WIDTH    =  34 / PPM;
    public static final float GRENADE_LAUNCHER_HEIGHT   =  17 / PPM;
    public static final float GRENADE_LAUNCHER_X_OFFSET = -32 / PPM;
    public static final float GRENADE_LAUNCHER_Y_OFFSET = -28 / PPM;
    public static final float ROCKET_LAUNCHER_WIDTH     =  26 / PPM;
    public static final float ROCKET_LAUNCHER_HEIGHT    =  13 / PPM;
    public static final float ROCKET_LAUNCHER_X_OFFSET  = -36 / PPM;
    public static final float ROCKET_LAUNCHER_Y_OFFSET  = -23 / PPM;
    public static final float DYNAMITE_LAUNCHER_WIDTH    =  40 / PPM;
    public static final float DYNAMITE_LAUNCHER_HEIGHT   =  40 / PPM;
    public static final float DYNAMITE_LAUNCHER_X_OFFSET = -10 / PPM;
    public static final float DYNAMITE_LAUNCHER_Y_OFFSET = -32 / PPM;
    public static final float BLADE_LAUNCHER_WIDTH       =  28 / PPM;
    public static final float BLADE_LAUNCHER_HEIGHT      =  28 / PPM;
    public static final float BLADE_LAUNCHER_X_OFFSET    = -10 / PPM;
    public static final float BLADE_LAUNCHER_Y_OFFSET    = -28 / PPM;

    public static final float BULLET_WIDTH      =  10 / PPM;
    public static final float BULLET_HEIGHT     =   5 / PPM;
    public static final float BULLET_X_OFFSET   =  79 / PPM;
    public static final float BULLET_Y_OFFSET   = -19 / PPM;
    public static final float SHOT_WIDTH        =   5 / PPM;
    public static final float SHOT_HEIGHT       =   5 / PPM;
    public static final float SHOT_X_OFFSET     =  91 / PPM;
    public static final float SHOT_Y_OFFSET     = -19 / PPM;
    public static final float FLAME_X_OFFSET    =  75 / PPM;
    public static final float FLAME_Y_OFFSET    = -19 / PPM;
    public static final float KNIFE_WIDTH       =   8 / PPM;
    public static final float KNIFE_HEIGHT      =  64 / PPM;
    public static final float KNIFE_X_OFFSET    =  41 / PPM;
    public static final float KNIFE_Y_OFFSET    = -11 / PPM;
    public static final float GRENADE_WIDTH     =  20 / PPM;
    public static final float GRENADE_HEIGHT    =  10 / PPM;
    public static final float GRENADE_X_OFFSET  =  -3 / PPM;
    public static final float GRENADE_Y_OFFSET  = -28 / PPM;
    public static final float ROCKET_WIDTH      =  40 / PPM;
    public static final float ROCKET_HEIGHT     =  20 / PPM;
    public static final float ROCKET_X_OFFSET   = -34 / PPM;
    public static final float ROCKET_Y_OFFSET   = -31 / PPM;
    public static final float DYNAMITE_WIDTH    =   5 / PPM;
    public static final float DYNAMITE_HEIGHT   =  35 / PPM;
    public static final float DYNAMITE_X_OFFSET = -10 / PPM;
    public static final float DYNAMITE_Y_OFFSET = -26 / PPM;
    public static final float BLADE_WIDTH       =  20 / PPM;
    public static final float BLADE_HEIGHT      =  20 / PPM;
    public static final float BLADE_X_OFFSET    =  -4 / PPM;
    public static final float BLADE_Y_OFFSET    = -28 / PPM;

    public static final float ENEMY_CIRCLE_WIDTH                 =  80 / PPM;
    public static final float ENEMY_CIRCLE_HEIGHT                =  80 / PPM;
    public static final float ENEMY_ELLIPSE_WIDTH                =  30 / PPM;
    public static final float ENEMY_ELLIPSE_HEIGHT               =  60 / PPM;
    public static final float ENEMY_PENTAGON_WIDTH               =  60 / PPM;
    public static final float ENEMY_PENTAGON_HEIGHT              =  60 / PPM;
    public static final float ENEMY_RECTANGLE_WIDTH              = 100 / PPM;
    public static final float ENEMY_RECTANGLE_HEIGHT             =  50 / PPM;
    public static final float ENEMY_SEMICIRCLE_WIDTH             =  40 / PPM;
    public static final float ENEMY_SEMICIRCLE_HEIGHT            =  80 / PPM;
    public static final float ENEMY_SQUARE_WIDTH                 =  40 / PPM;
    public static final float ENEMY_SQUARE_HEIGHT                =  40 / PPM;
    public static final float ENEMY_STAR_WIDTH                   =  80 / PPM;
    public static final float ENEMY_STAR_HEIGHT                  =  80 / PPM;
    public static final float ENEMY_TRIANGLE_WIDTH               =  60 / PPM;
    public static final float ENEMY_TRIANGLE_HEIGHT              =  60 / PPM;
    public static final float ENEMY_CIRCLE_AMMUNITION_WIDTH      =  40 / PPM;
    public static final float ENEMY_CIRCLE_AMMUNITION_HEIGHT     =  40 / PPM;
    public static final float ENEMY_PENTAGON_AMMUNITION_WIDTH    =  30 / PPM;
    public static final float ENEMY_PENTAGON_AMMUNITION_HEIGHT   =  30 / PPM;
    public static final float ENEMY_RECTANGLE_AMMUNITION_WIDTH   =  50 / PPM;
    public static final float ENEMY_RECTANGLE_AMMUNITION_HEIGHT  =  25 / PPM;
    public static final float ENEMY_SEMICIRCLE_AMMUNITION_WIDTH  =  20 / PPM;
    public static final float ENEMY_SEMICIRCLE_AMMUNITION_HEIGHT =  40 / PPM;
    public static final float ENEMY_STAR_AMMUNITION_WIDTH        =  40 / PPM;
    public static final float ENEMY_STAR_AMMUNITION_HEIGHT       =  40 / PPM;
    public static final float ENEMY_TRIANGLE_AMMUNITION_WIDTH    =  30 / PPM;
    public static final float ENEMY_TRIANGLE_AMMUNITION_HEIGHT   =  30 / PPM;

    public static final float FIXED_TIME_STEP = 1.0f / 60.0f;

    public static final int MACHINE_GUN_ID       = 0;
    public static final int GRENADE_LAUNCHER_ID  = 1;
    public static final int SHOTGUN_ID           = 2;
    public static final int ROCKET_LAUNCHER_ID   = 3;
    public static final int FLAMETHROWER_ID      = 4;
    public static final int DYNAMITE_LAUNCHER_ID = 5;
    public static final int KNIFE_THROWER_ID     = 6;
    public static final int BLADE_LAUNCHER_ID    = 7;

    public static final char LEVEL_CELL_EMPTY            = '-';
    public static final char LEVEL_CELL_ENEMY_SQUARE     = '1';
    public static final char LEVEL_CELL_ENEMY_TRIANGLE   = '2';
    public static final char LEVEL_CELL_ENEMY_CIRCLE     = '3';
    public static final char LEVEL_CELL_ENEMY_ELLIPSE    = '4';
    public static final char LEVEL_CELL_ENEMY_SEMICIRCLE = '5';
    public static final char LEVEL_CELL_ENEMY_RECTANGLE  = '6';
    public static final char LEVEL_CELL_ENEMY_PENTAGON   = '7';
    public static final char LEVEL_CELL_ENEMY_STAR       = '8';

    public static final int GAME_MODE_ID_EASY   = 0;
    public static final int GAME_MODE_ID_MEDIUM = 1;
    public static final int GAME_MODE_ID_HARD   = 2;

    public static final short CATEGORY_PLAYER                   = 1;
    public static final short CATEGORY_PLAYER_AMMUNITION        = 2;
    public static final short CATEGORY_ENEMY                    = 4;
    public static final short CATEGORY_ENEMY_AMMUNITION         = 8;
    public static final short CATEGORY_GENERAL_PARTICLE         = 16;
    public static final short CATEGORY_PLAYER_IGNORING_PARTICLE = 32;
    public static final short CATEGORY_LEVEL_WALL               = 64;
    public static final short CATEGORY_COLLECTIBLE              = 128;
    public static final short CATEGORY_LEVEL_END_SENSOR         = 256;

    public static final short MASK_PLAYER =
            CATEGORY_ENEMY | CATEGORY_ENEMY_AMMUNITION | CATEGORY_GENERAL_PARTICLE | CATEGORY_LEVEL_WALL | CATEGORY_COLLECTIBLE | CATEGORY_LEVEL_END_SENSOR;
    public static final short MASK_PLAYER_AMMUNITION =
            CATEGORY_ENEMY | CATEGORY_ENEMY_AMMUNITION | CATEGORY_LEVEL_WALL;
    public static final short MASK_ENEMY =
            CATEGORY_PLAYER | CATEGORY_PLAYER_AMMUNITION | CATEGORY_ENEMY | CATEGORY_GENERAL_PARTICLE | CATEGORY_PLAYER_IGNORING_PARTICLE | CATEGORY_LEVEL_WALL;
    public static final short MASK_ENEMY_AMMUNITION =
            CATEGORY_PLAYER | CATEGORY_PLAYER_AMMUNITION | CATEGORY_GENERAL_PARTICLE | CATEGORY_PLAYER_IGNORING_PARTICLE | CATEGORY_LEVEL_WALL;
    public static final short MASK_GENERAL_PARTICLE =
            CATEGORY_PLAYER | CATEGORY_ENEMY | CATEGORY_ENEMY_AMMUNITION | CATEGORY_LEVEL_WALL;
    public static final short MASK_PLAYER_IGNORING_PARTICLE =
            CATEGORY_ENEMY | CATEGORY_ENEMY_AMMUNITION | CATEGORY_LEVEL_WALL;
    public static final short MASK_LEVEL_WALL =
            CATEGORY_PLAYER | CATEGORY_PLAYER_AMMUNITION | CATEGORY_ENEMY | CATEGORY_ENEMY_AMMUNITION | CATEGORY_GENERAL_PARTICLE | CATEGORY_PLAYER_IGNORING_PARTICLE;
    public static final short MASK_COLLECTIBLE =
            CATEGORY_PLAYER;
    public static final short MASK_LEVEL_END_SENSOR =
            CATEGORY_PLAYER;

    public static final String TEX_SRC_TEXTURE_ATLAS_BAS     = "textures/texture_atlas_bas.atlas";
    public static final String TEX_SRC_FIGHTER               = "fighter";
    public static final String TEX_SRC_BACKGROUND            = "ui_background";
    public static final String TEX_SRC_MACHINE_GUN           = "weapon_machine_gun";
    public static final String TEX_SRC_SHOTGUN               = "weapon_shotgun";
    public static final String TEX_SRC_FLAMETHROWER          = "weapon_flamethrower";
    public static final String TEX_SRC_KNIFE_THROWER         = "weapon_knife_thrower";
    public static final String TEX_SRC_GRENADE_LAUNCHER      = "weapon_grenade_launcher";
    public static final String TEX_SRC_ROCKET_LAUNCHER       = "weapon_rocket_launcher";
    public static final String TEX_SRC_DYNAMITE_LAUNCHER     = "weapon_dynamite_launcher";
    public static final String TEX_SRC_BLADE_LAUNCHER        = "weapon_blade_launcher";
    public static final String TEX_SRC_BULLET                = "ammunition_bullet";
    public static final String TEX_SRC_SHOT                  = "ammunition_shot";
    public static final String TEX_SRC_KNIFE                 = "ammunition_knife";
    public static final String TEX_SRC_GRENADE               = "ammunition_grenade";
    public static final String TEX_SRC_ROCKET                = "ammunition_rocket";
    public static final String TEX_SRC_DYNAMITE              = "ammunition_dynamite";
    public static final String TEX_SRC_BLADE                 = "ammunition_blade";
    public static final String TEX_SRC_ENEMY_TRIANGLE        = "enemy_triangle";
    public static final String TEX_SRC_ENEMY_CIRCLE          = "enemy_circle";
    public static final String TEX_SRC_ENEMY_SQUARE          = "enemy_square";
    public static final String TEX_SRC_ENEMY_RECTANGLE       = "enemy_rectangle";
    public static final String TEX_SRC_ENEMY_ELLIPSE         = "enemy_ellipse";
    public static final String TEX_SRC_ENEMY_PENTAGON        = "enemy_pentagon";
    public static final String TEX_SRC_ENEMY_STAR            = "enemy_star";
    public static final String TEX_SRC_ENEMY_SEMICIRCLE      = "enemy_semicircle";
    public static final String TEX_SRC_SCORE                 = "ui_score";
    public static final String TEX_SRC_DISTANCE              = "ui_distance";
    public static final String TEX_SRC_STAR_UNLOCKED         = "ui_star_unlocked";
    public static final String TEX_SRC_STAR_LOCKED           = "ui_star_locked";
    public static final String TEX_SRC_BUTTON_MOVE_UP_U      = "ui_button_move_up_u";
    public static final String TEX_SRC_BUTTON_MOVE_UP_D      = "ui_button_move_up_d";
    public static final String TEX_SRC_BUTTON_MOVE_DOWN_U    = "ui_button_move_down_u";
    public static final String TEX_SRC_BUTTON_MOVE_DOWN_D    = "ui_button_move_down_d";
    public static final String TEX_SRC_BUTTON_USE_PRI_WEA_U  = "ui_button_use_pri_wea_u";
    public static final String TEX_SRC_BUTTON_USE_PRI_WEA_D  = "ui_button_use_pri_wea_d";
    public static final String TEX_SRC_BUTTON_USE_SEC_WEA_U  = "ui_button_use_sec_wea_u";
    public static final String TEX_SRC_BUTTON_USE_SEC_WEA_D  = "ui_button_use_sec_wea_d";
    public static final String TEX_SRC_BUTTON_STATS_U        = "ui_button_stats_u";
    public static final String TEX_SRC_BUTTON_STATS_D        = "ui_button_stats_d";
    public static final String TEX_SRC_BUTTON_SETTINGS_U     = "ui_button_settings_u";
    public static final String TEX_SRC_BUTTON_SETTINGS_D     = "ui_button_settings_d";
    public static final String TEX_SRC_BUTTON_CREDITS_U      = "ui_button_credits_u";
    public static final String TEX_SRC_BUTTON_CREDITS_D      = "ui_button_credits_d";
    public static final String TEX_SRC_BUTTON_PLAY_U         = "ui_button_play_u";
    public static final String TEX_SRC_BUTTON_PLAY_D         = "ui_button_play_d";
    public static final String TEX_SRC_BUTTON_HOME_U         = "ui_button_home_u";
    public static final String TEX_SRC_BUTTON_HOME_D         = "ui_button_home_d";
    public static final String TEX_SRC_BUTTON_PAUSE_U        = "ui_button_pause_u";
    public static final String TEX_SRC_BUTTON_PAUSE_D        = "ui_button_pause_d";
    public static final String TEX_SRC_BUTTON_REPLAY_U       = "ui_button_replay_u";
    public static final String TEX_SRC_BUTTON_REPLAY_D       = "ui_button_replay_d";
    public static final String TEX_SRC_RECTANGLE_BLACK       = "ui_rectangle_black";
    public static final String TEX_SRC_RECTANGLE_GREY        = "ui_rectangle_grey";
    public static final String TEX_SRC_RECTANGLE_BLUE        = "ui_rectangle_blue";
    public static final String TEX_SRC_KNOB                  = "ui_knob";
    public static final String TEX_SRC_HEALTH_BAR_CONDITION  = "ui_health_bar_condition";
    public static final String TEX_SRC_HEALTH_BAR_BACKGROUND = "ui_health_bar_background";
    public static final String TEX_SRC_HEALTH_BAR_END        = "ui_health_bar_end";
    public static final String TEX_SRC_GMAE_MODE_EASY        = "game_mode_easy";
    public static final String TEX_SRC_GMAE_MODE_MEDIUM      = "game_mode_medium";
    public static final String TEX_SRC_GMAE_MODE_HARD        = "game_mode_hard";

    public static final String SOUND_SRC_BUTTON_POS        = "sounds/button_pos.wav";
    public static final String SOUND_SRC_BUTTON_NEG        = "sounds/button_neg.wav";
    public static final String SOUND_SRC_ENEMY_HIT         = "sounds/enemy_hit.wav";
    public static final String SOUND_SRC_ENEMY_EXPLOSION   = "sounds/enemy_explosion.wav";
    public static final String SOUND_SRC_ENEMY_AMMUNITION  = "sounds/enemy_ammunition.wav";
    public static final String SOUND_SRC_BULLET            = "sounds/bullet.wav";
    public static final String SOUND_SRC_SHOT              = "sounds/shot.wav";
    public static final String SOUND_SRC_KNIFE             = "sounds/knife.wav";
    public static final String SOUND_SRC_GRENADE           = "sounds/grenade.wav";
    public static final String SOUND_SRC_ROCKET            = "sounds/rocket.wav";
    public static final String SOUND_SRC_DYNAMITE_A        = "sounds/dynamite_a.wav";
    public static final String SOUND_SRC_DYNAMITE_B        = "sounds/dynamite_b.wav";
    public static final String SOUND_SRC_DYNAMITE_C        = "sounds/dynamite_c.wav";
    public static final String SOUND_SRC_BLADE             = "sounds/blade.wav";
    public static final String SOUND_SRC_EXPLOSION_SMALL_A = "sounds/explosion_small_a.wav";
    public static final String SOUND_SRC_EXPLOSION_SMALL_B = "sounds/explosion_small_b.wav";
    public static final String SOUND_SRC_EXPLOSION_SMALL_C = "sounds/explosion_small_c.wav";
    public static final String SOUND_SRC_EXPLOSION_BIG_A   = "sounds/explosion_big_a.wav";
    public static final String SOUND_SRC_EXPLOSION_BIG_B   = "sounds/explosion_big_b.wav";
    public static final String SOUND_SRC_EXPLOSION_BIG_C   = "sounds/explosion_big_c.wav";

    public static final String PE_SRC_FIGHTER_EXPLOSION          = "particle_effects/explosion_fighter.p";
    public static final String PE_SRC_FIGHTER_FLAME              = "particle_effects/fighter_flame.p";
    public static final String PE_SRC_GRENADE_EXPLOSION          = "particle_effects/explosion_grenade.p";
    public static final String PE_SRC_ROCKET_EXPLOSION           = "particle_effects/explosion_rocket.p";
    public static final String PE_SRC_DYNAMITE_EXPLOSION         = "particle_effects/explosion_dynamite.p";
    public static final String PE_SRC_GRENADE_SMOKE              = "particle_effects/weapon_grenade_smoke.p";
    public static final String PE_SRC_ROCKET_SMOKE               = "particle_effects/weapon_rocket_smoke.p";
    public static final String PE_SRC_DYNAMITE_FLAME             = "particle_effects/weapon_dynamite_flame.p";
    public static final String PE_SRC_ENEMY_CIRCLE_EXPLOSION     = "particle_effects/enemy_circle_explosion.p";
    public static final String PE_SRC_ENEMY_ELLIPSE_EXPLOSION    = "particle_effects/enemy_ellipse_explosion.p";
    public static final String PE_SRC_ENEMY_PENTAGON_EXPLOSION   = "particle_effects/enemy_pentagon_explosion.p";
    public static final String PE_SRC_ENEMY_RECTANGLE_EXPLOSION  = "particle_effects/enemy_rectangle_explosion.p";
    public static final String PE_SRC_ENEMY_SEMICIRCLE_EXPLOSION = "particle_effects/enemy_semicircle_explosion.p";
    public static final String PE_SRC_ENEMY_SQUARE_EXPLOSION     = "particle_effects/enemy_square_explosion.p";
    public static final String PE_SRC_ENEMY_STAR_EXPLOSION       = "particle_effects/enemy_star_explosion.p";
    public static final String PE_SRC_ENEMY_TRIANGLE_EXPLOSION   = "particle_effects/enemy_triangle_explosion.p";
    public static final String PE_SRC_ENEMY_CIRCLE_HIT           = "particle_effects/enemy_circle_hit.p";
    public static final String PE_SRC_ENEMY_ELLIPSE_HIT          = "particle_effects/enemy_ellipse_hit.p";
    public static final String PE_SRC_ENEMY_PENTAGON_HIT         = "particle_effects/enemy_pentagon_hit.p";
    public static final String PE_SRC_ENEMY_RECTANGLE_HIT        = "particle_effects/enemy_rectangle_hit.p";
    public static final String PE_SRC_ENEMY_SEMICIRCLE_HIT       = "particle_effects/enemy_semicircle_hit.p";
    public static final String PE_SRC_ENEMY_SQUARE_HIT           = "particle_effects/enemy_square_hit.p";
    public static final String PE_SRC_ENEMY_STAR_HIT             = "particle_effects/enemy_star_hit.p";
    public static final String PE_SRC_ENEMY_TRIANGLE_HIT         = "particle_effects/enemy_triangle_hit.p";
}