package com.github.bot.curiosone.app.games.endlessroad.utilities;

/**
 * In this class are stored all the game's important informations
 * @author Paolo Pierantozzi
 */
public class GameConstants
{
    /**
     * The game width
     */
    public static final int WIDTH = 480;

    /**
     * The game height
     */
    public static final int HEIGHT = 800;

    /**
     * The minimum player's speed
     */
    public static final float MIN_PLAYER_SPEED = 25f;

    /**
     * The maximum player's speed
     */
    public static final float MAX_PLAYER_SPEED = 75f;

    /**
     * Here are stored the x values for each lane in the game
     * @author Paolo Pierantozzi
     *
     */
    public enum Lanes
    {

        LEFT(40),
        CENTRE_LEFT((GameConstants.WIDTH/2F) - 100),
        CENTRE_RIGHT((GameConstants.WIDTH/2f)-5),
        RIGHT(GameConstants.WIDTH - 145);

        private float x;

        private Lanes(float x) {this.x = x;}

        public float getX(){return x;}

    }
}

