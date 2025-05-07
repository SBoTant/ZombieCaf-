package com.fungineers.zombie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



public class LevelEditor implements Screen, InputProcessor
{
	SpriteBatch characterBatch;
	Animation walkAnimation;
	TextureRegion[] idleFrames;
	Animation idleAnimation;
	Animation jumpAnimation;
	TextureRegion[] jumpFrames;
	TextureRegion[] walkFrames;
	SpriteBatch spriteBatch;
	TextureRegion currentFrame;
	BitmapFont scoreText;
	BitmapFont speedText;
	Texture mainCharacterT;
	boolean gameStatus = false;
	
	Sprite currentRing;
	
	Animation walkAnimationL;
	TextureRegion[] walkFramesL;
	Animation idleAnimationL;
	TextureRegion[] idleFramesL;
	Animation jumpAnimationL;
	TextureRegion[] jumpFramesL;
	Animation shootAnimationL;
	TextureRegion[] shootFramesL;
	Animation shootAnimation;
	TextureRegion[] shootFrames;
	Animation zombieAnimation;
	TextureRegion[] zombieFrames;
	Animation zombieAnimationL;
	TextureRegion[] zombieFramesL;
	Animation zombieAnimation2L;
	TextureRegion[] zombieFrames2L;
	Animation zombieAnimation2;
	TextureRegion[] zombieFrames2;
	Animation zombieDeathAnimation;
	TextureRegion[] zombieDeathFrames;
	Animation zombieDeathAnimationL;
	TextureRegion[] zombieDeathFramesL;
	Animation zombieDeathAnimation2L;
	TextureRegion[] zombieDeathFrames2L;
	Animation zombieDeathAnimation2;
	TextureRegion[] zombieDeathFrames2;
	Animation deathAnimationL;
	TextureRegion[] deathFramesL;
	Animation deathAnimation;
	TextureRegion[] deathFrames;
	
	Player mainCharacter;
	EditorWorld gameWorld;
	ZombieManager gameParent;
	boolean leftPressed = false;
	boolean rightPressed = false;
	boolean jumpPressed = false;
	boolean isVictory = true;
	int jumpPointer = -1;
	int charHeight = 0;
	int jumpDirection = -1;
	int savedYPos;
	int detectedSmallest;
	int prevX = -1;
	int sceneState = 0;
	int sceneHandler = 0;
	
	float stateTime = 0;
	float jumpTime = 0;
	float jumpContainer = 0;
	float shootContainer = 0;
	float bulletRotation = 0;
	float blockCount = 0;
	float blinkCount = 0;
	float gColor = 47f/255f;
	float relativePos = -700;
	float deathAlpha = 1;
	float alphaTimer = 1;
	float buttonAlpha = 0;
	
	double speedModifier = 1;
	float speedTimer = 0;
	float gameTimer = 0;
	float deathTimer = 0;
	
	boolean isBlockedLeft = false;
	boolean isBlockedRight = false;
	
	boolean isCompleted = true;
	boolean isMoving = true;
	boolean isJumping = false;
	boolean isMovingRight = true;
	boolean isShooting;
	boolean isDamage = true;
	boolean isBlocked = false;
	boolean isBlinking;
	boolean isDyingRight;
	
	int zombiesKilled = 0;
	int shotsFired = 0;
	int timesJumped = 0;
	float maxSpeed = 0;
	
	
	
	String mapName = "Baby Steps";
	int mapWidth = 5000;
	int xDragged = -1;
	int zombieType = 0;
	int yDragged = -1;
	int toRemove = -1;
	int removeType = -1;
	
	public LevelEditor(ZombieManager parent)
	{
		
		
		int rand = (int)(Math.random()*15)+1;
		int rand2 = (int)(Math.random() * 16000) + 7000;
		int rand3 = (int)(Math.random()*50)+1;
		rand3 = 0;
		rand2 = mapWidth;
		rand = 0;
		int[] temparray = new int[rand];
		int[] temparray2 = new int[rand3];
		int[] temparray3 = new int[rand3];
		gameWorld = new EditorWorld(rand2, 100, temparray, temparray2, temparray3, 45);
		Gdx.input.setInputProcessor(this);
		gameParent = parent;
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);


		
		
		mainCharacter = new Player(100,20,3);
		scoreText = new BitmapFont(gameParent.fontData, gameParent.fontTexture, false);
        TextureRegion[][] tmp = TextureRegion.split(gameParent.walkSheet, gameParent.walkSheet.getWidth() / 8, gameParent.walkSheet.getHeight() / 2);
        TextureRegion[][] tmp2 = TextureRegion.split(gameParent.walkSheetL, gameParent.walkSheetL.getWidth() / 8, gameParent.walkSheetL.getHeight() / 2);
        walkFrames = new TextureRegion[11];
        walkFramesL = new TextureRegion[11];
        int index = 0;
        for (int i = 0; i < 2; i++) 
        {
                for (int j = 0; j < 8; j++) 
                {
                	if(index < 11)
                	{
                		walkFrames[index++] = tmp[i][j];
                		walkFramesL[index-1] = tmp2[i][j];
                	}
                }
        }
        walkAnimation = new Animation(0.04f, walkFrames);
        walkAnimationL = new Animation(0.04f, walkFramesL);
        tmp = TextureRegion.split(gameParent.idleSheet, gameParent.idleSheet.getWidth() / 4, gameParent.idleSheet.getHeight() / 2);
        tmp2 = TextureRegion.split(gameParent.idleSheetL, gameParent.idleSheetL.getWidth() / 4, gameParent.idleSheetL.getHeight() / 2);
        idleFrames = new TextureRegion[8];
        idleFramesL = new TextureRegion[8];
        index = 0;
        for (int i = 0; i < 2; i++) 
        {
                for (int j = 0; j < 4; j++) 
                {
                	if(index < 11)
                	{
                		idleFrames[index++] = tmp[i][j];
                		idleFramesL[index-1] = tmp2[i][j];
                	}
                }
        }
        idleAnimation = new Animation(0.2f, idleFrames);
        idleAnimationL = new Animation(0.2f, idleFramesL);
        tmp = TextureRegion.split(gameParent.jumpSheet, gameParent.jumpSheet.getWidth() / 8, gameParent.jumpSheet.getHeight() / 2);
        tmp2 = TextureRegion.split(gameParent.jumpSheetL, gameParent.jumpSheetL.getWidth() / 8, gameParent.jumpSheetL.getHeight() / 2);
        jumpFrames = new TextureRegion[11];
        jumpFramesL = new TextureRegion[11];
        index = 0;
        for (int i = 0; i < 2; i++) 
        {
                for (int j = 0; j < 8; j++) 
                {
                	if(index < 11)
                	{
                		jumpFrames[index++] = tmp[i][j];
                		jumpFramesL[index-1] = tmp2[i][j];
                	}
                }
        }
        jumpAnimation = new Animation(0.1f, jumpFrames);
        jumpAnimationL = new Animation(0.1f, jumpFramesL);
        
