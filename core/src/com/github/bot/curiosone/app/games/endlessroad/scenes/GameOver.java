package com.github.bot.curiosone.app.games.endlessroad.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.bot.curiosone.app.workflow.Chat;

import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsLoader;
import com.github.bot.curiosone.app.games.endlessroad.utilities.GameConstants;

/**
 * This class represents the game over screen
 * @author Paolo Pierantozzi
 */
public class GameOver implements Screen
{
    private Chat game;
    private AssetsLoader loader;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Sprite background,gameover;
    private ImageButton replayButton,quitButton;
    private Table table;

    public GameOver(Chat game)
    {
        this.game = game;
        loader = new AssetsLoader();
        loader.loadGameOverAssets();
        background = new Sprite(loader.getManager().get("EndlessRoad/Backgrounds/menus-bg.png",Texture.class));

        gameover = new Sprite(loader.getManager().get("EndlessRoad/Logos/gameover.png",Texture.class));
        gameover.setPosition(0,GameConstants.HEIGHT/2f+150f);

        camera = new OrthographicCamera(GameConstants.WIDTH,GameConstants.HEIGHT);
        camera.position.set(GameConstants.WIDTH/2f,GameConstants.HEIGHT/2f,0);
        viewport = new StretchViewport(GameConstants.WIDTH,GameConstants.HEIGHT,camera);
        stage = new Stage(viewport,game.getBatch());

        table = new Table();
        table.bottom();
        table.setFillParent(true);
        createAndPositionButtons();
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

    }

    /**
     * Creates and positions the buttons on the screen
     */
    private void createAndPositionButtons()
    {
        replayButton = new ImageButton(new SpriteDrawable(new Sprite(loader.getManager().get("EndlessRoad/Buttons/replaybutton.png",Texture.class))));
        quitButton = new ImageButton(new SpriteDrawable(new Sprite(loader.getManager().get("EndlessRoad/Buttons/quitbutton.png",Texture.class))));

        replayButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                game.setScreen(new Gameplay(game));
            }

        });

        quitButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event,Actor actor)
            {
                game.setScreen(new EndlessRoad(game));
            }
        });

        table.add(replayButton).padBottom(20f);
        table.row();
        table.add(quitButton).padBottom(40f);

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
        game.getBatch().begin();
        game.getBatch().draw(background,background.getX(),background.getY());
        game.getBatch().draw(gameover,gameover.getX(),gameover.getY());
        game.getBatch().end();
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
        game.dispose();
        loader.getManager().clear();
    }
}
