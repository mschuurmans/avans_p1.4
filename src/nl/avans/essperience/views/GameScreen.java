package nl.avans.essperience.views;

import nl.avans.essperience.models.GameModel;

public abstract class GameScreen 
{
	private GameModel _gameModel;
	
	public GameScreen(GameModel model)
	{
		this._gameModel = model;
	}
	
	public abstract void render();
	public abstract void update();
}
