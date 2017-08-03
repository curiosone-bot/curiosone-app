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
    public  boolean MUSIC,SFX; //the only data to save into Preferences
    //default settings and constants
    public long SPAWN_RATE = 1500000000;
    public int ACCELERATION = 0;
    public static int SCORE = 0;

    public Settings(){
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
  public static void setMUSIC(boolean bool) {
      prefs.putBoolean("MUSIC",bool);
      prefs.flush();
      settings.MUSIC = bool;
    }
  /**
   * Sets the sfx to the @param bool and
   * save it into preferences
   */
    public static void setSFX(boolean bool) {
      prefs.putBoolean("SFX",bool);
      prefs.flush();
      settings.SFX = bool;
    }

    public static void saveScore(){
      if(prefs.getInteger("SCORE")<settings.SCORE) {
        prefs.putInteger("SCORE", settings.SCORE);
        prefs.flush();
      }
    }

    public static void addScore(Points points){
      SCORE += points.getPoints();
    }
}
