package com.fungineers.zombie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingScreen implements Screen
{
	
	SpriteBatch spriteBatch;
	boolean isDone = false;
	float spriteCount = 0;
	Texture loadingTexture;
	
	public LoadingScreen()
	{
		spriteBatch = new SpriteBatch();
		loadingTexture = new Texture(Gdx.files.internal("data/loading.png"));
	}
	
	@Override
	public void render(float delta)
	{
		spriteCount += delta;
		if(spriteCount <= 2f)
		{
			spriteBatch.begin();
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
			float color = 65f/255f;
			Gdx.gl.glClearColor(color, color, color, 1);
			spriteBatch.draw(loadingTexture, 10, 10);
			spriteBatch.end();
		}
		else
		{
			isDone = true;
		}
	}

	@Override
	public boolean isDone() 
	{
		return isDone;
	}

	@Override
	public Screen dispose() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Screen dispose(ZombieManager app) {
		// TODO Auto-generated method stub
		return null;
	}

}
