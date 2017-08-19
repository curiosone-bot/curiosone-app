package com.github.bot.curiosone.app.games.airborneassault.settings;

public enum Amount {
  PLANE(100),
  ALLIED(180),
  KAMIKAZE(140),
  TANK(160),
  STEALTH(200),
  FASTPLANE(160),
  HEALTHPACK1(25),
  HEALTHPACK2(50),
  HEALTHPACK3(75),
  ;

  private int amount;
  Amount(int i) {
    amount = i;
  }
  public int getAmount(){
    return amount;
  }
}
