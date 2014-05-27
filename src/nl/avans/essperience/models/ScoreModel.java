package nl.avans.essperience.models;

public class ScoreModel extends GameModel
{
	private int _score;
	
	public int getScore()
	{
		return _score;
	}
	
	public void update()
	{
		_score+= 100;
		_score+= (Math.random() * 100) - 50;
	}
}
