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
	RECORDS_BUTTON("EndlessRoad/Buttons/recordsbutton.png"),
	QUIT_BUTTON("EndlessRoad/Buttons/quitbutton.png"),
	REPLAY_BUTTON("EndlessRoad/Buttons/replaybutton.png"),
	ACCELERATOR_BUTTON("EndlessRoad/Buttons/accelerator.png"),
	BRAKE_BUTTON("EndlessRoad/Buttons/brake.png"),
	LEFT_ARROW("EndlessRoad/Buttons/left_arrow.png"),
	RIGHT_ARROW("EndlessRoad/Buttons/right_arrow.png"),
	SPEAKER("EndlessRoad/Buttons/speaker.png"),
	MUTE("EndlessRoad/Buttons/mute.png"),
	MUSIC_GREEN("EndlessRoad/Buttons/music_green.png"),
	MUSIC_RED("EndlessRoad/Buttons/music_red.png"),
	
	
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
	RECORDS("EndlessRoad/Logos/records.png"),
	CREDITS("EndlessRoad/Logos/credits.png"),
	GAME_OVER("EndlessRoad/Logos/gameover.png"),
	
	//Miscellaneous
	CROWN("EndlessRoad/Miscellaneous/crown.png"),
	
	//Sounds
	ENGINE_ON("EndlessRoad/Sounds/engine_on.mp3"),
	CRASH("EndlessRoad/Sounds/crash.mp3"),
	IDLE("EndlessRoad/Sounds/idle.ogg"),
	BRAKE("EndlessRoad/Sounds/brake.mp3"),
	MENU_LOOP("EndlessRoad/Sounds/menu_loop.ogg"),
	MUSIC_LOOP("EndlessRoad/Sounds/loop.ogg"),
	GAMEOVER_LOOP("EndlessRoad/Sounds/gameover_loop.ogg");
	
	
	
	private String path;
	
	private AssetsPaths(String path) {this.path = path;}
	
	public String getPath() {return path;}
	
}
