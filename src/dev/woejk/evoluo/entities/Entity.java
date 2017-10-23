package dev.woejk.evoluo.entities;

import dev.woejk.evoluo.Game;
import dev.woejk.evoluo.Handler;

import java.awt.*;

/**
 * Created by Woejk on 24/04/2017.
 */
public abstract class Entity
{

    public static final int DEFAUL_HEALTH = 10;
    protected Handler handler;
    protected float x, y;
    protected int width, height;
    private int health;
    protected boolean active = true;
    protected Rectangle bounds;

    public Entity(Handler handler, float x, float y, int width, int height)
    {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        health = DEFAUL_HEALTH;

        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void die();                                                                             //Death abstract method

    public void hurt(int amt)
    {
        health -= amt;
        if(health <= 0)
        {
            active = false;
            die();
        }

    }

    public boolean checkEntityCollisions(float xOffset, float yOffset)
    {
        for(Entity e : handler.getWorld().getEntityManager().getEntities())
        {
            if(e.equals(this))                                                                                          //If the entity that we are looping for is this entity, then just continue. (Continue in java skips rest of the loop.
                continue;

            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))             //Whole boolean means -> If two rectangles are overlapping, return true.
                return true;
        }
        return false;                                                                                                   //If they do not overlap return false.
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset)                                                   //Collision rectangle for entities
    {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
