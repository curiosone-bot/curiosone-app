package com.github.bot.curiosone.app.games.airborneassault.settings;

public enum  Points {
  PLANE(60),
  KAMIKAZE(80),
  TANK(70),
  STEALTH(110),
  FASTPLANE(90),
    ;



  private int points;

  Points(int i) {
    points = i;
  }

  public int getPoints() {
    return points;
  }
}
