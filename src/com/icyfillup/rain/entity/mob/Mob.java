package com.icyfillup.rain.entity.mob;

import com.icyfillup.rain.entity.Entity;
import com.icyfillup.rain.entity.projectile.Projectile;
import com.icyfillup.rain.entity.projectile.WizardProjectile;
import com.icyfillup.rain.graphics.Screen;
import com.icyfillup.rain.graphics.Sprite;

public abstract class Mob extends Entity 
{
	protected boolean moving = false;
	protected boolean walking = false;
	
	protected enum Direction
	{
		UP, DOWN, LEFT, RIGHT;
	}
	
	protected Direction dir;
	
	public void move(double xa, double ya) 
	{
//		System.out.println("Size: " + level.getProjectiles().size());
		
		if(xa != 0 && ya != 0) 
		{
			move(xa, 0);
			move(0 , ya);
			return;
		}
		
		if(xa > 0) { dir = Direction.RIGHT; }
		if(xa < 0) { dir = Direction.LEFT; }
		if(ya > 0) { dir = Direction.DOWN; }
		if(ya < 0) { dir = Direction.UP; }
		
//		1, 2, 3, 4, 5, 6, etc. - ALLOWS
//		1.5, 2.3, 3.6, 4.4, 5.9, 6.7, etc. - Doesn't ALLOWS
		while(xa != 0)
		{
			if(Math.abs(xa) > 1)
			{
				if(!collision(abs(xa), ya)) { this.x += abs(xa); }				
				xa -= abs(xa);
			}
			else
			{
				if(!collision(abs(xa), ya)) { this.x += xa; }	
				xa = 0;
			}
		}
		
		while(ya != 0)
		{
			if(Math.abs(ya) > 1)
			{
				if(!collision(xa, abs(ya))) { this.y += abs(ya); }				
				ya -= abs(ya);
			}
			else
			{
				if(!collision(xa, abs(ya))) { this.y += ya; }	
				ya = 0;
			}
		}
		
		
	}
	
	private int abs(double value)
	{
		if(value < 0) { return -1; }
		return 1;
	}
	
	public abstract void update();
	
	public abstract void render(Screen screen);

	
	protected void shoot(double x, double y, double dir)
	{
//		dir = Math.toDegrees(dir);
//		System.out.println("Angle: " + dir);
		
		Projectile p = new WizardProjectile(x, y, dir);
		level.add(p);
	}
	
	private boolean collision(double xa, double ya)
	{
		boolean solid = false;
		for(int c = 0; c < 4; c++)
		{
//			corner code goes here
			double xt = ((x + xa) - c % 2 * 16) / 16;
			double yt = ((y + ya) - c / 2 * 16) / 16;
			
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if(c % 2 == 0) { ix = (int) Math.floor(xt); }
			if(c / 2 == 0) { iy = (int) Math.floor(yt); }
			
			if(level.getTile(ix, iy).solid()) { solid = true; }
		}
		
		return solid;
	}
	
}
