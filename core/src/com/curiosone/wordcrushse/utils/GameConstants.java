package com.curiosone.wordcrushse.utils;

/** Class that contain all constants of the game
 * Created by giuseppe on 25/07/17.
 */

public class GameConstants
{
    /* Width screen */
    public static final int WIDTH = 480;

    /* Height screen */
    public static final int HEIGHT = 800;

    /* Location of description file for texture atlas */
    public static final String TEXTURE_ATLAS_OBJECTS = "totalAssets.txt";

    /* Location of vocabulary */
    public static final String VOCABULARY = "txt/vocabulary3000.txt";

    /* Font score*/
    public static final String FONT_SCORE = "fontScuro.fnt";

    /* Max number of match */
    public static final int MAX_MATCH = 5;
    /* Number of magic words */
    public static int NUMBER_OF_MAGIC_WORDS  ;
    public static void setNumberOfMagicWords(int numberOfMagicWords) { NUMBER_OF_MAGIC_WORDS = numberOfMagicWords;}

    /* Max score */
    public static final int MAX_POINT = 5;

    /* Easy Difficulty */
    public static final int EASY_DIFFICULTY = 1 ;

    /* Normal Difficulty */
    public static final int NORMAL_DIFFICULTY = 3 ;

    /* Hard Difficulty */
    public static final int HARD_DIFFICULTY = 4 ;

    /* Preferences */
    public static String PREFERENCES = "preferences.txt";

    public enum Number
    {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE
    }


}
