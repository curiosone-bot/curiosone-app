package com.github.bot.curiosone.app.games.wordtiles.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {

    private static Preferences prefs;
    public static boolean MUSIC = true,SFX=true; //the only data to save into Preferences
    //default settings
    public static Difficulty MODE = Difficulty.EASY;
    public static long SPAWN_RATE = 2000000000;
    public static int SPEED = 175;

    public Settings(){
      prefs = Gdx.app.getPreferences("My Preferences");
      prefs.putBoolean("MUSIC",true);
      MUSIC = prefs.getBoolean("Music");
      prefs.putBoolean("SFX",true);
      SFX = prefs.getBoolean("SFX");
    }

    public static void setMUSIC(boolean bool) {
      prefs.putBoolean("MUSIC",bool);
      MUSIC = bool;
    }

    public static void setSFX(boolean bool) {
      prefs.putBoolean("SFX",bool);
      SFX = bool;
    }

    public enum Difficulty{
        EASY,NORMAL,HARD,EXTREME
    }

}
