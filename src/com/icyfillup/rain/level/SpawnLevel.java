package com.icyfillup.rain.level;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.icyfillup.rain.level.tile.Tile;

public class SpawnLevel extends Level
{
	
	public SpawnLevel(String path)
	{
		super(path);
	}
	
	protected void loadLevel(String path)
	{
		try 
		{
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = this.width = image.getWidth();
			int h = this.height = image.getHeight();
			
			tiles = new int[w * h];
			image.getRGB(0,  0, w, h, tiles, 0, w);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception! Could not load level file.");
		}
	}

	protected void generateLevel()
	{
		
	}
}
