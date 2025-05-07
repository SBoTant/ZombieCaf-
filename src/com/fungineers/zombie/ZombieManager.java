package com.fungineers.zombie;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;



public class ZombieManager implements ApplicationListener
{
	
	private Screen screen;
	private Screen pausedscreen;
	
	public int musicVolume;
	public int soundVolume;
	public int levelNumber = 0;
	public boolean isPaused = false;
	
	public Texture backgroundTexture;
	public Texture leftButton;
	public Texture rightButton;
	public Texture jumpButton;
	public Texture roadTexture;
	public Texture houseOne;
	public Texture houseTwo;
	public Texture hillTexture;
	public Texture burstTexture;
	public Texture walkSheet;
	public Texture walkSheetL;
	public Texture idleSheet;
	public Texture idleSheetL;
	public Texture jumpSheet;
	public Texture jumpSheetL;
	public Texture shootSheet;
	public Texture shootSheetL;
	public Texture zombieSheet;
	public Texture zombieSheetL;
	public Texture zombieSheet2;
	public Texture zombieSheet2L;
	public Texture ringTexture;
	public Texture zombieDeathSheet;
	public Texture zombieDeathSheetL;
	public Texture zombieDeathSheet2;
	public Texture zombieDeathSheet2L;
	public Texture zombieDeathFinal2;
	public Texture zombieDeathFinal2L;
	public Texture deathFinal;
	public Texture deathSheet;
	public Texture deathFinalL;
	public Texture deathSheetL;
	public Texture ringRedTexture;
	public Texture ringYellowTexture;
	public Texture blackTexture;
	public Texture blackLine;
	public Texture yellowLine;
	public Texture whiteLine;
	public Texture pauseButton;
	public Texture greyHeart;
	public Texture redHeart;
	public Texture mmButton;
	public Texture rButton;
	public Texture menuTexture;
	public Texture whiteTexture;
	public Texture startButton;
	public Texture guideButton;
	public Texture optionsButton;
	public Texture creditsButton;
	public Texture level1Texture;
	public Texture level2Texture;
	public Texture levelFrame;
	public Texture optionsTexture;
	public Texture overTexture;
	public Texture rankTexture;
	public Texture pauseTexture;
	public Texture resumeButton;
	public Texture menuButton;
	public Texture restartButton;
	public Texture creditsRoll;
	public Texture timesUp;
	public Texture[] guideTextures;
	
	Sound clickSound;
	
	public TextureRegion fontTexture;
	public BitmapFont.BitmapFontData fontData;
	
	SpriteBatch spriteBatch;


