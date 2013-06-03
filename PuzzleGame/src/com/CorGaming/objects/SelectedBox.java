package com.CorGaming.objects;

import java.util.Vector;

public class SelectedBox extends Box
{
	protected Vector<Move> path;
	
	public SelectedBox(int x, int y, int number, int color)
	{
		super(x, y, number, color);
		path = new Vector<Move>();
		path.add(new Move(x, y, 0));
	}
	
	public void moveTo(int x, int y, float time)
	{
		this.x = x;
		this.y = y;
		
		path.add(new Move(x, y, time)); //Adding the move to the Vector
	}
	
}
