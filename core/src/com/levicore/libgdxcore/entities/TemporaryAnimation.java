package com.levicore.libgdxcore.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.levicore.libgdxcore.core.Entity;

/**
 * Created by Leonard Vincent C. Onera on 5/17/2015.
 *
 * Utility class for creating temporary animations
 *
 */
public class TemporaryAnimation extends Entity {

    public TemporaryAnimation(float x, float y, Animation animation) {
        TextureRegion region = animation.getKeyFrame(stateTime);

        setRegion(animation.getKeyFrame(0));
        setBounds(x, y, region.getRegionWidth(), region.getRegionHeight());
        stateTime = 0;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        /** Dispose Entity if animation is finished. */
        if(animation.isAnimationFinished(stateTime)) {
            getTexture().dispose();
        }
    }

}
