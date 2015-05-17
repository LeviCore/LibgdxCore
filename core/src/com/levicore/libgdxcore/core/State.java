package com.levicore.libgdxcore.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.levicore.libgdxcore.LibgdxCore;
import com.levicore.libgdxcore.entities.Button;
import com.levicore.libgdxcore.entities.TemporaryAnimation;
import com.levicore.libgdxcore.tween.ActorAccessor;
import com.levicore.libgdxcore.tween.EntityAccessor;
import com.levicore.libgdxcore.tween.OrthographicCameraAccessor;
import com.levicore.libgdxcore.tween.SpriteAccessor;
import com.levicore.libgdxcore.tween.SpriteBatchAccessor;

import java.util.ArrayList;
import java.util.List;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;


/**
 * Created by user on 5/13/2015.
 */
public class State extends InputAdapter {

    protected LibgdxCore core;

    protected List<Entity> entities;
    protected OrthographicCamera camera;
    protected TweenManager tweenManager;

    private Vector3 screenCoordinates;

    public State(LibgdxCore core, float screenWidth, float screenHeight) {
        this.core = core;

        entities = new ArrayList<>();
        camera = new OrthographicCamera(screenWidth, screenHeight);
        screenCoordinates = new Vector3();

        tweenManager = core.getTweenManager();

        // Register Acessors
        Tween.registerAccessor(Entity.class, new EntityAccessor());
        Tween.registerAccessor(SpriteBatch.class, new SpriteBatchAccessor());
        Tween.registerAccessor(OrthographicCamera.class, new OrthographicCameraAccessor());
        Tween.registerAccessor(Actor.class, new ActorAccessor());
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
    }

    /**
     * Update, render and dispose
     */
    public void update(float delta) {
        for(Entity entity : entities) {
            entity.update(delta);

            if(entity instanceof TemporaryAnimation) {
                if(entity.getAnimation().isAnimationFinished(entity.getStateTime())) {
                    entities.remove(entity);
                }
            }
        }
    }
    public void render(Batch batch) {
        batch.setProjectionMatrix(camera.combined);
        for(Entity entity : entities) {
            entity.draw(batch);
        }
    }
    public void dispose() {
        for(Entity entity : entities) {
            if(entity.getTexture() != null) {
                entity.getTexture().dispose();
            }
        }
    }


    /**
     * Touch inputs
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for(Entity entity : entities) {
            if(entity instanceof Button) {
                if(entity.contains(getScreenCoordinates().x, getScreenCoordinates().y)) {
                    ((Button) entity).setToTouchDownGraphic();

                    if(((Button) entity).getOnTouchdown() != null) {
                        ((Button) entity).getOnTouchdown().execute();
                    }
                }
            }
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for(Entity entity : entities) {
            if(entity instanceof Button) {
                Button button_ = ((Button) entity);

                if(!entity.contains(getScreenCoordinates().x, getScreenCoordinates().y)) {
                    button_.setToTouchUpGraphic();
                } else {
                    if(button_.getOnTouchUp() != null) {
                        button_.getOnTouchUp().execute();
                    }
                }
            }
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return super.touchDragged(screenX, screenY, pointer);
    }

    /**
     *  Pause, Play
     */
    private boolean playing = true;
    public Tween pause() {
        return Tween.call(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                playing = false;
            }
        });
    }
    public Tween resume() {
        return Tween.call(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                playing = true;
            }
        });
    }

    public boolean isPlaying() {
        return playing;
    }

    /**
     * Visibility
     */
    public boolean visible = true;
    public Tween show() {
        return Tween.call(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                visible = false;
            }
        });
    }
    public Tween hide() {
        return Tween.call(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                visible = false;
            }
        });
    }

    public boolean isVisible() {
        return visible;
    }

    /**
     * Accessors and mutators
     */
    public Vector3 getScreenCoordinates() {
        screenCoordinates.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        screenCoordinates = camera.unproject(screenCoordinates);

        return screenCoordinates;
    }




}
