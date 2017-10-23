package dev.woejk.evoluo.entities;

import dev.woejk.evoluo.Handler;
import dev.woejk.evoluo.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Woejk on 05/05/2017. Supposed to manage entities in project.
 */
public class EntityManager
{
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;                                     //Array list of entities. Brackets (<,>) means what type of information or which class information it has to include. It is same as Entity[x]; but it does not have to determine a size.



    private Comparator<Entity> renderSorter = new Comparator<Entity>()
    {                                                                                   //Comparator serves to compare objects in term for further rendering.
        @Override
        public int compare(Entity a, Entity b)
        {
                    if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
                        return -1;
            return 1;
        }
    };




    public EntityManager(Handler handler, Player player)
    {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);

    }

    public void tick()
    {
        for(int i = 0; i < entities.size(); i++)
        {
            Entity e = entities.get(i);                                 //It is almost same as 'Entity e = entities[i]'
            e.tick();
            if(!e.isActive())
                entities.remove(e);
        }
        entities.sort(renderSorter);                                    //Sorter for method private Comparator<Entity> thats all!
    }

    public void render(Graphics g)
    {
        for(Entity e : entities)                                    //Create Entity "e" for every entities in entity arrayList
        {
            e.render(g);
        }
    }

    public void addEntity(Entity e)
    {
        entities.add(e);
    }

    //GETTERS AND SETTERS ..............................................................................................


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
