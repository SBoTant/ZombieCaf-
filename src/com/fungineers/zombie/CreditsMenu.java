package com.fungineers.zombie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CreditsMenu implements Screen, InputProcessor
{
	Texture creditsRoll;
	float textAlpha = 0;
	ZombieManager gameParent;
	boolean alphaIncreasing = true;
	SpriteBatch spriteBatch;
	boolean gameState = false;
	BitmapFont scoreText;
	boolean isCompleted = false;
	int screenMod;
	float creditsPos = -1300;
	
	CreditsMenu(ZombieManager app)
	{
		if(LevelLoader.gameWidth == 800)
		{
			screenMod = 27;
		}
		else
		{
			screenMod = 0;
		}
		Gdx.input.setInputProcessor(this);
		gameParent = app;
		gameParent.textFlush();
		scoreText = new BitmapFont(gameParent.fontData, gameParent.fontTexture, false);
		scoreText.setScale(0.4f);
		creditsRoll = gameParent.creditsRoll;
		gameState = false;
		spriteBatch = new SpriteBatch();
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
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		if(isCompleted)
		{
			gameState = true;
		}
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
	public void render(float delta) 
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(30f/255f, 30f/255f, 30f/255f, 1);
		spriteBatch.begin();
		creditsPos += delta*100;
		if(creditsPos >= 330)
		{
			isCompleted = true;
			creditsPos = 330;
			scoreText.setColor(1, 1, 1, textAlpha);
			if(alphaIncreasing)
			{
				textAlpha += delta;
				if(textAlpha >= 1)
				{
					textAlpha = 1;
					alphaIncreasing = false;
				}
				scoreText.draw(spriteBatch, "Touch anywhere to continue...", 280-screenMod, 320);
			}
			else
			{
				textAlpha -= delta;
				if(textAlpha <= 0)
				{
					textAlpha = 0;
					alphaIncreasing = true;
				}
				scoreText.draw(spriteBatch, "Touch anywhere to continue...", 280-screenMod, 320);
			}
		}
		spriteBatch.draw(creditsRoll, 171-screenMod, creditsPos);
        spriteBatch.end();
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
