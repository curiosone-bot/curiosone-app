package com.github.bot.curiosone.app.games.wordcrush.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.assets.AssetManager;
import com.github.bot.curiosone.app.games.wordcrush.utils.GameConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.*;

/** Class that manage file,audio and image of all the game.
 * Created by Giuseppe on 26/07/2017.
 */

public class Assets implements Disposable,AssetErrorListener
{
    /* Fields */
    public static final String TAG = AssetManager.class.getName();
    public static final Assets instance = new Assets();
    private  AssetManager assetManager ;
    public AssetMenuDecoration assetMenu;
    public AssetLevelDecoration assetLevel;
    public AssetGameModeDecoration assetGameModeDecoration;
    public AssetGameDifficultDecoration assetGameDifficultDecoration;
    public AssetsRememberDecoration assetsRememberDecoration;
    public AssetsEndDecoration assetsEndDecoration;
    private String text;
    private ArrayList<String> words;
    public AssetLetter assetLetter;
    public AssetSound assetSound;
    public AssetMusic assetMusic;


    /* singleton = prevent instantiation from other classes */
    private Assets(){}

    /**
     * Method that initialize the AssetManager
     * @param assetManager
     */
    public void init(AssetManager assetManager)
    {
        this.assetManager = assetManager;

        /* Set assets manager error handler */
        assetManager.setErrorListener(this);

        /* Load texture atlas */
        assetManager.load(GameConstants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        assetManager.load(GameConstants.FONT_SCORE,BitmapFont.class);
        assetManager.load("wordcrushassets/gameModeTexture.txt", TextureAtlas.class);
        assetManager.load("wordcrushassets/difficultyGameAssets.txt", TextureAtlas.class);
        assetManager.load("wordcrushassets/rememberAssets.txt", TextureAtlas.class);
        assetManager.load("wordcrushassets/tileDownAssets.txt", TextureAtlas.class);
        assetManager.load("wordcrushassets/endAssets.txt", TextureAtlas.class);
        assetManager.load("wordcrushassets/remberFont.fnt", BitmapFont.class);
        assetManager.load("wordcrushassets/darkBlueFont.fnt", BitmapFont.class);
        assetManager.load("wordcrushassets/yellowFont.fnt", BitmapFont.class);

        /* Audio load */
        assetManager.load("wordcrushassets/audio/Greasy.mp3", Music.class);
        assetManager.load("wordcrushassets/audio/CoralReef.mp3", Music.class);
        assetManager.load("wordcrushassets/audio/GameSong.mp3", Music.class);
        assetManager.load("wordcrushassets/audio/TumCrash.mp3", Sound.class);
        assetManager.load("wordcrushassets/audio/ClickMenuButton.mp3", Sound.class);
        assetManager.load("wordcrushassets/audio/LetterSound.mp3", Sound.class);
        assetManager.load("wordcrushassets/audio/Ding.mp3", Sound.class);
        assetManager.load("wordcrushassets/audio/ErrorSound.mp3", Sound.class);
        assetManager.load("wordcrushassets/audio/Boing.mp3", Sound.class);



        /* Load vocabulary */
        FileHandle file = Gdx.files.internal(GameConstants.VOCABULARY);
        text = file.readString("UTF-8");
        words = new ArrayList<String>();
        // for MAC & Linux
        words.addAll(Arrays.asList(text.split("\n")));
        if (words.size() == 1) // if it didn't work
            words.addAll(Arrays.asList(text.split("\r"))); // \r for windows!!!!
        Collections.sort(words);


        /* start loading assets and wait until finished */
        assetManager.finishLoading();

        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);

        for(String a : assetManager.getAssetNames())
            Gdx.app.debug(TAG, "asset: " + a);

        TextureAtlas atlas = assetManager.get(GameConstants.TEXTURE_ATLAS_OBJECTS);
        TextureAtlas atlas1 = assetManager.get("wordcrushassets/gameModeTexture.txt");
        TextureAtlas atlas2 = assetManager.get("wordcrushassets/difficultyGameAssets.txt");
        TextureAtlas atlas3 = assetManager.get("wordcrushassets/rememberAssets.txt");
        TextureAtlas atlas4 = assetManager.get("wordcrushassets/tileDownAssets.txt");
        TextureAtlas atlas5 = assetManager.get("wordcrushassets/endAssets.txt");



        // enable texture filtering for pixel smoothing
        for (Texture t : atlas.getTextures())
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        // create game resource objects
        assetMenu = new AssetMenuDecoration(atlas);
        assetLevel = new AssetLevelDecoration(atlas);
        assetLetter = new AssetLetter(atlas,atlas4);
        assetGameModeDecoration = new AssetGameModeDecoration(atlas1);
        assetGameDifficultDecoration = new AssetGameDifficultDecoration(atlas2);
        assetsRememberDecoration = new AssetsRememberDecoration(atlas3);
        assetsEndDecoration = new AssetsEndDecoration(atlas5);
        assetMusic = new AssetMusic(assetManager);
        assetSound = new AssetSound(assetManager);


    }

