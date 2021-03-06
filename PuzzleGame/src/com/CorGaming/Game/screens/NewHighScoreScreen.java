package com.CorGaming.Game.screens;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

import android.graphics.Color;
import android.os.Environment;

import com.CorGaming.PuzzleGame.Assets;
import com.CorGaming.framework.Game;
import com.CorGaming.framework.Graphics;
import com.CorGaming.framework.Screen;
import com.CorGaming.framework.Input.TouchEvent;
import com.CorGaming.gui.Button;

public class NewHighScoreScreen extends Screen
{
	private final int TIME = 1;
	private float timer;
	private Button menu;
	
	public NewHighScoreScreen(Game game)
	{
		super(game);
		
		menu = new Button(162, 212, 150, 49);
		
		saveScores();
	}

	@Override
	public void update(float deltaTime)
	{
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
    	
    	for (int i = 0; i < touchEvents.size(); i++)
    	{
    		if (touchEvents.get(i).type == TouchEvent.TOUCH_DOWN ||
        		touchEvents.get(i).type == TouchEvent.TOUCH_DRAGGED)
    		{
    			if (timer > TIME)
    			{
    				if (menu.isPressed(touchEvents.get(i).x, touchEvents.get(i).y))
    					game.setScreen(new MenuScreen(game));
    				else
    					game.setScreen(new GameScreen(game));
    			}
    		}
    		
    		else if (touchEvents.get(i).type == TouchEvent.TOUCH_UP)
    		{
    			
    		}
    	}
    	
    	timer += deltaTime;
	}

	@Override
	public void present(float deltaTime)
	{
		Graphics g = game.getGraphics();
		g.clear(Color.BLACK);
		
		g.drawPixmap(Assets.highScoreImage, 0, 0);
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
	
	private void saveScores()
	{
		try
		{
			File dir = new File(Environment.getExternalStorageDirectory()
	                .getAbsolutePath() + File.separator + "TimeBoxes");
			dir.mkdir();
			
			OutputStream file = game.getFileIO().writeFile("TimeBoxes" + File.separator + "highScores.dat");
			ObjectOutputStream buffer = new ObjectOutputStream(file);
			
			buffer.writeObject(Assets.highScores);
			
			buffer.close();
		}
		catch(Exception e)
		{
			
		}
	}
}
