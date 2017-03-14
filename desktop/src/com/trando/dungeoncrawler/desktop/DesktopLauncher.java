package com.trando.dungeoncrawler.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.trando.dungeoncrawler.DungeonCrawler;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.vSyncEnabled = true;
		config.foregroundFPS = 30;
		config.backgroundFPS = 30;
		config.width = 1080;
		config.height = 720;
		new LwjglApplication(new DungeonCrawler(), config);
	}
}
