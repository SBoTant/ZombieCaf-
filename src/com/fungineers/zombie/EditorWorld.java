package com.fungineers.zombie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class EditorWorld 
{
	public int worldWidth;
	public int startingAmmo;
	public int houseNumber;
	int arrayCount = 0;
	public Enemy[] enemyList;
	public int[] houseList;
	public int[] houseTypes;
	public Ring[] ringList;
	public int secondsLimit;
	public String mapName = "";
	public int redTime;
	public int yellowTime;
	public int blueTime;
	
	public EditorWorld(int width, int ammo, int[] one, int[] ringsx, int[] ringsy, int time)
	{
		FileHandle file = Gdx.files.external("zombieseditor/settings.txt");
		String contents = file.readString();
		String delims = "[|]+";
		String[] parsed = contents.split(delims);
		mapName = parsed[0];
		worldWidth = Integer.parseInt(parsed[1]);
		redTime = Integer.parseInt(parsed[4]);
		yellowTime = Integer.parseInt(parsed[3]);
		blueTime = Integer.parseInt(parsed[2]);
		
		
		startingAmmo = ammo;
		enemyList = new Enemy[one.length];
		secondsLimit = time;
		for(int x=0;x<one.length;x++)
		{
			double rand = Math.random();
			if(rand < 0.5)
			{
				enemyList[arrayCount] = new Enemy(one[x], 0);
			}
			else
			{
				enemyList[arrayCount] = new Enemy(one[x], 1);
			}
			arrayCount++;
		}
		ringList = new Ring[ringsx.length];
		for(int x=0;x<ringsx.length;x++)
		{
			ringList[x] = new Ring(ringsx[x], ringsy[x], 0);
		}
		int houses = (int)Math.floor((double)width/(double)600);
		houseList = new int[houses];
		houseTypes = new int[houses];
		houseNumber = houses;
		int count = 0;
		for(int x=0;x<houses-1000;x++) //remove 1000 if I ever want to try that silly house again WHICH IS NEVER.
		{
			double temprand = Math.random();
			{
				if(temprand >= 0.5)
				{
					houseTypes[x] = 0;
					boolean test = true;
					while(test)
					{
						temprand = Math.random() * width;
						int y;
						for(y=0;y<count;y++)
						{
							if(temprand>=(houseList[y]-270) && temprand<=(houseList[y]+570))
							{
								y=count+100;
							}
						}
						if(y<(count+50))
						{
							test = false;
						}
						else if(count == 0)
						{
							test = false;
						}
					}
					houseList[x] = (int)temprand;
				}
				else
				{
					houseTypes[x] = 1;
					boolean test = true;
					while(test)
					{
						temprand = Math.random() * width;
						int y;
						for(y=0;y<count;y++)
						{
							if(temprand>=(houseList[y]-270) && temprand<=(houseList[y]+570))
							{
								y=count+100;
							}
						}
						if(y<(count+50))
						{
							test = false;
						}
						else if(count == 0)
						{
							test = false;
						}
					}
					houseList[x] = (int)temprand;
				}
				count++;
			}
		}
	}
	public void RemoveEnemy(int num)
	{
		enemyList[num] = null;
		arrayCount--;
	}

	public void AddZombie(int y, int type)
	{
		Enemy[] temp = new Enemy[enemyList.length+1];
		for(int x=0;x<enemyList.length;x++)
		{
			temp[x] = enemyList[x];
		}
		temp[enemyList.length] = new Enemy(y, type);
		enemyList = temp;
	}
	
	public void AddRing(int x, int y)
	{
		Ring[] temp = new Ring[ringList.length+1];
		for(int z=0;z<ringList.length;z++)
		{
			temp[z] = ringList[z];
		}
		temp[ringList.length] = new Ring(x, y, 0);
		ringList = temp;
	}
	
	public void RemoveZombie(int y)
	{
		int count = 0;
		Enemy[] temp = new Enemy[enemyList.length-1];
		for(int x=0;x<enemyList.length;x++)
		{
			if(x != y)
			{
				temp[count] = enemyList[x];
				count++;
			}
		}
		enemyList = temp;
	}
	
	public void RemoveRing(int y)
	{
		int count = 0;
		Ring[] temp = new Ring[ringList.length-1];
		for(int x=0;x<ringList.length;x++)
		{
			if(x != y)
			{
				temp[count] = ringList[x];
				count++;
			}
		}
		ringList = temp;
	}
	public void saveMap()
	{
		String data;
		String zombie1 = "";
		String zombie2 = "";
		String ringsx = "";
		String ringsy = "";
		for(int x=0;x<enemyList.length;x++)
		{
			if(enemyList[x].zombieType == 0)
			{
				zombie1 += enemyList[x].zombiePosition + ",";
			}
			else
			{
				zombie2 += enemyList[x].zombiePosition + ",";
			}
		}
		for(int x=0;x<ringList.length;x++)
		{
			ringsx += ringList[x].xPosition + ",";
			ringsy += ringList[x].yPosition + ",";
		}
		if(ringsx != "")
		{
			ringsx = ringsx.substring(0, ringsx.length()-1);
			ringsy = ringsy.substring(0, ringsy.length()-1);
		}
		else
		{
			ringsx = "null";
			ringsy = "null";
		}
		if(zombie1 != "")
		{
			zombie1 = zombie1.substring(0, zombie1.length()-1);
		}
		else
		{
			zombie1 = "null";
		}
		if(zombie2 != "")
		{
			zombie2 = zombie2.substring(0, zombie2.length()-1);
		}
		else
		{
			zombie2 = "null";
		}
		data = mapName + "|" + worldWidth + "|" + ringsx + "|" + ringsy + "|" + zombie1 + "|" + zombie2 + "|" + blueTime +
				"|" + yellowTime + "|" + redTime;
		FileHandle file = Gdx.files.external("zombieseditor/map.txt");
		file.writeString(data, false);
	}
	
}

