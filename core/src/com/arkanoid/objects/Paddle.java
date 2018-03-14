package com.arkanoid.objects;

import com.arkanoid.utils.Constants;
import com.arkanoid.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Class for the paddle.
 * It moves only horizontally.
 * Its hit box fits the sprite, which is took from the original Arkanoid game
 * @author Simone Sanfratello
 *
 */
public class Paddle extends Actor {
    private float w, h;
    private float x, y;
    private float width, height;
    private float velocity;
    private Sprite sprite;
    private Rectangle bounds;
    private boolean leftMove;
    private boolean rightMove;

    public Paddle() {
        this.w = Gdx.graphics.getWidth();
        this.h = Gdx.graphics.getHeight();
        this.x = ((Constants.WIDTH - Constants.PADDLE_WIDTH)/2) * w/Constants.WIDTH;
        this.y = Constants.PADDLE_Y * h/Constants.HEIGHT;
        this.width = Constants.PADDLE_WIDTH * w/Constants.WIDTH;
        this.height = Constants.PADDLE_HEIGHT * h/Constants.HEIGHT;
        this.velocity = Constants.PADDLE_VELOCITY * w/Constants.WIDTH;
        this.sprite = new Sprite(Resources.getInstance().getSprite());
        this.sprite.setPosition(x, y);
        this.sprite.setSize(width, height);
        this.bounds = new Rectangle(x, y, width, height);
	}

	public void updateMotion(float deltaTime) {
        if (leftMove) {
            this.x -= velocity*deltaTime;
            if (x < 0) x = 0;
        }
        if (rightMove) {
            this.x += velocity*deltaTime;
            if (x > w - width) x = w - width;
        }
        bounds.setPosition(x, y);
        sprite.setPosition(x, y);
	}

	public void setLeftMove(boolean b) {
        if (rightMove && b) rightMove = false;
        leftMove = b;
	}

	public void setRightMove(boolean b) {
        if (leftMove && b) leftMove = false;
        rightMove = b;
    }

    @Override
    public float getX() {
        return x;
    }
    @Override
	public float getY() {
        return y;
    }
    @Override
    public void setX(float x) {
        this.x = x;
    }
    @Override
    public float getWidth() {
        return width;
    }
    @Override
    public float getHeight() {
        return height;
    }

    public void draw(Batch batch) {
        sprite.draw(batch);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Sprite getSprite() {
        return sprite;
	}

}
