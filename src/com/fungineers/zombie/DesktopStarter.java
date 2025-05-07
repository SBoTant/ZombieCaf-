package com.fungineers.zombie;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopStarter 
{
	public static void main(String[] args)
	{
		new LwjglApplication(new ZombieManager(), "Application", 854, 480, false);
	}
}
