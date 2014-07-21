package com.icyfillup.rain.entity.mob;

import com.icyfillup.rain.entity.Entity;
import com.icyfillup.rain.entity.particle.Particle;
import com.icyfillup.rain.entity.projectile.Projectile;
import com.icyfillup.rain.entity.projectile.WizardProjectile;
import com.icyfillup.rain.graphics.Sprite;

public abstract class Mob extends Entity 
{
	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	protected boolean walking = false;
	
	public void move(int xa, int ya) 
	{
//		System.out.println("Size: " + level.getProjectiles().size());
		
		if(xa != 0 && ya != 0) 
		{
			move(xa, 0);
			move(0 , ya);
			return;
		}
		
		if(xa > 0) { dir = 1; }
		if(xa < 0) { dir = 3; }
		if(ya > 0) { dir = 2; }
		if(ya < 0) { dir = 0; }
		
		
		if(!collision(xa, ya)) 
		{
			x += xa;
			y += ya;
		}
	}
	
	public void update() 
	{
		
	}
	
	protected void shoot(int x, int y, double dir)
	{
//		dir = Math.toDegrees(dir);
//		System.out.println("Angle: " + dir);
		
		Projectile p = new WizardProjectile(x, y, dir);
		level.add(p);
	}
	
	private boolean collision(int xa, int ya)
	{
		boolean solid = false;
		for(int c = 0; c < 4; c++)
		{
//			corner code goes here
			int xt = ((x + xa) + c % 2 * 14 - 8) >> 4;
			int yt = ((y + ya) + c / 2 * 12 + 3) >> 4;
			
			if(level.getTile(xt, yt).solid()) { solid = true; }
		}
		
		return solid;
	}
	
	public void render()
	{
		
	}
}
