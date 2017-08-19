package com.github.bot.curiosone.app.games.airborneassault.spawner;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.github.bot.curiosone.app.games.airborneassault.gameobjects.*;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.settings.Bounds;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Alessandro Roic
 * This class is designed to spawn the common planes
 */
public class PlaneSpawner implements Iterable<Actor>{

  private Array<Actor> actors;
  private Settings settings;
  private Random random;

  public PlaneSpawner() {
    Manager manager = Manager.getIstance();
    manager.loadPlaneSpawer();
    settings = Settings.getIstance();
    actors = new Array<Actor>();
    random = new Random();
    spawn();
  }

  @Override
  public Iterator<Actor> iterator() {
    return actors.iterator();
  }

  public void spawn(){
   for(int i=0;i<10;i++){
      actors.add(new FastPlane(new Random().nextInt(Bounds.FASTPLANE.getBound())));
   }
   for(int i=0;i<15;i++){
     actors.add(new Plane(new Random().nextInt(Bounds.PLANE.getBound())));
   }
   actors.add(new TankPlane(random.nextInt(Bounds.TANK.getBound())));
   actors.add(new AlliedPlane(random.nextInt(Bounds.ALLIED.getBound())));
   actors.add(new StealthPlane(random.nextInt(Bounds.STEALTH.getBound())));

   for(int i=0;i<3;i++){
     actors.shuffle();
   }

  }

  public Array<Actor> getActors(){
    return actors;
  }
}
