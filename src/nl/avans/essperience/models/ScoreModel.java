package nl.avans.essperience.models;

public class ScoreModel extends GameModel
{
	private int _score;
	private int _randomScore;
	
	public int getScore()
	{
		return _score;
	}
	
	public int getRandomScore()
	{
		return _randomScore;
	}
	
	public void update()
	{
		_score+= 100;
		_randomScore = (int) ((Math.random() * 60) - 30);
	}
	
	public void resetScore()
	{
		_score = 0;
	}
	
	public void scoreCount()
	{
		if (_randomScore > 0)
		{
			_score++;
			_randomScore--;
		}
		else if (_randomScore < 0)
		{
			_score--;
			_randomScore++;
		}
	}
}
