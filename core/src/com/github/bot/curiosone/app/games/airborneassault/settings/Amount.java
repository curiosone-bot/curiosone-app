package com.github.bot.curiosone.app.games.airborneassault.settings;

/**
 * Enumerates the Damage or Heal amount.
 */
public enum Amount {
  PLANE(100),
  ALLIED(180),
  KAMIKAZE(140),
  TANK(160),
  STEALTH(200),
  FASTPLANE(160),
  HEALTHPACK1(50),
  HEALTHPACK2(100),
  HEALTHPACK3(150),
  ;

  private int amount;
  Amount(int i) {
    amount = i;
  }
  public int getAmount(){
    return amount;
  }
}
