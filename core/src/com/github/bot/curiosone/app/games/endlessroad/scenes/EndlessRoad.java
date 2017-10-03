package com.github.bot.curiosone.app.games.endlessroad.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsLoader;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsPaths;
import com.github.bot.curiosone.app.games.endlessroad.utilities.GameInfos;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.workflow.GameCenter;


/**
 * This class represents the main menu screen
 * @author Paolo Pierantozzi
 */
public class EndlessRoad implements Screen
{
    private Chat game;
    private SpriteBatch batch;
    private Sprite background,logo;
    private ImageButton playButton,recordsButton,creditsButton,quitButton,musicGreen,musicRed,speakerButton,muteButton;
    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private boolean playTouched = false;
    private Music loop;


    public EndlessRoad(Chat game)
    {

        this.game = game;
        this.batch = new SpriteBatch();
        AssetsLoader.getInstance().loadMainMenuAssets();

        loop = AssetsLoader.getInstance().getManager().get(AssetsPaths.MENU_LOOP.getPath(),Music.class);

        if (!GameInfos.muteMusic)
        {
        	loop.setLooping(true);
        	loop.setVolume(0.3f);
        	loop.play();
        }

        background = new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.MENUS_BG.getPath(),Texture.class));
        logo = new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.ENDLESS_ROAD.getPath(),Texture.class));
        logo.setPosition(0,GameInfos.HEIGHT/2f+180f);

        camera = new OrthographicCamera(GameInfos.WIDTH,GameInfos.HEIGHT);
        camera.position.set(GameInfos.WIDTH/2f,GameInfos.HEIGHT/2f,0);
        viewport = new StretchViewport(GameInfos.WIDTH,GameInfos.HEIGHT,camera);
        stage = new Stage(viewport,batch);
        createAndPositionButtons();
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void show()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta)
    {
        stage.act(delta);


        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        logo.draw(batch);
        batch.end();
        stage.draw();

        camera.update();

    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width,height,true);
    }

    @Override
    public void pause()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose()
    {
        batch.dispose();
        AssetsLoader.getInstance().dispose();

    }

    /**
     * Creates and positions the main menu's buttons on the screen
     */
    private void createAndPositionButtons()
    {
        playButton = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.PLAY_BUTTON.getPath(),Texture.class))));
        recordsButton = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.RECORDS_BUTTON.getPath(),Texture.class))));
        creditsButton = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.CREDITS_BUTTON.getPath(),Texture.class))));
        quitButton = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.QUIT_BUTTON.getPath(),Texture.class))));
        musicGreen = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.MUSIC_GREEN.getPath(),Texture.class))));
        if (GameInfos.muteMusic) musicGreen.setVisible(false);
        musicGreen.setPosition(0,-50f);
        musicGreen.setWidth(100f);
        musicRed = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.MUSIC_RED.getPath(),Texture.class))));
        if (!GameInfos.muteMusic) musicRed.setVisible(false);
        musicRed.setPosition(0,-50f);
        musicRed.setWidth(100f);

        speakerButton = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.SPEAKER.getPath(),Texture.class))));
        if (GameInfos.muteFX) speakerButton.setVisible(false);
        speakerButton.setPosition(GameInfos.WIDTH-115f,-50f);
        speakerButton.setWidth(100f);
        muteButton = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.MUTE.getPath(),Texture.class))));
        if (!GameInfos.muteFX) muteButton.setVisible(false);
        muteButton.setPosition(GameInfos.WIDTH-115f,-50f);
        muteButton.setWidth(100f);

        playButton.addListener(new ChangeListener()
        {
        	Sound sound = AssetsLoader.getInstance().getManager().get(AssetsPaths.ENGINE_ON.getPath(),Sound.class);

            @Override
            public void changed(ChangeEvent event,Actor actor)
            {
            	GameInfos.startFromMainMenu = true;
            	float waitTime = !GameInfos.muteFX? 1:0;
            	if (!playTouched)
            	{
            		playTouched = true;
            		if (!GameInfos.muteFX)sound.play();
            		loop.stop();
                	Timer.schedule(new Task()
                	{
                		@Override
                		public void run()
                		{
                			//EndlessRoad.this.game.setScreen(new Gameplay(EndlessRoad.this.game));
                			EndlessRoad.this.game.setScreen(new Gameplay(EndlessRoad.this.game));
                		}
                	},waitTime);
            	}


            }
        });

        quitButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event,Actor actor)
            {
            	AssetsLoader.getInstance().dispose();
                EndlessRoad.this.game.setScreen(new GameCenter(EndlessRoad.this.game));
            }
        });

        creditsButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event,Actor actor)
			{
				EndlessRoad.this.game.setScreen(new Credits(EndlessRoad.this.game));
			}

		});

        recordsButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event,Actor actor)
			{
				EndlessRoad.this.game.setScreen(new Records(EndlessRoad.this.game));
			}

		});

        musicGreen.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event,Actor actor)
            {
            	GameInfos.muteMusic = true;
            	loop.stop();
                musicGreen.setVisible(false);
                musicRed.setVisible(true);

            }
        });

        musicRed.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event,Actor actor)
            {
            	GameInfos.muteMusic = false;
            	loop.play();
                musicRed.setVisible(false);
                musicGreen.setVisible(true);

            }
        });

        speakerButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event,Actor actor)
            {
            	GameInfos.muteFX = true;
                speakerButton.setVisible(false);
                muteButton.setVisible(true);

            }
        });

        muteButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event,Actor actor)
            {
            	GameInfos.muteFX = false;
            	muteButton.setVisible(false);
                speakerButton.setVisible(true);

            }
        });


        Table menuTable = new Table();
        menuTable.center();
        menuTable.setFillParent(true);

        menuTable.add(playButton).padTop(180f).padBottom(15f);
        menuTable.row();
        menuTable.add(recordsButton).padBottom(15f);
        menuTable.row();
        menuTable.add(creditsButton).padBottom(15f);
        menuTable.row();
        menuTable.add(quitButton).padBottom(60f);
        menuTable.row();

        stage.addActor(musicGreen);
        stage.addActor(musicRed);
        stage.addActor(speakerButton);
        stage.addActor(muteButton);
        stage.addActor(menuTable);
    }

}
