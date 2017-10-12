package com.github.bot.curiosone.app.games.endlessroad.worldmanager;

import java.text.DecimalFormat;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.games.endlessroad.entities.Player;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsLoader;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsPaths;
import com.github.bot.curiosone.app.games.endlessroad.utilities.GameInfos;



public class WorldRenderer implements Disposable
{
	private Chat game;
  private SpriteBatch batch;
	private WorldController controller;
	private OrthographicCamera camera,hudCamera;
	private Sprite[] backgrounds;
	private Label distanceLabel,scoreLabel,speedLabel,distanceValue,scoreValue,speedValue;
	private ImageButton accelerator,brake,leftArrow,rightArrow;
	private float lastBackgroundY;


	public WorldRenderer(Chat game,WorldController controller)
	{
		this.game = game;
    this.batch = new SpriteBatch();
		this.controller = controller;
		init();
	}

	private void init()
	{
		camera = controller.camera;
		hudCamera = controller.hudCamera;
		backgrounds = new Sprite[3];
        createBackgrounds();
        createHud();
        if (Gdx.app.getType().equals(ApplicationType.Android)) createTouchControls();
	}

	/**
     * Creates and positions the hud elements on the screen
     */
    private void createHud()
	{
		BitmapFont infosFont = AssetsLoader.getInstance().getManager().get(AssetsPaths.AGENCY_FB.getPath());
		infosFont.getData().setScale(1f);

        BitmapFont digitsFont = AssetsLoader.getInstance().getManager().get(AssetsPaths.AGENCY_FB.getPath());
        digitsFont.getData().setScale(0.8f);

		distanceLabel = new Label("DISTANCE:",new Label.LabelStyle(infosFont,Color.WHITE));
		scoreLabel = new Label("SCORE:",new Label.LabelStyle(infosFont,Color.WHITE));
		speedLabel = new Label("SPEED:",new Label.LabelStyle(infosFont,Color.WHITE));

        distanceValue = new Label("0",new Label.LabelStyle(digitsFont,Color.WHITE));
        scoreValue = new Label("0",new Label.LabelStyle(digitsFont,Color.WHITE));
        speedValue = new Label("0",new Label.LabelStyle(digitsFont,Color.WHITE));



		Table distanceAndScoreTable = new Table();
    	distanceAndScoreTable.top().left();
    	distanceAndScoreTable.setFillParent(true);
		distanceAndScoreTable.add(distanceLabel).padTop(10f).padLeft(10f);
		distanceAndScoreTable.row();
		distanceAndScoreTable.add(distanceValue).padLeft(10f).align(Align.topLeft);
        distanceAndScoreTable.row();
        distanceAndScoreTable.add(scoreLabel).padLeft(10f).align(Align.topLeft);
        distanceAndScoreTable.row();
        distanceAndScoreTable.add(scoreValue).padLeft(10f).align(Align.topLeft);

		controller.hudStage.addActor(distanceAndScoreTable);

		Table speedTable = new Table();
		speedTable.top().right();
    	speedTable.setFillParent(true);
    	speedTable.add(speedLabel).padTop(10f).padRight(30f);
        speedTable.row();
        speedTable.add(speedValue).padTop(10f).padRight(80f).width(50f);
    	controller.hudStage.addActor(speedTable);

	}

