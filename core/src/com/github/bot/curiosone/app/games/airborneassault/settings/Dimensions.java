package com.github.bot.curiosone.app.games.airborneassault.settings;

/**
 * Enumerates the gameobjects dimensions
 */
public enum Dimensions {
  PLANE(118,200),
  STEALTH(160,120),
  TANK(130,220),
  KAMIKAZE(120,180),
  ALLIED(110,180),
  FASTPLANE(118,200),
  HEALTHPACK(60,60),
  ;


  private final int width;
  private final int height;

  Dimensions(int width, int height) {
    this.width = width;
    this.height = height;
  }


  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
