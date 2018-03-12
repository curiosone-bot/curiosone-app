package com.arkanoid.utils;

import com.arkanoid.WorldController;
import com.arkanoid.objects.Ball;
import com.arkanoid.objects.Paddle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

/**
 * Class for managing the input on the game screen
 * Created by Simone on 24/02/2018.
 *
 */
public class GameInputProcessor extends InputAdapter {
    private Ball ball;
    private Paddle paddle;

    public GameInputProcessor(WorldController world) {
        this.ball = world.getBall();
        this.paddle = world.getPaddle();
        world.getStage().setKeyboardFocus(paddle);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.LEFT) {
            paddle.setLeftMove(true);
        }
        if (keycode == Keys.RIGHT) {
            paddle.setRightMove(true);
        }
        if (keycode == Keys.SPACE) {
            if (!ball.getMotion())
                ball.setMotion(true);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.LEFT) {
            paddle.setLeftMove(false);
        }
        if (keycode == Keys.RIGHT) {
            paddle.setRightMove(false);
        }
        return false;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //Gdx.graphics.getHeight() - screenY because touch screen y-axis is inverted
        if (paddle.getBounds().contains(screenX, Gdx.graphics.getHeight() - screenY)){
            if (!ball.getMotion())
                ball.setMotion(true);
        }
        else if (screenX < Gdx.graphics.getWidth()/2) {
            paddle.setLeftMove(true);
        }
        else if (screenX > Gdx.graphics.getWidth()/2) {
            paddle.setRightMove(true);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (screenX < Gdx.graphics.getWidth()/2 ) {
            paddle.setLeftMove(false);
        }
        else if (screenX > Gdx.graphics.getWidth()/2) {
            paddle.setRightMove(false);
        }
        return false;
    }

    public void setWorld(WorldController world) {
        this.ball = world.getBall();
        this.paddle = world.getPaddle();
        world.getStage().setKeyboardFocus(paddle);
    }
}

