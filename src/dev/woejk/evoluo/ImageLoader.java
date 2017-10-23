package dev.woejk.evoluo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Paulinka on 2017-04-22. Image loading class.
 */
public class ImageLoader {

    public static BufferedImage loadImage(String path)  //Public static cause they need to be accessible from whole program
    {
        try                             //Resolution for errors
            {
            return ImageIO.read(ImageLoader.class.getResource(path));       //Return buffered object into image.
            }
        catch (IOException e)
            {
            e.printStackTrace();
            System.exit(1);        //Exit node.
            }
    return null;
    }

}
