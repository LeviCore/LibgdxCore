package com.levicore.libgdxcore.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.levicore.libgdxcore.LibgdxCore;
import com.levicore.test.MapLoadingTest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MapLoadingTest(), config);
	}
}
