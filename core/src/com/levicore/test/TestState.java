package com.levicore.test;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.levicore.libgdxcore.LibgdxCore;
import com.levicore.libgdxcore.core.Entity;
import com.levicore.libgdxcore.core.State;
import com.levicore.libgdxcore.entities.Button;
import com.levicore.libgdxcore.utils.selection.OptionCallback;

/**
 * Created by user on 5/13/2015.
 */
public class TestState extends State {

    public TestState(LibgdxCore core) {
        super(core, 1000, 1000);
        entities.add(new Entity("badlogic.jpg"));
        entities.get(0).moveTo(-50, -50, 1).start(tweenManager);

        entities.add(Button.create(this).addOnTouchUpCallback(new OptionCallback() {
            @Override
            public void execute() {
                System.out.print("haha");
            }
        }));


    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void render(Batch batch) {
        super.render(batch);
    }
}
