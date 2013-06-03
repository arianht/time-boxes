package com.CorGaming.objects;

import java.util.Vector;

public class AutoBox extends Box
{
	private Vector<Move> path;
	private int pos;
	
	public AutoBox(int number, int color, Vector<Move> path)
	{
		super(path.get(0).x, path.get(0).y, number, color);
		this.path = path;
		pos = 1;
	}
	
	public void update(float time)
	{
		if(pos < path.size() && time >= path.get(pos).time)
		{
			x = path.get(pos).x;
			y = path.get(pos).y;
			pos++;
		}
	}
	
	public int getStartX()
	{
		return path.get(0).x;
	}
	
	public int getStartY()
	{
		return path.get(0).y;
	}
	
	public int getDesX()
	{
		return path.get(path.size() - 1).x;
	}
	
	public int getDesY()
	{
		return path.get(path.size() - 1).y;
	}
	
	public void reset()
	{
		x = path.get(0).x;
		y = path.get(0).y;
		pos = 1;
	}
}
