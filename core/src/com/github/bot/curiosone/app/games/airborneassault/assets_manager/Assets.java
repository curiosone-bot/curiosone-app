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
  playBackground("airborneassaultassets/backgrounds/background1.png"),
  playBackground2("airborneassaultassets/backgrounds/background2.png"),
  playBackground3("airborneassaultassets/backgrounds/background3.png"),
  playMusic(""),
  baseBar("airborneassaultassets/basebar.png"),
  healthBar("airborneassaultassets/healthbar.png"),
  //Planes Textures
  planeUp("airborneassaultassets/planetextures/planetextures/plane.txt"),
  planeDown("airborneassaultassets/planetextures/planetextures/plane_exposion.txt"),
  fastPlane("airborneassaultassets/planetextures/fastplanetextures/fastplanebase.png"),
  fastPlaneDown("airborneassaultassets/planetextures/fastplanetextures/fastplaneAnimation.txt"),
  kamikaze("airborneassaultassets/planetextures/kamikaze.png"),
  kamikazeDown("airborneassaultassets/planetextures/explosion.png"),
  stealth("airborneassaultassets/planetextures/stealthtextures/stealth_base.png"),
  stealthDown("airborneassaultassets/planetextures/stealthtextures/stealth_explosion.txt"),
  stealthInvisible("airborneassaultassets/planetextures/stealthtextures/stealth.txt"),
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
