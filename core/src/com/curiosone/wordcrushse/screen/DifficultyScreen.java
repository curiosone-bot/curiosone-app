package com.curiosone.wordcrushse.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.curiosone.wordcrushse.utils.*;
import com.curiosone.wordcrushse.WordCrushSE;
import com.curiosone.wordcrushse.loaders.Assets;
import com.curiosone.wordcrushse.utils.WorldController;
import com.curiosone.wordcrushse.loaders.AudioManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;

/** Class delegate to selection the game difficulty
 * Created by Giuseppe on 05/08/2017.
 */

public class DifficultyScreen extends AbstractGameScreen
{
    private com.curiosone.wordcrushse.utils.CameraHelper camera;
    private Stage stage;
    private Table table;
    private ImageButton easy,normal,hard;
    private Skin skin;
    private WorldController worldController;


    public DifficultyScreen(WordCrushSE wc)
    {
        super(wc);
        worldController = new WorldController(wc);

        /* Camera Settings */
        camera = new com.curiosone.wordcrushse.utils.CameraHelper();




        /* Actors */
        table = new Table();

        skin = new Skin();
        skin.addRegions(Assets.instance.assetGameDifficultDecoration.getAtlas());

        ImageButton.ImageButtonStyle easyButtonStyle = new ImageButton.ImageButtonStyle();
        easyButtonStyle.imageUp = skin.getDrawable("easyOff");
        easyButtonStyle.imageDown = skin.getDrawable("easyOn");
        easy = new ImageButton(easyButtonStyle);

        ImageButton.ImageButtonStyle normalButtonStyle = new ImageButton.ImageButtonStyle();
        normalButtonStyle.imageUp = skin.getDrawable("normalOff");
        normalButtonStyle.imageDown = skin.getDrawable("normalOn");
        normal = new ImageButton(normalButtonStyle);

        ImageButton.ImageButtonStyle hardButtonStyle = new ImageButton.ImageButtonStyle();
        hardButtonStyle.imageUp = skin.getDrawable("hardOff");
        hardButtonStyle.imageDown = skin.getDrawable("hardOn");
        hard = new ImageButton(hardButtonStyle);

        /* Add Actors to table */
        table.add(easy).padBottom(20);
        table.row();
        table.add(normal).padBottom(20);
        table.row();
        table.add(hard).padBottom(20);
        table.setPosition(GameConstants.WIDTH/2,GameConstants.HEIGHT/2);

        /* Add Actors to stage */


        easy.addListener( new ChangeListener(){

            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                GameConstants.setNumberOfMagicWords(GameConstants.EASY_DIFFICULTY);
                AudioManager.instance.play(Assets.instance.assetSound.clickMenuButton);
                DifficultyScreen.this.wc.setScreen(new RememberScreen(DifficultyScreen.this.wc));
            }
        });

        normal.addListener( new ChangeListener(){

            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                GameConstants.setNumberOfMagicWords(GameConstants.NORMAL_DIFFICULTY);
                AudioManager.instance.play(Assets.instance.assetSound.clickMenuButton);
                DifficultyScreen.this.wc.setScreen(new RememberScreen(DifficultyScreen.this.wc));
            }
        });

        hard.addListener( new ChangeListener(){

            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                GameConstants.setNumberOfMagicWords(GameConstants.HARD_DIFFICULTY);
                AudioManager.instance.play(Assets.instance.assetSound.clickMenuButton);
                DifficultyScreen.this.wc.setScreen(new RememberScreen(DifficultyScreen.this.wc));
            }
        });

    }

    @Override
    public void render(float delta)
    {
        stage.act(delta);
        wc.getBatch().begin();
        wc.getBatch().disableBlending();
        wc.getBatch().draw(Assets.instance.assetMenu.background,0,0);
        wc.getBatch().enableBlending();
        wc.getBatch().draw(skin.getRegion("selectLogo"),
                GameConstants.WIDTH/2 - Assets.instance.assetGameDifficultDecoration.selectLogo.getRegionWidth()/2,
                GameConstants.HEIGHT - 200
        );
        wc.getBatch().end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.getViewport().update(width,height,true);
    }

    @Override
    public void show()
    {
        InputProcessor backProcessor = new InputAdapter()
        {
            @Override
            public boolean keyDown(int keycode)
            {
                if ((keycode == Input.Keys.ESCAPE) || (keycode == Input.Keys.BACK) )
                    DifficultyScreen.this.wc.setScreen( new MenuScreen(wc,GameConstants.WIDTH,GameConstants.HEIGHT));
                return false;
            }
        };

        /*Stage*/
        stage = new Stage(camera.getViewport());
        InputMultiplexer multiplexer = new InputMultiplexer(stage,
                backProcessor);
        Gdx.input.setInputProcessor(multiplexer);
        stage.addActor(table);
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(0.2f));
    }

    @Override
    public void dispose()
    {
        super.dispose();
        stage.dispose();
        Assets.instance.dispose();
    }

}
