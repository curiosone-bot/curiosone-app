package com.arkanoid.screens;

import com.arkanoid.WorldController;
import com.arkanoid.WorldRenderer;
import com.arkanoid.utils.GameInputProcessor;
import com.arkanoid.utils.IllegalFormatException;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

/**
 * Class for the game screen.
 * It manages the {@link GameState} and all transition between RUNNING, PAUSE, GAME_OVER.
 *
 * Every new game starts as {@link GameState#RUNNING};
 * and can be paused by pressing {@link Keys#P} on the keyboard or {@link Keys#BACK} button on android.
 * See {@link #pause()};
 * While in RUNNING state the game logic is updated, see {@link WorldController#update(float)};
 *
 * While in PAUSE state the updating is suspended
 * and the game can be resumed by pressing again {@link Keys#P} or {@link Keys#BACK}, or with the {@link TextButton} "Back".
 * See {@link #resume()} ;
 * The pause is issued also when the entire application is suspended, but resuming the app won't make the game resume itself;
 *
 * GAME_OVER state occurs when the player lose all its lives or finish the game by winning the last level
 * and {@link #displayGameOver()} is called;
 * The logic is no longer updated and pausing and resuming the app won't affect this state.
 * A new game can be restarted by clicking {@link TextButton} "Try Again",
 * and "Back to Menu" will bring back to the {@link MainMenuScreen}.
 *
 * @author Simone Sanfratello
 *
 */
public class GameScreen extends AbstractGameScreen {
    private WorldController world;
    private WorldRenderer renderer;

    private GameState state;

    private TextButton resumeButton, menuButton, tryAgainButton;
    //private Table pauseTable;
    //private Table gameOverTable;

    private InputProcessor pauseProcessor;

    public enum GameState {
        RUNNING,
        PAUSE,
        GAME_OVER;
    }

    public GameScreen(final Game game, final int startLevel) throws IllegalFormatException {
        super(game, startLevel);
        this.game = game;

        resumeButton = new TextButton("Resume", style1);
        resumeButton.setPosition(265, 400, Align.center);
        resumeButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                resumeButton.setStyle(style2);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                resumeButton.setStyle(style1);
                resume();
            }
        });

        menuButton = new TextButton("Back to Menu", style1);
        menuButton.setPosition(265, 300, Align.center);
        menuButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menuButton.setStyle(style2);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                menuButton.setStyle(style1);
                stage.dispose();
                music.stop();
                game.setScreen(MainMenuScreen.getInstance(game));
            }
        });

        tryAgainButton = new TextButton("Try Again", style1);
        tryAgainButton.setPosition(265, 400, Align.center);
        tryAgainButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                tryAgainButton.setStyle(style2);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tryAgainButton.setStyle(style1);
                stage.dispose();
                try {
                    init(startLevel);
                } catch (IllegalFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        pauseProcessor = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Keys.P || keycode == Keys.BACK) {
                    if (state != GameState.GAME_OVER) {
                        if (state == GameState.RUNNING) {
                            pause();
                        }
                        else if (state == GameState.PAUSE) {
                            resume();
                        }
                    }
                }
                return false;
            }
        };

        stage.addActor(resumeButton);
        stage.addActor(menuButton);
        stage.addActor(tryAgainButton);
        scaleButtons();
        stage.getRoot().removeActor(resumeButton);
        stage.getRoot().removeActor(menuButton);
        stage.getRoot().removeActor(tryAgainButton);

        /*
        pauseTable = new Table();
        pauseTable.add(resumeButton).expandY();
        pauseTable.row();
        pauseTable.add(menuButton).expandY();

        gameOverTable = new Table();
        gameOverTable.add(tryAgainButton).expandY();
        gameOverTable.row();
        gameOverTable.add(menuButton).expandY();
        */

        init(startLevel);
    }

    private void init(int startLevel) throws IllegalFormatException
    {
        this.world = new WorldController(game, startLevel);
        this.renderer = WorldRenderer.getInstance(world);
        this.stage = world.getStage();

        GameInputProcessor gameProcessor = world.getInputProcessor();
        InputMultiplexer multiplexer = new InputMultiplexer(gameProcessor, pauseProcessor, stage);
        Gdx.input.setInputProcessor(multiplexer);
        this.state = GameState.RUNNING;
    }

    public GameState getState() { return state; }
    public void setState(GameState state) { this.state = state; }

    @Override
    public void show() {
        music.play();
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float deltaTime) {
        if (state == GameState.RUNNING) {
            try {
                world.update(deltaTime);
            }
            catch (IllegalFormatException e) {
                e.printStackTrace();
            }
        }
        else if (state == GameState.PAUSE) {

        }
        if (state == GameState.GAME_OVER) {
            displayGameOver();
        }
        renderer.render(deltaTime);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //renderer.resize(width, height);
	}

	@Override
    public void pause() {
        if (state == GameState.RUNNING) {
            state = GameState.PAUSE;
            stage.addActor(resumeButton);
            stage.addActor(menuButton);
            //stage.addActor(pauseTable);
		}
	}

	@Override
    public void resume() {
        if (state == GameState.PAUSE) {
            stage.getRoot().removeActor(resumeButton);
            stage.getRoot().removeActor(menuButton);
            //stage.getRoot().removeActor(pauseTable);
            state = GameState.RUNNING;
        }
	}

	private void displayGameOver() {
        stage.addActor(tryAgainButton);
        stage.addActor(menuButton);
        //stage.addActor(gameOverTable);
	}

	@Override
    public void hide() {
        music.stop();
	}

	@Override
    public void dispose() {

	}

}
