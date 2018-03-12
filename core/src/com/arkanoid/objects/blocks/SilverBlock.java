package com.arkanoid.objects.blocks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * Created by Simone on 01/02/2018.
 */

public class SilverBlock extends Block {

    private int stacks;
    private long hitTime = 0L;

    public SilverBlock() {
        this.color = Color.GRAY;
        this.topRightColor = Color.LIGHT_GRAY;
        this.isDestructible = true;
        this.isDestroyed = false;
        this.stacks = 2;
    }

    @Override
    public void isHit() {
        stacks--;
        if (stacks == 0) {
            if (hitTime == 0L) {
                hitTime = System.currentTimeMillis();
            }
            this.isDestroyed = true;
        }
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
