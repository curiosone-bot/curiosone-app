package com.github.bot.curiosone.app.games.airborneassault.settings;

/**
 * Enumerates the game objects speeds
 */
public enum Speed {
  BACKGROUND(70),
  PLANE(150),
  KAMIKAZE(170),
  TANK(130),
  STEALTH(220),
  FASTPLANE(220),
  HEALTHPACK(85),
  ;

  private int speed;
  Speed(int i) {
    speed = i;
  }
  public int getSpeed() {
    return speed;
  }
}
