package dev.woejk.evoluo.states;

import dev.woejk.evoluo.Handler;

import java.awt.Graphics;
/**
 * Created by Woejk on 24/04/2017.
 */
public abstract class State {

    private static State currentState = null;                               //What current state you want to tick() and render()

    public static void setState(State state)                                // Setting the current state
    {
        currentState = state;
    }

    public static State getState()                                          // Getting current state
    {
        return currentState;
    }


    //CLASS ............................................................................................................
    protected Handler handler;

    public State(Handler handler)
    {
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
