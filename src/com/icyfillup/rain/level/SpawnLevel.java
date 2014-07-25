package com.icyfillup.rain.level;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.icyfillup.rain.entity.mob.Dummy;
import com.icyfillup.rain.entity.mob.Shooter;

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
		for(int i = 0; i < 2; i++)
		{
//			add(new Dummy(20, 50));
		}
//		add(new Chaser(20, 50));
//		add(new Star(18, 35));
		add(new Shooter(20, 55));
		add(new Dummy(25, 50));
		add(new Shooter(23, 60));

	}

	protected void generateLevel()
	{
		
	}
}
