package com.CorGaming.Game.screens;

import java.util.List;

import android.graphics.Color;

import com.CorGaming.PuzzleGame.Assets;
import com.CorGaming.framework.Game;
import com.CorGaming.framework.Graphics;
import com.CorGaming.framework.Screen;
import com.CorGaming.framework.Input.TouchEvent;
import com.CorGaming.gui.Button;

public class Tutorial1Screen extends Screen
{
	private Button menu;
	private Button next;
	
	public Tutorial1Screen(Game game) 
	{
		super(game);

		menu = new Button(85, 252, 148, 48);
		next = new Button(254, 252, 148, 48);
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
    			
    			if (menu.isPressed(x, y))
    				game.setScreen(new MenuScreen(game));
    			
    			else if (next.isPressed(x, y))
    				game.setScreen(new Tutorial2Screen(game));
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
		
		g.drawPixmap(Assets.tutorial1Image, 0, 0);
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
