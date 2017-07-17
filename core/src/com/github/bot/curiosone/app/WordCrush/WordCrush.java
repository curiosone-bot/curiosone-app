package com.curiosone.wordcrush;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sun.prism.image.ViewPort;

public class WordCrush extends Game {
	SpriteBatch batch;
	FitViewport viewPort;
	BitmapFont font;
	String text;
	Texture background;
	Texture cloud;
	OrthographicCamera camera;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("myFont.fnt"));
		font.getData().setScale(1.5f);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 480, 800);
		camera.position.set(240,400,0);
		background = new Texture("backgroundBlueSky.png");
		cloud = new Texture("cloud1.png");
		text = "Word Crush";
		
	
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);// blackbackground
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update();
		camera.update();
		drawScene();
		
	}
	
	@Override
	public void dispose ()
	{
		batch.dispose();
		
		
	}
	
	public void update()
	{
		
	}
	
	public void drawScene()
	{
		batch.begin();
		batch.disableBlending();
		batch.draw(background, 0, 0);
		batch.enableBlending();
		font.draw(batch,text,(Gdx.graphics.getWidth()-440),(Gdx.graphics.getHeight()-100));
		batch.draw(cloud,700,300);
		batch.end();
	}
	
}
