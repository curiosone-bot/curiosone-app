package com.github.bot.curiosone.app.games.airborneassault.spawner;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.github.bot.curiosone.app.games.airborneassault.gameobjects.Plane;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
import java.util.Iterator;

/**
 * @author Alessandro Roic
 * This class is the spawner of the tiles
 */
public class PlaneSpawner implements Iterable<Actor>{

    private Array<Actor> actors;
    private Settings settings;
    private Manager manager;

  /**
   * Spawn the tiles according to the game difficulty
   */
  public PlaneSpawner() {
        manager = Manager.getIstance();
        manager.loadPlaneSpawer();
        settings = Settings.getIstance();
        actors = new Array<Actor>();
        actors.add(new Plane(190));
    }

    @Override
    public Iterator<Actor> iterator() {
        return actors.iterator();
    }
}