        tmp = TextureRegion.split(gameParent.shootSheet, gameParent.shootSheet.getWidth() / 2, gameParent.shootSheet.getHeight() / 2);
        tmp2 = TextureRegion.split(gameParent.shootSheetL, gameParent.shootSheetL.getWidth() / 2, gameParent.shootSheetL.getHeight() / 2);
        shootFrames = new TextureRegion[4];
        shootFramesL = new TextureRegion[4];
        index = 0;
        for (int i = 0; i < 2; i++) 
        {
                for (int j = 0; j < 2; j++) 
                {
                	if(index < 4)
                	{
                		shootFrames[index++] = tmp[i][j];
                		shootFramesL[index-1] = tmp2[i][j];
                	}
                }
        }
        shootAnimation = new Animation(0.1f, shootFrames);
        shootAnimationL = new Animation(0.1f, shootFramesL);
        
        tmp = TextureRegion.split(gameParent.zombieSheet, gameParent.zombieSheet.getWidth() / 8, gameParent.zombieSheet.getHeight() / 2);
        tmp2 = TextureRegion.split(gameParent.zombieSheetL, gameParent.zombieSheetL.getWidth() / 8, gameParent.zombieSheetL.getHeight() / 2);
        zombieFrames = new TextureRegion[9];
        zombieFramesL = new TextureRegion[9];
        index = 0;
        for (int i = 0; i < 2; i++) 
        {
                for (int j = 0; j < 8; j++) 
                {
                	if(index < 9)
                	{
                		zombieFrames[index++] = tmp[i][j];
                		zombieFramesL[index-1] = tmp2[i][j];
                	}
                }
        }
        zombieAnimation = new Animation(0.1f, zombieFrames);
        zombieAnimationL = new Animation(0.1f, zombieFramesL);
        
        tmp = TextureRegion.split(gameParent.zombieSheet2, gameParent.zombieSheet2.getWidth() / 4, gameParent.zombieSheet2.getHeight() / 2);
        tmp2 = TextureRegion.split(gameParent.zombieSheet2L, gameParent.zombieSheet2L.getWidth() / 4, gameParent.zombieSheet2L.getHeight() / 2);
        zombieFrames2 = new TextureRegion[6];
        zombieFrames2L = new TextureRegion[6];
        index = 0;
        for (int i = 0; i < 2; i++) 
        {
                for (int j = 0; j < 4; j++) 
                {
                	if(index < 6)
                	{
                		zombieFrames2[index++] = tmp[i][j];
                		zombieFrames2L[index-1] = tmp2[i][j];
                	}
                }
        }
        zombieAnimation2 = new Animation(0.1f, zombieFrames2);
        zombieAnimation2L = new Animation(0.1f, zombieFrames2L);
        
        tmp = TextureRegion.split(gameParent.zombieDeathSheet, gameParent.zombieDeathSheet.getWidth() / 4, gameParent.zombieDeathSheet.getHeight() / 2);
        tmp2 = TextureRegion.split(gameParent.zombieDeathSheetL, gameParent.zombieDeathSheetL.getWidth() / 4, gameParent.zombieDeathSheetL.getHeight() / 2);
        zombieDeathFrames = new TextureRegion[8];
        zombieDeathFramesL = new TextureRegion[8];
        index = 0;
        for (int i = 0; i < 2; i++) 
        {
                for (int j = 0; j < 4; j++) 
                {
                	if(index < 8)
                	{
                		zombieDeathFrames[index++] = tmp[i][j];
                		zombieDeathFramesL[index-1] = tmp2[i][j];
                	}
                }
        }
        zombieDeathAnimation = new Animation(0.1f, zombieDeathFrames);
        zombieDeathAnimationL = new Animation(0.1f, zombieDeathFramesL);
        
        tmp = TextureRegion.split(gameParent.zombieDeathSheet2, gameParent.zombieDeathSheet2.getWidth() / 4, gameParent.zombieDeathSheet2.getHeight() / 2);
        tmp2 = TextureRegion.split(gameParent.zombieDeathSheet2L, gameParent.zombieDeathSheet2L.getWidth() / 4, gameParent.zombieDeathSheet2L.getHeight() / 2);
        zombieDeathFrames2 = new TextureRegion[8];
        zombieDeathFrames2L = new TextureRegion[8];
        index = 0;
        for (int i = 0; i < 2; i++) 
        {
                for (int j = 0; j < 4; j++) 
                {
                	if(index < 8)
                	{
                		zombieDeathFrames2[index++] = tmp[i][j];
                		zombieDeathFrames2L[index-1] = tmp2[i][j];
                	}
                }
        }
        zombieDeathAnimation2 = new Animation(0.1f, zombieDeathFrames2);
        zombieDeathAnimation2L = new Animation(0.1f, zombieDeathFrames2L);
        
