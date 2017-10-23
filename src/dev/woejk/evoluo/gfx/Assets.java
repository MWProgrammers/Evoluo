package dev.woejk.evoluo.gfx;

import dev.woejk.evoluo.ImageLoader;

import java.awt.image.BufferedImage;

/**
 * Created by Woejk on 23/04/2017. Contains every photos, musics etcetera. Extremal speed up!
 */
public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage dirt, grass, stone, tree, wallpaper;
    public static BufferedImage mannequin;
    public static BufferedImage[] player_down, player_up, player_left, player_right, player_idle;                                                                             //Array for every "going down" sprite.
    public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right, zombie_idle;
    public static BufferedImage[] btn_start, btn_quit;

    public static void init()
    {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spriteSheet.png"));                         //Holds sprite and doesn't reload it frequently.

        //BUTTON ASSETS

        btn_start = new BufferedImage[2];
        btn_start[0] = sheet.crop(0, height * 4, width * 8, height);
        btn_start[1] = sheet.crop(0, height * 5, width * 8, height);


        btn_quit = new BufferedImage[2];
        btn_quit[0] = sheet.crop(0, height * 6, width * 4, height);
        btn_quit[1] = sheet.crop(width * 4, height * 6, width * 4, height);

        //PLAYER ASSETS

        player_down = new BufferedImage[2];                                                                                //For animation that has "2" images. If animation consist of 5 "imgaes" then you need to put 5 here.
        player_up = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];
        player_idle = new BufferedImage[1];


        player_down[0] = sheet.crop(0, height, width, height );                                             //Coordinates for sprite sheets images 32x32 (height x width)
        player_down[1] = sheet.crop(width, height, width, height );
        player_up[0] = sheet.crop(width * 2, height, width, height );
        player_up[1] = sheet.crop(width * 3, height, width, height );
        player_right[0] = sheet.crop(0, height * 2, width, height );
        player_right[1] = sheet.crop(width, height * 2, width, height );
        player_left[0] = sheet.crop(width * 2, height * 2, width, height );
        player_left[1] = sheet.crop(width * 3, height * 2, width, height );
        player_idle[0] = sheet.crop(0, 0, width, height );

        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width*2, 0, width, height);
        stone = sheet.crop(width*3, 0, width, height);
        tree = sheet.crop(0, height*3, width, height);
        mannequin = sheet.crop(width, height * 3, width, height);
        wallpaper = sheet.crop(width * 8, 0, width * 40, height * 22 + 16);

    }

}
