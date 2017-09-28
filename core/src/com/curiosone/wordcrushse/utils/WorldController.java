package com.curiosone.wordcrushse.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.curiosone.wordcrushse.WordCrushSE;
import com.curiosone.wordcrushse.game_object.Letter;
import com.curiosone.wordcrushse.game_object.Word;
import com.curiosone.wordcrushse.loaders.Assets;
import com.curiosone.wordcrushse.loaders.AudioManager;
import com.curiosone.wordcrushse.screen.EndScreen;
import com.curiosone.wordcrushse.screen.MenuScreen;
import com.curiosone.wordcrushse.screen.RememberScreen;
import com.curiosone.wordcrushse.screen.WordsCheckGameScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;


/** Class that manage all the logic of the game 
 * Created by giuseppe on 16/08/17.
 */

public class WorldController
{
    private WordCrushSE wc;
    private ArrayList<Letter> letters;
    private HashSet<Character> character;
    private float rememberTimer;
    private float gameTimer, changeScreenTimer;
    private ArrayList<String> words;
    private Image textBox, rememberLogo, bucket, scoreBox, multiplicator, numberOfMultiplicator;
    private Random random;
    private Skin skin;
    private BitmapFont rememberFont, permanentMarker, darkBlueFont, yellowFont;
    private ArrayList<Integer> placeMarker;
    private ArrayList<String> thisNWords;
    private int selectorOfWords, counterWordsForScreen, numberScore, counterRightWords, penality, counterOfMatch, multiplicatorInt = 1;
    private Word word;
    private Table table;
    private Label stringToPrint;
    private String string2Print;
    private ArrayList<Image> clouds;
    private WordsCheckGameScreen wordsCheckGameScreen;
    private RememberScreen rememberScreen;
    private ImageButton exit, again, skip;
    private ParticleEffect starsEffect;


    public WorldController(WordCrushSE wc)
    {
        this.wc = wc;
    }

    /* -------------------------------  INIT SECTION  ------------------------------------------- */

