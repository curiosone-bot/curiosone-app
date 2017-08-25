package com.github.bot.curiosone.app.games.airborneassault.settings;

/**
 * Enumerates the X limit where the objects can be spawned according to their width.
 */
public enum Bounds {
  FASTPLANE(362),
  PLANE(362),
  TANK(350),
  KAMIKAZE(380),
  STEALTH(320),
  ALLIED(390),
  ;

  private int bound;
  Bounds(int bound){
    this.bound = bound;
  }
  public int getBound(){return bound;}
}
