package com.curiosone.wordcrushse.game_object;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.curiosone.wordcrushse.loaders.Assets;

/** Letter of a tile table
 * Created by Giuseppe on 7/23/2017.
 */

public class Letter extends ImageButton implements Comparable<Letter>
{
    public final static float WIDTH = 1000;
    public final static float HEIGHT = 1000;
    private ImageButtonStyle imageButtonStyle;


    /**
     * Constructor used for alphabetic tile
     * @param name
     */
    public Letter (char name)
    {
        super (new SpriteDrawable( new Sprite( Assets.instance.assetLetter.skinTileUp.get(name - 'A'))));
        this.setName(name + "");
        setWidth(WIDTH);
        setHeight(HEIGHT);
        imageButtonStyle = new ImageButtonStyle();
        imageButtonStyle.imageUp = new SpriteDrawable( new Sprite( Assets.instance.assetLetter.skinTileUp.get(name - 'A')));
        imageButtonStyle.imageDown = new SpriteDrawable( new Sprite( Assets.instance.assetLetter.skinTileDown.get(name - 'A')));
        super.setStyle(imageButtonStyle);
    }

    /**
     * Constructor used for the tileblank
     * @param name
     */
    public Letter (String name)
    {
        super( new SpriteDrawable( new Sprite( Assets.instance.assetLetter.tileBlank)));
        this.setName(name);
        setWidth(WIDTH);
        setHeight(HEIGHT);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || getClass() != obj.getClass()) return false;
        Letter l = (Letter) obj;
        return (getName().equals(l.getName()));
    }

    @Override
    public int compareTo(Letter letter) {
        return getName().compareTo(letter.getName());
    }





}
