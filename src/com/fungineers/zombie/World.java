package com.fungineers.zombie;

public class World 
{
	public int worldWidth;
	public int houseNumber;
	int arrayCount = 0;
	public Enemy[] enemyList;
	public Ring[] ringList;
	public int secondsLimit;
	public String mapName;
	public int redTime;
	public int blueTime;
	public int yellowTime;
	
	public World(String name, int width, int[] ringsx, int[] ringsy, int[] one, int[] two, int bluetime, int yellowtime, int redtime)
	{
		worldWidth = width;
		enemyList = new Enemy[one.length];
		secondsLimit = bluetime;
		blueTime = bluetime;
		yellowTime = yellowtime;
		redTime = redtime;
		arrayCount = 1;
		
		ringList = new Ring[ringsx.length];
		for(int x=0;x<ringsx.length;x++)
		{
			ringList[x] = new Ring(ringsx[x], ringsy[x], 0);
		}
		enemyList = new Enemy[one.length + two.length];
		for(int x=0;x<one.length;x++)
		{
			enemyList[x] = new Enemy(one[x], 0);
		}
		for(int x=0;x<two.length;x++)
		{
			enemyList[one.length+x] = new Enemy(two[x], 1);
		}
		
	}
	public void RemoveEnemy(int num)
	{
		enemyList[num] = null;
		//arrayCount--;
	}
}
