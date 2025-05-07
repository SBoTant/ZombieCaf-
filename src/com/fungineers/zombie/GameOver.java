package com.fungineers.zombie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOver implements Screen, InputProcessor
{
	
	Texture backgroundTexture;
	Texture rankTexture;
	float alphaTimer = 1;
	SpriteBatch spriteBatch;
	int gameScore;
	boolean victoryState;
	boolean gameState = false;
	BitmapFont scoreText;
	float timeThresh = 0;
	
	ZombieManager gameParent;
	
	float gameTimer;
	int zombiesKilled;
	int shotsFired;
	int timesJumped;
	int levelNumber;
	int highScore;
	float maxSpeed;
	String fileContents;
	
	GameOver(ZombieManager app, float time, int killed, int fired, int jumped, float max, int level)
	{
		gameParent = app;
		gameTimer = time;
		zombiesKilled = killed;
		levelNumber = level;
		shotsFired = fired;
		timesJumped = jumped;
		maxSpeed = max;
		backgroundTexture = gameParent.overTexture;
		rankTexture = gameParent.rankTexture;
		Gdx.input.setInputProcessor(this);
		gameParent.textFlush();
		scoreText = new BitmapFont(gameParent.fontData, gameParent.fontTexture, false);
		gameState = false;
		scoreText.setColor(241f/255f, 241f/255f, 241f/255f, 1);
		spriteBatch = new SpriteBatch();
		
		FileHandle file = Gdx.files.external("zombies/highscores.txt");
		fileContents = file.readString();
		String delims = "[ ]";
		String[] parsed = fileContents.split(delims);
		highScore = Integer.parseInt(parsed[levelNumber]);
		if(Math.floor(gameTimer*100) < highScore || highScore == -1)
		{
			String temp = "";
			highScore = (int)Math.floor(gameTimer*100);
			for(int x=0;x<parsed.length;x++)
			{
				if(x != levelNumber)
				{
					temp += parsed[x] + " ";
				}
				else
				{
					temp += highScore + " ";
				}
			}
			file.writeString(temp, false);
		}
	}
	
	@Override
	public void render(float delta) 
	{
		spriteBatch.begin();
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		spriteBatch.draw(backgroundTexture, 0, 0);
		scoreText.setScale(37f/32f);
		scoreText.draw(spriteBatch, computeTime(), 476, 212);
		scoreText.setScale(31f/32f);
		scoreText.draw(spriteBatch, msecToMin(highScore), 208, 128);
		scoreText.setScale(14f/32f);
		scoreText.draw(spriteBatch, "" + zombiesKilled, 601, 147);
		scoreText.draw(spriteBatch, "" + shotsFired, 601, 127);
		scoreText.draw(spriteBatch, "" + timesJumped, 601, 107);
		scoreText.draw(spriteBatch, "" + (double)Math.round((maxSpeed) * 10) / 10, 601, 88);
		int[] colors = LevelLoader.getTimes(levelNumber);
		if(gameTimer <= colors[2])
		{
			spriteBatch.setColor(206f/255f, 24f/255f, 24f/255f, 1);
		}
		else if(gameTimer <= colors[1])
		{
			spriteBatch.setColor(231f/255f, 212f/255f, 32f/255f, 1);
		}
		else if(gameTimer <= colors[0])
		{
			spriteBatch.setColor(43f/255f, 121f/255f, 208f/255f, 1);
		}
		spriteBatch.draw(rankTexture, 275, 67);
		spriteBatch.setColor(1, 1, 1, 1);
		
		alphaTimer -= delta;
    	if(alphaTimer <= 0)
    	{
    		alphaTimer = 0;
    	}
    	spriteBatch.setColor(1, 1, 1, alphaTimer);
    	spriteBatch.draw(gameParent.blackTexture, 0, 0, 1000, 500);
    	spriteBatch.setColor(1, 1, 1, 1);
		
		
		
        spriteBatch.end();
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		if(alphaTimer <= 0)
		{
			if(x>=173 && x<=325 && y<=480 && y>= 436)
			{
				gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
				gameState = true;
			}
		}
		return false;
	}
	
	public String computeTime()
	{
		String value = "";
		int min;
		int secl;
		int secr;
		int msecl;
		int msecr;
		
		min = (int)Math.floor(gameTimer/60);
		secl = (int)Math.floor((gameTimer-min*60)/10);
		secr = (int)Math.floor((gameTimer-min*60)-secl*10);
		msecl = (int)Math.floor((gameTimer - min*60 - (secl*10 + secr))*10);
		msecr = (int)Math.floor((gameTimer - min*60 - (secl*10 + secr))*100)-msecl*10;
		
		value = min + ":" + secl + secr + ":" + msecl + msecr;
		return value;
	}
	
	public String msecToMin(int msec)
	{
		String value = "";
		int min;
		int secl;
		int secr;
		int msecl;
		int msecr;
		
		float msecs = ((float)msec)/100f;
		
		min = (int)Math.floor(msecs/60);
		secl = (int)Math.floor((msecs-min*60)/10);
		secr = (int)Math.floor((msecs-min*60)-secl*10);
		msecl = (int)Math.floor((msecs - min*60 - (secl*10 + secr))*10);
		msecr = (int)Math.floor((msecs - min*60 - (secl*10 + secr))*100)-msecl*10;
		
		value = min + ":" + secl + secr + ":" + msecl + msecr;
		return value;
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
