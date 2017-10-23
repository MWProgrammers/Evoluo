package dev.woejk.evoluo.states;

import dev.woejk.evoluo.Game;
import dev.woejk.evoluo.Handler;
import dev.woejk.evoluo.gfx.Assets;
import dev.woejk.evoluo.ui.ClickListener;
import dev.woejk.evoluo.ui.UIImageButton;
import dev.woejk.evoluo.ui.UIManager;
import dev.woejk.evoluo.ui.UIObject;

import java.awt.*;

/**
 * Created by Woejk on 24/04/2017. Menu state manager.
 */
public class MenuState extends State
{

    private UIManager uiManager;


    public MenuState(Handler handler)
    {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManaget().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(500, 320, 256, 32, Assets.btn_start, new ClickListener()
        {
            @Override
            public void onClick()
                {
                handler.getMouseManaget().setUiManager(null);                                                    //Below... This line cuts down problem in comment below.
                State.setState(handler.getGame().gameState);                                                            //User is still able to get mouse events even in gamestate!
                }
        }));


        uiManager.addObject(new UIImageButton(564, 380, 128, 32, Assets.btn_quit, new ClickListener()
        {
            @Override
            public void onClick()
            {
                System.exit(0);
            }
        }));
    }


    @Override
    public void tick()
    {
        //System.out.println(handler.getMouseManaget().getMouseX() + "  " + handler.getMouseManaget().getMouseY());     //Rect tester for mouse
        //if(handler.getMouseManaget().isLeftPressed() && handler.getMouseManaget().isRightPressed())                    //If both mouse keys are pressed change menu window to game window
        //    State.setState(handler.getGame().gameState);
        uiManager.tick();

    }

    @Override
    public void render(Graphics g)
    {
        //g.drawImage(Assets.stone, 0, 0, null);                       //Render dirt texture cut from sprite sheet.
        //g.setColor(Color.RED);                                                                                          //Rect tests for mouse
        //g.fillRect(handler.getMouseManaget().getMouseX(), handler.getMouseManaget().getMouseY(), 10, 10); //Rect tests for mouse
        g.drawImage(Assets.wallpaper, 0, 0, null);
        uiManager.render(g);

    }

}
