package dev.woejk.evoluo.entities.statics;

import dev.woejk.evoluo.Handler;
import dev.woejk.evoluo.entities.Entity;

/**
 * Created by Woejk on 05/05/2017. Base for every static entity. Which means those whose don't move. It is similiar to creature class.
 */
public abstract class StaticEntity extends Entity
{

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }

}
