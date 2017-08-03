package com.github.bot.curiosone.app.games.airborneassault.spawner;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.github.bot.curiosone.app.games.airborneassault.gameobjects.FastPlane;
import com.github.bot.curiosone.app.games.airborneassault.gameobjects.Plane;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Alessandro Roic
 * This class is the spawn of the tiles
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
        spawner();
    }

    @Override
    public Iterator<Actor> iterator() {
        return actors.iterator();
    }

    private void spawner(){
        for(int i=0;i<10;i++){
          actors.add(new FastPlane(new Random().nextInt(326)));
        }
    }
}
