package com.github.bot.curiosone.app.games.airborneassault.assets_manager;

public enum Assets {
  //Main Menu Assets
  menubackground("airborneassaultassets/backgrounds/menubackground.png"),
  buttonOn("airborneassaultassets/buttonUp.png"),
  buttonOff("airborneassaultassets/buttonUp2.png"),
  menuMusic(""),
  click(""),
  font("airborneassaultassets/font/optionfont.fnt"),
  //Option Menu Assets
  checkbox("airborneassaultassets/checkbox/if_checkbox-checked_83249.png"),
  checkbox2("airborneassaultassets/checkbox/if_checkbox-unchecked_83251.png"),
  cleanBackground("airborneassaultassets/Immagine.png"),
  //Plane Assets
  hit(""),
  //Play Screen Assets
  playBackground("airborneassaultassets/Immagine.png"),
  playMusic(""),
  baseBar("airborneassaultassets/basebar.png"),
  healthBar("airborneassaultassets/healthbar.png"),
  //Planes Textures
  planeUp("airborneassaultassets/planetextures/plane.png"),
  planeDown("airborneassaultassets/planetextures/explosion.png"),
  fastPlane("airborneassaultassets/planetextures/fastplanetextures/fastplanebase.png"),
  fastPlaneDown("airborneassaultassets/planetextures/fastplanetextures/fastplaneAnimation.txt"),
  kamikaze("airborneassaultassets/planetextures/kamikaze.png"),
  kamikazeDown("airborneassaultassets/planetextures/explosion.png"),
  stealth("airborneassaultassets/planetextures/stealth.png"),
  stealthDown("airborneassaultassets/planetextures/explosion.png"),
  allied("airborneassaultassets/planetextures/allied.png"),
  alliedDown("airborneassaultassets/planetextures/explosion.png"),
  //HealthPack
  healthPack("airborneassaultassets/planetextures/healthpack/healthpack.png"),
  //Game Over Assets
  gameOverBackground("airborneassaultassets/Immagine.png"),
  gameOverMusic(""),
  ;
  private final String path;

  Assets(String s) {
    path = s;
  }
  public String getPath(){return path;}
}
