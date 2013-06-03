package com.CorGaming.objects;

import android.graphics.Color;

import com.CorGaming.PuzzleGame.Assets;
import com.CorGaming.framework.Graphics;


public class Box {

	public static final int WIDTH = 32, HEIGHT= 32;
	protected int x, y, number;
	protected int color;
	
	public Box(int x, int y, int number, int color)
	{
		this.x = x;
		this.y = y;
		this.number = number;
		this.color = color;
	}
	
	public void draw(Graphics g)
	{
		g.drawRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT, color, true);
		
		g.drawPixmap(Assets.boxCover, x * WIDTH, y * HEIGHT);
		
		if (number < 10)
			g.drawText(Integer.toString(number), x * WIDTH + WIDTH / 2 - 5, y * HEIGHT + HEIGHT / 2 + 6, Color.BLACK, 16);
		else if (number < 100)
			g.drawText(Integer.toString(number), x * WIDTH + WIDTH / 2 - 9, y * HEIGHT + HEIGHT / 2 + 6, Color.BLACK, 16);
		else
			g.drawText(Integer.toString(number), x * WIDTH + WIDTH / 2 - 11, y * HEIGHT + HEIGHT / 2 + 6, Color.BLACK, 12);
	}
}
