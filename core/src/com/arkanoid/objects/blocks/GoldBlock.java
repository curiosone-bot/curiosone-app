package com.arkanoid.objects.blocks;

import com.badlogic.gdx.graphics.Color;

/**
 *
 * Created by Simone on 01/02/2018.
 */

public class GoldBlock extends Block {

    public GoldBlock() {
        this.color = Color.GOLD;
        this.topRightColor = Color.GOLDENROD;
        this.isDestructible = false;
        this.isDestroyed = false;
    }

    @Override
    public void isHit() { }
}
