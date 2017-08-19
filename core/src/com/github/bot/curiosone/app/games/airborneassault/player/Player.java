package com.github.bot.curiosone.app.games.airborneassault.player;

import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;

public class Player {
  private static Player player;
  private Settings settings;
  private String name;
  private static int CHANGED = 1000;
  private static boolean isDead = false;
  private static int CURRENT_HEALTH = 1000;
  private static final int MAX_HEALTH = 1000;

  private Player() {
    settings = Settings.getIstance();
  }

  public static Player getIstance(){
    if(player==null) {player = new Player();}
    return player;
  }

  public static boolean isDead() {
    return isDead;
  }

  public static void damage(int amount){
    if(CURRENT_HEALTH -amount<=0){
      CURRENT_HEALTH =0;
      isDead = true;
    }
    else {
      CURRENT_HEALTH -=amount;
    }
  }
  public static void heal(int amount){
    if(CURRENT_HEALTH +amount<1000){
    CURRENT_HEALTH +=amount;
    }
    else {
      CURRENT_HEALTH =1000;
    }
  }
  public static int getPlayerHealth(){
    return CURRENT_HEALTH;
  }

  public static int getMaxHealth() {
    return MAX_HEALTH;
  }

  public static boolean isChanged(){
    if(CURRENT_HEALTH!= CHANGED){
      CHANGED = CURRENT_HEALTH;
      return true;
    }
    return false;
  }
  public static void reset(){
    CURRENT_HEALTH = 1000;
    isDead = false;
    CHANGED = 1000;
  }
}
