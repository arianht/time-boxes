package com.CorGaming.Game;

import com.CorGaming.Game.screens.GameScreen;
import com.CorGaming.framework.Screen;
import com.CorGaming.framework.impl.AndroidGame;

public class Game extends AndroidGame
{
	private static final long serialVersionUID = 9065937296003495157L;

	@Override
	public Screen getStartScreen()
	{
		return new GameScreen(this);
	}
}
