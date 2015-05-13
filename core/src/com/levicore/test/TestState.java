package com.levicore.test;

import com.levicore.libgdxcore.LibgdxCore;
import com.levicore.libgdxcore.core.Entity;
import com.levicore.libgdxcore.core.State;

/**
 * Created by user on 5/13/2015.
 */
public class TestState extends State {

    public TestState(LibgdxCore core) {
        super(core, 1000, 1000);
        entities.add(new Entity("badlogic.jpg"));
        entities.get(0).moveTo(-50, -50, 1).start(tweenManager);
    }

}
