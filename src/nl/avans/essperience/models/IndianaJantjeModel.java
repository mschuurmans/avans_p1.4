package nl.avans.essperience.models;

import nl.avans.essperience.main.Main;

public class IndianaJantjeModel extends GameModel
{
	private int _amountOfBalls;
	private final int _STARTTIME = 10;
	private int _difficulty;
	private int _position;
	
	public IndianaJantjeModel() {
		_position = 0;
		_difficulty = Main.GAME.getDifficulty();
		this._maxTime = _STARTTIME - (_difficulty);
		this._speed = _difficulty/3;
		_amountOfBalls = _speed + 1;
	}
	
	public int getDifficulty() {
		return _difficulty;
	}
	
	public int getAmountOfBalls() {
		return _amountOfBalls;
	}
	public int getCurrentPosition() {
		return _position;
	}
	
	public void setCurrentPosition(int pos) {
		_position = pos;
	}
}
