package com.icyfillup.rain.level.tile;

import com.icyfillup.rain.graphics.Screen;
import com.icyfillup.rain.graphics.Sprite;

public class FlowerTile extends Tile
{
	public FlowerTile(Sprite sprite)
	{
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) 
	{
		screen.renderTile(x << 4, y << 4, this);
	}
}
