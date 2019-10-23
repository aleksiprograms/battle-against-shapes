package com.aleksiprograms.battleagainstshapes.desktop;

import com.aleksiprograms.battleagainstshapes.TheGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.x = 50;
		config.y = 50;
		new LwjglApplication(new TheGame(), config);
	}
}