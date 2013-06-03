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

public class GameScreen extends Screen 
{	
	private final int ROUND_TIME = 10;
	private float time;
	private Board board;
	private Button pauseButton;
	private Button music;
	private Button menu;
	private boolean pause;
	
	private boolean exit;
	
	public GameScreen(Game game) 
    {
        super(game);
        
        time = 0;
        
        board = new Board();
        
        pauseButton = new Button(379, 188, 44, 44);
        music = new Button(379, 249, 44, 44);
        menu = new Button(367, 295, 69, 23);
    
        Assets.bgMusic.setLooping(true);
        
        if (Assets.music)
        	Assets.bgMusic.play();
        
        pause = false;
        
        //This is for when the user presses the "Menu" button
        exit = false;
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
    			if (touchEvents.get(i).type == TouchEvent.TOUCH_DOWN)
    			{
    				if(menu.isPressed(touchEvents.get(i).x, touchEvents.get(i).y))
    					exit = true;
    					
    				if (pauseButton.isPressed(touchEvents.get(i).x, touchEvents.get(i).y))
    				{
    					pause = !pause;
    				}
    				
    				if (music.isPressed(touchEvents.get(i).x, touchEvents.get(i).y))
    				{
    					if(Assets.bgMusic.isPlaying())
    					{
    						Assets.bgMusic.pause();
    						Assets.music = false;
    					}
    					else
    					{
    						Assets.bgMusic.play();
    						Assets.music = true;
    					}
    				}
    			}
    			
    			if (touchEvents.get(i).x < 320 && !pause)
    				board.touched(touchEvents.get(i).x, touchEvents.get(i).y, time);
    		}
    		
    		else if (touchEvents.get(i).type == TouchEvent.TOUCH_UP)
    		{
    			
    		}
    	}
    	
    	
    	if(!pause)
    	{
	    	time += deltaTime;
	    	
	    	if (time > ROUND_TIME)
	    	{
	    		Assets.beep.play(1);
	    		gameOver(0);
	    	}
	    	
	    	board.update(time);
	    	
	    	if (board.checkCollision())
	    	{
	    		Assets.collisionSound.play(1);
	    		gameOver(0);
	    	}
	    	
	    	if (board.reachedDest())
	    	{
	    		board.newRound(time);
	    		time = 0;
	    	}
    	}
    	
    	//In case the user pressed the "Menu" button
    	if(exit)
    		gameOver(-1);
    }

    @Override
    public void present(float deltaTime) 
    {
    	Graphics g = game.getGraphics();
    	g.clear(Color.BLACK);
    	
    	board.draw(g);
    	
    	g.drawText("" + Math.round(ROUND_TIME - time), 395, 53, Color.rgb(200, 196, 196), 24);
    	g.drawText(Integer.toString(board.getRound() + 1), 395, 170, Color.rgb(200, 196, 196), 24);
    	
    	if (board.getScore() < 10)
    		g.drawText(Integer.toString(board.getScore()), 395, 110, Color.rgb(200, 196, 196), 24);
    	else if (board.getScore() < 100)
    		g.drawText(Integer.toString(board.getScore()), 388, 110, Color.rgb(200, 196, 196), 24);
    	else if (board.getScore() < 1000)
    		g.drawText(Integer.toString(board.getScore()), 381, 110, Color.rgb(200, 196, 196), 24);
    	else if (board.getScore() < 10000)
    		g.drawText(Integer.toString(board.getScore()), 384, 110, Color.rgb(200, 196, 196), 24);
    	
    	if (pause)
    		g.drawPixmap(Assets.playButton, 379, 188);
    }
    
    @Override
    public void pause() 
    {
    	Assets.bgMusic.stop();
    }

    @Override
    public void resume() 
    {
    	
    }

    @Override
    public void dispose() 
    {
    	Assets.bgMusic.stop();
    }
    
    //If indicator is -1 that means the user has chosen to exit the game and go back to the main menu
    private void gameOver(int indicator)
    {
    	if (indicator == -1)
    		game.setScreen(new MenuScreen(game));
    	
    	else
    	{
	    	if (board.getScore() > Assets.highScores[0])
	    	{
	    		Assets.highScores[2] = Assets.highScores[1];
	    		Assets.highScores[1] = Assets.highScores[0];
	    		Assets.highScores[0] = board.getScore();
	    		game.setScreen(new NewHighScoreScreen(game));
	    	}
	    	else if (board.getScore() > Assets.highScores[1])
	    	{
	    		Assets.highScores[2] = Assets.highScores[1];
	    		Assets.highScores[1] = board.getScore();
	    		game.setScreen(new NewHighScoreScreen(game));
	    	}
	    	else if (board.getScore() > Assets.highScores[2])
	    	{
	    		Assets.highScores[2] = board.getScore();
	    		game.setScreen(new NewHighScoreScreen(game));
	    	}
	    	else
	    		game.setScreen(new GameOverScreen(game));
    	}
    }
}
