package com.CorGaming.objects;

import java.util.Random;
import java.util.Vector;

import android.graphics.Color;
import android.util.Log;

import com.CorGaming.PuzzleGame.Assets;
import com.CorGaming.framework.Graphics;

public class Board 
{
	private Vector<AutoBox> boxes;
	private SelectedBox sBox;
	private int round;
	private int desX;
	private int desY;
	private int score;
	
	public static int maxBoxNum = 6;
	
	public Board()
	{
		boxes = new Vector<AutoBox>();
		round = 0;
		newBox();
		
		score = 0;
	}
	
	public void update(float time)
	{
		for (AutoBox b : boxes)
			b.update(time);
	}
	
	public void draw(Graphics g)
	{
		g.drawPixmap(Assets.board, 0, 0);
		
		for (AutoBox b : boxes)
			b.draw(g);
		
		sBox.draw(g);
		
		g.drawRect(desX * Box.WIDTH, desY * Box.HEIGHT, Box.WIDTH, Box.HEIGHT, sBox.color, false);
	}
	
	public void touched(int x, int y, float time)
	{
		x = pixelToTile(x);
		y = pixelToTile(y);
		if ((x == sBox.x - 1 && y == sBox.y) 		|| //left
			(x == sBox.x + 1 && y == sBox.y) 		|| //right
			(y == sBox.y - 1 && x == sBox.x) 		|| //up
			(y == sBox.y + 1 && x == sBox.x)    	|| //down
			(x == sBox.x - 1 && y == sBox.y - 1) 	|| //left up
			(x == sBox.x + 1 && y == sBox.y - 1) 	|| //right up
			(y == sBox.y + 1 && x == sBox.x - 1)	|| //left down
			(y == sBox.y + 1 && x == sBox.x + 1))	   //right down
		{
			sBox.moveTo(x, y, time);
		}
	}
	
	public boolean checkCollision()
	{
		for (AutoBox b : boxes)
		{
			if (b.x == sBox.x && b.y == sBox.y)
				return true;
		}
		
		return false;
	}
	
	public void newRound(float time)
	{
		if (time < 2)
			score += 100;
		else if (time < 5)
			score += 50;
		else if (time < 7)
			score += 25;
		else
			score += 10;
		
		boxes.add(new AutoBox(sBox.number, sBox.color, sBox.path));
		
		while (boxes.size() > maxBoxes() - 1)
			boxes.remove(0);
		
		round++;
		
		if ((round + 1) % 10 == 0)
			score += round * 5;
			
		for (AutoBox b : boxes)
			b.reset();
			
		newBox();
	}
	
	private void newBox()
	{
		Random rand = new Random();
		
		boolean collide = true;
		int x = 0;
		int y = 0;
		
		// Create new position of box
		while (collide)
		{
			collide = false;
			
			x = rand.nextInt(10);
			y = rand.nextInt(10);
			
			for (AutoBox b : boxes)
			{
				if (x == b.getStartX() && y == b.getStartY())
					collide = true; 
			}
		}
		
		int color = Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		
		while (color < -5555555)
		{
			color = Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		}
		
		sBox = new SelectedBox(x, y, (round + 1), color);
		collide = true;
		
		// Create new destination of box
		while (collide)
		{
			collide = false;
			
			desX = rand.nextInt(10);
			desY = rand.nextInt(10);
			
			for (AutoBox b : boxes)
			{
				if (desX == b.getDesX() && desY == b.getDesY())
					collide = true;
			}
			
			if (reachedDest())
				collide = true;
		}
	}
	
	public boolean reachedDest()
	{
		if (sBox.x == desX && sBox.y == desY)
			return true;
		else
			return false;
	}
	
	private int maxBoxes()
	{
		int max = (round - 1) / 10 + 3;
		Log.d("Max", "" + max);
		if (max > maxBoxNum)
			return maxBoxNum;
		else
			return max;
	}
	
	private int pixelToTile(int num)
	{
		return num / Box.WIDTH;
	}
	
	public int getRound()
	{
		return round;
	}
	
	public int getScore()
	{
		return score;
	}
}
