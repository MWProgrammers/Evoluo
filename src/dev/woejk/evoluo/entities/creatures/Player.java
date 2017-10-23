package dev.woejk.evoluo.entities.creatures;

import com.sun.org.apache.xpath.internal.SourceTree;
import dev.woejk.evoluo.Game;
import dev.woejk.evoluo.Handler;
import dev.woejk.evoluo.entities.Entity;
import dev.woejk.evoluo.gfx.Animation;
import dev.woejk.evoluo.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Woejk on 24/04/2017.
 */
public class Player extends Creature
{

    //Animations........................................................................................................
    private Animation animDown, animUp, animLeft, animRight, animIdle;
    private int animSpeed;
    //Attack timer......................................................................................................
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;


    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);    //You can change DEFAULT_CREATURE_* for scaling effect here or in Creature class.

        bounds.x = 10;
        bounds.y = 14;
        bounds.width = 12;
        bounds.height = 18;

        //Animation.....................................................................................................
        animSpeed = 125;
        animDown = new Animation(animSpeed, Assets.player_down);                                                                            //Animation ( speed of its change in ms, Actual animation frame )
        animUp = new Animation(animSpeed, Assets.player_up);
        animLeft = new Animation(animSpeed, Assets.player_left);
        animRight = new Animation(animSpeed, Assets.player_right);
        animIdle = new Animation(0, Assets.player_idle);
    }

    @Override
    public void tick()
    {
        //Animation.....................................................................................................
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
        animIdle.tick();

        //Movement......................................................................................................
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        //Attacks.......................................................................................................
        checkAttacks();
    }

    private void checkAttacks()
    {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;


        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if(handler.getKeyManager().aUp)
        {
            System.out.println("Up attack!");
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        } else if(handler.getKeyManager().aDown){
            System.out.println("Down attack!");
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        } else if(handler.getKeyManager().aLeft){
            System.out.println("Left attack!");
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }else if(handler.getKeyManager().aRight){
            System.out.println("Right attack!");
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }else{
            //System.out.println("checkAttacks error in Player class");
            return;
        }

        attackTimer = 0;

        for(Entity e : handler.getWorld().getEntityManager().getEntities())
        {
            if(e.equals(this))                                                                            //For every Entity e : (that is in handler.***) check if object in e isnt that one (in this case a player) and stop loop, else do else
                continue;
            if(e.getCollisionBounds(0, 0).intersects(ar))
            {
                e.hurt(1);
                return;
            }
        }

    }

    public void die()
    {
        System.out.println("You died. Game over.");
    }

    private void getInput()
    {
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(getCurrentAnimationFrame(),     (int)(x-handler.getGameCamera().getxOffset()),
                                                    (int)(y-handler.getGameCamera().getyOffset()),
                                                    width, height, null);

    /*    g.setColor(Color.red);                                                                                        //TESTER SHOWING COLLISION BOX!
        g.fillRect( (int)(x + bounds.x - handler.getGameCamera().getxOffset()),
                    (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
                          bounds.width, bounds.height);
     */
    }

    private BufferedImage getCurrentAnimationFrame()
    {
        if(xMove < 0){
            return animLeft.getCurrentFrame();
        }else if(xMove > 0){
            return animRight.getCurrentFrame();
        }else if(yMove < 0) {
            return animUp.getCurrentFrame();
            //}else {
        }else if(yMove > 0){
            return animDown.getCurrentFrame();
        }else {
            return animIdle.getCurrentFrame();
        }
    }
}
