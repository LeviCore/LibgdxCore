package com.levicore.libgdxcore.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.levicore.libgdxcore.LibgdxCore;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by user on 5/13/2015.
 */
public class State extends InputAdapter {

    protected LibgdxCore core;

    protected List<Entity> entities;
    protected OrthographicCamera camera;

    private Vector3 screenCoordinates;

    public State(LibgdxCore core) {
        this.core = core;

        entities = new ArrayList<>();
        camera = new OrthographicCamera(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        screenCoordinates = new Vector3();
    }

    public void update(float delta) {
        for(Entity entity : entities) {
            entity.update(delta);
        }
    }

    public void render(Batch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for(Entity entity : entities) {
            entity.draw(batch);
        }

        batch.end();
    }

    public void dispose() {
        for(Entity entity : entities) {
            if(entity.getTexture() != null) {
                entity.getTexture().dispose();
            }
        }
    }


    /**
     *  Pause, Play
     */
    private boolean playing = true;

    public void pause() {
        playing = false;
    }

    public void resume() {
        playing = true;
    }

    public boolean isPlaying() {
        return playing;
    }

    /**
     * Visibility
     */
    public boolean visible = true;

    public void show() {
        visible = true;
    }

    public void hide() {
        visible = false;
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
