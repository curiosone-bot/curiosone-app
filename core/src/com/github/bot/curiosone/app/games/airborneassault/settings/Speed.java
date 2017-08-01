package com.github.bot.curiosone.app.games.airborneassault.settings;

public enum Speed {
  BACKGROUND(50),
  PLANE(100),
  KAMIKAZE(130),
  TANK(80),
  STEALTH(150),
  FASTPLANE(150)
  ;

  public int getSpeed() {
    return speed;
  }

  private int speed;
  Speed(int i) {
    speed = i;
  }
}
