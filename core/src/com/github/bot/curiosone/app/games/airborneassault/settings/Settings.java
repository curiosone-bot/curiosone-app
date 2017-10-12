package com.github.bot.curiosone.app.games.airborneassault.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * @author Alessandro Roic
 * Contais the game settings and constants
 */
public class Settings {
    private static Settings settings;
    private static Preferences prefs = Gdx.app.getPreferences("My Preferences");
    private boolean MUSIC,SFX; //the only data to save into Preferences
    //default settings and constants
    private long SPAWN_RATE = 1000000000;
    private int ACCELERATION = 0;
    private int SCORE = 0;

    private Settings(){
      MUSIC = prefs.getBoolean("MUSIC");
      SFX = prefs.getBoolean("SFX");
    }

    /**
     * @return the settings istance
     */
      public static Settings getIstance(){
        if(settings == null){settings = new Settings();}
        return settings;
      }

    /**
     * Sets the music to the @param bool and
     * save it into preferences
     */
      public void setMUSIC(boolean bool) {
        prefs.putBoolean("MUSIC",bool);
        prefs.flush();
        settings.MUSIC = bool;
      }
    /**
     * Sets the sfx to the @param bool and
     * save it into preferences
     */
      public void setSFX(boolean bool) {
        prefs.putBoolean("SFX",bool);
        prefs.flush();
        settings.SFX = bool;
      }

    /**
     * Saves the score in memory.
     */
    public void saveScore(){
        if(prefs.getInteger("SCORE")<SCORE) {
          prefs.putInteger("SCORE", SCORE);
          prefs.flush();
        }
      }

    /**
     * Resets the current score to 0
     */
    public void resetScore(){
        SCORE = 0;
      }

    /**
     * Resets the current acceleration to 0
     */
    public void resetAcceleration(){
        ACCELERATION = 0;
      }

    /**
     * Adds
     * @param points to the score
     */
    public void addScore(Points points){
        SCORE += points.getPoints();
      }

    /**
     * @return the current score
     */
    public int getScore() {
        return SCORE;
      }

    /**
     * @return the saved score
     */
    public int getSavedScore(){
        return prefs.getInteger("SCORE");
      }

    /**
     * @return the spawn rate
     */
    public long getSpawnRate() {
        return SPAWN_RATE;
      }

    /**
     * @return the current acceleration
     */
    public int getAccelleration() {
        return ACCELERATION;
      }

    /**
     * Adds
     * @param amount of acceleration to the current one
     */
    public void addAccelleration(int amount){ACCELERATION+=amount;}

    /**
     * @return MUSIC
     */
    public boolean isMUSIC() {
        return MUSIC;
      }

    /**
     * @return SFX
     */
    public boolean isSFX() {
        return SFX;
      }
}
