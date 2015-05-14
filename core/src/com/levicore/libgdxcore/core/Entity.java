package com.levicore.libgdxcore.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.levicore.libgdxcore.tween.EntityAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

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

    /**
     * Update animation
     */
    public void update(float delta) {
        if(animation != null) {
            setRegion(animation.getKeyFrame(stateTime));
            stateTime += delta;
        }
    }


    /**
     * Convenience methods
     */
    public boolean contains(float x, float y) {
        return getBoundingRectangle().contains(x, y);
    }
    public boolean overlaps(Rectangle rectangle) {
        return getBoundingRectangle().overlaps(rectangle);
    }


    /**
     * Method for changing graphic on timeline.
     */
    public Tween setGraphic(final String image) {
        return Tween.call(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                stateTime = 0;
                animation = null;
                getTexture().dispose();
                setTexture(new Texture(image));
                setSize(getTexture().getWidth(), getTexture().getHeight());
            }
        });
    }
    public void setGraphic_void(String image) {
        stateTime = 0;
        animation = null;
        getTexture().dispose();
        setTexture(new Texture(image));
        setSize(getTexture().getWidth(), getTexture().getHeight());
    }
    public void setGraphic_void(Texture texture) {
        stateTime = 0;
        animation = null;
        getTexture().dispose();
        setTexture(texture);
        setSize(getTexture().getWidth(), getTexture().getHeight());
    }


    /**
     * Method for changing animation on timeline.
     */
    public Tween setAnimation(final Animation newAnimation, final boolean resize) {
        return Tween.call(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                stateTime = 0;
                animation = newAnimation;
                if(resize) {
                    setSize(animation.getKeyFrame(0).getRegionWidth(),
                            animation.getKeyFrame(0).getRegionHeight());
                }
            }
        });
    }
    public void setAnimation_void(Animation newAnimation, boolean resize) {
        stateTime = 0;
        animation = newAnimation;
        if(resize) {
            setSize(animation.getKeyFrame(0).getRegionWidth(),
                    animation.getKeyFrame(0).getRegionHeight());
        }
    }


    /**
     * Interpolation of properties
     */
    public Tween fadeIn(float duration) {
        return Tween.to(this, EntityAccessor.ALPHA, duration).target(1);
    }
    public Tween fadeOut(float duration) {
        return Tween.to(this, EntityAccessor.ALPHA, duration).target(0);
    }
    public Tween fadeTo(float target, float duration) {
        return Tween.to(this, EntityAccessor.ALPHA, duration).target(target);
    }
    public Tween moveTo(float x, float y, float duration) {
        return Tween.to(this, EntityAccessor.POSXY, duration).target(x, y);
    }
    public Tween rotateTo(float angle, float duration) {
        return Tween.to(this, EntityAccessor.ROTATION, duration).target(angle);
    }
    public Tween scaleTo(float scale, float duration) {
        return Tween.to(this, EntityAccessor.SCALE, duration).target(scale);
    }

}
