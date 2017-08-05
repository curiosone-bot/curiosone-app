package com.github.bot.curiosone.app.games.airborneassault.settings;

public enum Amount {
  PLANE(50),
  ALLIED(90),
  KAMIKAZE(70),
  TANK(80),
  STEALTH(100),
  FASTPLANE(80),
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
