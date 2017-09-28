package com.curiosone.wordcrushse.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.curiosone.wordcrushse.utils.*;
import com.curiosone.wordcrushse.WordCrushSE;
import com.curiosone.wordcrushse.loaders.Assets;
import com.curiosone.wordcrushse.loaders.AudioManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

/** Class delegate to selection the game mode
 * Created by Giuseppe on 05/08/2017.
 */

public class GameModeScreen extends AbstractGameScreen
{

    private final static String TAG = GameModeScreen.class.getName();
    private ImageButton wordCheckOff;
    //private ImageButton visionaryOff;
    private com.curiosone.wordcrushse.utils.CameraHelper cameraHelper;
    private Stage stage;
    private Table table;
    private Image imageGameModeLogo;

    public GameModeScreen(WordCrushSE wc)
    {
        super(wc);
        /* Actors */
        imageGameModeLogo = new Image(Assets.instance.assetGameModeDecoration.gameModeLogo);
        imageGameModeLogo.setPosition(GameConstants.WIDTH/2 - Assets.instance.assetGameModeDecoration.gameModeLogo.getRegionWidth()/2
                ,650);
        wordCheckOff = new ImageButton(new SpriteDrawable( new Sprite(Assets.instance.assetGameModeDecoration.wordCheckOff)));

        table = new Table();
        table.add(wordCheckOff).padBottom(30);
        table.row();
        /*
        visionaryOff = new ImageButton( new SpriteDrawable( new Sprite( Assets.instance.assetGameModeDecoration.visionaryOff)));
        table.add(visionaryOff);
        */
        table.setPosition(240,GameConstants.HEIGHT/2);


        /* Event Management */
        wordCheckOff.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                AudioManager.instance.play(Assets.instance.assetSound.clickMenuButton);
                GameModeScreen.super.wc.setScreen( new DifficultyScreen(GameModeScreen.super.wc));
            }
        });

    }

    @Override
    public void render(float delta)
    {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        super.wc.getBatch().begin();
        super.wc.getBatch().disableBlending();
        super.wc.getBatch().draw(Assets.instance.assetMenu.background,0,0);
        super.wc.getBatch().enableBlending();
        super.wc.getBatch().end();
        stage.draw();
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(0.2f));

    }

    @Override
    public void resize(int width, int height) { cameraHelper.getViewport().update(width,height,true); }

    @Override
    public void show()
    {
        cameraHelper = new com.curiosone.wordcrushse.utils.CameraHelper();

        /* Stage */
        stage = new Stage(cameraHelper.getViewport());
        Gdx.input.setInputProcessor(stage);

        /* Adds to Stage */
        stage.addActor(imageGameModeLogo);
        stage.addActor(table);
    }


    @Override
    public void dispose()
    {
        super.dispose();
        stage.dispose();
        Assets.instance.dispose();
    }

}
