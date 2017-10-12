package com.github.bot.curiosone.app.games.endlessroad.scenes;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsLoader;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsPaths;
import com.github.bot.curiosone.app.games.endlessroad.utilities.GameInfos;



/**
 * This class represents the records screen
 * @author Paolo Pierantozzi
 */
public class Records implements Screen
{
    private Chat game;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Sprite background,records;
    private ImageButton backButton;
    private String score,distance;
    private Label scoreLabel,distanceLabel,scoreText,distanceText;
    private Preferences preferences;

    public Records(Chat game)
    {
        this.game = game;
        this.batch = new SpriteBatch();

        AssetsLoader.getInstance().loadRecordsAssets();

        preferences = Gdx.app.getPreferences("");

        background = new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.MENUS_BG.getPath(),Texture.class));

        records = new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.RECORDS.getPath(),Texture.class));
        records.setPosition(0,GameInfos.HEIGHT/2f+190f);

        camera = new OrthographicCamera(GameInfos.WIDTH,GameInfos.HEIGHT);
        camera.position.set(GameInfos.WIDTH/2f,GameInfos.HEIGHT/2f,0);
        viewport = new StretchViewport(GameInfos.WIDTH,GameInfos.HEIGHT,camera);
        stage = new Stage(viewport,batch);



        createAndPositionButtons();

        Gdx.input.setInputProcessor(stage);

    }

    /**
     * Creates and positions the record's buttons on the screen
     */
    private void createAndPositionButtons()
    {

    	score = String.valueOf(preferences.getInteger("scoreRecord"));
    	DecimalFormat formatter = new DecimalFormat("#.#");
    	distance = String.valueOf(formatter.format(preferences.getFloat("distanceRecord")).replaceAll(",",".")) + " Km";
    	BitmapFont infosFont = AssetsLoader.getInstance().getManager().get(AssetsPaths.AGENCY_FB.getPath());
        infosFont.getData().setScale(1.2f);
        scoreLabel = new Label("BEST SCORE:",new Label.LabelStyle(infosFont,Color.WHITE));
		distanceLabel = new Label("BEST DISTANCE:",new Label.LabelStyle(infosFont,Color.WHITE));
    	scoreText = new Label(score,new Label.LabelStyle(infosFont,Color.ORANGE));
    	distanceText = new Label(distance,new Label.LabelStyle(infosFont,Color.ORANGE));
        backButton = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.BACK_BUTTON.getPath(),Texture.class))));

        backButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event,Actor actor)
            {
                game.setScreen(new EndlessRoad(game));
            }
        });

        Table infosTable = new Table();
        infosTable.center();
        infosTable.setFillParent(true);
        infosTable.add(scoreLabel).center().padTop(50f);
        infosTable.row();
        infosTable.add(scoreText).padTop(20f);
        infosTable.row();
        infosTable.add(distanceLabel).padTop(40f);
        infosTable.row();
        infosTable.add(distanceText).padTop(20f);
        infosTable.setScale(50f);
        stage.addActor(infosTable);


        Table buttonsTable = new Table();
        buttonsTable.bottom();
        buttonsTable.setFillParent(true);
        buttonsTable.add(backButton).padBottom(40f);
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
        batch.draw(records,records.getX(),records.getY());
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

