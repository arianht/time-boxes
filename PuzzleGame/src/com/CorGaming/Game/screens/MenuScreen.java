package com.CorGaming.Game.screens;

import java.util.List;

import android.graphics.Color;

import com.CorGaming.PuzzleGame.Assets;
import com.CorGaming.framework.Game;
import com.CorGaming.framework.Graphics;
import com.CorGaming.framework.Screen;
import com.CorGaming.framework.Input.TouchEvent;
import com.CorGaming.gui.Button;

public class MenuScreen extends Screen
{
	private Button play;
	private Button options;
	private Button tutorial;
	private Button scores;
	
	public MenuScreen(Game game)
	{
		super(game);
		
		play = new Button(60, 141, 148, 48);
		options = new Button(272, 140, 148, 48);
		tutorial = new Button(60, 220, 148, 48);
		scores = new Button(272, 220, 148, 48);
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
    			
    			if (play.isPressed(x, y))
    				game.setScreen(new GameScreen(game));
    			
    			else if (options.isPressed(x, y))
    				game.setScreen(new OptionsScreen(game));
    			
    			else if (tutorial.isPressed(x, y))
    				game.setScreen(new Tutorial1Screen(game));
    			
    			else if (scores.isPressed(x, y))
    				game.setScreen(new ScoreScreen(game));
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
		
		g.drawPixmap(Assets.menu, 0, 0);
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
