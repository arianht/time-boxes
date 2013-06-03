package com.CorGaming.Game.screens;

import java.util.List;

import android.graphics.Color;

import com.CorGaming.PuzzleGame.Assets;
import com.CorGaming.framework.Game;
import com.CorGaming.framework.Graphics;
import com.CorGaming.framework.Screen;
import com.CorGaming.framework.Input.TouchEvent;
import com.CorGaming.gui.Button;

public class ScoreScreen extends Screen
{
	private Button menu;
	
	public ScoreScreen(Game game)
	{
		super(game);
		
		menu = new Button(171, 251, 149, 47);
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
		
		g.drawPixmap(Assets.scores, 0, 0);
		
		g.drawText("1.................................." + Assets.highScores[0], 100, 130, Color.rgb(200, 196, 196), 24);
		g.drawText("2.................................." + Assets.highScores[1], 100, 170, Color.rgb(200, 196, 196), 24);
		g.drawText("3.................................." + Assets.highScores[2], 100, 210, Color.rgb(200, 196, 196), 24);
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
