package com.fungineers.zombie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PauseMenu implements Screen, InputProcessor
{

	SpriteBatch spriteBatch;
	boolean gameState = false;
	int sceneState = 0;
	Texture backgroundTexture;
	Texture resumeButton;
	Texture menuButton;
	Texture restartButton;
	ZombieManager gameParent;
	
	PauseMenu(ZombieManager app)
	{
		gameParent = app;
		backgroundTexture = gameParent.pauseTexture;
		resumeButton = gameParent.resumeButton;
		menuButton = gameParent.menuButton;
		restartButton = gameParent.restartButton;
		Gdx.input.setInputProcessor(this);
		gameState = false;
		spriteBatch = new SpriteBatch();
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		spriteBatch.begin();
		spriteBatch.draw(backgroundTexture, 0, 0);
		spriteBatch.draw(resumeButton, 505, 114);
		spriteBatch.draw(menuButton, 505, 64);
		spriteBatch.draw(restartButton, 505, 14);
        spriteBatch.end();
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		if(x>=505 && x<=795)
		{
			if(y<=366 && y>=302)
			{
				gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
				sceneState = 1;
				gameState = true;
			}
			else if(y>=365 && y<=416)
			{
				gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
				sceneState = 2;
				gameState = true;
			}
			else if(y>=415 && y<=466)
			{
				gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
				sceneState = 3;
				gameState = true;
			}
		}
		return false;
	}
	
	
	
	
	@Override
	public boolean keyDown(int arg0)
	{
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDone()
	{
		return gameState;
	}

	@Override
	public Screen dispose() {
		return null;
		// TODO Auto-generated method stub
		
	}
	@Override
	public Screen dispose(ZombieManager app) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
