package com.github.bot.curiosone.app.games.endlessroad.worldmanager;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.games.endlessroad.entities.Car;
import com.github.bot.curiosone.app.games.endlessroad.entities.Player;
import com.github.bot.curiosone.app.games.endlessroad.scenes.GameOver;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsLoader;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsPaths;
import com.github.bot.curiosone.app.games.endlessroad.utilities.CarsSpawner;
import com.github.bot.curiosone.app.games.endlessroad.utilities.GameInfos;




public class WorldController
{
	protected Chat game;
    protected SpriteBatch batch;
    protected OrthographicCamera camera,hudCamera;
    protected Viewport viewport,hudViewport;
    protected Stage stage,hudStage,buttonsStage,pedalsStage;
    protected long idleID;
    protected Sound idle;
    protected Music loop;
    protected Preferences preferences;
    protected boolean crashed;
    protected CarsSpawner carsSpawner;
    protected boolean acceleratorDown,brakeDown;

	public WorldController(Chat game)
	{
		this.game = game;
    this.batch = new SpriteBatch();
		init();
	}

	private void init()
	{
        this.game = game;

        GameInfos.gameOver = false;
        GameInfos.lastScore = 0;
        GameInfos.lastDistance = 0;
        GameInfos.newScoreRecord = false;
        GameInfos.newDistanceRecord = false;

        preferences = Gdx.app.getPreferences("");
        AssetsLoader.getInstance().loadGameplayAssets();
        loop = AssetsLoader.getInstance().getManager().get(AssetsPaths.MUSIC_LOOP.getPath(),Music.class);
        idle = AssetsLoader.getInstance().getManager().get(AssetsPaths.IDLE.getPath(),Sound.class);

        float waitTime = (GameInfos.startFromMainMenu && !GameInfos.muteFX)? 0.5f:0;

    	if (!GameInfos.loopPlaying && !GameInfos.muteMusic)
    	{
    		Timer.schedule(new Task()
    		{
    			@Override
    			public void run()
    			{
    				loop.setVolume(0.1f);
        			loop.setLooping(true);
        			loop.play();
    			}
    		}, waitTime);
    	}

    	if (!GameInfos.idlePlaying && !GameInfos.muteFX)
		{
    		Timer.schedule(new Task()
    		{

    			@Override
    			public void run()
    			{
    				GameInfos.idlePlaying = true;
    	    		idleID = idle.loop(0.2f,0.5f,0);

    			}
    		}, 0.1f);

		}


        camera = new OrthographicCamera(GameInfos.WIDTH,GameInfos.HEIGHT);
        camera.position.set(GameInfos.WIDTH/2f,GameInfos.HEIGHT/2f,0);
        viewport = new StretchViewport(GameInfos.WIDTH,GameInfos.HEIGHT,camera);
        stage = new Stage(viewport,batch);



        Player.getInstance().setX(GameInfos.Lanes.CENTRE_RIGHT.getX()+2f);
        Player.getInstance().setY(10f);
        Player.getInstance().setSpeed(GameInfos.MIN_PLAYER_SPEED);
        Player.getInstance().addToStage(stage);

        carsSpawner = new CarsSpawner(stage);
        hudCamera = new OrthographicCamera(GameInfos.WIDTH,GameInfos.HEIGHT);
        hudCamera.position.set(GameInfos.WIDTH/2f,GameInfos.HEIGHT/2f,0);
        hudViewport = new StretchViewport(GameInfos.WIDTH,GameInfos.HEIGHT,hudCamera);
        hudStage = new Stage(hudViewport,batch);

        switch (Gdx.app.getType())
        {
            case Android:
            	buttonsStage = new Stage(hudViewport,batch);
            	pedalsStage = new Stage(hudViewport,batch);
            	InputMultiplexer im = new InputMultiplexer(buttonsStage,pedalsStage);
            	Gdx.input.setInputProcessor(im);
            	break;

            case Desktop:
            	Gdx.input.setInputProcessor(stage);
                stage.setKeyboardFocus(stage.getActors().first());
                break;

        }

	}

