package dev.woejk.evoluo.display;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Woejk on 21/04/2017.
 */
public class Display
{
    private JFrame frame;                           //Implements WindowConstants, Accesible, RootPaneContainer. Architectur simple and required for further work.
    private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay()
    {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Window will close as normal stance.
        frame.setResizable(false);                              //Window resizable option is an boolean!
        frame.setLocationRelativeTo(null);                      //Initializes window in a center of a screen.
        frame.setVisible(true);                                 //Makes window visible.
        //frame.setExtendedState(6);                               //Stance for full screen.    (DOES NOT WORK!)
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);           //Same as upper one.
        //frame.                                                //Other options in frame. check for yourself!

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));   //Prefered size of active in window terrain
        canvas.setMaximumSize(new Dimension(width,height));     //Maximal -...-
        canvas.setMinimumSize(new Dimension(width,height));     //Minimal -...-
        canvas.setFocusable(false);                             //

        frame.add(canvas);
        frame.pack();                                           //Resizes canvas window a little bit to see canvas fully in it.

    }

    public Canvas getCanvas()       //getCanvas operates, soo it's is accessible from other classes even if it's private
    {
        return canvas;
    }

    public JFrame getFrame()                                //In order to make frame from JFrame able to return in Display class
    {
        return frame;
    }

}
