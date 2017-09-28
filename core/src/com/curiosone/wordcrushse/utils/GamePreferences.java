package com.curiosone.wordcrushse.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

/** Class that contain the method to save and load the preset of sound
 * Created by Giuseppe on 26/08/2017.
 */

public class GamePreferences
{
    public static final String TAG = GamePreferences.class.getName();

    public static final GamePreferences instance = new GamePreferences();

    public boolean sound;
    public boolean music;
    public float volSound;
    public float volMusic;
    public int charSkin;
    public boolean showFpsCounter;

    private com.badlogic.gdx.Preferences prefs;

    private GamePreferences()
    {
        prefs = Gdx.app.getPreferences(GameConstants.PREFERENCES);
    }

    /**
     * Load all the preset from file
     */
    public void load()
    {
        sound = prefs.getBoolean("sound", true);
        music = prefs.getBoolean("music", true);
        volSound = MathUtils.clamp(prefs.getFloat("volSound", 0.5f), 0.0f, 1.0f);
        volMusic = MathUtils.clamp(prefs.getFloat("volMusic", 0.5f), 0.0f, 1.0f);
        charSkin = MathUtils.clamp(prefs.getInteger("charSkin", 0), 0, 2);
        showFpsCounter = prefs.getBoolean("showFpsCounter", false);
    }

    /**
     * Save all the preset on a file
     */
    public void save()
    {
        prefs.putBoolean("sound", sound);
        prefs.putBoolean("music", music);
        prefs.putFloat("volSound", volSound);
        prefs.putFloat("volMusic", volMusic);
        prefs.putInteger("charSkin", charSkin);
        prefs.putBoolean("showFpsCounter", showFpsCounter);
        prefs.flush();
    }

}
