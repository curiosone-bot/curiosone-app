package com.github.bot.curiosone.app.workflow.com.curiosone.WordTilesSrc.Settings;

public class Settings {
    public static Difficulty MODE = Difficulty.EASY;
    public static long SPAWN_RATE = 2000000000;
    public static int SPEED = 150;
    public static int SPAWN_NUMBER = 50;
    public static int SPAWN_WRONG = 15;
    public static boolean MUSIC = true;
    public static boolean SFX = true;

    public enum Difficulty{
        EASY,NORMAL,HARD,EXTREME
    }
}
