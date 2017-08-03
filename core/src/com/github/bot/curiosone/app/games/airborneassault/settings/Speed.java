package com.github.bot.curiosone.app.games.airborneassault.settings;

public enum Speed {
  BACKGROUND(60),
  PLANE(130),
  KAMIKAZE(160),
  TANK(110),
  STEALTH(200),
  FASTPLANE(200),
  HEALTHPACK(65),
  ;



  private int speed;
  Speed(int i) {
    speed = i;
  }
  public int getSpeed() {
    return speed;
  }
}
