package com.curiosone.wordcrushse.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.curiosone.wordcrushse.utils.*;
import com.curiosone.wordcrushse.WordCrushSE;
import com.curiosone.wordcrushse.loaders.Assets;
import com.curiosone.wordcrushse.loaders.AudioManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

/**
 * Created by giuseppe on 23/07/17.
 */

public class MenuScreen extends AbstractGameScreen
{
    private final static String TAG = MenuScreen.class.getName();
    private WordCrushSE wc;
    private com.curiosone.wordcrushse.utils.CameraHelper cameraHelper;
    private Stage stage;
    private ImageButton playButton;
    private ImageButton creditsButton;
    private Image logo;
    private Table table;
    private Skin skin;
    float w,h;

    public MenuScreen(WordCrushSE wc, float w, float h)
    {
        super(wc);
        this.wc = wc;
        this.w = w;
        this.h = h;

        /* Camera Settings */
        cameraHelper = new com.curiosone.wordcrushse.utils.CameraHelper();

        skin = new Skin( Assets.instance.assetMenu.getAtlas());

        /* Button */
        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        playButtonStyle.imageUp = skin.getDrawable("playOff");
        playButtonStyle.imageDown = skin.getDrawable("playOn");
        playButton = new ImageButton(playButtonStyle);

        ImageButton.ImageButtonStyle creditsButtonStyle = new ImageButton.ImageButtonStyle();
        creditsButtonStyle.imageUp = skin.getDrawable("creditsOff");
        creditsButtonStyle.imageDown = skin.getDrawable("creditsOn");
        creditsButton = new ImageButton(creditsButtonStyle);

        table = new Table();
        table.add(playButton).padBottom(10);
        table.row();
        table.add(creditsButton).padBottom(10);
        table.setPosition(GameConstants.WIDTH/2, GameConstants.HEIGHT/2 );
        logo = new Image(Assets.instance.assetMenu.wcLogo);
        logo.setPosition(GameConstants.WIDTH/2 - Assets.instance.assetMenu.wcLogo.getRegionWidth()/2 ,
                 GameConstants.HEIGHT - Assets.instance.assetMenu.wcLogo.getRegionHeight()/2-140);

        playButton.addListener(new ChangeListener(){

            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                AudioManager.instance.play(Assets.instance.assetSound.clickMenuButton);
                MenuScreen.this.wc.setScreen(new DifficultyScreen(MenuScreen.this.wc));
            }
        }
        );

        creditsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AudioManager.instance.play(Assets.instance.assetSound.clickMenuButton);
                MenuScreen.this.wc.setScreen(new CreditsScreen(MenuScreen.this.wc));
            }
        });
    }

    @Override
    public void render(float delta)
    {
        Gdx.input.setCatchBackKey(true);
        stage.act(delta);
        cameraHelper.getCamera().update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        wc.getBatch().setProjectionMatrix(cameraHelper.getCamera().combined);
        wc.getBatch().begin();
        wc.getBatch().disableBlending();
        wc.getBatch().draw(Assets.instance.assetMenu.background, 0, 0);
        wc.getBatch().enableBlending();
        stage.draw();
        wc.getBatch().end();
    }

    @Override
    public void resize(int width,int height)
    {
        cameraHelper.getViewport().update(width,height,true);
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
                { // Maybe perform other operations before exiting
                    AudioManager.instance.play(Assets.instance.assetSound.clickMenuButton);
                    Gdx.app.exit();
                }
                return false;
            }
        };

        /*Stage*/
        stage = new Stage(cameraHelper.getViewport());
        InputMultiplexer multiplexer = new InputMultiplexer(stage,
                backProcessor);
        Gdx.input.setInputProcessor(multiplexer);
        stage.addActor(logo);
        stage.addActor(table);
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(0.2f));
    }


    @Override
    public void dispose()
    {
        stage.dispose();
        super.dispose();
    }

}
