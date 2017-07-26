package com.github.bot.curiosone.app.games.endlessroad.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsPaths;
import com.github.bot.curiosone.app.games.endlessroad.utilities.GameConstants;
import com.github.bot.curiosone.app.workflow.Chat;

/**
 * Represents the credits screen of the game
 * @author Paolo Pierantozzi
 *
 */
public class Credits implements Screen
{
	private Chat game;
	private AssetsLoader loader;
	private OrthographicCamera camera;
	private Stage stage;
	private Viewport viewport;
	private Sprite background,credits;
	private ImageButton backButton;
	private Table table;
	private BitmapFont creditsFont;
	private String creditsText;

	
	public Credits(Chat game)
	{
		this.game = game;
		loader = new AssetsLoader();
		loader.loadCreditsAssets();
		background = new Sprite(loader.getManager().get(AssetsPaths.MENUS_BG.getPath(),Texture.class));
		
		credits = new Sprite(loader.getManager().get(AssetsPaths.CREDITS.getPath(),Texture.class));
		credits.setPosition(0,GameConstants.HEIGHT/2f+150f);		
		camera = new OrthographicCamera(GameConstants.WIDTH,GameConstants.HEIGHT);
		camera.position.set(GameConstants.WIDTH/2f,GameConstants.HEIGHT/2f,0);
		viewport = new StretchViewport(GameConstants.WIDTH,GameConstants.HEIGHT,camera);
		stage = new Stage(viewport,game.getBatch());
		creditsFont = loader.getManager().get(AssetsPaths.AGENCY_FB.getPath());
		creditsFont.getData().setScale(0.8f);
		creditsText = "CODE: \n    Paolo Pierantozzi \n\nGRAPHICS: \n    Paolo Pierantozzi \n    FreeCreatives.com \n    UnLuckyStudio.com";
		
		table = new Table();
		table.bottom();
		table.setFillParent(true);
		createAndPositionButtons();
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
		
	}
	
	private void createAndPositionButtons()
	{
		
		backButton = new ImageButton(new SpriteDrawable(new Sprite(loader.getManager().get(AssetsPaths.BACK_BUTTON.getPath(),Texture.class))));
		
		backButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				game.setScreen(new EndlessRoad(game));				
			}
			
		});
		
		
		table.add(backButton).padBottom(40f);
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
		game.getBatch().draw(credits,credits.getX(),credits.getY());
		creditsFont.draw(game.getBatch(),creditsText,50f,GameConstants.HEIGHT/2f*1.3f);
		game.getBatch().end();
		stage.draw();
		camera.update();
		
	}

	@Override
	public void resize(int width, int height)
	{
		viewport.update(width,height);
		
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

