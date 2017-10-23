package dev.woejk.evoluo;

import dev.woejk.evoluo.display.*;
import dev.woejk.evoluo.*;
/**
 * Created by Woejk on 21/04/2017. Launcher is a window initialization class. 0.0.31 / TODO PLAYER ATTACKS animation and hit animation!
 */


public class Launcher
{
    public static void main(String[] args)
                                                                            //Below, created game object that stores variable called game.
    {
        Game game = new Game("Evoluo 0.1a", 1280, 720);  // new Display( "Evoluo 0.01a", 1280, 720); //Initialization for packages and classes in *.evoluo.display through Game() [Game class.]
        game.start();
    }
}