package com.github.bot.curiosone.app.games.airborneassault.assets_manager;

/**
 * This enum contains all the assets paths
 */
public enum Assets {
  //Main Menu Assets
  menubackground("airborneassaultassets/backgrounds/menubackground.png"),
  playOn("airborneassaultassets/buttons/PLAY1.png"),
  playOff("airborneassaultassets/buttons/PLAY2.png"),
  optionOn("airborneassaultassets/buttons/options1.png"),
  optionOff("airborneassaultassets/buttons/options2.png"),
  exitOn("airborneassaultassets/buttons/exit1.png"),
  exitOff("airborneassaultassets/buttons/exit2.png"),
  menuMusic("airborneassaultassets/music/Volatile Reaction.mp3"),
  click("airborneassaultassets/sound_effects/clickButton.mp3"),
  font("airborneassaultassets/font/optionfont.fnt"),
  //Option Menu Assets
  checkbox("airborneassaultassets/checkbox/if_checkbox-checked_83249.png"),
  checkbox2("airborneassaultassets/checkbox/if_checkbox-unchecked_83251.png"),
  optionBackground("airborneassaultassets/backgrounds/optionbackground.png"),
  //Plane SFX
  hit("airborneassaultassets/sound_effects/Plane_Explosion_1.mp3"),
  hit2("airborneassaultassets/sound_effects/Plane_Explosion_2.mp3"),
  hit3("airborneassaultassets/sound_effects/Plane_Explosion_3.mp3"),
  shot("airborneassaultassets/sound_effects/shot.mp3"),
  //Play Screen Assets
  playBackground("airborneassaultassets/backgrounds/background1.png"),
  playBackground2("airborneassaultassets/backgrounds/background2.png"),
  playBackground3("airborneassaultassets/backgrounds/background3.png"),
  playMusic("airborneassaultassets/music/Clash Defiant.mp3"),
  baseBar("airborneassaultassets/basebar.png"),
  healthBar("airborneassaultassets/healthbar.png"),
  backButton("airborneassaultassets/ui/backButton.png"),
  continueButton("airborneassaultassets/ui/continue.png"),
  continueButtonPressed("airborneassaultassets/ui/continue2.png"),
  backToButton("airborneassaultassets/ui/back.png"),
  backToButtonPressed("airborneassaultassets/ui/back2.png"),
  //Planes Textures
  planeUp("airborneassaultassets/planetextures/planetextures/plane.txt"),
  planeDown("airborneassaultassets/planetextures/planetextures/plane_exposion.txt"),
  fastPlane("airborneassaultassets/planetextures/fastplanetextures/fastplanebase.png"),
  fastPlaneDown("airborneassaultassets/planetextures/fastplanetextures/fastplaneAnimation.txt"),
  kamikaze("airborneassaultassets/planetextures/kamikazetextures/kamikaze.txt"),
  kamikazeDown("airborneassaultassets/planetextures/kamikazetextures/kamikaze_explosion.txt"),
  kamikazeSelfExplosion("airborneassaultassets/planetextures/kamikazetextures/kamikaze_self_explosion.txt"),
  stealth("airborneassaultassets/planetextures/stealthtextures/stealth_base.png"),
  stealthDown("airborneassaultassets/planetextures/stealthtextures/stealth_explosion.txt"),
  stealthInvisible("airborneassaultassets/planetextures/stealthtextures/stealth.txt"),
  allied("airborneassaultassets/planetextures/alliedtextures/allied.png"),
  alliedDown("airborneassaultassets/planetextures/alliedtextures/allied_explosion.txt"),
  tankDown("airborneassaultassets/planetextures/tankplanetextures/tank_explosion.txt"),
  //HealthPack
  healthPack("airborneassaultassets/planetextures/healthpack/healthpack.png"),
  //Game Over Assets
  gameOverBackground("airborneassaultassets/backgrounds/gameoverbackground.png"),
  gameOverMusic(""),
  ;
  private final String path;

  Assets(String s) {
    path = s;
  }
  public String getPath(){return path;}
}
