package nl.avans.essperience.models;

import nl.avans.essperience.main.Main;

public class IndianaJantjeModel extends GameModel
{
	private int _amountOfBalls;
	private final int _STARTTIME = 10;
	private int _difficulty;
	
	public IndianaJantjeModel() {
		_difficulty = Main.GAME.getDifficulty();
		this._maxTime = _STARTTIME - (_difficulty);
		this._speed = _difficulty;
		_amountOfBalls = 3;
	}
	
	public int getDifficulty() {
		return _difficulty;
	}
}
