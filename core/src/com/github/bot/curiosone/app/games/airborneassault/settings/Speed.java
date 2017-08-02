package com.github.bot.curiosone.app.games.airborneassault.settings;

public enum Speed {
  BACKGROUND(50),
  PLANE(120),
  KAMIKAZE(150),
  TANK(100),
  STEALTH(170),
  FASTPLANE(190),
  HEALTHPACK(50),
  ;



  private int speed;
  Speed(int i) {
    speed = i;
  }
  public int getSpeed() {
    return speed;
  }
}
