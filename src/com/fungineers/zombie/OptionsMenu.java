package com.fungineers.zombie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OptionsMenu implements Screen, InputProcessor
{

	SpriteBatch spriteBatch;
	boolean gameState = false;
	Texture backgroundTexture;
	Texture whiteTexture;
	int musicLength = 100;
	int soundLength = 100;
	
	ZombieManager gameParent = null;
	
	OptionsMenu(ZombieManager app)
	{
		gameParent = app;
		musicLength = app.musicVolume;
		soundLength = app.soundVolume;
		backgroundTexture = gameParent.optionsTexture;
		whiteTexture = gameParent.whiteTexture;
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
		spriteBatch.setColor(241f/255f, 239f/255f, 72f/255f, 1);
		spriteBatch.draw(whiteTexture, 406, 307, musicLength, 5); //321
		spriteBatch.draw(whiteTexture, 406, 224, soundLength, 5);
		spriteBatch.setColor(1,1,1,1);
        spriteBatch.end();
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		if(x>=356 && x<=492 && y>=408 && y<=474)
		{
			gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
			gameState = true;
		}
		if(x>=389 && x<= 741)
		{
			if(y>=142 && y<=190)
			{
				if(x<=406)
				{
					musicLength=0;
				}
				else if(x>=726)
				{
					musicLength=321;
				}
				else
				{
					musicLength = x-406;
				}
			}
			else if(y>=242 && y<=268)
			{
				if(x<=406)
				{
					soundLength=0;
				}
				else if(x>=726)
				{
					soundLength=321;
				}
				else
				{
					soundLength = x-406;
				}
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
	public Screen dispose() 
	{
		gameParent.soundVolume = soundLength;
		gameParent.musicVolume = musicLength;
		FileHandle file = Gdx.files.external("zombies/settings.txt");
		file.writeString(musicLength + " " + soundLength, false);
		return null;
	}
	@Override
	public Screen dispose(ZombieManager app) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
