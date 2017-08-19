package com.github.bot.curiosone.app.games.airborneassault.settings;

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
