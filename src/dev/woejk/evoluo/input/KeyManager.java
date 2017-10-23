package dev.woejk.evoluo.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Woejk on 24/04/2017. Manages key presses
 */
public class KeyManager implements KeyListener
{
    private boolean[] keys;
    public boolean up, down, left, right;
    public boolean aUp, aDown, aLeft, aRight;

    public KeyManager()
    {
        keys = new boolean[256];
    }

    public void tick()
    {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];

        aUp = keys[KeyEvent.VK_UP];
        aDown = keys[KeyEvent.VK_DOWN];
        aLeft  = keys[KeyEvent.VK_LEFT];
        aRight = keys[KeyEvent.VK_RIGHT];
    }


    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)                      // Whenever player presses key action
    {
        keys[e.getKeyCode()] = true;
     //   System.out.println("Key pressed");                // Test for pressing key!


    }

    @Override
    public void keyReleased(KeyEvent e)                     // Whenever player releases key action
    {
        keys[e.getKeyCode()] = false;
    }
}
