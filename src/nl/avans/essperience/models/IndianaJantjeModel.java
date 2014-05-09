package nl.avans.essperience.models;

public class IndianaJantjeModel extends GameModel
{
	private int _amountOfBalls;
	private final int _STARTTIME = 10;
	
	public IndianaJantjeModel(int difficulty) {
		this._maxTime = _STARTTIME - (difficulty);
		this._speed = difficulty;
		_amountOfBalls = 3;
	}
	
	public void run() {
		while(_amountOfBalls > 0) {
			int direction = (int) (Math.random() * 3);
			animation(direction);
			_amountOfBalls--;
		}
	}
	
	private void animation(int dir) {
		switch (dir) {
		case 0:
			
			break;
		case 1:
			
			break;
		case 2:
			
			break;
		default: 
			System.out.println("Direction error");
			break;
		}
	}
}
