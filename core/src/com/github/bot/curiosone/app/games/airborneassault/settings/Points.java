package com.github.bot.curiosone.app.games.airborneassault.settings;

public enum  Points {
  PLANE(50),
  KAMIKAZE(70),
  TANK(60),
  STEALTH(100),
  FASTPLANE(80),
    ;



  private int points;

  Points(int i) {
    points = i;
  }

  public int getPoints() {
    return points;
  }
}
