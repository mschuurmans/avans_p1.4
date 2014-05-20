package nl.avans.essperience.models;

import nl.avans.essperience.main.Main;


public class RedButtonModel extends GameModel
{
	private int _colorChange;
	private int _difficulty;
	
	public RedButtonModel()
	{
		_maxTime = 3000;
		_difficulty = Main.GAME.getDifficulty();
		_colorChange = 0;
	}
	
	@Override
	public void update()
	{
		if (getTimeRemaining() == 0)
		{
			if(_modelToControllerListener != null)
				_modelToControllerListener.timesUpEvent();
		}
		_colorChange += _difficulty;
	}
	
	public boolean getColorChange() {
		if (_colorChange > 15) {
			_colorChange -= 15;
			return true;
		} else {
			return false;
		}
	}
}
