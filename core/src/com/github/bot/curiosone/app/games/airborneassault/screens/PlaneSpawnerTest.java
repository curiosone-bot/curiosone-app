package com.github.bot.curiosone.app.games.airborneassault.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.gameobjects.*;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
import java.util.Iterator;
import java.util.Random;

public class PlaneSpawnerTest implements Iterable<Actor> {
  private Array<Actor> actors;
  private Settings settings;
  private Manager manager;

  /**
   * Spawn the tiles according to the game difficulty
   */
  public PlaneSpawnerTest() {
    manager = Manager.getIstance();
    manager.loadPlaneSpawer();
    settings = Settings.getIstance();
    actors = new Array<Actor>();
    actors.add(new StealthPlane(200));
  }

  @Override
  public Iterator<Actor> iterator() {
    return actors.iterator();
  }
}
