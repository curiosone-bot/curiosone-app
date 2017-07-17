package com.github.bot.curiosone.app.workflow.com.curiosone.wordtiles.Spawner;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.github.bot.curiosone.app.com.curiosone.wordtiles.Settings.Settings;
import com.github.bot.curiosone.app.com.curiosone.wordtiles.Sprites.AbstractTile;
import com.github.bot.curiosone.app.com.curiosone.wordtiles.Sprites.Tile;
import com.github.bot.curiosone.app.com.curiosone.wordtiles.Sprites.WrongTile;

import java.util.Iterator;

public class TileSpawner implements Iterable<AbstractTile>{

    private Array<AbstractTile> tiles;
    private final Array<Integer> lines = new Array<Integer>(new Integer[]{0,121,242,362});
    private Array<String> words;
    private Array<String> wrongWords;
    public static final BitmapFont font = new BitmapFont(Gdx.files.internal("WordTiles/Font/lexie.fnt"));

    public TileSpawner() {
        tiles = new Array<AbstractTile>();
        wrongWords = new Array<String>();
        switch (Settings.MODE){
            case EASY:
                words = new Array<String>(generateWords("WordTiles/Texts/Animals_Easy"));
                wrongWords = new Array<String>(generateWords("WordTiles/Texts/BodyParts_Easy"));
                break;
            case NORMAL:
                words = new Array<String>(generateWords("WordTiles/Texts/Animals_Normal"));
                wrongWords = new Array<String>(generateWords("WordTiles/Texts/BodyParts_Normal"));
                break;
            case HARD:
                words = new Array<String>(generateWords("WordTiles/Texts/Animals_Hard"));
                wrongWords = new Array<String>(generateWords("WordTiles/Texts/BodyParts_Hard"));
                break;
            case EXTREME:
                words = new Array<String>(generateWords("WordTiles/Texts/Animals_Extreme"));
                wrongWords = new Array<String>(generateWords("WordTiles/Texts/BodyParts_Extreme"));
                break;
        }
        spawner();
    }


    private void spawner() {
        int spawn_wrong = Settings.SPAWN_WRONG;
        int spawn_right = Settings.SPAWN_NUMBER - spawn_wrong;

        for(int a=0;a<spawn_right;a++) {
            String random = words.random();
            tiles.add(new Tile(lines.random(),random));
            words.removeIndex(words.indexOf(random,false));
        }

        for(int b=0;b<spawn_wrong;b++) {
            String random = wrongWords.random();
            tiles.add(new WrongTile(lines.random(),random));
            wrongWords.removeIndex(wrongWords.indexOf(random,false));
        }
        tiles.shuffle();
        tiles.shuffle();
    }

    @Override
    public Iterator<AbstractTile> iterator() {
        return tiles.iterator();
    }
    public static Array<String> generateWords(String path){
        Array<String> list = new Array<String>();
        FileHandle file = Gdx.files.internal(path);
        String text = file.readString();
        String[] words = text.split("\n");
        //Every line from the text has to be splitted due to limitations in space
        for(String word: words){
            if(word.length()>6){
                if(word.length()>12){
                    word = word.substring(0,6)+"\n"+word.substring(6,12)+"\n"+word.substring(12);
                }
                else {
                    word = word.substring(0,6)+"\n"+word.substring(6);
                }
            }
            list.add(word);
        }
        return list;
    }
}
