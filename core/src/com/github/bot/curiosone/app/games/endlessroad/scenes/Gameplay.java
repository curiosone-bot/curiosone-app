package com.github.bot.curiosone.app.games.endlessroad.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.games.endlessroad.utilities.GameInfos;
import com.github.bot.curiosone.app.games.endlessroad.worldmanager.WorldController;
import com.github.bot.curiosone.app.games.endlessroad.worldmanager.WorldRenderer;



/**
 * This class represents the gameplay screen
 * @author Paolo Pierantozzi
 */
public class Gameplay implements Screen
{
	private WorldController controller;
	private WorldRenderer renderer;

	public Gameplay(Game game)
	{
		controller = new WorldController(game);
		renderer = new WorldRenderer(game, controller);
	}
	@Override
	public void show()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta)
	{
		if (!GameInfos.gameOver)
		{
			controller.update(delta);
			renderer.render(delta);
		}

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
		renderer.dispose();
	}

}
