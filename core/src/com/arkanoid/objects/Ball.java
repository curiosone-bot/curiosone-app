package com.arkanoid.objects;

import com.arkanoid.WorldController;
import com.arkanoid.objects.blocks.Block;
import com.arkanoid.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Class for the ball
 *
 * @author Simone
 *
 */
public class Ball extends Actor
{
    private float w, h;
	private float radius;
    private float x, y;
	private float velX, velY;
	private Circle bounds;
	private boolean isMoving;
	
	public Ball() {
        this.w = Gdx.graphics.getWidth();
        this.h = Gdx.graphics.getHeight();
        this.radius = Constants.RADIUS*h/Constants.HEIGHT;
        this.bounds = new Circle();
        setMotion(false);
	}
	
	/**
	 * Position the ball with the given x and y
     * @param x x-coordinate
     * @param y y-coordinate
	 */
	@Override
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		setBounds(x, y);
	}

    /**
     * Keeps the ball stuck on the center of the paddle.
     * @param paddle the paddle
     */
	public void setPosition(Paddle paddle) {
		x = paddle.getX()+paddle.getWidth()/2;
		y = paddle.getY()+paddle.getHeight()+radius;
		setBounds(x, y);
	}
	
	/**
	 * set the ball hitbox
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	private void setBounds(float x, float y) {
		bounds.set(x, y, radius);
	}
	
	@Override
	public float getX() { return x; }
	@Override
	public float getY() { return y; }
	
	public float getRadius() { return radius; }
	
	public Circle getBounds() { return bounds; }
	
	public void setVelocity(float velX, float velY) {
		this.velX = velX * w/Constants.WIDTH;
		this.velY = velY * w/Constants.HEIGHT;
	}
	
	public void setMotion(boolean isMoving) {

		if (isMoving)
			setVelocity(200f, 400f);
		else
			setVelocity(0f, 0f);
		this.isMoving = isMoving;
	}

	public boolean getMotion() { return isMoving; }
	
	/**
	 * Aggiorna la posizione della pallina mentre si muove
	 * e rimbalza sulle pareti nel caso le tocchi (eccetto quella inferiore perché pụ cadere)
	 */
	public void updateMotion(float deltaTime)
	{
		x += deltaTime*velX;
		y += deltaTime*velY;
		if (x + radius > Gdx.graphics.getWidth()) {
            velX *= -1;
            x = Gdx.graphics.getWidth() - radius;
		}
		if (x - radius < 0) {
			velX *= -1;
            x = radius;
		}
        if (y + radius > Gdx.graphics.getHeight()) {
        	y = Gdx.graphics.getHeight() - radius;
            velY *= -1;
        }
        setBounds(x, y);
	}
	
	/**
	 * Metodo invocato in {@link WorldController} quando la palla è ancorata al paddle
	 * La palla si sposta con il paddle e viene posizionata sopra di esso, in posizione centrata.
	 * @param paddle
	 */
	public void translate(Paddle paddle) {
		setPosition(paddle);
	}
	
	/**
     * At contact with a block the ball performs a bounce on it
     * and the direction of the ball depends on the side hit and the previous direction of the ball.
     * @param b the block
     * @param l the level that contains the block
     */
	public void bounce(Block b, Level l) {

		float blockX = b.getBounds().getX();
		float blockY = b.getBounds().getY();
		
		float blockWidth = b.getBounds().getWidth();
		float blockHeight = b.getBounds().getHeight();

		if (blockX <= x && x <= blockX + blockWidth) {
			if (y < blockY)
				y = blockY - radius;
			else
				y = blockY + blockHeight + radius;
			velY *= -1;
		}
		else if (blockY <= y && y <= blockY + blockHeight) {
			if (x < blockX)
				x = blockX - radius;
			else
				x = blockX + blockWidth + radius;
			velX *= -1;
		}
		else {
            int i = b.getI();
            int j = b.getJ();
            //manage bottom left corner
			if (x < blockX && y < blockY) {
                if (l.isBlockPresent(i+1, j)) {
                    x = blockX - radius;
                    velX *= -1;
                }
                else if (l.isBlockPresent(i, j-1)) {
                    y = blockY - radius;
                    velY *= -1;
                }
                else {
                    x = blockX - (radius/(float)Math.sqrt(2));
                    y = blockY - (radius/(float)Math.sqrt(2));
                    float temp = velX;
                    velX = velY;
                    velY = temp;
                    velX *= -1;
                    velY *= -1;
                }

			}
			//manage top left corner
			else if (x < blockX && y > blockY + blockHeight) {
                if (l.isBlockPresent(i, j-1)) {
                    y = blockY + blockHeight + radius;
                    velY *= -1;
                }
                else if (l.isBlockPresent(i-1, j)) {
                    x = blockX - radius;
                    velX *= -1;
                }
                else {
                    x = blockX - (radius/(float)Math.sqrt(2));
                    y = blockY + (radius/(float)Math.sqrt(2)) + blockHeight;
                    float temp = velX;
                    velX = velY;
                    velY = temp;
                }
			}
			//manage top right corner
			else if (x > blockX + blockWidth && y > blockY + blockHeight) {
                if (l.isBlockPresent(i-1, j)) {
                    x = blockX + blockWidth + radius;
                    velX *= -1;
                }
                else if (l.isBlockPresent(i, j+1)) {
                    y = blockY + blockHeight + radius;
                    velY *= -1;
                }
                else {
                    x = blockX + (radius/(float)Math.sqrt(2)) + blockWidth;
                    y = blockY + (radius/(float)Math.sqrt(2)) + blockHeight;
                    float temp = velX;
                    velX = velY;
                    velY = temp;
                    velX *= -1;
                    velY *= -1;
                }
			}
			//manage bottom right corner
			else {
                if (l.isBlockPresent(i, j+1)) {
                    y = blockY - radius;
                    velY *= -1;
                }
                else if (l.isBlockPresent(i+1, j)) {
                    x = blockX + blockWidth + radius;
                    velX *= -1;
                }
                else {
                    x = blockX + (radius/(float)Math.sqrt(2)) + blockWidth;
                    y = blockY - (radius/(float)Math.sqrt(2));
                    float temp = velX;
                    velX = velY;
                    velY = temp;
                }
			}
		}
		setBounds(x, y);
		
	}
	
	/**
	 * Al contatto con il paddle la pallina esegue un rimbalzo su di esso
	 * In base a quale parte tocca del paddle, la pallina rimbalza 
	 * a sinistra o a destra, di 45(parte rossa della sprite) o 60 gradi(parte grigia della sprite). 
	 * I valori sono impostati in modo che la velocità risultante sia costante. (circa 447,2)
	 * @param paddle the paddle
	 */
	public void bounce(Paddle paddle)
	{
		float paddleX = paddle.getBounds().getX();
		float paddleY = paddle.getBounds().getY();
		y = paddleY + paddle.getHeight() + radius;
		if (x < paddleX + paddle.getWidth()/8) {
			setVelocity(-316f, 316f);
		}
		if (paddleX + paddle.getWidth()/8 <= x && x < paddleX + paddle.getWidth()/2) {
			setVelocity(-200f, 400f);
		}
		if (paddleX + paddle.getWidth()/2 <= x && x < paddleX + paddle.getWidth()*7/8) {
			setVelocity(200f, 400f);
		}
		if (paddleX + paddle.getWidth()*7/8 <= x) {
			setVelocity(316f, 316f);
		}
	}
	
	public void render(ShapeRenderer sr)
	{
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.CYAN);
		sr.circle(x, y, radius);
		sr.end();
	}
}
