package com.github.bot.curiosone.app.games.endlessroad.scenes;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.bot.curiosone.app.workflow.Chat;

import com.github.bot.curiosone.app.games.endlessroad.entities.Car;
import com.github.bot.curiosone.app.games.endlessroad.entities.Player;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsLoader;
import com.github.bot.curiosone.app.games.endlessroad.utilities.GameConstants;
import com.github.bot.curiosone.app.games.endlessroad.utilities.CarsSpawner;

/**
 * This class represents the gameplay screen
 * @author Paolo Pierantozzi
 *
 */
public class Gameplay implements Screen
{
    private Chat game;
    private AssetsLoader loader;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Stage stage;
    private Table hud;
    private Label score,speed,distance;
    private Sprite[] backgrounds;
    private float lastBackgroundY;

    private CarsSpawner carsSpawner;

    private Player player;

    public Gameplay(Chat game)
    {
        this.game = game;
        loader = new AssetsLoader();
        loader.loadGameplayAssets();

        camera = new OrthographicCamera(GameConstants.WIDTH,GameConstants.HEIGHT);
        camera.position.set(GameConstants.WIDTH/2f,GameConstants.HEIGHT/2f,0);

        viewport = new StretchViewport(GameConstants.WIDTH,GameConstants.HEIGHT,camera);
        stage = new Stage(viewport,game.getBatch());
        hud = new Table();
        hud.align(Align.topRight);

        //createHud();
        backgrounds = new Sprite[3];
        player = new Player(loader.getManager(),stage,GameConstants.Lanes.CENTRE_RIGHT.getX()+2f,10f);
        carsSpawner = new CarsSpawner(loader.getManager(),stage);
        Gdx.input.setInputProcessor(stage);
        stage.setKeyboardFocus(stage.getActors().first());
        createBackgrounds();
    }




    /**
     * Updates all the elements in the scene
     */
    private void update(float deltaTime)
    {
        moveCamera();
        rearrangeBackgrounds();
        carsSpawner.setCameraY(camera.position.y);
        carsSpawner.rearrangeCars(player);
        handleCollisions();


        if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.getSpeed() < GameConstants.MAX_PLAYER_SPEED)
        {
            player.setSpeed(player.getSpeed() + 2);
            camera.zoom += 0.01f;
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.getSpeed() > GameConstants.MIN_PLAYER_SPEED)
        {
            player.setSpeed(player.getSpeed() - 2);
            camera.zoom -= 0.01f;
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) System.out.println(player.getSpeed());

    }

    /**
     * Updates the camera's position
     */
    private void moveCamera()
    {
        camera.position.set(new Vector2(GameConstants.WIDTH/2f,player.getY()+GameConstants.HEIGHT/2f+player.getSpeed()*(player.getSpeed()/33f)),0);

    }


    /**
     * Creates the backgrounds needed for the game
     */
    private void createBackgrounds()
    {
        for (int i =0;i<backgrounds.length;i++)
        {
            backgrounds[i]= new Sprite(loader.getManager().get("EndlessRoad/Backgrounds/road.png",Texture.class));
            backgrounds[i].setPosition(-250f,(backgrounds[i].getHeight()*i));
            lastBackgroundY = backgrounds[i].getY();
        }



    }



    /**
     * Draws on the screen all the backgrounds created and positioned so far
     */
    private void drawBackgrounds()
    {
        for (int i=0;i<backgrounds.length;i++) game.getBatch().draw(backgrounds[i],backgrounds[i].getX(),backgrounds[i].getY());
    }



    /**
     * Rearranges the position of the backgrounds whenever they go out of bounds
     */

    private void rearrangeBackgrounds()
    {
        for (int i=0;i<backgrounds.length;i++)
        {
            if ((backgrounds[i].getY() + backgrounds[i].getHeight() + GameConstants.HEIGHT) < camera.position.y)
            {
                float newY = backgrounds[i].getHeight() + lastBackgroundY;
                backgrounds[i].setPosition(-250f,newY);
                lastBackgroundY = newY;
            }
        }

    }


    /**
     * Handles the collisions between the cars
     */
    private void handleCollisions()
    {
        for (Car c: carsSpawner.getCars())
        {
            if (c.getBounds().overlaps(player.getBounds()))
            {
                camera.position.set(GameConstants.WIDTH/2f,GameConstants.HEIGHT/2f,0);
                game.setScreen(new GameOver(game));
            }
        }
    }

    /**
     * Creates the heads up display
     */
    /*
    private void createHud()
    {
        score = new Label("0",new Skin());
    }
    */

    @Override
    public void show()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta)
    {
        update(delta);
        stage.act(delta);
        stage.draw();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        drawBackgrounds();
        stage.getBatch().draw(player.getSprite(),player.getX(),player.getY(),player.getOriginX(),player.getOriginY(),player.getWidth(),player.getHeight(),player.getScaleX(),player.getScaleY(),player.getRotation());
        carsSpawner.drawCars(game.getBatch());
        stage.getBatch().end();

        camera.update();

    }

    @Override
    public void resize(int width, int height)
    {
        // TODO Auto-generated method stub

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
        game.dispose();
        loader.getManager().clear();
    }

}

