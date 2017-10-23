package dev.woejk.evoluo.gfx;

import dev.woejk.evoluo.Game;
import dev.woejk.evoluo.Handler;
import dev.woejk.evoluo.entities.Entity;
import dev.woejk.evoluo.tiles.Tile;
import dev.woejk.evoluo.worlds.World;

/**
 * Created by Woejk on 25/04/2017. Camera class, makes some tiles posssible to be seen and some not... :D
 */
public class GameCamera
{
    private Handler handler;
    private float xOffset, yOffset;

    public GameCamera(Handler handler, float xOffset, float yOffset)
    {
        this.handler = handler;
        this.xOffset = xOffset;                                     //X and Y position of camera
        this.yOffset = yOffset;
    }

    public void checkBlankSpace()                                                                                          //Checks for blank spaces, if true makes impossible to see it.
    {
        if(xOffset < 0)                                                                                                 //Sequences for blocking camera view, every other part limits edges of map for moving the camera.
        {
            xOffset = 0;
        }else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth() )
                    {
                    xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
                    }
        if(yOffset < 0)
        {
            yOffset = 0;
        }else if( yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight() )
                    {
                    yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
                    }

    }

    public void centerOnEntity(Entity e)
    {
        xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
        yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace();                                                                                                  //Method above is now called
    }


    public void move(float xAmt, float yAmt)
    {
        xOffset += xAmt;                                            //X and Y position of player movement
        yOffset += yAmt;
        checkBlankSpace();
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
