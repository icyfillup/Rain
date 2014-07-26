package com.icyfillup.rain.entity.mob;

import java.util.List;

import com.icyfillup.rain.entity.Entity;
import com.icyfillup.rain.entity.projectile.WizardProjectile;
import com.icyfillup.rain.graphics.AnimatedSprite;
import com.icyfillup.rain.graphics.Screen;
import com.icyfillup.rain.graphics.Sprite;
import com.icyfillup.rain.graphics.SpriteSheet;
import com.icyfillup.rain.util.Debug;
import com.icyfillup.rain.util.Vector2i;

public class Shooter extends Mob
{
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	
	private int time = 0;
	private int xa = 0, ya = 0;

	private Entity rand = null;
	
	private int fireRate = 0;
	
	public Shooter(int x, int y)
	{
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
		fireRate = WizardProjectile.FIRE_RATE;
	}
	
	public void update() 
	{
		time++;
		if(fireRate > 0) fireRate--;
		if(time % (random.nextInt(50) + 30) == 0)
		{
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
			

			if(random.nextInt(4) == 0)
			{
				xa = 0;
				ya = 0;
			}
			
		}
		
		if(walking) { animSprite.update(); }
		else { animSprite.setFrame(0); }
		
		if(ya < 0)
		{ 
			dir = Direction.UP;
			animSprite = up;
		}
		else if(ya > 0) 
		{ 
			dir = Direction.DOWN;
			animSprite = down;
		}
		if(xa < 0) 
		{ 
			dir = Direction.LEFT;
			animSprite = left;
		}
		else if(xa > 0) 
		{  
			dir = Direction.RIGHT;
			animSprite = right;
		}
		
		if(xa != 0 || ya != 0)
		{
//			move(xa, ya);
			walking = true;
		}
		else { walking = false; }
		
		shootRandom();
		
	}
	
	private void shootRandom()
	{
		if(time % 60 == 0)
		{
			List<Entity> entities = level.getEntities(this, 500);
			entities.add(level.getClientPlayer());
			int index = random.nextInt(entities.size());
			rand = entities.get(index);			
			
//			System.out.println(entities.size());
		}
		
		
		if(rand != null && fireRate <= 0)
		{
			double dx = rand.getX() - x;
			double dy = rand.getY() - y;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;
		}
	}
	
	private void shootClosest() 
	{
		List<Entity> entities = level.getEntities(this, 500);
		entities.add(level.getClientPlayer());
		
		double min = 0;
		Entity closest = null;
		for(int i = 0; i < entities.size(); i++)
		{
			Entity e = entities.get(i);
			double distance = Vector2i.getDistance(new Vector2i((int) x, (int) y), new Vector2i((int) e.getX(), (int) e.getY()));
			if(i == 0 || distance < min)
			{ 
				min = distance; 
				closest = e;
			}
		}
		
		if(closest != null)
		{
			double dx = closest.getX() - x;
			double dy = closest.getY() - y;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
		}
	}

	public void render(Screen screen)
	{
		sprite = animSprite.getSprite();
		Debug.drawRect(screen, 50, 50, 32, 32, false);
		screen.renderMob((int) x - 16, (int) y - 16, this);
	}

}
