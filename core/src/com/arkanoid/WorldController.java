package com.arkanoid;

import com.arkanoid.objects.Ball;
import com.arkanoid.objects.Level;
import com.arkanoid.objects.Paddle;
import com.arkanoid.objects.blocks.Block;
import com.arkanoid.screens.GameScreen;
import com.arkanoid.screens.GameScreen.GameState;
import com.arkanoid.utils.GameInputProcessor;
import com.arkanoid.utils.IllegalFormatException;
import com.arkanoid.utils.Resources;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Class for managing the game logic
 *  
 * @author Simone
 *
 */
public class WorldController {

    private Game game;

    private Level level;
    private Ball ball;
    private Paddle paddle;

    private int blocksLeft;
    private int lives;
    private int score;
    private int currentLevel;

    private Stage stage;

    private Sound sound;

    private GameInputProcessor processor;

    public WorldController(Game game, int startLevel) throws IllegalFormatException {
        this.game = game;
        currentLevel = startLevel;
        sound = Resources.getInstance().getSound();
        lives = 3;
        stage = new Stage();
        processor = new GameInputProcessor(this);
        init();
    }

    private void init() throws IllegalFormatException {
        level = new Level(currentLevel);
        paddle = new Paddle();
        ball = new Ball();
        ball.setPosition(paddle);
        blocksLeft = level.countBlocks();

        stage.addActor(paddle);
        stage.addActor(ball);
        processor.setWorld(this);
    }

    /**
     * updates the game logic
     *
     * @param deltaTime the time interval between each update
     * @throws IllegalFormatException
     */
    public void update(float deltaTime) throws IllegalFormatException {
        stage.act(deltaTime);

        //updates every actor
        paddle.updateMotion(deltaTime);
        if (!ball.getMotion()) {
            ball.translate(paddle);
        }
        ball.updateMotion(deltaTime);
        if (ball.getY() + ball.getRadius() < 0) {
            loseLive();
        }

        //Check if the ball hits a block
        for (Block[] line : level.getBlocks()) {
            for (Block b : line) {
                if (b != null && !b.isDestroyed()) {
                    if (Intersector.overlaps(ball.getBounds(), b.getBounds())) {
                        b.isHit();
                        sound.play(0.2f);
                        if (b.isDestructible())
                            score += 100;
                        if (b.isDestroyed())
                            blocksLeft--;
                        ball.bounce(b, level);
                    }
                }
            }
        }

        //check if the ball hits the paddle
        if (ball.getMotion() && Intersector.overlaps(ball.getBounds(), paddle.getBounds()))
            ball.bounce(paddle);

        //when all destructible block are taken down go to the next level
        if (blocksLeft == 0) nextLevel();
    }

    /**
     * Goes to the next level if the current level is cleared if it's not the last level
     * otherwise set the {@link GameState} to {@link GameState#GAME_OVER}
     *
     * @throws IllegalFormatException
     */
    private void nextLevel() throws IllegalFormatException {
        if (currentLevel < 10) {
            currentLevel++;
            addLive();
            init();
        } else {
            ((GameScreen) game.getScreen()).setState(GameState.GAME_OVER);
        }
    }

    /**
     * Loses a live and reset ball and paddle position and movement.
     * If there are not lives left set the {@link GameState} to {@link GameState#GAME_OVER}
     */
    private void loseLive() {
        lives--;
        if (lives == 0) {
            ((GameScreen)game.getScreen()).setState(GameState.GAME_OVER);
        }
        else {
            paddle = new Paddle();
            ball.setMotion(false);
            ball.setPosition(paddle);
            processor.setWorld(this);
        }
    }

    public void addLive() {
        lives++;
    }

    public int getLives() {
        return lives;
    }

    public Level getLevel() {
        return level;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public Ball getBall() {
        return ball;
    }

    public Stage getStage() {
        return stage;
    }

    public int getScore() {
        return score;
    }

    public Game getGame() {
        return game;
    }

    public GameInputProcessor getInputProcessor() {
        return processor;
    }

}
