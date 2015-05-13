package com.levicore.libgdxcore;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.levicore.libgdxcore.core.State;
import com.levicore.libgdxcore.core.Settings;
import com.levicore.test.TestState;

import java.util.Stack;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class LibgdxCore extends ApplicationAdapter {

    private SpriteBatch batch;
    private TweenManager tweenManager;
    private InputMultiplexer inputMultiplexer;
    private Stack<State> states;

    private float delta;

	@Override
	public void create () {
		batch = new SpriteBatch();

        tweenManager = new TweenManager();
        inputMultiplexer = new InputMultiplexer();
        states = new Stack<>();

        push(new TestState(this));
	}

    /**
     *  State control methods
     */
    public void push(State state) {
        states.push(state);
        inputMultiplexer.addProcessor(state);
    }

    public void pop() {
        inputMultiplexer.removeProcessor(states.peek());
        states.peek().dispose();
        states.pop();
    }

    public void setPeek(State state) {
        pop();
        states.push(state);
        inputMultiplexer.addProcessor(state);
    }

    /**
     * Update game logic
     */
    public void update() {
        delta = Gdx.graphics.getDeltaTime() * Settings.GAME_SPEED;
        tweenManager.update(delta);

        for(State state : states) {
            if(state.isPlaying()) {
                state.update(delta);
            }
        }
    }

    /**
     * Render State
     */
	@Override
	public void render () {
        clearScreen();
        update();

        for(State state : states) {
            if(state.isVisible()) {
                state.render(batch);
            }
        }
	}

    /**
     * Dispose
     */
    @Override
    public void dispose() {
        batch.dispose();

        for(State state : states) {
            state.dispose();
        }
    }

    /**
     * Clear the screen
     */
    public void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Accessors and mutators
     */
    public TweenManager getTweenManager() {
        return tweenManager;
    }

}
