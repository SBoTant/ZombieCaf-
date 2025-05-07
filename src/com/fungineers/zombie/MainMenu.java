package com.fungineers.zombie;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu implements Screen, InputProcessor
{
	Texture backgroundTexture;
	Texture whiteTexture;
	Texture startButton;
	Texture guideButton;
	Texture optionsButton;
	Texture creditsButton;
	int startPos = 630;
	int guidePos = 630;
	int optionsPos = 630;
	int creditsPos = 630;
	int selectedButton = 0;
	int prevButton = 0;
	int traveledDist = 0;
	int screenMod = 0;
	boolean isMoving = false;
	SpriteBatch spriteBatch;
	boolean gameState = false;
	ZombieManager gameParent;
	
	MainMenu(ZombieManager app)
	{
		gameParent = app;
		Gdx.input.setInputProcessor(this);
		backgroundTexture = gameParent.menuTexture;
		whiteTexture = gameParent.whiteTexture;
		startButton = gameParent.startButton;
		guideButton = gameParent.guideButton;
		optionsButton = gameParent.optionsButton;
		creditsButton = gameParent.creditsButton;
		spriteBatch = new SpriteBatch();
	}
	
	@Override
	public void render(float delta) 
	{
		spriteBatch.begin();
		if(LevelLoader.gameWidth == 800)
		{
			screenMod = 54;
			spriteBatch.draw(backgroundTexture, -54, 0);
		}
		else
		{
			screenMod = 0;
			spriteBatch.draw(backgroundTexture, 0, 0);
		}
		updatePosition(delta);
		spriteBatch.draw(startButton, startPos-screenMod, 256);
		spriteBatch.draw(guideButton, guidePos-screenMod, 130);
		spriteBatch.draw(optionsButton, optionsPos-screenMod, 193);
		spriteBatch.draw(creditsButton, creditsPos-screenMod, 67);
        spriteBatch.end();
	}
	
	public void updatePosition(float delta)
	{
		float modifier = 0;
		if(isMoving)
		{
			modifier = 400f*delta;
			modifier = (int)Math.floor(modifier);
			traveledDist += modifier;
			if(traveledDist >= 103)
			{
				modifier -= (traveledDist-103);
				modifier = (int)Math.floor(modifier);
				traveledDist = 0;
				isMoving = false;
			}
		}
		if(selectedButton == 0)
		{
			spriteBatch.setColor(25f/255f, 25f/255f, 25f/255f, 1);
		}
		if(selectedButton == 1 || prevButton == 1)
		{
			if(selectedButton == 1)
			{
				spriteBatch.setColor(187f/255f, 58f/255f, 34f/255f, 1);
				startPos -= modifier;
			}
			else
			{
				startPos += modifier;
			}
		}
		if(selectedButton == 2 || prevButton == 2)
		{
			if(selectedButton == 2)
			{
				spriteBatch.setColor(42f/255f, 87f/255f, 2, 1);
				guidePos -= modifier;
			}
			else
			{
				guidePos += modifier;
			}
		}
		if(selectedButton == 3 || prevButton == 3)
		{
			if(selectedButton == 3)
			{
				spriteBatch.setColor(1, 202f/255f, 18f/255f, 1);
				optionsPos -= modifier;
			}
			else
			{
				optionsPos += modifier;
			}
		}
		if(selectedButton == 4 || prevButton == 4)
		{
			if(selectedButton == 4)
			{
				spriteBatch.setColor(70f/255f, 70f/255f, 70f/255f, 1);
				creditsPos -= modifier;
			}
			else
			{
				creditsPos += modifier;
			}
		}
		if(LevelLoader.gameWidth == 800)
		{
			spriteBatch.draw(whiteTexture, -4, 0, 37, 500);
		}
		else
		{
			spriteBatch.draw(whiteTexture, 0, 0, 37, 500);
		}
		spriteBatch.setColor(1, 1, 1, 1);
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		if(!isMoving)
		{
			if(x<=startPos+330-screenMod && x>=startPos-screenMod && y>=161 && y<=224)
			{
				prevButton = selectedButton;
				selectedButton = 1;
				gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
				if(prevButton == selectedButton)
				{
					gameState = true;
				}
				else
				{
					isMoving = true;
				}
			}
			else if(x<=guidePos+330-screenMod && x>=optionsPos-screenMod && y>=225 && y<=287)
			{
				gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
				prevButton = selectedButton;
				selectedButton = 3;
				if(prevButton == selectedButton)
				{
					gameState = true;
				}
				else
				{
					isMoving = true;
				}
			}
			else if(x<=optionsPos+330-screenMod && x>=guidePos-screenMod && y>=288 && y<=350)
			{
				prevButton = selectedButton;
				selectedButton = 2;
				gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
				if(prevButton == selectedButton)
				{
					gameState = true;
				}
				else
				{
					isMoving = true;
				}
			}
			else if(x<=creditsPos+330-screenMod && x>=creditsPos-screenMod && y>=351 && y<=413)
			{
				prevButton = selectedButton;
				selectedButton = 4;
				gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
				if(prevButton == selectedButton)
				{
					gameState = true;
				}
				else
				{
					isMoving = true;
				}
			}
			else
			{
				prevButton = selectedButton;
				if(selectedButton != 0)
				{
					gameParent.clickSound.play(((float)gameParent.soundVolume/321)*2f);
					isMoving = true;
				}
				selectedButton = 0;
			}
		}
		return true;
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
	public Screen dispose(ZombieManager app)
	{
		Screen temp = null;
		if(selectedButton == 1)
		{
			temp = new LevelSelection(app);
		}
		else if(selectedButton == 2)
		{
			temp = new GuideMenu(app);
		}
		else if(selectedButton == 3)
		{
			temp = new OptionsMenu(app);
		}
		else
		{
			temp = new CreditsMenu(app);
		}
		return temp;
	}

}
