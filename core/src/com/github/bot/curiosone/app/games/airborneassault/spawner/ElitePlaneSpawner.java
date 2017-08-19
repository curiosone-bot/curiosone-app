package com.github.bot.curiosone.app.games.airborneassault.spawner;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.gameobjects.Plane;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
import java.util.Iterator;

public class ElitePlaneSpawner implements Iterable<Actor> {

  private Array<Actor> actors;
  private Settings settings;
  private Manager manager;

  /**
   * Spawn the elite planes
   */
  public ElitePlaneSpawner() {
    manager = Manager.getIstance();
    manager.loadPlaneSpawer();
    settings = Settings.getIstance();
    actors = new Array<Actor>();
    spawn();
  }

  @Override
  public Iterator<Actor> iterator() {
    return actors.iterator();
  }

  public void spawn(){

  }

  public Array<Actor> getActors(){return actors;}
}
