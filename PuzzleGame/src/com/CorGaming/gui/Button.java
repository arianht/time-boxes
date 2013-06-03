package com.CorGaming.gui;

public class Button
{
	private int x;
	private int y;
	private int w;
	private int h;
	
	public Button(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public boolean isPressed(int tX, int tY)
	{
		return !(tX < x || tX > x + w || tY < y || tY > y + h);
	}
}
