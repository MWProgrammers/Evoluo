package dev.woejk.evoluo.ui;

import dev.woejk.evoluo.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Woejk on 09/05/2017.
 */
public class UIManager
{
    private Handler handler;
    private ArrayList<UIObject> objects;

    public UIManager(Handler handler)
    {
        this.handler = handler;
        objects = new ArrayList<UIObject>();
    }

    public void tick()
    {
        for(UIObject o : objects)         //For every single UIObject in our objects do object o.tick() method
            o.tick();
    }

    public void render(Graphics g)
    {
        for(UIObject o : objects)
            o.render(g);
    }

    public void onMouseMove(MouseEvent e)
    {
        for(UIObject o : objects)
            o.onMouseMove(e);
    }

    public void onMouseRelease(MouseEvent e)
    {
        for(UIObject o : objects)
            o.onMouseRelease(e);
    }

    public void addObject(UIObject o)
    {
        objects.add(o);
    }

    public void removeObject(UIObject o)
    {
        objects.remove(o);
    }


    //GETTERS && SETTERS ...............................................................................................


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.objects = objects;
    }
}
