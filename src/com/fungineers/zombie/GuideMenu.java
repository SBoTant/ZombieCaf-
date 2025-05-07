package com.fungineers.zombie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuideMenu implements Screen, InputProcessor
{

	SpriteBatch spriteBatch;
	Texture backgroundTexture;
	boolean gameState = false;
	int screenNumber = 1;
	Texture currentScreen;
	ZombieManager gameParent;
	
	
	GuideMenu(ZombieManager app)
	{
		gameParent = app;
		Gdx.input.setInputProcessor(this);
		backgroundTexture = new Texture(Gdx.files.internal("data/guidebg.png"));
		currentScreen = gameParent.guideTextures[screenNumber-1];
		gameState = false;
		spriteBatch = new SpriteBatch();
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		spriteBatch.begin();
		spriteBatch.draw(backgroundTexture, 0, 0);
		spriteBatch.draw(currentScreen, 390, 60);
        spriteBatch.end();
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		if(x>=353 && x<=464 && y<=469 && y>=428)
		{
			gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
			gameState = true;
		}
		else if(x>=718 && x<=771 && y<=170 && y>=108)
		{
			if(screenNumber > 1)
			{
				screenNumber--;
				currentScreen = gameParent.guideTextures[screenNumber-1];
				gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
			}
		}
		else if(x>=715 && x<=773 && y<=466 && y>=401)
		{
			if(screenNumber < 8)
			{
				screenNumber++;
				currentScreen = gameParent.guideTextures[screenNumber-1];
				gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
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
