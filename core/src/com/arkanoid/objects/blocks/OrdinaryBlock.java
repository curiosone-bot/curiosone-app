package com.arkanoid.objects.blocks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * Created by Simone on 01/02/2018.
 */

public class OrdinaryBlock extends Block
{
    private Long hitTime = 0L;

    public OrdinaryBlock(Color color) {
        this.color = color;
        this.topRightColor = color;
        this.isDestructible = true;
        this.isDestroyed = false;
    }

    @Override
    public void isHit() {
        if (hitTime == 0L) {
            hitTime = System.currentTimeMillis();
        }
        this.isDestroyed = true;
    }

    @Override
    public void render(ShapeRenderer sr) {
        if (!isDestroyed ||
                ((System.currentTimeMillis() - hitTime >  62.5)&&(System.currentTimeMillis() - hitTime <= 125)) ||
                ((System.currentTimeMillis() - hitTime > 187.5)&&(System.currentTimeMillis() - hitTime <  250))) {
            super.render(sr);
        }
    }
}
