package com.github.bot.curiosone.app.games.endlessroad.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsLoader;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsPaths;
import com.github.bot.curiosone.app.games.endlessroad.utilities.GameInfos;
import com.github.bot.curiosone.app.workflow.Chat;


/**
 * Represents the credits screen of the game
 * @author Paolo Pierantozzi
 *
 */
public class Credits implements Screen
{
	private Chat game;
	private OrthographicCamera camera;
	private Stage stage;
	private Viewport viewport;
	private Sprite background,credits;
	private ImageButton backButton;

	
	public Credits(Chat game)
	{
		this.game = game;
		AssetsLoader.getInstance().loadCreditsAssets();
		background = new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.MENUS_BG.getPath(),Texture.class));
		
		credits = new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.CREDITS.getPath(),Texture.class));
		credits.setPosition(0,GameInfos.HEIGHT/2f+180f);		
		camera = new OrthographicCamera(GameInfos.WIDTH,GameInfos.HEIGHT);
		camera.position.set(GameInfos.WIDTH/2f,GameInfos.HEIGHT/2f,0);
		viewport = new StretchViewport(GameInfos.WIDTH,GameInfos.HEIGHT,camera);
		stage = new Stage(viewport,game.getBatch());
		
		createMenu();
		Gdx.input.setInputProcessor(stage);
		
	}
	
	/**
	 * Creates and positions the credits menu's elements on the screen
	 */
	private void createMenu()
	{
		BitmapFont creditsFont = AssetsLoader.getInstance().getManager().get(AssetsPaths.AGENCY_FB.getPath());
		creditsFont.getData().setScale(1,0.7f);
		
		Label codeLabel = new Label("          CODE:\n Paolo Pierantozzi",new Label.LabelStyle(creditsFont,Color.WHITE));
		Label graphicsLabel = new Label("        GRAPHICS:      \n  Paolo Pierantozzi\n FreeCreatives.com\n UnLuckyStudio.com",new Label.LabelStyle(creditsFont,Color.WHITE));
		Label musicLabel = new Label("  MUSIC & FX:\n freeSFX.co.uk\nLooperman.com",new Label.LabelStyle(creditsFont,Color.WHITE));
		
		Table creditsTable = new Table();
		creditsTable.padTop(40f);
		creditsTable.row();
		creditsTable.setFillParent(true);
		
		creditsTable.center();
		creditsTable.row();
		creditsTable.add(codeLabel);
		creditsTable.row();
		creditsTable.add(graphicsLabel).padTop(20f);
		creditsTable.row();
		creditsTable.add(musicLabel).padTop(20f);
		creditsTable.row();
		
		Table buttonTable = new Table();
		buttonTable.setFillParent(true);
		
		backButton = new ImageButton(new SpriteDrawable(new Sprite(AssetsLoader.getInstance().getManager().get(AssetsPaths.BACK_BUTTON.getPath(),Texture.class))));
		
		backButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				game.setScreen(new EndlessRoad(game));				
			}
			
		});
		
		buttonTable.bottom();
		buttonTable.add(backButton).padBottom(40f);
		
		stage.addActor(creditsTable);
		stage.addActor(buttonTable);
		
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
	     AssetsLoader.getInstance().dispose();
		
	}

}

