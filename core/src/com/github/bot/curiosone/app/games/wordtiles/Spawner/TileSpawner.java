package com.github.bot.curiosone.app.games.wordtiles.Spawner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.github.bot.curiosone.app.games.wordtiles.Settings.Settings;
import com.github.bot.curiosone.app.games.wordtiles.Sprites.AbstractTile;
import com.github.bot.curiosone.app.games.wordtiles.Sprites.Tile;
import com.github.bot.curiosone.app.games.wordtiles.Sprites.WrongTile;

import java.util.Iterator;

public class TileSpawner implements Iterable<AbstractTile>{

    private Array<AbstractTile> tiles;
    private final Array<Integer> lines = new Array<Integer>(new Integer[]{0,121,242,362});
    private Array<String> words;
    public static final BitmapFont font = new BitmapFont(Gdx.files.internal("WordTiles/Font/lexie.fnt"));
    public static TextureRegionDrawable up,down,down2;
    public static TextButton.TextButtonStyle style,style2;

    public TileSpawner() {
        tiles = new Array<AbstractTile>();
        up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("WordTiles/TilesTextures/tile.png"))));
        down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("WordTiles/TilesTextures/Tile_Touched.png"))));
        down2 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("WordTiles/TilesTextures/Wrong_Tile_Touched.png"))));
        style = new TextButton.TextButtonStyle(up,down,null, TileSpawner.font);
        style2 = new TextButton.TextButtonStyle(up,down2,null,TileSpawner.font);
        switch (Settings.MODE){
            case EASY:
                words = new Array<String>(generateWords("WordTiles/Texts/Level_Easy"));
                break;
            case NORMAL:
                words = new Array<String>(generateWords("WordTiles/Texts/Level_Normal"));
                break;
            case HARD:
                words = new Array<String>(generateWords("WordTiles/Texts/Level_Hard"));
                break;
            case EXTREME:
                words = new Array<String>(generateWords("WordTiles/Texts/Level_Extreme"));
                break;
        }
        spawner();
    }


    private void spawner() {
        for(String word:words){
          if(word.contains(".")){
              word = word.substring(0,word.length()-2);
              tiles.add(new WrongTile(lines.random(),word));
          }
          else {
              tiles.add(new Tile(lines.random(),word));
          }
        }
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
        for(String string: words){
          if(string.length()>6){
            if(string.length()>12){
              string = string.substring(0,6)+"\n"+string.substring(6,12)+"\n"+string.substring(12);
            }
            else {
              string = string.substring(0,6)+"\n"+string.substring(6);
            }
          }
            list.add(string);
        }
        return list;
    }
}
