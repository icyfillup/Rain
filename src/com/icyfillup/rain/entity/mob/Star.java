package com.icyfillup.rain.entity.mob;

import java.util.List;

import com.icyfillup.rain.graphics.AnimatedSprite;
import com.icyfillup.rain.graphics.Screen;
import com.icyfillup.rain.graphics.Sprite;
import com.icyfillup.rain.graphics.SpriteSheet;
import com.icyfillup.rain.level.Node;
import com.icyfillup.rain.util.Vector2i;

public class Star extends Mob
{
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	
	private double xa = 0;
	private double ya = 0;
	private List<Node> path = null;
	private int time = 0;
	
	public Star(int x, int y)
	{
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}
	
	private void move()
	{
		xa = 0;
		ya = 0;
		
//		List<Player> players = level.getPlayers(this, 50);
//		if(players.size() > 0)
//		{
//			Player player = players.get(0);
//			if(x < player.getX()) { xa =+ 1; }
//			if(x > player.getX()) { xa =- 1; }
//			if(y < player.getY()) { ya =+ 1; }
//			if(y > player.getY()) { ya =- 1; }			
//		}
		
		int px = (int) level.getPlayerAt(0).getX();
		int py = (int) level.getPlayerAt(0).getY();
		
		Vector2i start = new Vector2i((int) getX() >> 4, (int) getY() >> 4);
		Vector2i destination = new Vector2i(px >> 4, py >> 4);
		
		if(time % 20 == 0 ) { path = level.findPath(start, destination);}
		
		if(path != null)
		{
			if(path.size() > 0)
			{
				Vector2i vec = path.get(path.size() - 1).tile; 
				if(x < (vec.getX() << 4)) { xa++; }
				if(x > (vec.getX() << 4)) { xa--; }
				if(y < (vec.getY() << 4)) { ya++; }
				if(y > (vec.getY() << 4)) { ya--; }
				
//				System.out.println("Star: " + x + ", " + y);
//				System.out.println((vec.getX() << 4) + ", " + (vec.getY() << 4));
//				System.out.println(x < (vec.getX() << 4));
//				System.out.println(x > (vec.getX() << 4));
//				System.out.println(y < (vec.getY() << 4));
//				System.out.println(y > (vec.getY() << 4));
//				System.out.println("xa: " + xa + " ya: " + ya);
			}
		}
		
		if(xa != 0 || ya != 0)
		{
			move(xa, ya);
			walking = true;
		}
		else { walking = false; }
	}
	
	public void update() 
	{
		time++;
		move();
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
		
		
	}

	public void render(Screen screen) 
	{
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), this);
	}
	
}
