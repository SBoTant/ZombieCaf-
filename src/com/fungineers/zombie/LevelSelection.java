package com.fungineers.zombie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelSelection implements Screen, InputProcessor
{

	SpriteBatch spriteBatch;
	boolean gameState = false;
	Texture backgroundTexture;
	Texture levelFrame;
	Texture whiteTexture;
	String fileContents;
	String mapName;
	BitmapFont scoreText;
	int largestNum = 17;
	int clickedNum = -1;
	int highScore;
	int zombieNumber;
	int ringNumber;
	int redTime;
	int yellowTime;
	int blueTime;
	int worldWidth;
	String[] parsed;
	boolean isClicked = false;
	
	ZombieManager gameParent = null;
	
	LevelSelection(ZombieManager app)
	{
		gameParent = app;
		backgroundTexture = gameParent.level1Texture;
		whiteTexture = gameParent.whiteTexture;
		levelFrame = gameParent.levelFrame;
		FileHandle file = null;
		Gdx.input.setInputProcessor(this);
		file = Gdx.files.external("zombies/highscores.txt");
		if(file.exists())
		{
			fileContents = file.readString();
		}
		else
		{
			
			fileContents = "";
			for(int x=0;x<18;x++)
			{
				fileContents += "-1 ";
			}
			file.writeString(fileContents, false);
		}
		String delims = "[ ]";
		parsed = fileContents.split(delims);
		gameParent.textFlush();
		scoreText = new BitmapFont(gameParent.fontData, gameParent.fontTexture, false);
		scoreText.setColor(241f/255f, 241f/255f, 241/255f, 1);
		gameState = false;
		spriteBatch = new SpriteBatch();
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		spriteBatch.begin();
		spriteBatch.draw(backgroundTexture, 0, 0);
		
		if(!isClicked)
		{
			boolean nextmap = false;
			
			for(int a=0;a<3;a++)
			{
				for(int b=0;b<6;b++)
				{
					int[] colors = LevelLoader.getTimes((a*6)+b);
					int time = (int)Math.ceil((float)(Integer.parseInt(parsed[a*6+b]))/100);
					if(Integer.parseInt(parsed[a*6+b]) != -1)
					{
						if(time < colors[2])
						{
							spriteBatch.setColor(206f/255f, 24f/255f, 24f/255f, 1);
						}
						else if(time < colors[1])
						{
							spriteBatch.setColor(231f/255f, 212f/255f, 32f/255f, 1);
						}
						else if(time < colors[0])
						{
							spriteBatch.setColor(43f/255f, 121f/255f, 208f/255f, 1);
						}
						spriteBatch.draw(whiteTexture, 140+(b*105), (290-105*a), 60, 8);
					}
					else
					{
						if(!nextmap)
						{
							nextmap = true;
							largestNum = a*6+b;
							spriteBatch.setColor(0, 0, 0, 1);
							spriteBatch.draw(levelFrame, 133+(b*105), (283-105*a));
						}
					}
				}
			}
		}
		else
		{
			int time = (int)Math.ceil((float)(Integer.parseInt(parsed[clickedNum-1]))/100);
			if(Integer.parseInt(parsed[clickedNum-1]) != -1)
			{
				if(time < redTime)
				{
					spriteBatch.setColor(206f/255f, 24f/255f, 24f/255f, 1);
				}
				else if(time < yellowTime)
				{
					spriteBatch.setColor(231f/255f, 212f/255f, 32f/255f, 1);
				}
				else if(time < blueTime)
				{
					spriteBatch.setColor(43f/255f, 121f/255f, 208f/255f, 1);
				}
			}
			else
			{
				spriteBatch.setColor(96f/255f, 96f/255f, 96f/255f, 1);
			}
			spriteBatch.draw(whiteTexture, 76, 347, 172, 20);
			scoreText.setScale(1.15f);
			scoreText.draw(spriteBatch, mapName, 256, 367);
			scoreText.setScale(0.5f);
			scoreText.draw(spriteBatch, msecToMin(blueTime*100), 105, 166);
			scoreText.draw(spriteBatch, msecToMin(yellowTime*100), 105, 139);
			scoreText.draw(spriteBatch, msecToMin(redTime*100), 105, 112);
			scoreText.setScale(0.7f);
			if(Integer.parseInt(parsed[clickedNum-1]) != -1)
			{
				scoreText.draw(spriteBatch, msecToMin(Integer.parseInt(parsed[clickedNum-1])), 455, 302);
			}
			else
			{
				scoreText.draw(spriteBatch, "__:__:__", 455, 302);
			}
			scoreText.draw(spriteBatch, zombieNumber + "", 582, 273);
			scoreText.draw(spriteBatch, ringNumber + "", 543, 245);
			scoreText.draw(spriteBatch, worldWidth + "", 474, 217);
			scoreText.setScale(1.2f);
			scoreText.setColor(0, 0, 0, 1);
			if(clickedNum < 10)
			{
				scoreText.draw(spriteBatch, "0" + clickedNum, 80, 228);
			}
			else
			{
				scoreText.draw(spriteBatch, "" + clickedNum, 80, 228);
			}
			
		}
		scoreText.setColor(241f/255f, 241f/255f, 241/255f, 1);
		spriteBatch.setColor(1, 1, 1, 1);
        spriteBatch.end();
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		if(!isClicked)
		{
			int num = -1;
			for(int a=0;a<3;a++)
			{
				for(int b=0;b<6;b++)
				{
					if(x >= (140+105*b) && x <= (200+105*b) && y >= (130+105*a) && y <= (190+105*a))
					{
						num = (a*6)+b+1;
					}
				}
			}
			if(num != -1 && num <= largestNum+1)
			{
				gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
				backgroundTexture = gameParent.level2Texture;
				clickedNum = num;
				isClicked = true;
				int[] info = LevelLoader.getTimes(clickedNum-1);
				blueTime = info[0];
				yellowTime = info[1];
				redTime = info[2];
				ringNumber = info[3];
				zombieNumber = info[4];
				worldWidth = info[5];
				
				mapName = LevelLoader.getName(clickedNum-1);
			}
			if(x>=47 && x<=192 && y<=444 && y>=407)
			{
				gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
				gameState = true;
			}
		}
		else
		{
			if(x>=55 && x<= 222 && y<=440 && y>=378)
			{
				backgroundTexture = gameParent.level1Texture;
				gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
				isClicked = false;
			}
			else if(x>=361 && x<=781 && y<=463 && y>=378)
			{
				gameParent.levelNumber = clickedNum;
				gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
				gameState = true;
			}
		}
		return false;
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
	public Screen dispose() 
	{
		return null;
	}
	@Override
	public Screen dispose(ZombieManager app) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
