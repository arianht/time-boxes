package com.CorGaming.Game.screens;

import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;

import com.CorGaming.PuzzleGame.Assets;
import com.CorGaming.framework.Game;
import com.CorGaming.framework.Graphics;
import com.CorGaming.framework.Graphics.PixmapFormat;
import com.CorGaming.framework.Screen;

public class LoadingScreen extends Screen
{
	public LoadingScreen(Game game)
	{
		super(game);		
	}

	@Override
	public void update(float deltaTime)
	{
		Graphics g = game.getGraphics();
		
		//Load images
        Assets.board = g.newPixmap("board-bg.png", PixmapFormat.ARGB8888);
        Assets.gameOver = g.newPixmap("game_over_screen.png", PixmapFormat.ARGB8888);
        Assets.menu = g.newPixmap("menu.png", PixmapFormat.ARGB8888);
        Assets.options = g.newPixmap("options.png", PixmapFormat.ARGB8888);
        Assets.scores = g.newPixmap("high_scores.png", PixmapFormat.ARGB8888);
        Assets.tickMark = g.newPixmap("tickmark.png", PixmapFormat.ARGB8888);
        Assets.boxCover = g.newPixmap("box-cover.png", PixmapFormat.ARGB8888);
        Assets.playButton = g.newPixmap("play-button.png", PixmapFormat.ARGB8888);
        Assets.highScoreImage = g.newPixmap("newHighScore_screen.png", PixmapFormat.ARGB8888);
        Assets.tutorial1Image = g.newPixmap("tutorial-1.png", PixmapFormat.ARGB8888);
        Assets.tutorial2Image = g.newPixmap("tutorial-2.png", PixmapFormat.ARGB8888);
        
        //Load sounds
        Assets.collisionSound = game.getAudio().newSound("collision-sound.ogg");
        Assets.bgMusic = game.getAudio().newMusic("bg-music.ogg");
        Assets.beep = game.getAudio().newSound("beep.ogg");
        
        //Load high scores
        try {
			InputStream file = game.getFileIO().readFile("TimeBoxes" + File.separator + "highScores.dat");
			ObjectInputStream buffer = new ObjectInputStream(file);
			
			Assets.highScores = (int[]) buffer.readObject();
			
			buffer.close();
			
		} catch (Exception e) {
			Assets.highScores = new int[3];
			Assets.highScores[0] = 0;
			Assets.highScores[1] = 0;
			Assets.highScores[2] = 0;
		}
        
		game.setScreen(new MenuScreen(game));
	}

	@Override
	public void present(float deltaTime)
	{

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
