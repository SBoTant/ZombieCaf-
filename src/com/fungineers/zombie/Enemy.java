package com.fungineers.zombie;

public class Enemy
{
	public float currentTime;
	public int zombiePosition;
	public int zombieHealth;
	public int zombieType;
	public int fallDirection = 0;
	public float currentFrame = 0;
	public float deathTimer = -1;
	
	public Enemy(int position, int type)
	{
		currentTime = 0;
		zombiePosition = position;
		zombieType = type;
		if(type == 0)
		{
			zombieHealth = 1;
		}
		else if(type == 1)
		{
			zombieHealth = 2;
		}
	}
	
	public boolean HitZombie()
	{
		zombieHealth--;
		if(zombieHealth <= 0)
		{
			return true;
		}
		return false;
	}
	
	public float AddFrame(float time)
	{
		currentTime += time;
		return currentTime;
	}
}
