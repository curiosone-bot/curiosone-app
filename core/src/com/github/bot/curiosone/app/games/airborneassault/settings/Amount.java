package com.github.bot.curiosone.app.games.airborneassault.settings;

public enum Amount {
  ALLIED(70),
  KAMIKAZE(50),
  TANK(60),
  STEALTH(80),
  FASTPLANE(60),
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
