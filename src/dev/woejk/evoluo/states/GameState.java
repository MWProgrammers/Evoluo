package dev.woejk.evoluo.states;

import dev.woejk.evoluo.Game;
import dev.woejk.evoluo.Handler;
import dev.woejk.evoluo.entities.creatures.Player;
import dev.woejk.evoluo.entities.statics.Tree;
import dev.woejk.evoluo.gfx.Assets;
import dev.woejk.evoluo.tiles.Tile;
import dev.woejk.evoluo.worlds.World;

import java.awt.*;

/**
 * Created by Woejk on 24/04/2017. Game state manager. Entity is everything that is NOT a TILE (Item, enemy, player, etc. )
 */
public class GameState extends State{

    private World world;


    public GameState(Handler handler)
    {
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);


    }

    @Override
    public void tick()
    {
        world.tick();

    }

    @Override
    public void render(Graphics g)
    {
        world.render(g);
        //Tile.tiles[2].render(g, 0,0);                                                                                 //Test for rendering tiles!

    }

}
