package dev.woejk.evoluo.gfx;

import java.awt.image.BufferedImage;

/**
 * Created by Woejk on 23/04/2017. It's purpose is to cut sprite sheets into existing textures.
 */
public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet)
    {
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int width, int height)                     //
    {
        return sheet.getSubimage(x, y, width, height);                                  //Return new BufferedImage from whatever specified x,y,width and height.
    }

}
