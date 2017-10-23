package dev.woejk.evoluo.tiles;

import dev.woejk.evoluo.gfx.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by Woejk on 24/04/2017.
 */
public class RockTile extends Tile
{

    public RockTile( int id)
    {
        super(Assets.stone, id);
    }

    @Override
    public boolean isSolid()                    // Override in purpose to make stone non passable
    {
        return true;
    }

}
