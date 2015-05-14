package com.levicore.libgdxcore.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.levicore.libgdxcore.core.Entity;
import com.levicore.libgdxcore.core.State;
import com.levicore.libgdxcore.utils.selection.OptionCallback;

/**
 * Created by user on 5/14/2015.
 */
public class Button extends Entity {

    private State state;

    private OptionCallback onTouchdown;
    private OptionCallback onTouchUp;

    private Animation normalAnimation;
    private Texture normalTexture;

    private Animation touchDownAnimation;
    private Texture touchDownTexture;

    private Button(State state) {
        super("badlogic.jpg");
        this.state = state;
    }

    public static Button create(State state) {
        return new Button(state);
    }

    /**
     * Set graphic depending on input
     */
    public void setToTouchDownGraphic() {
        if (touchDownAnimation != null) {
            setAnimation_void(touchDownAnimation, false);
        } else if (touchDownTexture != null) {
            setGraphic_void(touchDownTexture);
        }
    }
    public void setToTouchUpGraphic() {
        if (normalAnimation != null) {
            setAnimation_void(normalAnimation, false);
        } else if (normalTexture != null) {
            setGraphic_void(normalTexture);
        }
    }

    /**
     * Normal State graphic
     */
    public Button setNormalGraphic(String image) {
        normalTexture = new Texture(image);
        setGraphic_void(image);
        return this;
    }
    public Button setNormalGraphic(Animation animation, boolean resize) {
        normalAnimation = animation;
        if(resize) {
            setSize(normalAnimation.getKeyFrame(0).getRegionWidth(),
                    normalAnimation.getKeyFrame(0).getRegionHeight());
        }

        setAnimation_void(animation, resize);
        return this;
    }


    /**
     * Touchdown State graphic
     */
    public Button setTouchdownGraphic(String image) {
        touchDownTexture = new Texture(image);
        return this;
    }
    public Button setTouchdownGraphic(Animation animation, boolean resize) {
        touchDownAnimation = animation;
        if(resize) {
            setSize(touchDownAnimation.getKeyFrame(0).getRegionWidth(),
                    touchDownAnimation.getKeyFrame(0).getRegionHeight());
        }
        return this;
    }

    /**
     * Callbacks
     */
    public Button addOnTouchdownCallback(OptionCallback callback) {
        onTouchdown = callback;
        return this;
    }
    public Button addOnTouchUpCallback(OptionCallback callback) {
        onTouchUp = callback;
        return this;
    }

    /**
     * Accessors and mutators
     */
    public OptionCallback getOnTouchdown() {
        return onTouchdown;
    }
    public OptionCallback getOnTouchUp() {
        return onTouchUp;
    }
}
