package dev.woejk.evoluo.entities.statics;

import dev.woejk.evoluo.Handler;
import dev.woejk.evoluo.gfx.Assets;
import dev.woejk.evoluo.tiles.Tile;

import java.awt.*;

/**
 * Created by Woejk on 05/05/2017. Similiar role to Player class.
 */
public class Tree extends StaticEntity
{
    public Tree(Handler handler, float x, float y)
    {
        super(handler, x, y, Tile.TILEWIDTH*3, Tile.TILEHEIGHT*3);

        bounds.x = 9*4;                                                                                                 //Collision rectangle properties. Needs remap.
        bounds.y = (int) (height / 1.5f);
        bounds.width = width  - 18*4;
        bounds.height = (int) (height - height/1.5f);
    }

    @Override
    public void tick()
    {

    }

    @Override
    public void die()
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(Assets.tree,(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
