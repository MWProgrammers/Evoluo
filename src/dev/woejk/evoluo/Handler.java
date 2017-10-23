package dev.woejk.evoluo;

import dev.woejk.evoluo.gfx.GameCamera;
import dev.woejk.evoluo.input.KeyManager;
import dev.woejk.evoluo.input.MouseManager;
import dev.woejk.evoluo.worlds.World;

/**
 * Created by Woejk on 25/04/2017. Handler will help with collision occurances. That class makes life easier. It stores methods for world and game class which gives us possibility to produce real time collision system.
 */
public class Handler
{
    private Game game;
    private World world;


    public Handler(Game game)
    {
        this.game = game;
    }

    public GameCamera getGameCamera()
    {
        return game.getGameCamera();
    }

    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }

    public MouseManager getMouseManaget()
    {
        return game.getMouseManager();
    }

    public int getWidth()
    {
        return game.getWidth();
    }

    public int getHeight()
    {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
