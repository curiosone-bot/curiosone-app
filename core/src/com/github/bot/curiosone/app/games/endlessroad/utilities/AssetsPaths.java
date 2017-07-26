package com.github.bot.curiosone.app.games.endlessroad.utilities;

/**
 * Here are stored the paths to all the game'assets
 * @author Paolo Pierantozzi
 *
 */
public enum AssetsPaths
{
	//Backgrounds
	MENUS_BG("EndlessRoad/Backgrounds/menus-bg.png"),
	ROAD("EndlessRoad/Backgrounds/road.png"),
	
	//Buttons
	BACK_BUTTON("EndlessRoad/Buttons/backbutton.png"),
	CREDITS_BUTTON("EndlessRoad/Buttons/creditsbutton.png"),
	MENU_BUTTON("EndlessRoad/Buttons/menubutton.png"),
	PLAY_BUTTON("EndlessRoad/Buttons/playbutton.png"),
	QUIT_BUTTON("EndlessRoad/Buttons/quitbutton.png"),
	REPLAY_BUTTON("EndlessRoad/Buttons/replaybutton.png"),
	
	//Cars
	PLAYER("EndlessRoad/Cars/player.png"),
	CAR1("EndlessRoad/Cars/car1.png"),
	CAR2("EndlessRoad/Cars/car2.png"),
	CAR3("EndlessRoad/Cars/car3.png"),
	CAR4("EndlessRoad/Cars/car4.png"),
	CAR5("EndlessRoad/Cars/car5.png"),
	CAR6("EndlessRoad/Cars/car6.png"),
	CAR7("EndlessRoad/Cars/car7.png"),
	CAR8("EndlessRoad/Cars/car8.png"),
	CAR9("EndlessRoad/Cars/car9.png"),

	//Fonts
	AGENCY_FB("EndlessRoad/Fonts/Agency FB.fnt"),
	
	//Logos
	ENDLESS_ROAD("EndlessRoad/Logos/endlessroad.png"),
	CREDITS("EndlessRoad/Logos/credits.png"),
	GAME_OVER("EndlessRoad/Logos/gameover.png");
	
	private String path;
	
	private AssetsPaths(String path) {this.path = path;}
	
	public String getPath() {return path;}
	
}