    @Override
    public void dispose()
    {
        assetManager.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable)
    {
        Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception) throwable );
    }

    public class AssetLetter
    {
        public final ArrayList<AtlasRegion> skinTileUp;
        public final ArrayList<AtlasRegion> skinTileDown;
        public final AtlasRegion tileBlank;

        public AssetLetter (TextureAtlas atlas, TextureAtlas atlas2)
        {
            skinTileUp = new ArrayList<AtlasRegion>();
            skinTileDown = new ArrayList<AtlasRegion>();
            int i = 0;
            String nameTileUp = "tile1_A";
            String nameTileDown = "tile1down_A";

            while (i < 26)
            {
                /* Used for up tile */
                skinTileUp.add(atlas.findRegion(nameTileUp));
                nameTileUp = nameTileUp.replace((char) ('A' + i), (char) ('A' + i + 1));

                skinTileDown.add(atlas2.findRegion(nameTileDown));
                nameTileDown = nameTileDown.replace((char) ('A' + i), (char) ('A' + i + 1));
                i++;
            }
            tileBlank = atlas.findRegion("tile1_blank");
        }
    }

    /**
     * Class that contains all the texture for the menu's screen
     */
    public class AssetMenuDecoration
    {
        public final AtlasRegion background;
        public final AtlasRegion wcLogo;
        public final AtlasRegion startOff;
        public final AtlasRegion startOn;
        public final AtlasRegion creditsOff;
        public final AtlasRegion creditsOn;
        private final TextureAtlas textureAtlas;

        public AssetMenuDecoration(TextureAtlas atlas)
        {
            background = atlas.findRegion("mainBackground");
            wcLogo = atlas.findRegion("wcLogo");
            startOff = atlas.findRegion("playOff");
            startOn =  atlas.findRegion("playOn");
            creditsOff = atlas.findRegion("creditsOff");
            creditsOn = atlas.findRegion("creditsOn");
            textureAtlas = atlas;
        }

        public TextureAtlas getAtlas()
        {
            return textureAtlas;
        }

    }

    /**
     *  Class that contain all the level's decorations
     */
    public class AssetLevelDecoration
    {
        public final AtlasRegion backgroundLevel;
        public final ArrayList<AtlasRegion> tiles;
        public final TextureAtlas textureAtlas;
        public final AtlasRegion textBox;
        public final AtlasRegion skipUp;
        public final AtlasRegion skipDown;


        public AssetLevelDecoration(TextureAtlas atlas)
        {
            backgroundLevel = atlas.findRegion("voodoo_cactus_island");
            tiles = new ArrayList<AtlasRegion>();
            textBox = atlas.findRegion("textBox");
            textureAtlas = atlas;
            skipUp = atlas.findRegion("skip");
            skipDown = atlas.findRegion("skipDown");

        }

        public TextureAtlas getAtlas()
        {
            return textureAtlas;
        }
    }

    public class AssetGameModeDecoration
    {
        public final AtlasRegion visionaryOff;
        public final AtlasRegion visionaryOn;
        public final AtlasRegion wordCheckOff;
        public final AtlasRegion wordCheckOn;
        public final AtlasRegion gameModeLogo;
        public final TextureAtlas textureAtlas;

        public AssetGameModeDecoration(TextureAtlas atlas)
        {
            visionaryOff = atlas.findRegion("visionaryOff");
            visionaryOn = atlas.findRegion("visionaryOn");
            wordCheckOff = atlas.findRegion("wordsCheckOff");
            wordCheckOn = atlas.findRegion("wordsCheckOn");
            gameModeLogo = atlas.findRegion("gameModeLogo");
            textureAtlas = atlas;
        }

        public TextureAtlas getAtlas()
        {
            return textureAtlas;
        }

    }

    public class AssetGameDifficultDecoration
    {
        public final AtlasRegion selectLogo;
        public final AtlasRegion easyOff;
        public final AtlasRegion easyOn;
        public final AtlasRegion normalOff;
        public final AtlasRegion normalOn;
        public final AtlasRegion hardOff;
        public final AtlasRegion hardOn;
        private final TextureAtlas textureAtlas;

        public AssetGameDifficultDecoration (TextureAtlas atlas)
        {
            selectLogo = atlas.findRegion("selectLogo");
            easyOff = atlas.findRegion("easyOff");
            easyOn = atlas.findRegion("easyOn");
            normalOff = atlas.findRegion("easyOff");
            normalOn = atlas.findRegion("easyOn");
            hardOff = atlas.findRegion("hardOff");
            hardOn = atlas.findRegion("hardOn");
            textureAtlas = atlas;
        }

        public ArrayList<AtlasRegion> getRegions()
        {
            ArrayList<AtlasRegion> atlasRegions = new ArrayList<AtlasRegion>();
            atlasRegions.add(selectLogo);
            atlasRegions.add(easyOff);
            atlasRegions.add(easyOn);
            atlasRegions.add(normalOff);
            atlasRegions.add(normalOn);
            atlasRegions.add(hardOff);
            atlasRegions.add(hardOn);

            return atlasRegions;
        }

        public TextureAtlas getAtlas()
        {
            return textureAtlas;
        }
    }

    public class AssetsRememberDecoration
    {
        private TextureAtlas textureAtlas;
        public AtlasRegion lowBlack;
        public AtlasRegion rememberLogo;
        public AtlasRegion bucket;

        private AssetsRememberDecoration(TextureAtlas atlas)
        {
            textureAtlas = atlas;
            lowBlack = atlas.findRegion("lowBlack");
            rememberLogo = atlas.findRegion("rememberLogo");
            bucket = atlas.findRegion("bucket");
        }

        public TextureAtlas getAtlas()
        {
            return textureAtlas;
        }
    }

    public class AssetsEndDecoration
    {
        public TextureAtlas textureAtlas;
        public AtlasRegion cloud1;
        public AtlasRegion cloud2;
        public AtlasRegion cloud3;
        public AtlasRegion exitUp;
        public AtlasRegion exitDown;
        public AtlasRegion againLogoUp;
        public AtlasRegion againLogoDown;
        public AtlasRegion backgroundBlueNight;
        public AtlasRegion textBox;

        private AssetsEndDecoration(TextureAtlas atlas)
        {
            textureAtlas = atlas;
            backgroundBlueNight = atlas.findRegion("backgroundBlueNight");
            cloud1 = atlas.findRegion("cloud1");
            cloud2 = atlas.findRegion("cloud2");
            cloud3 = atlas.findRegion("cloud3");
            exitUp = atlas.findRegion("signExit");
            exitDown = atlas.findRegion("signExitDown");
            againLogoDown = atlas.findRegion("againLogoDown");
            againLogoUp = atlas.findRegion("againLogoUp");
            textBox = atlas.findRegion("LabelBox");

        }

    }

    public class AssetSound
    {
        public final Sound tumCrash;
        public final Sound clickMenuButton;
        public final Sound letterSound;
        public final Sound ding;
        public final Sound errorSound;
        public final Sound boing;

        public AssetSound ( AssetManager assetManager)
        {
            tumCrash = assetManager.get("wordcrushassets/audio/TumCrash.mp3", Sound.class);
            clickMenuButton = assetManager.get("wordcrushassets/audio/ClickMenuButton.mp3", Sound.class);
            letterSound = assetManager.get("wordcrushassets/audio/LetterSound.mp3", Sound.class);
            ding = assetManager.get("wordcrushassets/audio/Ding.mp3", Sound.class);
            errorSound = assetManager.get("wordcrushassets/audio/ErrorSound.mp3", Sound.class);
            boing = assetManager.get("wordcrushassets/audio/Boing.mp3", Sound.class);
        }

    }

    public class AssetMusic
    {
        public final Music slidy;
        public final Music hawaii;
        public final Music rocker;

        public AssetMusic(AssetManager assetManager)
        {
            slidy = assetManager.get("wordcrushassets/audio/Greasy.mp3", Music.class);
            hawaii = assetManager.get("wordcrushassets/audio/CoralReef.mp3", Music.class);
            rocker = assetManager.get("wordcrushassets/audio/GameSong.mp3", Music.class);
        }
    }
    /**
     *  Method that return an array with all words
     * @return
     */
    public ArrayList<String> getVocabulary() { return words; }

    public BitmapFont getBitmapFontBlack() { return assetManager.get(GameConstants.FONT_SCORE); }
    public BitmapFont getBitmapFontRemember() { return assetManager.get("wordcrushassets/remberFont.fnt"); }
    public BitmapFont getDarkBlueFont () { return assetManager.get("wordcrushassets/darkBlueFont.fnt"); }
    public BitmapFont getYellowFont () { return  assetManager.get("wordcrushassets/yellowFont.fnt"); }

}