	/**
     * Updates all the elements in the scene
     * @param deltaTime The time gap between the previous and the current frame
     */
    public void update(float deltaTime)
    {
        carsSpawner.setCameraY(camera.position.y);
        carsSpawner.rearrangeCars();
        handleCollisions();

        switch(Gdx.app.getType())
        {

        	case Android:
        		if (acceleratorDown && !(brakeDown) && Player.getInstance().getSpeed() < GameInfos.MAX_PLAYER_SPEED)
                {
                	Player.getInstance().setSpeed(Player.getInstance().getSpeed() + 0.20f);
                    if (camera.zoom < 1.25f) camera.zoom += 0.00100f;
                }

                else if (brakeDown && !(acceleratorDown) && Player.getInstance().getSpeed() > GameInfos.MIN_PLAYER_SPEED)
                {
                	Player.getInstance().setSpeed(Player.getInstance().getSpeed() - 0.60f);
                	if(camera.zoom > 1.0) camera.zoom -= 0.00300f;
                }


                else if (!(brakeDown) && !(acceleratorDown) && Player.getInstance().getSpeed() > GameInfos.MIN_PLAYER_SPEED)
                {
                	Player.getInstance().setSpeed(Player.getInstance().getSpeed() - 0.030f);
                	if(camera.zoom > 1.0) camera.zoom -= 0.000150f;
                }

        		break;

        	case Desktop:
        		boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        		boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        		if (upPressed && !(downPressed) && Player.getInstance().getSpeed() < GameInfos.MAX_PLAYER_SPEED)
                {
                    Player.getInstance().setSpeed(Player.getInstance().getSpeed() + 0.20f);
                    if (camera.zoom < 1.25f) camera.zoom += 0.00100f;
                }

                else if (downPressed && Player.getInstance().getSpeed() > GameInfos.MIN_PLAYER_SPEED)
                {
                    Player.getInstance().setSpeed(Player.getInstance().getSpeed() - 0.60f);
                    if(camera.zoom > 1.0) camera.zoom -= 0.00300f;
                }

                else if (!(downPressed) && !(upPressed) && Player.getInstance().getSpeed() > GameInfos.MIN_PLAYER_SPEED)
                {
                	Player.getInstance().setSpeed(Player.getInstance().getSpeed() - 0.030f);
                	if(camera.zoom > 1.0) camera.zoom -= 0.000150f;
                }

        		break;

        }


        if (((int)(Player.getInstance().getSpeed()*2)+30f) % 3 == 0) idle.setPitch(idleID,(Player.getInstance().getSpeed()-25f)/25f+1);


    }

    /**
     * Updates the camera's position
     */
    public void moveCamera()
    {
        camera.position.set(new Vector2(GameInfos.WIDTH/2f,Player.getInstance().getY()+GameInfos.HEIGHT/2f+Player.getInstance().getSpeed()*(Player.getInstance().getSpeed()/25f)),0);
    }




    /**
     * Handles the collisions between the cars
     */
    private void handleCollisions()
    {
        for (Car c: carsSpawner.getCars())
        {

            if (c.getBounds().overlaps(Player.getInstance().getBounds()))
            {
            	Sound sound = AssetsLoader.getInstance().getManager().get(AssetsPaths.CRASH.getPath(),Sound.class);
            	if (!GameInfos.muteFX && !crashed)
            	{
            		crashed = true;
            		sound.play(0.5f);
            	}
            	GameInfos.gameOver = true;
            	GameInfos.idlePlaying = false;
            	idle.stop();
            	GameInfos.loopPlaying = false;
            	loop.stop();
            	Player.getInstance().clearActions();

                camera.position.set(GameInfos.WIDTH/2f,GameInfos.HEIGHT/2f,0);
                if (GameInfos.lastScore > preferences.getInteger("scoreRecord"))
                {
                	preferences.putInteger("scoreRecord",GameInfos.lastScore);
                	preferences.flush();
                	GameInfos.newScoreRecord = true;

                }


                if (GameInfos.lastDistance > preferences.getFloat("distanceRecord"))
                {
                	preferences.putFloat("distanceRecord",GameInfos.lastDistance).flush();
                	preferences.flush();
                	GameInfos.newDistanceRecord = true;

                }

                game.setScreen(new GameOver(game));
                break;

            }


            if (c.getBounds().overlaps(
            new Rectangle((Player.getInstance().getBounds().getX()-Player.getInstance().getHeight()/4f),Player.getInstance().getBounds().getY(),Player.getInstance().getWidth() + Player.getInstance().getWidth(),Player.getInstance().getHeight())))
            {
            	if (Player.getInstance().getSpeed()>30 && Player.getInstance().getSpeed() < 50) GameInfos.lastScore += 5f;

            	else if (Player.getInstance().getSpeed() >= 50 && Player.getInstance().getSpeed()<GameInfos.MAX_PLAYER_SPEED)
            		GameInfos.lastScore += 10f;

            	else if (Player.getInstance().getSpeed() > GameInfos.MAX_PLAYER_SPEED -3f)
            		GameInfos.lastScore += 20f;
            }

        }

    }
}
