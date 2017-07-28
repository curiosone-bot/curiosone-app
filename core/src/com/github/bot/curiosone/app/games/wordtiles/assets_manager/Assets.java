package com.github.bot.curiosone.app.games.wordtiles.assets_manager;

public enum Assets {
  //Tiles
  tile("crazytiles/TilesTextures/tile.png"),
  tileTouched("crazytiles/TilesTextures/Tile_Touched.png"),
  bombTileTouched("crazytiles/TilesTextures/Wrong_Tile_Touched.png"),
  //SFX
  hit("crazytiles/Sound Effects/Hit.wav"),
  click("crazytiles/Sound Effects/Click.wav"),
  //Fonts
  font("crazytiles/Font/lexie.fnt"),
  //Checkboxs
  checkbox("crazytiles/checkbox-textures/blue_boxCheckmark.png"),
  checkbox2("crazytiles/checkbox-textures/blue_boxCheckmark2.png"),
  //Buttons
  buttonOn("crazytiles/ButtonTextures/button1.png"),
  buttonOff("crazytiles/ButtonTextures/button2.png"),
  //Backgorund
  background("crazytiles/Background.png"),
  gameOver("crazytiles/GameOver.png"),
  playBackground("crazytiles/playbackground.png"),
  cleanBackground("crazytiles/Background_Clean.jpg"),
  //Music
  musicMenu("crazytiles/Songs/Five Card Shuffle.mp3"),
  playMusic("crazytiles/Songs/Funky Chunk.mp3"),
  gameOverMusic("crazytiles/Sound Effects/GameOver.mp3");
  private final String path;

  Assets(String s) {
    path = s;
  }
  public String getPath(){return path;}
}
