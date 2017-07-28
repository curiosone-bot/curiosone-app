package com.github.bot.curiosone.app.games.wordtiles.spawner;

import com.badlogic.gdx.utils.Array;
import com.github.bot.curiosone.app.games.wordtiles.assets_manager.Manager;
import com.github.bot.curiosone.app.games.wordtiles.settings.Settings;
import com.github.bot.curiosone.app.games.wordtiles.gameobjects.AbstractTile;
import com.github.bot.curiosone.app.games.wordtiles.gameobjects.Tile;

import java.util.Iterator;

/**
 * @author Alessandro Roic
 * This class is the spawner of the tiles
 */
public class TileSpawner implements Iterable<AbstractTile>{

    private Array<AbstractTile> tiles;
    private final Array<Integer> lines = new Array<Integer>(new Integer[]{0,121,242,362});
    private Settings settings;
    private Manager manager;

  /**
   * Spawn the tiles according to the game difficulty
   */
  public TileSpawner() {
        manager = Manager.getIstance();
        manager.loadTileSpawer();
        settings = Settings.getIstance();
        tiles = new Array<AbstractTile>();
        tiles.add(new Tile(190));
    }

    @Override
    public Iterator<AbstractTile> iterator() {
        return tiles.iterator();
    }
}
