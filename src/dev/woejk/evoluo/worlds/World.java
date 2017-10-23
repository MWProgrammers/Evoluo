package dev.woejk.evoluo.worlds;

import dev.woejk.evoluo.Handler;
import dev.woejk.evoluo.entities.EntityManager;
import dev.woejk.evoluo.entities.creatures.Player;
import dev.woejk.evoluo.entities.statics.Mannequin;
import dev.woejk.evoluo.entities.statics.Tree;
import dev.woejk.evoluo.tiles.Tile;
import utils.Utils;

import java.awt.*;

/**
 * Created by Woejk on 24/04/2017.
 */
public class World
{

    private Handler handler;
    private int width, height;                                                                                          //For purpose world can be 5 on 5 tiles long which means it would took 160x160 px.
    private int spawnX, spawnY;                                                                                         //Located in world.txt as second line [px]
    private int[][] tiles;

    //Entities..........................................................................................................
    private EntityManager entityManager;


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public World(Handler handler, String path)                                                                                                      //World from a file!
    {

        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 650, 350));                                 //Start for adding entity like player.


        //TEMPORARY ENTITIES
        entityManager.addEntity(new Tree(handler, 200, 250));

        entityManager.addEntity(new Mannequin(handler, 480, 260));

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public void tick()
    {
        entityManager.tick();
    }

    public void render(Graphics g)
    {
        int     xStart  =(int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH),                                     //Optimalization methematics. Now you can make as big map as you want
                xEnd    =(int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1),
                yStart  =(int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT),
                yEnd    =(int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getWidth()) / Tile.TILEHEIGHT - 15);                                                                                                 //rendering optimalization

        for(int y = yStart; y < yEnd; y++)
        {
            for(int x = xStart; x < xEnd; x++)                                                                          //xStart is tile on the left that player can see, xEnd is a tile on the right that player can see
            {
                getTile(x, y).render(g, (int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                                        (int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        //Entities......................................................................................................
        entityManager.render(g);
    }

    public Tile getTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)                                                                 //Line to prevent code errors when player glitches or teleports away from game map.
            return Tile.grassTile;

        Tile t = Tile.tiles[tiles[x][y]];

        if(t == null)
            return Tile.dirtTile;
        return t;
    }

    private void loadWorld(String path)
    {
       String file = Utils.loadFileAsString(path);
       String[] tokens = file.split("\\s+");
       width = Utils.parseInt(tokens[0]);
       height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }


    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

}
