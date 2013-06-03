package com.CorGaming.Game.screens;

import java.util.List;

import android.graphics.Color;

import com.CorGaming.PuzzleGame.Assets;
import com.CorGaming.framework.Game;
import com.CorGaming.framework.Graphics;
import com.CorGaming.framework.Screen;
import com.CorGaming.framework.Input.TouchEvent;
import com.CorGaming.gui.Button;
import com.CorGaming.objects.Board;

public class OptionsScreen extends Screen
{
	private Button easy;
	private Button medium;
	private Button hard;
	private Button menu;
	
	private final int EASY = 4;
	private final int MEDIUM = 6;
	private final int HARD = 9;
	
	public OptionsScreen(Game game)
	{
		super(game);		
		
		easy = new Button(165, 71, 149, 52);
		medium = new Button(167, 130, 151, 45);
		hard = new Button(166, 187, 148, 45);
		menu = new Button(165, 250, 149, 48);
	}

	@Override
	public void update(float deltaTime)
	{
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
    	
    	for (int i = 0; i < touchEvents.size(); i++)
    	{
    		if (touchEvents.get(i).type == TouchEvent.TOUCH_DOWN)
    		{
    			int x = touchEvents.get(i).x;
    			int y = touchEvents.get(i).y;
    			
    			if (easy.isPressed(x, y))
    				Board.maxBoxNum = EASY;
    			else if (medium.isPressed(x, y))
    				Board.maxBoxNum = MEDIUM;
    			else if (hard.isPressed(x, y))
    				Board.maxBoxNum = HARD;
    			else if (menu.isPressed(x, y))
    				game.setScreen(new MenuScreen(game));
    		}
    		
    		else if (touchEvents.get(i).type == TouchEvent.TOUCH_UP)
    		{
    			
    		}
    	}
	}

	@Override
	public void present(float deltaTime)
	{
		Graphics g = game.getGraphics();
		g.clear(Color.BLACK);
		
		g.drawPixmap(Assets.options, 0 , 0);
		
		if (Board.maxBoxNum == EASY)
			g.drawPixmap(Assets.tickMark, 110, 71);
		else if (Board.maxBoxNum == MEDIUM)
			g.drawPixmap(Assets.tickMark, 110, 130);
		else
			g.drawPixmap(Assets.tickMark, 110, 187);
	}

	@Override
	public void pause()
	{

	}

	@Override
	public void resume()
	{

	}

	@Override
	public void dispose()
	{

	}
}
