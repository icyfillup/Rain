package com.icyfillup.rain.entity.mob;

import com.icyfillup.rain.graphics.AnimatedSprite;
import com.icyfillup.rain.graphics.Screen;
import com.icyfillup.rain.graphics.Sprite;
import com.icyfillup.rain.graphics.SpriteSheet;

public class Dummy extends Mob
{
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	
	private Direction dir;
	
	public Dummy(int x, int y)
	{
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}
	
	public void update()
	{
		int xa = 0;
		int ya = 0;
		
//		ya--;
//		xa--;
		
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
			move(xa, ya);
			walking = true;
		}
		else { walking = false; }
		
	}
	
	public void render(Screen screen)
	{
		sprite = animSprite.getSprite();
		
		screen.renderMob(x, y, sprite, 0);
	}
	
	
	
	
	
	
	
}