    /**
     * Method that initialize the game structure
     */
    public void initGame()
    {
        changeScreenTimer= 0.0f;
        penality = 0;
        /* Font Score */
        permanentMarker = Assets.instance.getBitmapFontBlack();

        /* Audio */
        AudioManager.instance.play(Assets.instance.assetMusic.rocker);

        /* Set timer */
        gameTimer = 30f;
        /* Magic words */
        character = new HashSet<Character>();
        ArrayList<String> app = new ArrayList<String>();
        for(String s : thisNWords)
            app.addAll(Arrays.asList(s.split("")));
        for(String x : app)
            if (!x.equals("")) // bug api26
                character.add(x.charAt(0));

        /* Creating Letters */
        letters = new ArrayList<com.curiosone.wordcrushse.game_object.Letter>();
        com.curiosone.wordcrushse.game_object.Letter l;
        for(Character c : character)
            if(Character.isLetter(c.charValue()) &&  !letters.contains(l  = new com.curiosone.wordcrushse.game_object.Letter(Character.toUpperCase(c.charValue()))))
                letters.add(l);

        /* Table */
        table = new Table();
        ArrayList<com.curiosone.wordcrushse.game_object.Letter> blankList = new ArrayList<com.curiosone.wordcrushse.game_object.Letter>();

        if (letters.size() < 16 )
            for (int i =0; i < 16 - letters.size(); i++)
                blankList.add( new com.curiosone.wordcrushse.game_object.Letter("tile1_blank"));
        int sizeBlankList = blankList.size();
        int x = 0; // indice delle lettere

        // scope -> put in tileBlank
        for (int i = 0 ; i < 16 ; i++ )
        {
            if( ( x == letters.size() && sizeBlankList >0) || (sizeBlankList >= 1 && new Random().nextBoolean()))
            {
                table.add(new com.curiosone.wordcrushse.game_object.Letter("tile1_blank")).pad(10).center();
                sizeBlankList--;
            }
            else
            {
                final int finalX = x;
                final com.curiosone.wordcrushse.game_object.Letter provvisor = letters.get(x);
                provvisor.addListener( new ChangeListener (){
                    @Override
                    public void changed(ChangeEvent event, Actor actor)
                    {
                        /* Setting the string to print */
                        string2Print += letters.get(finalX).toString().charAt(0);
                        stringToPrint.setText(string2Print);
                        stringToPrint.setPosition(com.curiosone.wordcrushse.utils.GameConstants.WIDTH/2, com.curiosone.wordcrushse.utils.GameConstants.HEIGHT/2 + 145);
                        stringToPrint.setAlignment(Align.center);
                        stringToPrint.setFontScale(1.5f);

                        /* Sound */
                        AudioManager.instance.play(Assets.instance.assetSound.letterSound);

                        /* check if player is writin' right words else empty string2Print */
                        boolean flag = false;
                        for (String s: thisNWords)
                            if (s.length() >= string2Print.length() && string2Print.toLowerCase().equals(s.toLowerCase().substring(0,string2Print.length())))
                                flag = true;
                        if (!flag)
                        {
                            string2Print = new String();
                            penality++;
                            stringToPrint.setText(string2Print);
                            AudioManager.instance.play(Assets.instance.assetSound.errorSound);
                        }

                        String delete = new String() ;
                        /* Organizing events */
                        for (String s : thisNWords)
                        {
                            if (s.toLowerCase().equals(string2Print.toLowerCase())) // if you insert a right word
                            {
                                AudioManager.instance.play(Assets.instance.assetSound.ding); // play sound
                                if( numberScore < 99999) // if minor then max
                                    numberScore += (com.curiosone.wordcrushse.utils.GameConstants.MAX_POINT - penality) * multiplicatorInt; // calculate point
                                if ( numberScore < 0 ) // if your score is under 0 let 0
                                    numberScore = 0;

                                float delay = 0.8f; // seconds
                                Timer.schedule(new Timer.Task(){
                                    @Override
                                    public void run() {
                                        // Do your work
                                        string2Print = new String(); // empty string to print
                                        stringToPrint.setText(string2Print);
                                        penality = 0;
                                    }
                                }, delay);

                                delete = s;
                            }
                        }
                        thisNWords.remove(delete); // remove the insert word from others
                    }
                });
                table.add(provvisor).pad(10).center();
                x++;
            }
            if ((i+1) % 4 == 0)
                table.row().pad(10).center();
        }
        /* Skip Button */
        ImageButton.ImageButtonStyle skipStyle = new ImageButton.ImageButtonStyle();
        skipStyle.imageUp = new SpriteDrawable( new Sprite(Assets.instance.assetLevel.skipUp));
        skipStyle.imageDown = new SpriteDrawable( new Sprite(Assets.instance.assetLevel.skipDown));
        skip = new ImageButton(skipStyle);
        skip.setBounds(com.curiosone.wordcrushse.utils.GameConstants.WIDTH/3 - 120, com.curiosone.wordcrushse.utils.GameConstants.HEIGHT/2+135,150f,150f);
        skip.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                float delay = 0.5f; // seconds
                Assets.instance.assetSound.boing.play();
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {

                        resetRememberScreen();
                        counterOfMatch++;
                        if(counterOfMatch == com.curiosone.wordcrushse.utils.GameConstants.MAX_MATCH)
                        {
                            counterWordsForScreen = 0;
                            counterOfMatch = 0;
                            wc.setScreen(new EndScreen(wc, WorldController.this));
                        }
                        else
                            wc.setScreen(rememberScreen);
                    }
                }, delay);

            }
        });

        /* TextField creation */
        textBox = new Image(Assets.instance.assetLevel.textBox);
        textBox.setBounds(com.curiosone.wordcrushse.utils.GameConstants.WIDTH/2 - Assets.instance.assetLevel.textBox.getRegionWidth()/2+20, com.curiosone.wordcrushse.utils.GameConstants.HEIGHT/2+100,420,80);
        table.setPosition(com.curiosone.wordcrushse.utils.GameConstants.WIDTH/2, com.curiosone.wordcrushse.utils.GameConstants.HEIGHT/2-150);

        /* String to print */
        string2Print = "";
        stringToPrint = new Label(string2Print,skin,"remberFont",skin.get("remberFont",BitmapFont.class).getColor());

        /* Multiplicator */
        //
        String finder = "hud" + com.curiosone.wordcrushse.utils.GameConstants.Number.values()[multiplicatorInt-1].toString().charAt(0) +
                com.curiosone.wordcrushse.utils.GameConstants.Number.values()[multiplicatorInt-1].toString().substring(1,
                com.curiosone.wordcrushse.utils.GameConstants.Number.values()[multiplicatorInt-1].toString().length()).toLowerCase() ;
        multiplicator = new Image(Assets.instance.assetsEndDecoration.textureAtlas.findRegion("hudX"));
        multiplicator.setPosition(330,600, Align.left);
        numberOfMultiplicator = new Image(Assets.instance.assetsEndDecoration.textureAtlas.findRegion(finder));
        numberOfMultiplicator.setPosition(370,600, Align.left);

    }

    /**
     *  Method that initialize the Remember Screen
     */
    public void initRemember()
    {
        rememberTimer = 2.0f;

        /* Initialize font */
        rememberFont = Assets.instance.getBitmapFontRemember();

        /* Create Words */
        random = new Random();
        placeMarker = new ArrayList<Integer>();
        thisNWords = new ArrayList<String>(); // contain 3 words
        words = Assets.instance.getVocabulary(); // contain all the vocabulary
        selectorOfWords = random.nextInt(words.size()); // random from 0 to number of  words in dictionary

        /* Actors */
        rememberLogo = new Image( new SpriteDrawable( new Sprite( Assets.instance.assetsRememberDecoration.rememberLogo )));
        rememberLogo.setPosition(com.curiosone.wordcrushse.utils.GameConstants.WIDTH/2 - rememberLogo.getPrefWidth()/2 , 650);
        bucket = new Image( new SpriteDrawable( new Sprite( Assets.instance.assetsRememberDecoration.bucket)));
        bucket.setPosition(com.curiosone.wordcrushse.utils.GameConstants.WIDTH/2 - bucket.getPrefWidth()/2, com.curiosone.wordcrushse.utils.GameConstants.HEIGHT/2 - bucket.getPrefHeight()/2);
        skin = new Skin(Assets.instance.assetsRememberDecoration.getAtlas());
        skin.add("remberFont", rememberFont, BitmapFont.class); // set font to words

        /* Effect */
        starsEffect = new ParticleEffect();
        starsEffect.load(Gdx.files.internal("starEffect"),Gdx.files.internal(""));
        starsEffect.setPosition(com.curiosone.wordcrushse.utils.GameConstants.WIDTH/2, com.curiosone.wordcrushse.utils.GameConstants.HEIGHT/2);


        /* Word Management */
        //word = new Label(words.get(selectorOfWords),skin,"remberFont",skin.get("remberFont",BitmapFont.class).getColor());
        word = new Word(words.get(selectorOfWords),skin,"remberFont",skin.get("remberFont",BitmapFont.class).getColor(), starsEffect);
        thisNWords.add(words.get(selectorOfWords));
        counterWordsForScreen++;
        word.setPosition(com.curiosone.wordcrushse.utils.GameConstants.WIDTH/2 - word.getWidth()/2, com.curiosone.wordcrushse.utils.GameConstants.HEIGHT/2);
        word.setFontScale(1.8f);
        word.setAlignment(Align.center);
    }

    /**
     *  Method that initialize the End Screen
     */
    public void initEnd()
    {
        /* Import Font */
        darkBlueFont = Assets.instance.getDarkBlueFont();
        yellowFont = Assets.instance.getYellowFont();

         /* Audio */
        AudioManager.instance.play(Assets.instance.assetMusic.slidy);

        /* Import textBox */
        scoreBox = new Image(Assets.instance.assetsEndDecoration.textBox);
        scoreBox.setBounds(com.curiosone.wordcrushse.utils.GameConstants.WIDTH/2 - Assets.instance.assetsEndDecoration.textBox.getRegionWidth()/2, com.curiosone.wordcrushse.utils.GameConstants.HEIGHT/2+100,400,60);

        /* Import clouds*/
        clouds = new ArrayList<Image>();
        clouds.add( new Image(Assets.instance.assetsEndDecoration.cloud1));
        clouds.get(0).setPosition(-10 - Assets.instance.assetsEndDecoration.cloud1.getRegionWidth()/2,
                650 - Assets.instance.assetsEndDecoration.cloud1.getRegionHeight());
        clouds.get(0).addAction(Actions.repeat(1000,Actions.sequence(Actions.moveBy(25,0,3.8f), Actions.moveBy(-25,0,3.8f))));

        clouds.add( new Image(Assets.instance.assetsEndDecoration.cloud2));
        clouds.get(1).setPosition(490 - Assets.instance.assetsEndDecoration.cloud2.getRegionWidth()/2,
                660 - Assets.instance.assetsEndDecoration.cloud1.getRegionHeight());
        clouds.get(1).addAction(Actions.repeat(1000,Actions.sequence(Actions.moveBy(-20,0,3.8f), Actions.moveBy(20,0,3.8f))));
        clouds.get(1).addAction(Actions.repeat(1000,Actions.sequence(Actions.moveBy(-20,0,3.8f), Actions.moveBy(20,0,3.8f))));

        clouds.add( new Image(Assets.instance.assetsEndDecoration.cloud2));
        clouds.get(2).setPosition(-15 - Assets.instance.assetsEndDecoration.cloud2.getRegionWidth()/2,
                500 - Assets.instance.assetsEndDecoration.cloud1.getRegionHeight());
        clouds.get(2).addAction(Actions.repeat(1000,Actions.sequence(Actions.moveBy(30,0,3.8f), Actions.moveBy(-30,0,3.8f))));

        clouds.add( new Image(Assets.instance.assetsEndDecoration.cloud3));
        clouds.get(3).setPosition(500 - Assets.instance.assetsEndDecoration.cloud3.getRegionWidth()/2,
                500 - Assets.instance.assetsEndDecoration.cloud1.getRegionHeight());
        clouds.get(3).addAction(Actions.repeat(1000,Actions.sequence(Actions.moveBy(-40,0,3.8f), Actions.moveBy(40,0,3.8f))));

        /* Import exit */
        ImageButton.ImageButtonStyle exitButtonStyle = new ImageButton.ImageButtonStyle();
        exitButtonStyle.imageUp = new SpriteDrawable( new Sprite(Assets.instance.assetsEndDecoration.exitUp));
        exitButtonStyle.imageDown = new SpriteDrawable( new Sprite(Assets.instance.assetsEndDecoration.exitDown));
        exit = new ImageButton(exitButtonStyle);
        exit.setPosition(com.curiosone.wordcrushse.utils.GameConstants.WIDTH/2 - Assets.instance.assetsEndDecoration.exitUp.getRegionWidth()/2,
                0);

        exit.addListener( new ChangeListener(){
        @Override
        public void changed(ChangeEvent event, Actor actor)
        {
            AudioManager.instance.play(Assets.instance.assetSound.clickMenuButton);
            wc.setScreen(new MenuScreen(wc,Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
            AudioManager.instance.play(Assets.instance.assetMusic.hawaii);
        }

    });
        /* Import again */
        ImageButton.ImageButtonStyle againButtonStyle = new ImageButton.ImageButtonStyle();
        againButtonStyle.imageUp = new SpriteDrawable( new Sprite(Assets.instance.assetsEndDecoration.againLogoUp));
        againButtonStyle.imageDown = new SpriteDrawable( new Sprite(Assets.instance.assetsEndDecoration.againLogoDown));
        again = new ImageButton(againButtonStyle);
        again.setPosition(com.curiosone.wordcrushse.utils.GameConstants.WIDTH/2 - Assets.instance.assetsEndDecoration.againLogoUp.getRegionWidth()/2,
                com.curiosone.wordcrushse.utils.GameConstants.HEIGHT/2 - Assets.instance.assetsEndDecoration.againLogoUp.getRegionHeight()/2 - 100);
        again.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                AudioManager.instance.play(Assets.instance.assetSound.clickMenuButton);
                if (multiplicatorInt<= 9)
                    multiplicatorInt++;
                wc.setScreen(new RememberScreen(wc, WorldController.this));
            }
        });

    }



    /* -----------------------------  UPDATE SECTION  ---------------------------------------------*/

    /**
     * Method that update the game screen
     * @param deltaTime
     */
    public void updateGame(float deltaTime)
    {
        /* Update game timer */

        if (thisNWords.size() == 0) // if you have insert all words
        {
            changeScreenTimer += deltaTime;
            if (changeScreenTimer >= 1.0f) // manage change screen timer
            {
                resetRememberScreen();
                resetGameScreen();
                counterOfMatch++;
                if (counterOfMatch == com.curiosone.wordcrushse.utils.GameConstants.MAX_MATCH)
                {
                    counterWordsForScreen = 0;
                    counterOfMatch = 0;
                    wc.setScreen(new EndScreen(wc, this));
                }
                else
                    wc.setScreen(rememberScreen);
            }
        }

        if (gameTimer > 0)
            gameTimer -= deltaTime;
        else
        {
            resetRememberScreen();
            counterOfMatch++;
            if(counterOfMatch == com.curiosone.wordcrushse.utils.GameConstants.MAX_MATCH)
            {
                counterWordsForScreen = 0;
                counterOfMatch = 0;
                wc.setScreen(new EndScreen(wc, this));
            }
            else
                wc.setScreen(rememberScreen);
        }

    }

    /**
     * Method that update the remember screen
     * @param deltaTime
     */
    public void updateRemember(float deltaTime)
    {
        // Calculate max number of letter --> 16
        HashSet<String> setString = new HashSet<String>(thisNWords.subList(0,thisNWords.size()));


         /* Show words every 2 seconds */
        if (counterWordsForScreen == com.curiosone.wordcrushse.utils.GameConstants.NUMBER_OF_MAGIC_WORDS && rememberTimer <= 0) // check the counter of word to show in this Screen
            wc.setScreen(new WordsCheckGameScreen(wc,this));
        else
        {
            if (!(counterWordsForScreen == com.curiosone.wordcrushse.utils.GameConstants.NUMBER_OF_MAGIC_WORDS) && rememberTimer <= 0 && selectorOfWords < words.size() - 1)
            {
                int app = 0 ;
                placeMarker.add(selectorOfWords);
                if (!(placeMarker.contains(app = random.nextInt(3000)) && setString.size() + words.get(app).length() < 16))
                {
                    selectorOfWords = app;
                    thisNWords.add(words.get(selectorOfWords));
                    word.reset();
                }
                else
                {
                    while (placeMarker.contains(app) && setString.size() + words.get(app).length() > 16)
                    {
                        app = random.nextInt(3000);
                    }
                    selectorOfWords = app;
                    thisNWords.add(words.get(selectorOfWords));
                    word.reset();
                }
                counterWordsForScreen++;
                rememberTimer = 2.0f; // reset timer to 2
            }

            word.setText(words.get(selectorOfWords)); // update the word to print
            rememberTimer -= Gdx.graphics.getDeltaTime();
        }
    }

    public void updateEndScreen (float deltaTime)
    {

    }

    /* ----------------------------------  RESET SCENE  ---------------------------------------*/


    /**
     * Method that reset the remember screen
     */
    public void resetRememberScreen()
    {
        counterWordsForScreen = 0;
        thisNWords.clear();
    }

    /**
     * Method that reset the game screen
     */
    public void resetGameScreen ()
    {
        counterRightWords = 0;
        changeScreenTimer = 0;
    }




    /* Setter */
    public void setRememberScreen (RememberScreen rememberScreen) { this.rememberScreen = rememberScreen;}

    public void setWordsCheckGameScreen (WordsCheckGameScreen wordsCheckGameScreen ) { this.wordsCheckGameScreen = wordsCheckGameScreen;}

    /* Getter */

    /**
     *  Return the GUI's Font
     * @return BitmapFont
     */
    public BitmapFont getGUIFont () { return permanentMarker;}

    /**
     *  Return the DarkBlueFont
     * @return BitmapFont
     */
    public BitmapFont getDarkBlueFont () { return darkBlueFont;}

    /**
     * Return the yellow font
     * @return BitmapFont
     */
    public BitmapFont getYelowFont () { return yellowFont;}

    /**
     *  Return the score
     * @return int
     */
    public int getScore () { return numberScore;}

    /**
     * Return timer of game screen
     * @return float
     */
    public float getGameTimer () { return gameTimer;}

    /**
     * Return the Word Label
     * @return Label
     */
    public Label getWord () { return word;}

    /**
     * Return the Remember Logo
     * @return Image
     */
    public Image getRememberLogo () { return rememberLogo;}

    /**
     * Return the bucket
     * @return Image
     */
    public Image getBucket () { return  bucket;}

    /**
     * Return the textbox of game screen
     * @return
     */
    public Image getTextBox () { return  textBox;}

    /**
     * Return the tile table
     * @return Table
     */
    public Table getTileTable () { return table;}

    /**
     * Return the string that is insert in the text box of game screen
     * @return Label
     */
    public Label getStringToPrint () { return  stringToPrint;}

    /**
     * Return the score box
     * @return Image
     */
    public Image getScoreBox () { return scoreBox;}

    /**
     * Return the exit cartel
     * @return ImageButton
     */
    public ImageButton getExit () {return exit;}

    /**
     * Return the Again logo
     * @return ImageButton
     */
    public ImageButton getAgain () {return again;}

    /**
     * Return an array list of clouds that are placed in the end screen
     * @return
     */
    public ArrayList<Image> getClouds () { return clouds;}

    /**
     * Return the "X" multiplicator logo
     * @return Image
     */
    public Image getMultiplicator () { return multiplicator;}

    /**
     * Return the multiplicator value logo
     * @return Image
     */
    public Image getNumberOfMultiplicator () { return numberOfMultiplicator;}

    /**
     * Return the effect used in the remember screen called "Stars effect"
     * @return ParticleEffect
     */
    public ParticleEffect getStarsEffect () { return  starsEffect;}

    /**
     * Rwturn the skip button of game screen
     * @return ImageButton
     */
    public ImageButton getSkip () { return skip;}




}
