package com.arkanoid.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for asset managing purpose. The manager is implemented as a singleton.
 * All files and folders are within the dir android/assets
 * @author Simone Sanfratello
 *
 */
public class Resources implements Disposable {
	
	private static Resources instance;
	
	private final static AssetManager manager = new AssetManager();

    private final String menuBackground = "Backgrounds/Menu.jpg";
    private final String gameBackground = "Backgrounds/Background-0.jpg";

    private final String level1Miniature = "Miniatures/Level1.png";
    private final String level2Miniature = "Miniatures/Level2.png";
    private final String level3Miniature = "Miniatures/Level3.png";
    private final String level4Miniature = "Miniatures/Level4.png";
    private final String level5Miniature = "Miniatures/Level5.png";
    private final String level6Miniature = "Miniatures/Level6.png";
    private final String level7Miniature = "Miniatures/Level7.png";
    private final String level8Miniature = "Miniatures/Level8.png";
    private final String level9Miniature = "Miniatures/Level9.png";
    private final String level10Miniature = "Miniatures/Level10.png";

    private final String consoleFont1 = "Fonts/console.fnt";
    private final String consoleFont2 = "Fonts/console2.fnt";

    private final String menuMusic = "Music/UnstructuredSpaceEchoWave.ogg";
    private final String gameMusic = "Music/Little-Space-Probe...Very-Big-Journey.mp3";

    private final String hitSound = "Sounds/NFF-metal-hit.wav";

    private final String sprite = "Vaus1.png";

    private final String level1 = "Levels/Level1.txt";
    private final String level2 = "Levels/Level2.txt";
    private final String level3 = "Levels/Level3.txt";
    private final String level4 = "Levels/Level4.txt";
    private final String level5 = "Levels/Level5.txt";
    private final String level6 = "Levels/Level6.txt";
    private final String level7 = "Levels/Level7.txt";
    private final String level8 = "Levels/Level8.txt";
    private final String level9 = "Levels/Level9.txt";
    private final String level10 = "Levels/Level10.txt";

    private String levelText;

    private Map<Integer, String> miniatures;
    private Map<Integer, String> levels;
    private Map<String, Color> colors;

    private Resources() { }

    public static Resources getInstance() {
        if (instance == null)
            instance = new Resources();
        return instance;
    }

    /**
     * Loads assets for menu screens
     */
	public void loadMenuAssets() {
        manager.load(menuBackground, Texture.class);

        miniatures = new HashMap<Integer, String>();
        miniatures.put(1, level1Miniature);
        miniatures.put(2, level2Miniature);
        miniatures.put(3, level3Miniature);
        miniatures.put(4, level4Miniature);
        miniatures.put(5, level5Miniature);
        miniatures.put(6, level6Miniature);
        miniatures.put(7, level7Miniature);
        miniatures.put(8, level8Miniature);
        miniatures.put(9, level9Miniature);
        miniatures.put(10, level10Miniature);
        for (int i = 1; i <= 10; i++) {
            manager.load(miniatures.get(i), Texture.class);
        }

        manager.load(consoleFont1, BitmapFont.class);
        manager.load(consoleFont2, BitmapFont.class);
        manager.load(menuMusic, Music.class);
        manager.finishLoading();
	}

	public Texture getMenuBackground() {
        return manager.get(menuBackground, Texture.class);
    }

    public Texture getMiniature(int i) {
        String s = miniatures.get(i);
        return manager.get(s, Texture.class);
    }

    public BitmapFont getFont1() {
        return manager.get(consoleFont1, BitmapFont.class);
    }

    public BitmapFont getFont2() {
        return manager.get(consoleFont2, BitmapFont.class);
    }

    public Music getMenuMusic() {
        return manager.get(menuMusic, Music.class);
    }

    /**
     * Loads assets for the game screen
     */
    public void loadGameAssets() {
        manager.load(gameBackground, Texture.class);
        manager.load(consoleFont1, BitmapFont.class);
        manager.load(consoleFont2, BitmapFont.class);
        manager.load(gameMusic, Music.class);
        manager.load(hitSound, Sound.class);
        manager.load(sprite, Texture.class);
        manager.finishLoading();
        //creates a map that matches every level with its number.
        //levels are configured as txt files, which contains a matrix that represents the block matrix
        //that has to be built in the Level class.
        levels = new HashMap<Integer, String>();
        levels.put(1, level1);
        levels.put(2, level2);
        levels.put(3, level3);
        levels.put(4, level4);
        levels.put(5, level5);
        levels.put(6, level6);
        levels.put(7, level7);
        levels.put(8, level8);
        levels.put(9, level9);
        levels.put(10, level10);
        //creates a map that matches every color with the correspondent letter
        //in level files, every block is represented by a single letter, which is its color
        colors = new HashMap<String, Color>();
        colors.put("A", Color.GRAY);
        colors.put("B", Color.BLUE);
        colors.put("D", Color.GREEN);
        colors.put("G", Color.GOLD);
        colors.put("L", Color.LIME);
        colors.put("M", Color.MAGENTA);
        colors.put("O", Color.ORANGE);
        colors.put("P", Color.PURPLE);
        colors.put("R", Color.RED);
        colors.put("S", Color.SKY);
        colors.put("W", Color.WHITE);
        colors.put("Y", Color.YELLOW);
    }

    public Texture getGameBackground() {
        return manager.get(gameBackground, Texture.class);
    }

    public Music getGameMusic() {
        return manager.get(gameMusic, Music.class);
    }

	public Sound getSound() {
        return manager.get(hitSound, Sound.class);
	}

    public Texture getSprite() {
        return manager.get(sprite, Texture.class);
    }

	/**
     * Return the string from the .txt level
	 * @param i the number of the level
	 * @return the text of the level which is processed in {@link com.arkanoid.objects.Level}
	 */
	public String getLevel(int i) {
		FileHandle handle = Gdx.files.internal(levels.get(i));
		levelText = handle.readString();
		return levelText;
	}
	
	public Color getColor(String color) {
		return colors.get(color);
	}

    @Override
    public void dispose() {
        manager.clear();
    }
}
