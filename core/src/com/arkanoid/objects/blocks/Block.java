package com.arkanoid.objects.blocks;

import com.arkanoid.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Abstract class for blocks. Extended by {@link GoldBlock}, {@link SilverBlock}, {@link OrdinaryBlock}.
 * Created by Simone on 01/02/2018.
 */

public abstract class Block extends Actor {

    protected final float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
    protected final float width = Constants.BLOCK_WIDTH*w/Constants.WIDTH, height = Constants.BLOCK_HEIGHT*h/Constants.HEIGHT;
    protected int i, j;
    protected float x, y;
    protected final Color borderColor = Color.BLACK;
    protected Color color;
    protected Color topRightColor; //can be different, it's used for shading metal blocks
    protected Rectangle bounds;
    protected boolean isDestructible;
    protected boolean isDestroyed;

    public void setMatrixCoordinates(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        setBounds(x, y);
    }

    @Override
    public float getX() {
        return x;
    }
    @Override
    public float getY() {
        return y;
    }

    public boolean isDestructible() {
        return isDestructible;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    private void setBounds(float x, float y) {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public abstract void isHit();

    public void render(ShapeRenderer sr) {
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect(x, y, width, height, borderColor, borderColor, borderColor, borderColor);
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(x, y+1, width-1, height-1, color, color, topRightColor, color); //it should be x+1 logically, but it doesn't work properly
        sr.end();
    }
}