    @Override
    public void render() 
    {
            screen.render(Gdx.graphics.getDeltaTime());
            if (screen.isDone()) 
            {
            	if (screen instanceof LoadingScreen)
            	{
            		spriteBatch.begin();
            		backgroundTexture = new Texture(Gdx.files.internal("data/background.png"));
            		leftButton = new Texture(Gdx.files.internal("data/left.png"));
            		rightButton = new Texture(Gdx.files.internal("data/right.png"));
            		jumpButton = new Texture(Gdx.files.internal("data/jump.png"));
            		roadTexture = new Texture(Gdx.files.internal("data/road.png"));
            		hillTexture = new Texture(Gdx.files.internal("data/hills.png"));
            		burstTexture = new Texture(Gdx.files.internal("data/burst.png"));
            		walkSheet = new Texture(Gdx.files.internal("data/running.png"));
            		walkSheetL = new Texture(Gdx.files.internal("data/runningl.png"));
            		idleSheet = new Texture(Gdx.files.internal("data/idle.png"));
                    idleSheetL = new Texture(Gdx.files.internal("data/idlel.png"));
                    jumpSheet = new Texture(Gdx.files.internal("data/jumping.png"));
                    jumpSheetL = new Texture(Gdx.files.internal("data/jumpingl.png"));
                    shootSheet = new Texture(Gdx.files.internal("data/shoot.png"));
                    shootSheetL = new Texture(Gdx.files.internal("data/shootl.png"));
                    zombieSheet = new Texture(Gdx.files.internal("data/zombie1walk.png"));
                    zombieSheetL = new Texture(Gdx.files.internal("data/zombie1walkl.png"));
                    zombieSheet2 = new Texture(Gdx.files.internal("data/zombie2walk.png"));
                    zombieSheet2L = new Texture(Gdx.files.internal("data/zombie2walkl.png"));
                    ringTexture = new Texture(Gdx.files.internal("data/ring.png"));
                    ringRedTexture = new Texture(Gdx.files.internal("data/ringred.png"));
                    ringYellowTexture = new Texture(Gdx.files.internal("data/ringyellow.png"));
                    zombieDeathSheet2 = new Texture(Gdx.files.internal("data/zombie2death.png"));
                    zombieDeathSheet2L = new Texture(Gdx.files.internal("data/zombie2deathl.png"));
                    zombieDeathSheet = new Texture(Gdx.files.internal("data/zombie1deathl.png"));
                    zombieDeathSheetL = new Texture(Gdx.files.internal("data/zombie1death.png"));
                    zombieDeathFinal2 = new Texture(Gdx.files.internal("data/zombie2deathfinall.png"));
                    zombieDeathFinal2L = new Texture(Gdx.files.internal("data/zombie2deathfinal.png"));
                    deathSheet = new Texture(Gdx.files.internal("data/death.png"));
                    deathSheetL = new Texture(Gdx.files.internal("data/deathl.png"));
                    deathFinal = new Texture(Gdx.files.internal("data/deathfinal.png"));
                    deathFinalL = new Texture(Gdx.files.internal("data/deathfinall.png"));
                    blackTexture = new Texture(Gdx.files.internal("data/black.png"));
                    redHeart = new Texture(Gdx.files.internal("data/redheart.png"));
                    greyHeart = new Texture(Gdx.files.internal("data/greyheart.png"));
                    pauseButton = new Texture(Gdx.files.internal("data/pause.png"));
                    blackLine = new Texture(Gdx.files.internal("data/blackline.png"));
                    yellowLine = new Texture(Gdx.files.internal("data/yellowline.png"));
                    whiteLine = new Texture(Gdx.files.internal("data/whiteline.png"));
                    mmButton = new Texture(Gdx.files.internal("data/mainmenubutton.png"));
                    rButton = new Texture(Gdx.files.internal("data/restartbutton.png"));
                    menuTexture = new Texture(Gdx.files.internal("data/mainmenu.png"));
            		whiteTexture = new Texture(Gdx.files.internal("data/white.png"));
            		startButton = new Texture(Gdx.files.internal("data/start.png"));
            		guideButton = new Texture(Gdx.files.internal("data/guide.png"));
            		optionsButton = new Texture(Gdx.files.internal("data/options.png"));
            		creditsButton = new Texture(Gdx.files.internal("data/credits.png"));
            		level1Texture = new Texture(Gdx.files.internal("data/levelselect.png"));
            		level2Texture = new Texture(Gdx.files.internal("data/levelselect2.png"));
            		levelFrame = new Texture(Gdx.files.internal("data/levelframe.png"));
            		optionsTexture = new Texture(Gdx.files.internal("data/optionsbg.png"));
            		overTexture = new Texture(Gdx.files.internal("data/victorybg.png"));
            		rankTexture = new Texture(Gdx.files.internal("data/rank.png"));
            		pauseTexture = new Texture(Gdx.files.internal("data/pausebg.png"));
            		resumeButton = new Texture(Gdx.files.internal("data/presume.png"));
            		menuButton = new Texture(Gdx.files.internal("data/pmainmenu.png"));
            		restartButton = new Texture(Gdx.files.internal("data/prestart.png"));
            		creditsRoll = new Texture(Gdx.files.internal("data/creditsscreen.png"));
            		timesUp = new Texture(Gdx.files.internal("data/timesup.png"));
            		
            		guideTextures = new Texture[8];
            		for(int x=0;x<8;x++)
            		{
            			guideTextures[x] = new Texture(Gdx.files.internal("data/guide" + (x+1) + ".png"));
            		}
            		
            		
                    fontData = new BitmapFont.BitmapFontData(Gdx.files.internal("data/font.fnt"), false);
                    Texture text = new Texture(Gdx.files.internal("data/font.tga"));
                    text.setFilter(TextureFilter.Linear, TextureFilter.Linear);
                    fontTexture = new TextureRegion(text);
                    
                    
                    spriteBatch.draw(jumpSheet, 1000, 1000);
                    spriteBatch.draw(idleSheet, 1000, 1000);
                    spriteBatch.draw(walkSheet, 1000, 1000);
                    spriteBatch.draw(jumpSheetL, 1000, 1000);
                    spriteBatch.draw(idleSheetL, 1000, 1000);
                    spriteBatch.draw(walkSheetL, 1000, 1000);
                    spriteBatch.draw(shootSheet, 1000, 1000);
                    spriteBatch.draw(shootSheetL, 1000, 1000);
                    spriteBatch.draw(zombieSheet, 1000, 1000);
                    spriteBatch.draw(zombieSheetL, 1000, 1000);
                    spriteBatch.draw(zombieSheet2, 1000, 1000);
                    spriteBatch.draw(zombieSheet2L, 1000, 1000);
                    spriteBatch.draw(zombieDeathSheet2, 1000, 1000);
                    spriteBatch.draw(zombieDeathSheet2L, 1000, 1000);
                    spriteBatch.draw(zombieDeathSheet, 1000, 1000);
                    spriteBatch.draw(zombieDeathSheetL, 1000, 1000);
                    spriteBatch.draw(deathSheet, 1000, 1000);
                    spriteBatch.draw(deathSheetL, 1000, 1000);
                    spriteBatch.draw(blackTexture, 1000, 1000);
                    spriteBatch.draw(ringTexture, 1000, 1000);
                    spriteBatch.draw(ringRedTexture, 1000, 1000);
                    spriteBatch.draw(ringYellowTexture, 1000, 1000);
                    spriteBatch.draw(greyHeart, 1000, 1000);
                    
                    spriteBatch.end();
                    screen = new MainMenu(this);
                    
            	}
            	else if(screen instanceof MainMenu)
            	{
            		screen = screen.dispose(this);
            	}
            	else if(screen instanceof GameLoop)
            	{
            		if(((GameLoop)screen).isTimesUp)
            		{
            			screen = new TimesUp(this);
            		}
            		else
            		{
	            		if(((GameLoop)screen).sceneState == 2)
	            		{
	            			screen = screen.dispose();
	            		}
	            		else if(((GameLoop)screen).sceneHandler == 1)
	            		{
	            			screen = new MainMenu(this);
	            		}
	            		else if(((GameLoop)screen).sceneHandler == 2)
	            		{
	            			screen = new GameLoop(this, levelNumber);
	            		}
	            		else
	            		{
		            		if(isPaused)
		            		{
		            			((GameLoop) screen).gameStatus = false;
		            			pausedscreen = screen;
		            			screen = new PauseMenu(this);
		            		}
		            		else
		            		{
		            			screen = screen.dispose();
		            		}
	            		}
            		}
            	}
            	else if(screen instanceof PauseMenu)
            	{
            		int num = ((PauseMenu)screen).sceneState;
            		if(num == 1)
            		{
            			isPaused = false;
            			screen = pausedscreen;
            			((GameLoop) screen).setInput();
            		}
            		else if(num == 2)
            		{
            			screen = new MainMenu(this);
            			
            		}
            		else
            		{
            			screen = new GameLoop(this, levelNumber);
            		}
            		pausedscreen = null;
            	}
            	else if(screen instanceof GameOver)
            	{
            		screen = new LevelSelection(this);
            	}
            	else if(screen instanceof OptionsMenu)
            	{
            		screen.dispose();
            		screen = new MainMenu(this);
            	}
            	else if(screen instanceof CreditsMenu)
            	{
            		screen = new MainMenu(this);
            	}
            	else if(screen instanceof GuideMenu)
            	{
            		screen = new MainMenu(this);
            	}
            	else if(screen instanceof TimesUp)
            	{
            		screen = new LevelSelection(this);
            	}
            	else if(screen instanceof LevelSelection)
            	{
            		if(((LevelSelection) screen).isClicked)
            		{
            			screen = new GameLoop(this, levelNumber);
            		}
            		else
            		{
            			screen = new MainMenu(this);
            		}
            	}
            }
    }

    public void textFlush()
    {
    	fontData = null;
    	fontTexture = null;
    	
    	fontData = new BitmapFont.BitmapFontData(Gdx.files.internal("data/font.fnt"), false);
        Texture text = new Texture(Gdx.files.internal("data/font.tga"));
        text.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        fontTexture = new TextureRegion(text);
    }
    
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public void dispose () 
    {

    }

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create()
	{
		clickSound =  Gdx.audio.newSound(Gdx.files.internal("data/click.ogg"));
		clickSound.play(0);
		LevelLoader.gameWidth = ((ScreenUtils.getFrameBufferTexture().getRegionWidth()));
		FileHandle file = null;
		String contents = "";
		file = Gdx.files.external("zombies/settings.txt");
		if(file.exists())
		{
			contents = file.readString();
		}
		else
		{
			
			file.writeString("160 160", false);
			contents = file.readString();
		}
		for(int x=0;x<file.length();x++)
		{
			if(contents.substring(x, x+1).equals(" "))
			{
				musicVolume = Integer.parseInt(contents.substring(0,x));
				soundVolume = Integer.parseInt(contents.substring(x+1, (int)file.length()));
			}
		}
		
		spriteBatch = new SpriteBatch();
		screen = new LoadingScreen();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
}
