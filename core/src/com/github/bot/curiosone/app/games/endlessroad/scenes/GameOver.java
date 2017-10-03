package com.github.bot.curiosone.app.games.endlessroad.scenes;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsLoader;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsPaths;
import com.github.bot.curiosone.app.games.endlessroad.utilities.GameInfos;



/**
 * This class represents the game over screen
 * @author Paolo Pierantozzi
 */
public class GameOver implements Screen
{
    private Chat game;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Sprite background,gameover,crown;
    private ImageButton replayButton,menuButton;
    private String score,distance;
    private Label scoreLabel,distanceLabel,scoreText,distanceText;
    private Music loop;

    public GameOver(Chat game)
    {
        this.game = game;
        this.batch = new SpriteBatch();

        AssetsLoader.getInstance().loadGameOverAssets();

        background = new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.MENUS_BG.getPath(),Texture.class));
        loop = AssetsLoader.getInstance().getInstance().getManager().get(AssetsPaths.GAMEOVER_LOOP.getPath(),Music.class);

        if (!GameInfos.muteMusic)
        {
        	loop.setLooping(true);
        	loop.setVolume(0.2f);
        	float waitTime = !GameInfos.muteFX? 0.15f:0;
        	Timer.schedule(new Task()
        	{
        		@Override
        		public void run()
				{
        			loop.play();
				}
        	}, waitTime);

        }
        gameover = new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.GAME_OVER.getPath(),Texture.class));
        gameover.setPosition(0,GameInfos.HEIGHT/2f+150f);

        crown = new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.CROWN.getPath(),Texture.class));
        camera = new OrthographicCamera(GameInfos.WIDTH,GameInfos.HEIGHT);
        camera.position.set(GameInfos.WIDTH/2f,GameInfos.HEIGHT/2f,0);
        viewport = new StretchViewport(GameInfos.WIDTH,GameInfos.HEIGHT,camera);
        stage = new Stage(viewport,batch);



        createAndPositionButtons();

        Gdx.input.setInputProcessor(stage);

    }

    /**
     * Creates and positions the game over menu's buttons on the screen
     */
    private void createAndPositionButtons()
    {

    	score = String.valueOf(GameInfos.lastScore);
    	DecimalFormat formatter = new DecimalFormat("#.#");
    	distance = String.valueOf(formatter.format(GameInfos.lastDistance).replaceAll(",",".")) + " Km";
    	BitmapFont infosFont = AssetsLoader.getInstance().getManager().get(AssetsPaths.AGENCY_FB.getPath());
        infosFont.getData().setScale(1.2f);
        scoreLabel = new Label("SCORE:",new Label.LabelStyle(infosFont,Color.RED));
		distanceLabel = new Label("DISTANCE:",new Label.LabelStyle(infosFont,Color.RED));
    	scoreText = new Label(score,new Label.LabelStyle(infosFont,Color.WHITE));
    	distanceText = new Label(distance,new Label.LabelStyle(infosFont,Color.WHITE));
    	replayButton = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.REPLAY_BUTTON.getPath(),Texture.class))));
        menuButton = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.MENU_BUTTON.getPath(),Texture.class))));

        replayButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
            	loop.stop();
            	GameInfos.startFromMainMenu = false;
            	game.setScreen(new Gameplay(game));
            }

        });

        menuButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event,Actor actor)
            {
            	loop.stop();
                game.setScreen(new EndlessRoad(game));
            }
        });

        Table infosTable = new Table();
        infosTable.padBottom(40f);
        infosTable.center();
        infosTable.setFillParent(true);
        infosTable.add(scoreLabel).center();
        infosTable.row();
        infosTable.add(scoreText);
        infosTable.row();
        infosTable.add(distanceLabel);
        infosTable.row();
        infosTable.add(distanceText);

        stage.addActor(infosTable);


        Table buttonsTable = new Table();
        buttonsTable.bottom();
        buttonsTable.setFillParent(true);
        buttonsTable.add(replayButton).padBottom(20f);
        buttonsTable.row();
        buttonsTable.add(menuButton).padBottom(40f);
        stage.addActor(buttonsTable);
    }

    @Override
    public void show()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background,background.getX(),background.getY());
        batch.draw(gameover,gameover.getX(),gameover.getY());

        if (GameInfos.newScoreRecord)
        {
        	scoreText.setColor(Color.LIME);
        	batch.draw(crown,scoreText.getX()+scoreText.getWidth()+20f,scoreText.getY()+10f);
        }

        if (GameInfos.newDistanceRecord)
        {
        	distanceText.setColor(Color.LIME);
        	batch.draw(crown,distanceText.getX()+distanceText.getWidth()+20f,distanceText.getY()+10f);
        }



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
}

