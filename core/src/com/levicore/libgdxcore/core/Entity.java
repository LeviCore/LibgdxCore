package com.levicore.libgdxcore.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by user on 5/13/2015.
 */
public class Entity extends Sprite {

    protected float stateTime;
    protected Animation animation;

    protected Entity() {
        super();
    }
    public Entity(String texture) {
        super(new Texture(texture));
    }
    public Entity(Texture texture) {
        super(texture);
    }
    public Entity(TextureRegion region) {
        super(region);
    }
    public Entity(Animation animation) {
        super(animation.getKeyFrame(0));
        this.animation = animation;
        setSize(animation.getKeyFrame(0).getRegionWidth(), animation.getKeyFrame(0).getRegionHeight());

        stateTime = 0;
    }

    public void update(float delta) {
        if(animation != null) {
            setRegion(animation.getKeyFrame(stateTime));
            stateTime += delta;
        }
    }

    public boolean contains(float x, float y) {
        return getBoundingRectangle().contains(x, y);
    }

    public boolean overlaps(Rectangle rectangle) {
        return getBoundingRectangle().overlaps(rectangle);
    }

}
