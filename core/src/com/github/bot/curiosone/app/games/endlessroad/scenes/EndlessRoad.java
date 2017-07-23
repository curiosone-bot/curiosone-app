package com.github.bot.curiosone.app.games.endlessroad.scenes;

import com.badlogic.gdx.Screen;
import com.github.bot.curiosone.app.workflow.Chat;

import com.badlogic.gdx.Gdx;
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

import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsLoader;
import com.github.bot.curiosone.app.games.endlessroad.utilities.GameConstants;
import com.github.bot.curiosone.app.workflow.GameCenter;

/**
 * This class represents the main menu screen
 * @author Paolo Pierantozzi
 */
public class EndlessRoad implements Screen
{
    private Chat game;
    private AssetsLoader loader;
    private Sprite background,logo;
    private Table table;
    private ImageButton playButton,creditsButton,quitButton;
    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;

    public EndlessRoad(Chat game)
    {
        this.game = game;
        loader = new AssetsLoader();
        loader.loadMainMenuAssets();

        background = new Sprite(loader.getManager().get("EndlessRoad/Backgrounds/menus-bg.png",Texture.class));
        logo = new Sprite(loader.getManager().get("EndlessRoad/Logos/endlessroad.png",Texture.class));
        logo.setPosition(0,GameConstants.HEIGHT/2f+150f);

        camera = new OrthographicCamera(GameConstants.WIDTH,GameConstants.HEIGHT);
        camera.position.set(GameConstants.WIDTH/2f,GameConstants.HEIGHT/2f,0);
        viewport = new StretchViewport(GameConstants.WIDTH,GameConstants.HEIGHT,camera);
        stage = new Stage(viewport,game.getBatch());

        table = new Table();
        table.center();
        table.setFillParent(true);
        createAndPositionButtons();
        stage.addActor(table);
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
        game.getBatch().begin();
        background.draw(game.getBatch());
        logo.draw(game.getBatch());
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

    /**
     * Creates and positions the buttons on the screen
     */
    private void createAndPositionButtons()
    {
        playButton = new ImageButton(new SpriteDrawable(new Sprite(loader.getManager().get("EndlessRoad/Buttons/playbutton.png",Texture.class))));
        creditsButton = new ImageButton(new SpriteDrawable(new Sprite(loader.getManager().get("EndlessRoad/Buttons/creditsbutton.png",Texture.class))));
        quitButton = new ImageButton(new SpriteDrawable(new Sprite(loader.getManager().get("EndlessRoad/Buttons/quitbutton.png",Texture.class))));

        playButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event,Actor actor)
            {
                EndlessRoad.this.game.setScreen(new Gameplay(EndlessRoad.this.game));
            }
        });

        quitButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event,Actor actor)
            {
                EndlessRoad.this.game.setScreen(new GameCenter(EndlessRoad.this.game));
            }
        });





        table.add(playButton).padTop(150f).padBottom(30f);
        table.row();
        table.add(creditsButton).padBottom(30f);
        table.row();
        table.add(quitButton);

    }

}