    /**
     * Creates and positions the touch-screen controls
     */
    private void createTouchControls()
    {
    	Table buttons = new Table();
    	buttons.bottom().left();
    	buttons.padBottom(100f);
    	buttons.padLeft(10f);
    	buttons.setFillParent(true);


    	Table pedals= new Table();
    	pedals.bottom();
    	pedals.padBottom(10f);
    	pedals.padLeft(50f);
    	pedals.setFillParent(true);


    	accelerator = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.ACCELERATOR_BUTTON.getPath(),Texture.class))));
        brake = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.BRAKE_BUTTON.getPath(),Texture.class))));

        leftArrow = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.LEFT_ARROW.getPath(),Texture.class))));
        rightArrow = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.RIGHT_ARROW.getPath(),Texture.class))));

        accelerator.addListener(new InputListener()
        {
        	@Override
        	public boolean touchDown(InputEvent event,float x,float y,int pointer,int button)
        	{
        		controller.acceleratorDown = true;
            	return true;
        	}

        	@Override
        	public void touchUp(InputEvent event,float x,float y,int pointer,int button)
        	{
        		controller.acceleratorDown = false;
        	}

        });

        brake.addListener(new InputListener()
        {
        	Sound brakeSound = AssetsLoader.getInstance().getManager().get(AssetsPaths.BRAKE.getPath(),Sound.class);
        	@Override
        	public boolean touchDown(InputEvent event,float x,float y,int pointer,int button)
        	{
        		controller.brakeDown = true;
        		if ((int)Player.getInstance().getSpeed() > GameInfos.MIN_PLAYER_SPEED) brakeSound.play(0.1f,1.3f,0);
            	return true;
        	}

        	@Override
        	public void touchUp(InputEvent event,float x,float y,int pointer,int button)
        	{
        		controller.brakeDown = false;
        		brakeSound.stop();
        	}

        });

       leftArrow.addListener(new InputListener()
        {
        	@Override
        	public boolean touchDown(InputEvent event,float x,float y,int pointer,int button)
        	{
        		Player.getInstance().turn("left");
            	return true;
        	}
        });

       rightArrow.addListener(new InputListener()
       {
       	@Override
       	public boolean touchDown(InputEvent event,float x,float y,int pointer,int button)
       	{
       		Player.getInstance().turn("right");
           	return true;
       	}
       });



        buttons.add(leftArrow).width(65f).left();
        buttons.add(rightArrow).padLeft(10f).width(65f).left();
        buttons.row();
        pedals.add(brake).width(150f);
        pedals.add(accelerator).right().width(200f).padLeft(GameInfos.WIDTH/4f+60f);

        controller.buttonsStage.addActor(buttons);
        controller.pedalsStage.addActor(pedals);
    }
	public void render(float delta)
    {
		rearrangeBackgrounds();
        moveCamera();
        speedValue.setText(String.valueOf((int)(Player.getInstance().getSpeed()*2 +30f)) + "Km/h");
        GameInfos.lastDistance += ((Player.getInstance().getSpeed()*0.05f)/1000f);
        DecimalFormat formatter = new DecimalFormat("#.#");
        distanceValue.setText(String.valueOf(formatter.format(GameInfos.lastDistance))+" Km");

        if (Player.getInstance().getSpeed() < 50)
    	{
    		scoreLabel.setColor(Color.WHITE);
    		scoreValue.setColor(Color.WHITE);
    		scoreValue.setText(String.valueOf(GameInfos.lastScore));
    	}

        else if (Player.getInstance().getSpeed() >= 50 && Player.getInstance().getSpeed() < GameInfos.MAX_PLAYER_SPEED - 3f)
        {
        	scoreLabel.setColor(Color.GOLD);
    		scoreValue.setColor(Color.GOLD);
    		scoreValue.setText(String.valueOf(GameInfos.lastScore) + "(x2)");
        }

        else if (Player.getInstance().getSpeed() >= GameInfos.MAX_PLAYER_SPEED-3f)
        {
        	scoreLabel.setColor(Color.GOLDENROD);
    		scoreValue.setColor(Color.GOLDENROD);
    		scoreValue.setText(String.valueOf(GameInfos.lastScore) + "(x4)");
        }
		controller.update(delta);
        if (!GameInfos.gameOver && !GameInfos.gamePaused)
        {
        	controller.stage.act(delta);
            controller.hudStage.act(delta);
        }

        if (!(controller.buttonsStage == null && controller.pedalsStage == null))
        {
        	controller.buttonsStage.act();
        	controller.pedalsStage.act();
        }
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        controller.stage.getBatch().setProjectionMatrix(camera.combined);
        controller.stage.getBatch().begin();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawBackgrounds();
        controller.stage.getBatch().draw(Player.getInstance().getSprite(),Player.getInstance().getX(),Player.getInstance().getY(),Player.getInstance().getOriginX(),Player.getInstance().getOriginY(),Player.getInstance().getWidth(),Player.getInstance().getHeight(),Player.getInstance().getScaleX(),Player.getInstance().getScaleY(),Player.getInstance().getRotation());
        controller.carsSpawner.drawCars(batch);
        controller.stage.getBatch().end();
        batch.end();
        controller.stage.draw();
        controller.hudStage.draw();
        if (!(controller.buttonsStage== null && controller.pedalsStage == null))
        {
        	controller.buttonsStage.draw();
        	controller.pedalsStage.draw();
        }
        camera.update();
        hudCamera.update();
    }

        /**
         * Updates the camera's position
         */
        private void moveCamera()
        {
            camera.position.set(new Vector2(GameInfos.WIDTH/2f,Player.getInstance().getY()+GameInfos.HEIGHT/2f+Player.getInstance().getSpeed()*(Player.getInstance().getSpeed()/25f)),0);
        }

        /**
         * Creates the backgrounds needed for the game
         */
        private void createBackgrounds()
        {
            for (int i =0;i<backgrounds.length;i++)
            {
                backgrounds[i]= new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.ROAD.getPath(),Texture.class));
                backgrounds[i].setPosition(-250f,(backgrounds[i].getHeight()*i));
                lastBackgroundY = backgrounds[i].getY();
            }



        }

        /**
         * Draws on the screen all the backgrounds created and positioned so far
         */
        private void drawBackgrounds()
        {
            for (int i=0;i<backgrounds.length;i++) batch.draw(backgrounds[i],backgrounds[i].getX(),backgrounds[i].getY());
        }

        /**
         * Rearranges the position of the backgrounds whenever they go out of bounds
         */

        private void rearrangeBackgrounds()
        {
            for (int i=0;i<backgrounds.length;i++)
            {
                if ((backgrounds[i].getY() + backgrounds[i].getHeight() + GameInfos.HEIGHT) < camera.position.y)
                {
                    float newY = backgrounds[i].getHeight() + lastBackgroundY;
                    backgrounds[i].setPosition(-250f,newY);
                    lastBackgroundY = newY;
                }
            }

        }

	@Override
	public void dispose()
	{
		game.dispose();
		AssetsLoader.getInstance().dispose();
	}

}
