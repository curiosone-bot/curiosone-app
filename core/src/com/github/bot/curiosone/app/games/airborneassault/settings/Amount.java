package com.github.bot.curiosone.app.games.airborneassault.settings;

public enum Amount {
  KAMIKAZE(50),
  TANK(60),
  STEALTH(80),
  HEALTHPACK1(25),
  HEALTHPACK2(50),
  HEALTHPACK3(75),
  HEALTHPACK4(100),
  ;

  private int amount;
  Amount(int i) {
    amount = i;
  }
  public int getAmount(){
    return amount;
  }
}
