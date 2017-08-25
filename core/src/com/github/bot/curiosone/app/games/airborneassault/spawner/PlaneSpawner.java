package com.github.bot.curiosone.app.games.airborneassault.spawner;

import com.badlogic.gdx.Gdx;
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
 * This class is designed to spawn the planes
 */
public class PlaneSpawner implements Iterable<Actor>{

    private Array<Actor> actors;
    private Settings settings;
    private Random random;
    private int spawnType;

    public PlaneSpawner() {
      Manager manager = Manager.getIstance();
      settings = Settings.getIstance();
      actors = new Array<Actor>();
      random = new Random();
      spawn();
    }

    /**
     * Randomly Spawn a Set
     */
    public void spawn() {
        spawnType = random.nextInt(5);
        switch (spawnType){
          case 0: spawn1();
            break;
          case 1: spawn2();
            break;
          case 2: spawn3();
            break;
          case 3: spawn4();
            break;
          case 4: spawn5();
            break;
        }
    }

    private void spawn1(){
     Gdx.app.log("Spawned","SET-1");
     for(int i=0;i<10;i++){
        actors.add(new FastPlane(random.nextInt(Bounds.FASTPLANE.getBound())));
     }
     for(int i=0;i<15;i++){
       actors.add(new Plane(random.nextInt(Bounds.PLANE.getBound())));
     }
     actors.add(new TankPlane(random.nextInt(Bounds.TANK.getBound())));
     actors.add(new AlliedPlane(random.nextInt(Bounds.ALLIED.getBound())));
     actors.add(new StealthPlane(random.nextInt(Bounds.STEALTH.getBound())));

     for(int i=0;i<3;i++){
       actors.shuffle();
      }
    }

    private void spawn2(){
      Gdx.app.log("Spawned","SET-2");
      for(int i=0;i<5;i++){
        actors.add(new Plane(random.nextInt(Bounds.PLANE.getBound())));
      }
      for(int i=0;i<2;i++){
        actors.add(new StealthPlane(random.nextInt(Bounds.STEALTH.getBound())));
        actors.add(new AlliedPlane(random.nextInt(Bounds.ALLIED.getBound())));
      }
      for(int i=0;i<3;i++){
        actors.add(new TankPlane(random.nextInt(Bounds.TANK.getBound())));
      }
      for(int i=0;i<15;i++){
        actors.add(new FastPlane(random.nextInt(Bounds.FASTPLANE.getBound())));
      }

      for(int i=0;i<3;i++){
        actors.shuffle();
      }
    }

    private void spawn3(){
      Gdx.app.log("Spawned","SET-3");
      for(int i=0;i<5;i++){
        //KAMIKAZE
        actors.add(new Plane(random.nextInt(Bounds.KAMIKAZE.getBound())));
      }
      for(int i=0;i<10;i++){
        actors.add(new FastPlane(random.nextInt(Bounds.FASTPLANE.getBound())));
      }
      for(int i=0;i<5;i++){
        actors.add(new AlliedPlane(random.nextInt(Bounds.ALLIED.getBound())));
      }
      for(int i=0;i<2;i++){
        actors.add(new StealthPlane(random.nextInt(Bounds.STEALTH.getBound())));
      }
      actors.add(new TankPlane(random.nextInt(Bounds.TANK.getBound())));
      for(int i=0;i<3;i++){
        actors.shuffle();
      }
    }

    private void spawn4(){
      Gdx.app.log("Spawned","SET-4");
      for(int i=0;i<15;i++){
          actors.add(new AlliedPlane(random.nextInt(Bounds.ALLIED.getBound())));
      }
      for(int i=0;i<10;i++){
          actors.add(new FastPlane(random.nextInt(Bounds.FASTPLANE.getBound())));
      }
      actors.add(new TankPlane(random.nextInt(Bounds.TANK.getBound())));
      actors.add(new Plane(random.nextInt(Bounds.PLANE.getBound())));
      actors.add(new KamikazePlane(random.nextInt(Bounds.KAMIKAZE.getBound())));
      for(int i=0;i<3;i++){
        actors.shuffle();
      }
    }

    private void spawn5(){
      Gdx.app.log("Spawned","SET-5");
      for(int i=0;i<10;i++){
          actors.add(new AlliedPlane(random.nextInt(Bounds.ALLIED.getBound())));
      }
      for(int i=0;i<10;i++){
          actors.add(new KamikazePlane(random.nextInt(Bounds.KAMIKAZE.getBound())));
      }
      for(int i=0;i<8;i++){
        actors.add(new Plane(random.nextInt(Bounds.PLANE.getBound())));
      }
      for(int i=0;i<3;i++){
        actors.shuffle();
      }
    }

    public Array<Actor> getActors(){
      return actors;
    }

    @Override
    public Iterator<Actor> iterator() {
      return actors.iterator();
    }

}
