package com.CorGaming.Game.screens;

import java.util.List;

import android.graphics.Color;

import com.CorGaming.framework.Game;
import com.CorGaming.framework.Graphics;
import com.CorGaming.framework.Screen;
import com.CorGaming.framework.Input.TouchEvent;

public class GameScreen extends Screen 
{	
	int x = 20;
	int y = 20;
	
	public GameScreen(Game game) 
    {
        super(game);
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
    			x = touchEvents.get(i).x;
    			y = touchEvents.get(i).y;
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
    
    	g.drawCircle(x, y, 10, Color.RED, true);
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
