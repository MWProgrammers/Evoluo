package dev.woejk.evoluo;

import dev.woejk.evoluo.display.Display;
import dev.woejk.evoluo.gfx.Assets;
import dev.woejk.evoluo.gfx.GameCamera;
import dev.woejk.evoluo.gfx.SpriteSheet;
import dev.woejk.evoluo.input.KeyManager;
import dev.woejk.evoluo.input.MouseManager;
import dev.woejk.evoluo.states.GameState;
import dev.woejk.evoluo.states.MenuState;
import dev.woejk.evoluo.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Created by Woejk on 21/04/2017. Holds all of the basic code for game.
 */

public class Game implements Runnable                       //To separate threads we need to implement "Runnable",it requires public void run() and private Thread
{
    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;                                  //Separated thread

    private BufferStrategy bs;
    private Graphics g;

    // States...........................................................................................................
    public State gameState;         //Change it for private after tests
    public State menuState;

    //Input.............................................................................................................
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Camera............................................................................................................
    private GameCamera gameCamera;

    //Handler...........................................................................................................
    private Handler handler;



    public Game(String title, int width, int height)
    {
        this.width = width;                                    //width, height, disp=new disp " makes initialization of window in here, soo in Launcher class we don't have to initialize window but only a Game() !
        this.height = height;
        this.title = title;                                     //Changed to initialize variable called title. Soo I can access it in init method to be used in Display
        keyManager = new KeyManager();
        mouseManager = new MouseManager();


    }

        private void init()                                     //Method run only once. Initializes graphics, etc. | GAME LOOP : Update variables, positions of obj, etc -> Render (draw) screen
        {
            display= new Display(title, width, height);
            display.getFrame().addKeyListener(keyManager);
            display.getFrame().addMouseListener(mouseManager);
            display.getFrame().addMouseMotionListener(mouseManager);
            display.getCanvas().addMouseListener(mouseManager);
            display.getCanvas().addMouseMotionListener(mouseManager);
            Assets.init();

            handler = new Handler(this);
            gameCamera = new GameCamera(handler, 0, 0);


            gameState = new GameState(handler);
            menuState = new MenuState(handler);

            State.setState(menuState);                            //For now we won't make menu! Maybe later but initialized is only game.

        }

        private void render() {
            bs = display.getCanvas().getBufferStrategy();       //Setups bs to whatever BufferStrategy is atm. Buffer strategy is a way for computer to draw anything. Buffer->buffer->Actual render.
            if (bs == null)
            {
                display.getCanvas().createBufferStrategy(3); //If there's no bs it will make 3 buffers
                return;
            }

            g = bs.getDrawGraphics();                           //"Paint brush" for coding :D <3
            //Clear screen
            g.clearRect(0, 0, width, height);

            //Draw here! TODO || Y are reversed in JAVA! (!!!)

            if(State.getState() != null)                        //If it exists render every frame
                State.getState().render(g);


            //End drawing!!
            bs.show();
            g.dispose();
        }

        int x = 0;

        private void tick()
        {
            keyManager.tick();

           if(State.getState() != null)
                State.getState().tick();
        }

    public void run()                                           //Method required by implemented "Runnable" in a class.
    {

        init();

        int fps = 60;                                               //Frames per second
        double timePerTick = 1000000000 / fps;                      //Because one second is equal to 1 000 000 000 nano seconds and we divide it by our fps.
        double delta = 0;                                           //Amount of time we have until we have to call tick() and render() methods.
        long now;
        long lastTime = System.nanoTime();                          //Gets information about time in nano second and assigns it to long lastTime.
        long timer = 0;                                             //Once we hit 1 second we have to figure out somehow if it's well done! <NOT REQUIRED! BUT IT'S TEST>
        int ticks = 0;

        while(running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;                //When and when not to call tick and run methods.
            timer += now - lastTime;                                //FPS counter.
            lastTime = now;

            if(delta >= 1)
                {
                tick();
                render();
                ticks++;
                delta--;
                }

                if(timer >= 1000000000)                             //FPS counter.
                {
                    System.out.println("Ticks and frames: " + ticks);
                    ticks = 0;
                    timer = 0;
                }
            }

        stop();                                                 //Just in case while loop won't stop.
    }

    public KeyManager getKeyManager()                           //Makes keyManager possible to be accessed beyond class
    {
        return keyManager;
    }

    public MouseManager getMouseManager()
    {
        return mouseManager;
    }

    public GameCamera getGameCamera()
    {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public synchronized void start()                            //Method that starts thread. "Synchronized is used usually only with a thread to test synchronization in term of time."
    {
        if(running)                                             //If we call start() method we ensure that when it's already running we dont need to do rest of that methods code
            return;
        running=true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop()                             //Method that stops thread. "Synchronized is used usually only with a thread to test synchronization in term of time."
    {
        if(!running)
            return;
        running = false;

        try
        {
        thread.join();
        }
            catch (InterruptedException e)
            {
            e.printStackTrace();                                //Auto-generated catch block
            }
    }
}