        tmp = TextureRegion.split(gameParent.deathSheet, gameParent.deathSheet.getWidth() / 4, gameParent.deathSheet.getHeight() / 2);
        tmp2 = TextureRegion.split(gameParent.deathSheetL, gameParent.deathSheetL.getWidth() / 4, gameParent.deathSheetL.getHeight() / 2);
        deathFrames = new TextureRegion[8];
        deathFramesL = new TextureRegion[8];
        index = 0;
        for (int i = 0; i < 2; i++) 
        {
                for (int j = 0; j < 4; j++) 
                {
                	if(index < 8)
                	{
                		deathFrames[index++] = tmp[i][j];
                		deathFramesL[index-1] = tmp2[i][j];
                	}
                }
        }
        deathAnimation = new Animation(0.1f, deathFrames);
        deathAnimationL = new Animation(0.1f, deathFramesL);
        
        spriteBatch = new SpriteBatch();
        computeTime();
        stateTime = 0f;
	}

	@Override
	public void render(float delta)
	{
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(gColor, gColor, gColor, 1);
        spriteBatch.begin();
        
        updatePosition(delta);
        renderGUI(delta);
        if(sceneState == 0 || sceneState == 2)
        {
        	alphaTimer -= delta/2f;
        	if(alphaTimer <= 0)
        	{
        		alphaTimer = 0;
        	}
        	if(sceneState == 0)
        	{
        		spriteBatch.setColor(1, 1, 1, alphaTimer);
        	}
        	else
        	{
        		spriteBatch.setColor(1, 1, 1, 1-alphaTimer);
        	}
        	spriteBatch.draw(gameParent.blackTexture, 0, 0, 1000, 500);
        	spriteBatch.setColor(1, 1, 1, 1);
        }
        if(mainCharacter.xPos == 0)
        {
        	mainCharacter.xPos = 1;
        }
        

        scoreText.setScale(16f/32f);
        String display = "";
        if(zombieType == 0)
        {
        	display = "Small";
        }
        else if(zombieType == 1)
        {
        	display = "Large";
        }
        else if(zombieType == 2)
        {
        	display = "Ring";
        }
        else if(zombieType == 3)
        {
        	display = "Remove";
        }
        scoreText.draw(spriteBatch, "Mode: " + display, 15, 420);
        //scoreText.draw(spriteBatch, "x" + (double)Math.round((speedModifier) * 10) / 10, 15, 395);
        //scoreText.draw(spriteBatch, computeTime(), 15, 295);
        
        if(xDragged != -1)
        {
        	spriteBatch.setColor(0.5f,0.5f,0.5f,0.5f);
        	if(zombieType == 0)
        	{
        		spriteBatch.draw(zombieAnimationL.getKeyFrame(0.1f, true), xDragged, 10-charHeight, 230, 230);
        	}
        	else if(zombieType == 1)
        	{
        		spriteBatch.draw(zombieAnimation2L.getKeyFrame(0.1f, true), xDragged, -24-charHeight, 350, 350);
			}
        	else if(zombieType == 2)
        	{
        		spriteBatch.draw(gameParent.ringTexture, xDragged, yDragged);
        	}
        	else if(zombieType == 3)
        	{
        		int smallestzombie = -1;
        		int zombiedist = 1000;
        		int smallestring = -1;
        		int ringdist = 1000;
        		for(int x=0;x<gameWorld.enemyList.length;x++)
        		{
        			if(gameWorld.enemyList[x].zombiePosition <= mainCharacter.xPos+500 && 
    						gameWorld.enemyList[x].zombiePosition >= mainCharacter.xPos-500)
        			{
        				if(Math.abs(gameWorld.enemyList[x].zombiePosition - ((xDragged-299) + mainCharacter.xPos)) < zombiedist)
        				{
        					smallestzombie = x;
        					zombiedist = Math.abs(gameWorld.enemyList[x].zombiePosition - ((xDragged-299) + mainCharacter.xPos));
        				}
        			}
        		}
        		for(int x=0;x<gameWorld.ringList.length;x++)
        		{
        			if(gameWorld.ringList[x].xPosition <= mainCharacter.xPos+500 && 
        					gameWorld.ringList[x].xPosition >= mainCharacter.xPos-500)
        			{
        				if(Math.abs(gameWorld.ringList[x].xPosition - ((xDragged-299) + mainCharacter.xPos)) < ringdist)
        				{
        					smallestring = x;
        					ringdist = Math.abs(gameWorld.ringList[x].xPosition - ((xDragged-299) + mainCharacter.xPos));
        				}
        			}
        		}
        		
        		spriteBatch.setColor(1, 0, 0, 0.5f);
        		if(zombiedist < ringdist)
        		{
        			removeType = 0;
        			toRemove = smallestzombie;
       				spriteBatch.draw(gameParent.whiteLine, gameWorld.enemyList[smallestzombie].zombiePosition-mainCharacter.xPos+400, 0, 100, 200);
       			}
        		else if(ringdist < zombiedist)
        		{
        			removeType = 1;
        			toRemove = smallestring;
        			spriteBatch.draw(gameParent.whiteLine, gameWorld.ringList[smallestring].xPosition-mainCharacter.xPos+290, gameWorld.ringList[smallestring].yPosition, 20, 200);
        		}
       		}
        	spriteBatch.setColor(1,1,1,1);
        }
        spriteBatch.end();
	}
	
	public void renderGUI(float delta)
	{
		if(sceneState == 1)
		{
			gameTimer += delta;
		}
		else if(sceneState == 3 || sceneState == 4 || sceneState == 5)
		{
			if(sceneState == 4)
			{
				buttonAlpha += delta;
				if(buttonAlpha >= 1)
				{
					buttonAlpha = 1;
					sceneState = 5;
				}
			}
			spriteBatch.setColor(1, 1, 1, buttonAlpha);
			spriteBatch.draw(gameParent.mmButton, 17, 440);
			spriteBatch.draw(gameParent.rButton, 620, 440);
			spriteBatch.setColor(1, 1, 1, deathAlpha);
		}
		scoreText.setColor(229f/255f, 229f/255f, 229f/255f, deathAlpha);
		//scoreText.draw(spriteBatch, computeTime(), 338, 440);
		spriteBatch.draw(gameParent.yellowLine, 171, 456, ((float)mainCharacter.xPos/(float)gameWorld.worldWidth)*512, 4);
		if(gameTimer > gameWorld.secondsLimit)
		{
			spriteBatch.draw(gameParent.whiteLine, 171, 450, 512, 4);
		}
		else
		{
			spriteBatch.draw(gameParent.whiteLine, 171, 450, (gameTimer/(float)gameWorld.secondsLimit)*512, 4);
		}
		spriteBatch.draw(gameParent.blackLine, 171, 450);
		scoreText.setScale(14f/32f);
		scoreText.draw(spriteBatch, "x", 15, 437);
		scoreText.setScale(23f/32f);
		scoreText.draw(spriteBatch, "" + (double)Math.round((speedModifier) * 10) / 10, 30, 444);
		if(mainCharacter.playerHealth < 3)
		{
			//spriteBatch.draw(gameParent.greyHeart, 59, 450);
		}
		else
		{
			//spriteBatch.draw(gameParent.redHeart, 59, 450);
		}
		if(mainCharacter.playerHealth < 2)
		{
			//spriteBatch.draw(gameParent.greyHeart, 37, 450);
		}
		else
		{
			//spriteBatch.draw(gameParent.redHeart, 37, 450);
		}
		if(mainCharacter.playerHealth < 1)
		{
			//spriteBatch.draw(gameParent.greyHeart, 15, 450);
		}
		else
		{
			//spriteBatch.draw(gameParent.redHeart, 15, 450);
		}
		//spriteBatch.draw(gameParent.pauseButton, 808, 435);
		spriteBatch.setColor(1, 1, 1, 1);
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		yDragged = 480-y;
		xDragged = x;
		return false;
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) 
	{
		yDragged = -1;
		xDragged = -1;
		if(zombieType == 0)
		{
			gameWorld.AddZombie((x-299) + mainCharacter.xPos, 0);
		}
		else if(zombieType == 1)
		{
			gameWorld.AddZombie((x-299) + mainCharacter.xPos, 1);
		}
		else if(zombieType == 2)
		{
			gameWorld.AddRing((x-299) + mainCharacter.xPos+10, 480-y+10);
		}
		else if(zombieType == 3)
		{
			if(toRemove != -1)
			{
				if(removeType == 0)
				{
					gameWorld.RemoveZombie(toRemove);
					toRemove = -1;
				}
				else
				{
					gameWorld.RemoveRing(toRemove);
					toRemove = -1;
				}
			}
		}
		return true;
	}
	
	public void updatePosition(float delta)
	{
		if(mainCharacter.xPos < gameWorld.worldWidth && mainCharacter.xPos > 0 && !isShooting)
		{
			if(leftPressed || ((jumpPressed) && (jumpDirection == 1)))
			{
				if(!isBlockedLeft && !isBlocked)
				{
					if(jumpDirection == 1 || jumpDirection == -1)
					{
						if(mainCharacter.xPos - 500*delta*speedModifier <= 0)
						{
							mainCharacter.xPos = 1;
						}
						else if((jumpPressed) || (leftPressed && !jumpPressed))
						{
							mainCharacter.xPos -= 500*delta*speedModifier;
						}
					}
				}
			}
			if(rightPressed || ((jumpPressed) && (jumpDirection == 2)))
			{
				if(!isBlockedRight && !isBlocked)
				{
					if(jumpDirection == 2 || jumpDirection == -1)
					{
						if(mainCharacter.xPos + 500*delta*speedModifier >= gameWorld.worldWidth)
						{
							//mainCharacter.xPos = gameWorld.worldWidth-1;
							if(sceneState != 2)
							{
								alphaTimer = 1;
							}
							sceneState = 2;
							leftPressed = false;
							rightPressed = false;
							isBlocked = false;
							isShooting = false;
							isJumping = false;
							isMoving = true;
						}
						else if((jumpPressed) || (rightPressed && !jumpPressed))
						{
							mainCharacter.xPos += 500*delta*speedModifier;
						}
					}
				}
			}
			
			if(jumpPressed)
			{
				jumpContainer += delta;
				if(jumpContainer >= 1.0f)
				{
					jumpContainer = 1.0f;
					isJumping = true;
				}
				if(jumpContainer >= 0.2)
				{
					jumpTime += delta*35f;
					int jump = (int)(-jumpTime * (jumpTime - 40f));
					if(jump >= 0)
					{
						if(jump < 200)
						{
							mainCharacter.yPos = jump+20;
							charHeight = 0;
						}
						else
						{
							charHeight = jump-200;
						}
					}
					else
					{
						if(leftPressed)
						{
							isMovingRight = false;
						}
						else if(rightPressed)
						{
							isMovingRight = true;
						}
						jumpPressed = false;
						isJumping = false;
						jumpDirection = -1;
						jumpTime = 0;
						mainCharacter.yPos = 20;
						charHeight = 0;
					}
				}
			}
		}
		if(isBlocked && !isBlinking)
		{
			blockCount += delta*2;
			if(blockCount < 0.5f)
			{
				float yforce;
				if(blockCount <= 0.25)
				{
					yforce = (5*blockCount - 10*blockCount*blockCount)*20;
				}
				else
				{
					yforce = -(5*(blockCount-0.25f) - 10*(blockCount-0.25f)*(blockCount-0.25f))*19.3f;
				}
				if(isBlockedLeft && !isBlockedRight && (mainCharacter.xPos+500*delta < gameWorld.worldWidth))
				{
					mainCharacter.yPos += (int)yforce;
					mainCharacter.xPos += 500*delta;
				}
				else if(isBlockedRight && !isBlockedLeft && (mainCharacter.xPos-500*delta > 0))
				{
					mainCharacter.yPos += (int)yforce;
					mainCharacter.xPos -= 500*delta;
				}
			}
			else if(!isBlinking)
			{
				isBlocked = false;
				isBlinking = true;
			}
		}
		else if(isBlinking)
		{
			blockCount += delta*2;
			if(blockCount >= 4.5)
			{
				blockCount = 0;
				isBlinking = false;
			}
		}
		int iterations = (int)Math.floor((double)mainCharacter.xPos/(double)1024);
		int paraiterations = (int)Math.floor((double)(mainCharacter.xPos*0.25f)/(double)1024);
		int hilliterations = (int)Math.floor((double)(mainCharacter.xPos*0.5f)/(double)1024);
		if(mainCharacter.xPos < 0)
		{
			iterations++;
			paraiterations++;
			hilliterations++;
		}
		
		
		int offset = mainCharacter.xPos - iterations*1024;
		int paraoffset = (int)(mainCharacter.xPos*0.25f - paraiterations*1024);
		int hilloffset = (int)(mainCharacter.xPos*0.5 - hilliterations*1024);
		spriteBatch.draw(gameParent.backgroundTexture, -paraoffset-1024, 235-charHeight);
		spriteBatch.draw(gameParent.backgroundTexture, -paraoffset, 235-charHeight);
		spriteBatch.draw(gameParent.backgroundTexture, -paraoffset+1024, 235-charHeight);
		spriteBatch.draw(gameParent.hillTexture, -hilloffset-1024, 75 - charHeight);
		spriteBatch.draw(gameParent.hillTexture, -hilloffset, 75 - charHeight);
		spriteBatch.draw(gameParent.hillTexture, -hilloffset+1024, 75 - charHeight);
		//for(int x=0;x<gameWorld.houseNumber-1;x++)
		//{
			//if(gameWorld.houseList[x] <= mainCharacter.xPos+1000 && gameWorld.houseList[x] >= mainCharacter.xPos-1000)
			//{
				//if(gameWorld.houseTypes[x] == 0)
				//{
					//spriteBatch.draw(gameParent.houseTwo, gameWorld.houseList[x]-mainCharacter.xPos, 30-charHeight);
				//}
				//else
				//{
					//spriteBatch.draw(gameParent.houseOne, gameWorld.houseList[x]-mainCharacter.xPos, 30-charHeight);
				//}
			//}
		//}
		spriteBatch.draw(gameParent.roadTexture, -offset-1024, -charHeight);
		spriteBatch.draw(gameParent.roadTexture, -offset, -charHeight);
		spriteBatch.draw(gameParent.roadTexture, -offset+1024, -charHeight);
		renderZombies(delta);
		characterAnimationHandler(delta);
	}
	
	public void renderZombies(float delta)
	{
		if(!isBlocked)
		{
			isBlockedRight = false;
			isBlockedLeft = false;
		}
		for(int x=0; x<(gameWorld.enemyList.length);x++)
		{
			if(gameWorld.enemyList[x] != null)
			{
				if(gameWorld.enemyList[x].zombiePosition <= mainCharacter.xPos+1000 && 
						gameWorld.enemyList[x].zombiePosition >= mainCharacter.xPos-1000 && gameWorld.enemyList[x].deathTimer == -1)
				{
					int pos = gameWorld.enemyList[x].zombiePosition - mainCharacter.xPos;
					gameWorld.enemyList[x].currentFrame += delta;
					
					if(gameWorld.enemyList[x].zombieType == 0)
					{
						if(pos >= 0)
						{
							spriteBatch.draw(zombieAnimationL.getKeyFrame(gameWorld.enemyList[x].currentFrame, true), pos + 300, 10-charHeight, 230, 230);
						}
						else
						{
							spriteBatch.draw(zombieAnimation.getKeyFrame(gameWorld.enemyList[x].currentFrame, true), pos + 300, 10-charHeight, 230, 230);
						}
					}
					else
					{
						if(pos >= 0)
						{
							spriteBatch.draw(zombieAnimation2L.getKeyFrame(gameWorld.enemyList[x].currentFrame, true), pos + 300, -24-charHeight, 350, 350);
						}
						else
						{
							spriteBatch.draw(zombieAnimation2.getKeyFrame(gameWorld.enemyList[x].currentFrame, true), pos + 300, -24-charHeight, 350, 350);
						}
					}
					
					if(((mainCharacter.yPos < 150 && gameWorld.enemyList[x].zombieType == 0) || (mainCharacter.yPos < 180 && gameWorld.enemyList[x].zombieType == 1)) && sceneState == 1)
					{
						if(((pos >= 10 && pos <= 70 && gameWorld.enemyList[x].zombieType == 0) || 
								(pos >= 10 && pos <= 90 && gameWorld.enemyList[x].zombieType == 1)) &&
								!isBlinking && !isBlocked)
						{
							//mainCharacter.playerHealth--;
							speedModifier = 1.00f;
							if(mainCharacter.playerHealth <= 0)
							{
								if(!jumpPressed)
								{
									isMovingRight = true;
									leftPressed = false;
									rightPressed = false;
								}
								sceneState = 3;
								isVictory = false;
							}
							savedYPos = mainCharacter.yPos;
							isBlockedRight = true;
							isBlocked = true;
						}
						else if(((pos <= -10 && pos >= -110 && gameWorld.enemyList[x].zombieType == 0) || 
								(pos <= -10 && pos >= -170 && gameWorld.enemyList[x].zombieType == 1))
								&& !isBlinking && !isBlocked)
						{
							//mainCharacter.playerHealth--;
							speedModifier = 1.00f;
							if(mainCharacter.playerHealth <= 0)
							{
								if(!jumpPressed)
								{
									isMovingRight = false;
									leftPressed = false;
									rightPressed = false;
								}
								sceneState = 3;
								isVictory = false;
							}
							savedYPos = mainCharacter.yPos;
							isBlockedLeft = true;
							isBlocked = true;
						}
					}
					if(pos >= 70)
					{
						if(gameWorld.enemyList[x].zombieType == 0)
						{
							//gameWorld.enemyList[x].zombiePosition -= 250*delta;
						}
						else
						{
							//gameWorld.enemyList[x].zombiePosition -= 100*delta;
						}
					}
					else if(pos <= -110)
					{
						if(gameWorld.enemyList[x].zombieType == 0)
						{
							//gameWorld.enemyList[x].zombiePosition += 250*delta;
						}
						else
						{
							//gameWorld.enemyList[x].zombiePosition += 100*delta;
						}
					}
				}
				else if(gameWorld.enemyList[x].deathTimer != -1)
				{
					if(gameWorld.enemyList[x].zombieType == 0)
					{
						int pos = gameWorld.enemyList[x].zombiePosition - mainCharacter.xPos;
						gameWorld.enemyList[x].deathTimer += delta;
						if(gameWorld.enemyList[x].deathTimer < 0.8f)
						{
							if(gameWorld.enemyList[x].deathTimer < 0.5)
							{
								if(gameWorld.enemyList[x].fallDirection == 1)
								{
									gameWorld.enemyList[x].zombiePosition += 300*delta;
								}
								else
								{
									gameWorld.enemyList[x].zombiePosition -= 300*delta;
								}
							}
							if(gameWorld.enemyList[x].fallDirection == 1)
							{
								spriteBatch.draw(zombieDeathAnimation.getKeyFrame(gameWorld.enemyList[x].deathTimer, true), pos+305, 14-charHeight, 230, 230);
							}
							else
							{
								spriteBatch.draw(zombieDeathAnimationL.getKeyFrame(gameWorld.enemyList[x].deathTimer, true), pos+305, 14-charHeight, 230, 230);
							}
						}
						else if(gameWorld.enemyList[x].deathTimer < 2.8f)
						{
							float alpha = 1;
							if(gameWorld.enemyList[x].deathTimer > 2.3f)
							{
								alpha = (2.8f- gameWorld.enemyList[x].deathTimer)*2;
								spriteBatch.setColor(1f, 1f, 1f, alpha);
							}
							if(gameWorld.enemyList[x].fallDirection == 1)
							{
								spriteBatch.draw(zombieDeathAnimation.getKeyFrame(0.7f, true), pos+305, 14-charHeight, 230, 230);
							}
							else
							{
								spriteBatch.draw(zombieDeathAnimationL.getKeyFrame(0.7f, true), pos+305, 14-charHeight, 230, 230);
							}
						}
						else
						{
							gameWorld.RemoveEnemy(x);
						}
					}
					else
					{
						int pos = gameWorld.enemyList[x].zombiePosition - mainCharacter.xPos;
						gameWorld.enemyList[x].deathTimer += delta;
						if(gameWorld.enemyList[x].deathTimer < 0.8f)
						{
							if(gameWorld.enemyList[x].fallDirection == 1)
							{
								spriteBatch.draw(zombieDeathAnimation2L.getKeyFrame(gameWorld.enemyList[x].deathTimer, true), pos + 150, -25-charHeight, 700, 350);
							}
							else
							{
								spriteBatch.draw(zombieDeathAnimation2.getKeyFrame(gameWorld.enemyList[x].deathTimer, true), pos + 100, -25-charHeight, 700, 350);
							}
						}
						else if(gameWorld.enemyList[x].deathTimer < 2.5f)
						{
							float alpha = 1;
							if(gameWorld.enemyList[x].deathTimer > 2f)
							{
								alpha = (2.5f- gameWorld.enemyList[x].deathTimer)*2;
								spriteBatch.setColor(1f, 1f, 1f, alpha);
							}
							if(gameWorld.enemyList[x].fallDirection == 1)
							{
								spriteBatch.draw(gameParent.zombieDeathFinal2, pos + 150, -25-charHeight, 700, 350);
							}
							else
							{
								spriteBatch.draw(gameParent.zombieDeathFinal2L, pos + 100, -25-charHeight, 700, 350);
							}
						}
						else
						{
							gameWorld.RemoveEnemy(x);
						}
					}
					spriteBatch.setColor(1f, 1f, 1f, 1);
				}
			}
		}
	}
	
	public void characterAnimationHandler(float delta)
	{
		if(isBlinking || isBlocked)
		{
			blinkCount += delta;
			if(blinkCount <= 0.1)
			{
				spriteBatch.setColor(1f, 1f, 1f, 1.0f);
			}
			else if(blinkCount <= 0.2)
			{
				spriteBatch.setColor(0.5f, 0.5f, 0.5f, 1.0f);
			}
			else
			{
				blinkCount = 0;
			}
		}
		stateTime += delta;
		if(!isVictory && !jumpPressed)
		{
			if(!jumpPressed)
			{
				leftPressed = false;
				rightPressed = false;
				mainCharacter.yPos = 20;
				if(isMovingRight)
				{
					deathTimer += delta;
					currentFrame = deathAnimation.getKeyFrame(deathTimer, true);
					if(deathTimer < 0.6f)
					{
						mainCharacter.xPos -= 400f*delta;
						spriteBatch.draw(currentFrame, 180, -37 + mainCharacter.yPos);
					}
					else
					{
						if(deathTimer < 0.8f)
						{
							spriteBatch.draw(currentFrame, 180, -37 + mainCharacter.yPos);
						}
						else if(deathTimer < 1.5f)
						{
							spriteBatch.draw(gameParent.deathFinal, 180, -37 + mainCharacter.yPos);
						}
						else
						{
							deathAlpha -= delta/3f;
							if(deathAlpha <= 0)
							{
								deathAlpha = 0;
								
							}
							spriteBatch.setColor(1, 1, 1, 1-deathAlpha);
							spriteBatch.draw(gameParent.blackTexture, 0, 0, 1000, 500);
							spriteBatch.setColor(1, 1, 1, 1);
							spriteBatch.draw(gameParent.deathFinal, 180, -37 + mainCharacter.yPos);
						}
						if(deathTimer > 5)
						{
							sceneState = 4;
							leftPressed = false;
							rightPressed = false;
						}
					}
				}
				else
				{
					deathTimer += delta;
					currentFrame = deathAnimationL.getKeyFrame(deathTimer, true);
					if(deathTimer < 0.6f)
					{
						mainCharacter.xPos += 400f*delta;
						spriteBatch.draw(currentFrame, 145, -37 + mainCharacter.yPos);
					}
					else
					{
						if(deathTimer < 0.8f)
						{
							spriteBatch.draw(currentFrame, 145, -37 + mainCharacter.yPos);
						}
						else if(deathTimer < 1.5f)
						{
							spriteBatch.draw(gameParent.deathFinalL, 145, -37 + mainCharacter.yPos);
						}
						else
						{
							deathAlpha -= delta/3f;
							if(deathAlpha <= 0)
							{
								deathAlpha = 0;
								
							}
							spriteBatch.setColor(1, 1, 1, 1-deathAlpha);
							spriteBatch.draw(gameParent.blackTexture, 0, 0, 1000, 500);
							spriteBatch.setColor(1, 1, 1, 1);
							spriteBatch.draw(gameParent.deathFinalL, 145, -37 + mainCharacter.yPos);
						}
						if(deathTimer > 5)
						{
							sceneState = 4;
							leftPressed = false;
							rightPressed = false;
						}
					}
				}
			}
		}
		else if(isShooting)
		{
			if(!isBlocked)
			{
				mainCharacter.yPos = 20;
			}
			if(isMovingRight)
			{
				if(isDamage)
				{
					isDamage = false;
					detectedSmallest = -1;
					int smallestdist = 512;
					for(int x=0;x<gameWorld.enemyList.length;x++)
					{
						if(gameWorld.enemyList[x] != null && gameWorld.enemyList[x].deathTimer == -1)
						{
							int dist = gameWorld.enemyList[x].zombiePosition - mainCharacter.xPos - 80;
							if(dist+80 > 0)
							{
								if(dist < smallestdist)
								{
									smallestdist = dist;
									detectedSmallest = x;
								}
							}
						}
					}
					spriteBatch.draw(gameParent.burstTexture, 510, 118 + mainCharacter.yPos, 0f, 0f, 
							smallestdist, 16f, 1f, 1f, bulletRotation, 0, 0, smallestdist, 16, false, false);
					
				}
				if(shootContainer < 0.25f)
				{
					currentFrame = shootAnimation.getKeyFrame(shootContainer, true);
					if(isCompleted)
					{
						isCompleted = false;
						if(detectedSmallest != -1)
						{
							if(gameWorld.enemyList[detectedSmallest].HitZombie())
							{
								zombiesKilled++;
								gameWorld.enemyList[detectedSmallest].deathTimer = 0;
								gameWorld.enemyList[detectedSmallest].fallDirection = 1;
								if(gameWorld.arrayCount <= 0)
								{
									gameStatus = true;
									isVictory = true;
								}
							}
						}
					}
				}
				else
				{
					shootContainer = 0;
					isCompleted = true;
					currentFrame = shootAnimation.getKeyFrame(0.25f, true);
					isShooting = false;
					isDamage = true;
				}
				shootContainer += delta;
				spriteBatch.draw(currentFrame, 329, -33 + mainCharacter.yPos);
			}
			else
			{
				if(isDamage)
				{
					isDamage = false;
					detectedSmallest = -1;
					int smallestdist = -512;
					for(int x=0;x<gameWorld.enemyList.length;x++)
					{
						if(gameWorld.enemyList[x] != null && gameWorld.enemyList[x].deathTimer == -1)
						{
							int dist = gameWorld.enemyList[x].zombiePosition - mainCharacter.xPos + 140;
							if(dist-140 < 0)
							{
								if(dist > smallestdist)
								{
									smallestdist = dist;
									detectedSmallest = x;
								}
							}
						}
					}
					spriteBatch.draw(gameParent.burstTexture, -227 + (512+smallestdist), 119 + mainCharacter.yPos, -smallestdist, 0f, 
							-smallestdist, 16f, 1f, 1f, bulletRotation, 0, 0, -smallestdist, 16, true, false);
					
				}
				if(shootContainer < 0.25f)
				{
					currentFrame = shootAnimationL.getKeyFrame(shootContainer, true);
					if(isCompleted)
					{
						isCompleted = false;
						if(detectedSmallest != -1)
						{
							if(gameWorld.enemyList[detectedSmallest].HitZombie())
							{
								zombiesKilled++;
								gameWorld.enemyList[detectedSmallest].deathTimer = 0;
								gameWorld.enemyList[detectedSmallest].fallDirection = 2;
								if(gameWorld.arrayCount <= 0)
								{
									gameStatus = true;
									isVictory = true;
								}
							}
						}
					}
				}
				else
				{
					shootContainer = 0;
					isCompleted = true;
					currentFrame = shootAnimationL.getKeyFrame(0.25f, true);
					isShooting = false;
					isDamage = true;
				}
				shootContainer += delta;
				spriteBatch.draw(currentFrame, 249, -33 + mainCharacter.yPos);
			}
		}
		else if(jumpPressed)
		{
			if(isMovingRight)
			{
				if(isJumping)
				{
					currentFrame = jumpAnimation.getKeyFrame(1.0f, true);
				}
				else
				{
					currentFrame = jumpAnimation.getKeyFrame(jumpContainer, true);
				}
				spriteBatch.draw(currentFrame, 299, -35 + mainCharacter.yPos);
			}
			else
			{
				if(isJumping)
				{
					currentFrame = jumpAnimationL.getKeyFrame(1.0f, true);
				}
				else
				{
					currentFrame = jumpAnimationL.getKeyFrame(jumpContainer, true);
				}
				spriteBatch.draw(currentFrame, 279, -35 + mainCharacter.yPos);
			}
		}
		else if(isMoving)
		{
			if(!isBlocked)
			{
				mainCharacter.yPos = 20;
			}
			if(sceneState == 0)
			{
				if(relativePos < 307)
				{
					relativePos += 500f*delta;
				}
				else
				{
					relativePos = 307;
					sceneState = 1;
					isMoving = false;
				}
				currentFrame = walkAnimation.getKeyFrame(stateTime, true);
				spriteBatch.draw(currentFrame, relativePos, -37 + mainCharacter.yPos, 240f, 240f);
			}
			else if(sceneState == 2)
			{
				if(relativePos < 1500)
				{
					relativePos += 500f*delta;
				}
				else
				{
					gameStatus = true;
				}
				currentFrame = walkAnimation.getKeyFrame(stateTime, true);
				spriteBatch.draw(currentFrame, relativePos, -37 + mainCharacter.yPos, 240f, 240f);
			}
			else
			{
				if(isMovingRight)
				{
					currentFrame = walkAnimation.getKeyFrame(stateTime, true);
					spriteBatch.draw(currentFrame, 307, -37 + mainCharacter.yPos, 240f, 240f);
				}
				else
				{
					currentFrame = walkAnimationL.getKeyFrame(stateTime, true);
					spriteBatch.draw(currentFrame, 287, -37 + mainCharacter.yPos, 240f, 240f);
				}
			}
		}
		else
		{
			if(!isBlocked)
			{
				mainCharacter.yPos = 20;
			}
			if(isMovingRight)
			{
				currentFrame = idleAnimation.getKeyFrame(stateTime, true);
				spriteBatch.draw(currentFrame, 299, -40 + mainCharacter.yPos);
			}
			else
			{
				currentFrame = idleAnimationL.getKeyFrame(stateTime, true);
				spriteBatch.draw(currentFrame, 279, -40 + mainCharacter.yPos);
			}
		}
		spriteBatch.setColor(1f, 1f, 1f, 1.0f);
		renderRings(delta);
	}
	

	public void renderRings(float delta)
	{
		speedTimer += delta;
		if(speedTimer >= 1.5f)
		{
			speedTimer = 0;
			if(speedModifier >= 1.05f)
			{
				speedModifier -= 0.05f;
			}
			else
			{
				speedModifier = 1.00f;
			}
		}
		for(int x=0;x<gameWorld.ringList.length;x++)
		{
			int xpos = gameWorld.ringList[x].xPosition;
			if(xpos <= mainCharacter.xPos + 600 && xpos >= mainCharacter.xPos - 600)
			{
				if(Math.abs(xpos-mainCharacter.xPos-100) <= 50  && Math.abs(gameWorld.ringList[x].yPosition-mainCharacter.yPos) <= 100 
						&& gameWorld.ringList[x].ringState == 0)
				{
					gameWorld.ringList[x].ringState = 1;
					speedModifier += 0.10f;
					if(speedModifier > maxSpeed)
					{
						maxSpeed = (float)speedModifier;
					}
				}
				if(xpos - mainCharacter.xPos < 0 && gameWorld.ringList[x].ringState == 0)
				{
					gameWorld.ringList[x].ringState = 0;
				}
				
		        if(gameWorld.ringList[x].ringState == 1)
		        {
		        	currentRing = new Sprite(gameParent.ringYellowTexture);
		        }
		        else if(gameWorld.ringList[x].ringState == -1)
		        {
		        	currentRing = new Sprite(gameParent.ringRedTexture);
		        }
		        else
		        {
		        	currentRing = new Sprite(gameParent.ringTexture);
		        }
		        currentRing.setColor(1,1,1,deathAlpha);
		        currentRing.setPosition(xpos-mainCharacter.xPos + 285, gameWorld.ringList[x].yPosition - charHeight - 7);
		        prevX = mainCharacter.xPos;
		        currentRing.draw(spriteBatch);
			}
		}
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
		scoreText.setFixedWidthGlyphs(value);
		return value;
	}
	
	@Override
	public boolean keyDown(int key) 
	{
		if(key == Input.Keys.DPAD_LEFT)
		{
			if(!rightPressed)
			{
				//if(!jumpPressed)
				{
					jumpDirection = 1;
					isMovingRight = false;
				}
				isMoving = true;
				leftPressed = true;
			}
		}
		else if(key == Input.Keys.DPAD_RIGHT)
		{
			if(!leftPressed)
			{
				//if(!jumpPressed)
				{
					jumpDirection = 2;
					isMovingRight = true;
				}
				isMoving = true;
				rightPressed = true;
			}
		}
		else if(key == Input.Keys.Z && (!jumpPressed))
		{
			if(!leftPressed && !rightPressed)
			{
				jumpDirection = -1;
			}
			timesJumped++;
			jumpContainer = 0.2f;
			jumpPressed = true;
		}
		else if(key == Input.Keys.X && (!isShooting))
		{
			if(!jumpPressed)
			{
				if(speedModifier >= 1.1f)
				{
					speedModifier -= 0.1f;
				}
				shotsFired++;
				bulletRotation = (float)((Math.random() - 0.5) * 10);
				isShooting = true;
			}
		}
		else if(key == Input.Keys.Q)
		{
			zombieType++;
			if(zombieType == 4)
			{
				zombieType = 0;
			}
		}
		else if(key == Input.Keys.ENTER)
		{
			gameWorld.saveMap();
		}
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int key) 
	{
		if((key == Input.Keys.DPAD_LEFT || key == Input.Keys.DPAD_RIGHT) && sceneState == 1)
		{
			leftPressed = false;
			isMoving = false;
			rightPressed = false;
			
			if(!jumpPressed)
			{
				jumpPointer = -1;
			}
		}
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int point) {
		xDragged = x;
		yDragged = 480-y;
		return false;
	}

	@Override
	public boolean touchMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setInput()
	{
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public boolean isDone()
	{
		return gameStatus;
	}
	
	@Override
	public Screen dispose(ZombieManager app) 
	{
		return null;
	}
	
	@Override
	public Screen dispose()
	{
		Screen screen = new GameOver(gameParent, gameTimer, zombiesKilled, shotsFired, timesJumped, maxSpeed, 0);
		return screen;
	}

}
