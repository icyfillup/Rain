package com.icyfillup.rain.level.tile.spawn_level;

import com.icyfillup.rain.graphics.Screen;
import com.icyfillup.rain.graphics.Sprite;
import com.icyfillup.rain.level.tile.Tile;

public class SpawnHedgeTile extends Tile 
{

	public SpawnHedgeTile(Sprite sprite)
	{
		super(sprite);// TODO Auto-generated constructor stub
	}
	
	public void render(int x, int y, Screen screen) 
	{
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid()
	{
		return true;
	}
	
	public boolean breakable()
	{
		return true;
	}

}
