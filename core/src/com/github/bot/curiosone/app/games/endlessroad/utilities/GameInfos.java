package com.github.bot.curiosone.app.games.endlessroad.utilities;


/**
 * In this class are stored all the game's most important informations
 * @author Paolo Pierantozzi
 */
public class GameInfos
{
	/**
	 * The last user score
	 */
	public static int lastScore = 0;
	
	/**
	 * The last user distance
	 */
	public static float lastDistance = 0;
	
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
     * The number of the different car sprites stored in the EndlessRoad/Cars folder
     */
    public static final float CARS_SPRITES_AMOUNT = 10;

    /**
     * Here are stored the x values for each lane in the game
     * @author Paolo Pierantozzi
     *
     */
    public enum Lanes
    {

        LEFT(40),
        CENTRE_LEFT((GameInfos.WIDTH/2F) - 100),
        CENTRE_RIGHT((GameInfos.WIDTH/2f)-5),
        RIGHT(GameInfos.WIDTH - 145);

        private float x;

        private Lanes(float x) {this.x = x;}

        public float getX(){return x;}

    }
    
    /**
     * Indicates the game status
     */
    public static boolean gameOver = false;
    
    /**
     * Indicates if the game has been paused
     */
    public static boolean gamePaused = false;
    
    /**
     * Indicates the status of the music loop
     */
    public static boolean loopPlaying = false;
    
    /**
     * Indicates the status of the idle loop
     */
    public static boolean idlePlaying = false;
    
    /**
     * Setting for the music volume
     */
    public static boolean muteMusic = false;
    
    /**
     * Setting for the FX volume
     */
    public static boolean muteFX = false;
    
    /**
     * Indicates if the game has been started from the main menu
     */
    public static boolean startFromMainMenu = true;
    
    /**
     * Indicates if a new score record has been established
     */
    public static boolean newScoreRecord = false;
    
    /**
     * Indicates if a new distance record has been established
     */
    public static boolean newDistanceRecord = false;
    
    
}

