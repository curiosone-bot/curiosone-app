package com.arkanoid.objects;

import com.arkanoid.utils.Resources;
import com.arkanoid.objects.blocks.Block;
import com.arkanoid.objects.blocks.GoldBlock;
import com.arkanoid.objects.blocks.OrdinaryBlock;
import com.arkanoid.objects.blocks.SilverBlock;
import com.arkanoid.utils.Constants;
import com.arkanoid.utils.IllegalFormatException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Class for building the levels.
 * It contains a 13 x 18 matrix, implemented as 2D Array that can contain a block.
 * @author Simone Sanfratello
 *
 */
public class Level {

    private final int LINES = 13, COLUMNS = 18;

    private Block[][] blocks;

    /**
     * creates the array of blocks, reading the text file
     * correspondent to that level
     */
    public Level(int level) throws IllegalFormatException {
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        this.blocks = new Block[COLUMNS][LINES];

        String txt = Resources.getInstance().getLevel(level);
        String[] textLines = txt.split("\r\n");

        try {
            for (int i = 0; i < COLUMNS; i++) {
                String[] colors = textLines[i].split(",");
                for (int j = 0; j < LINES; j++) {
                    if (!(colors[j].equals("-"))) {
                        switch (colors[j].charAt(0)) {
                            case 'G':
                                blocks[i][j] = new GoldBlock();
                                break;
                            case 'A':
                                blocks[i][j] = new SilverBlock();
                                break;
                            default:
                                blocks[i][j] = new OrdinaryBlock(Resources.getInstance().getColor(colors[j]));
                        }
                        blocks[i][j].setMatrixCoordinates(i, j);
                        blocks[i][j].setPosition((Constants.BLOCK_WIDTH * j) * w/Constants.WIDTH,
                                ((Constants.HEIGHT - Constants.BLOCK_HEIGHT) - Constants.BLOCK_HEIGHT * i) * h/Constants.HEIGHT);
                    }
                }
            }
        }
        catch (Exception e) {
            throw new IllegalFormatException("The text file is not properly formatted");
        }
    }

    public int countBlocks() {
        int nBlocks = 0;
        for (Block[] line : blocks)
            for (Block b : line)
                if (b != null)
                    if (b.isDestructible())
                        nBlocks++;
        return nBlocks;
    }

    public void render(ShapeRenderer sr) {
        for (Block[] line : blocks)
            for (Block b : line)
                if (b != null)
                    b.render(sr);
    }

    public Block[][] getBlocks() {
        return blocks;
	}

	public boolean isBlockPresent(int i, int j) {
        if (i < 0 || i >= COLUMNS)
            return false;
        if (j < 0 || j >= LINES)
            return false;
        if (blocks[i][j] == null)
            return false;
        if (blocks[i][j].isDestroyed())
            return false;
        return true;
	}

}