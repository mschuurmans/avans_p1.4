package nl.avans.essperience.views;

import javax.swing.JPanel;

import nl.avans.essperience.models.GameModel;

public abstract class GameScreen extends JPanel
{
	private static final long serialVersionUID = 517082358948978120L;
	
	private GameModel _gameModel;
	
	public GameScreen(GameModel model)
	{
		this._gameModel = model;
	}
	
	public abstract void render();
	public abstract void update();
}
